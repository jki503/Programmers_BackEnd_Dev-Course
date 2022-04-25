package org.prgms.kdt.kdtspringorder.customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.*;

@Repository
@Primary
public class CustomerNamedJdbcRepository implements CustomerRepository {

    public static final Logger logger = LoggerFactory.getLogger(CustomerNamedJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

//    private final PlatformTransactionManager transactionManager;


    private static final RowMapper<Customer> customerRowMapper = (resultSet, i) -> {
        var customerName = resultSet.getString("name");
        var email = resultSet.getString("email");
        var customerId = toUUID(resultSet.getBytes("customer_id"));
        var lastLoginAt = resultSet.getTimestamp("last_login_at") != null ?
                resultSet.getTimestamp("last_login_at").toLocalDateTime() : null;
        var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();
        return new Customer(customerId, customerName, email, lastLoginAt, createdAt);
    };

    public CustomerNamedJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer insert(Customer customer) {
        var update = jdbcTemplate.update("INSERT INTO customers(customer_id,name,email,create_at) VALUES(UUID_TO_BIN(:customerId),:name,:email,:createdAt)",
                toParamMap(customer));

        if (update != 1)
            throw new RuntimeException("Nothing was inserted");
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        var update = jdbcTemplate.update("UPDATE customers SET name = :name , email = :email, last_login_at = :lastLoginAt WHERE customer_id = UUID_TO_BIN(:customerId)",
                toParamMap(customer));

        if (update != 1)
            throw new RuntimeException("Nothing was inserted");
        return customer;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from customers", Map.of(), Integer.class);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("select * from customers", customerRowMapper);
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {

        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from  customers WHERE customer_id = UUID_TO_BIN(:customerId)",
                    Collections.singletonMap("customerId", customerId.toString().getBytes()),
                    customerRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error("Got empty result", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from  customers WHERE name = :name",
                    Collections.singletonMap("name", name),
                    customerRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error("Got empty result", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select * from  customers WHERE email = :email",
                    Collections.singletonMap("email", email),
                    customerRowMapper));
        } catch (EmptyResultDataAccessException e) {
            logger.error("Got empty result", e);
            return Optional.empty();
        }
    }

    /*
        public void testTransaction(Customer customer){
            var transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try{
                jdbcTemplate.update("UPDATE customers SET name = :name WHERE customer_id = UUID_TO_BIN(:customerId)",toParamMap(customer));
                jdbcTemplate.update("UPDATE customers SET email = :email WHERE customer_id = UUID_TO_BIN(:customerId)",toParamMap(customer));
                transactionManager.commit(transaction);
            }catch (DataAccessException e){
                logger.error("Got error", e);
                transactionManager.rollback(transaction);
            }
        }
    */
    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM customers", Collections.emptyMap());
    }
/*
    public void testTransaction(Customer customer){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcTemplate.update("UPDATE customers SET name = :name WHERE customer_id = UUID_TO_BIN(:customerId)",toParamMap(customer));
                jdbcTemplate.update("UPDATE customers SET email = :email WHERE customer_id = UUID_TO_BIN(:customerId)",toParamMap(customer));
            }
        });
    }
*/

    /*
        private void mapToCustomer(List<Customer> allCustomer, ResultSet resultSet) throws SQLException {
            var customerName = resultSet.getString("name");
            var email = resultSet.getString("email");
            var customerId = toUUID(resultSet.getBytes("customer_id"));
            var lastLoginAt = resultSet.getTimestamp("last_login_at") != null ?
                    resultSet.getTimestamp("last_login_at").toLocalDateTime() : null;
            var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();

    //                logger.info("customer id -> {} customer name -> {}, createdAt -> {}", customerId, customerName, createdAt);
            allCustomer.add(new Customer(customerId, customerName, email, lastLoginAt, createdAt));
        }
    */
    private static UUID toUUID(byte[] bytes) {
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

    private Map<String, Object> toParamMap(Customer customer) {
        return new HashMap<>() {{
            put("customerId", customer.getCustomerId().toString().getBytes());
            put("name", customer.getName());
            put("email", customer.getEmail());
            put("lastLoginAt", customer.getLastLoginAt() != null ? Timestamp.valueOf(customer.getLastLoginAt()) : null);
            put("createdAt", Timestamp.valueOf(customer.getCreatedAt()));
        }};
    }
}
