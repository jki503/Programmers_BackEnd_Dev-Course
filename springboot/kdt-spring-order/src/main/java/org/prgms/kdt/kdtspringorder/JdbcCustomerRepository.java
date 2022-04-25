package org.prgms.kdt.kdtspringorder;

import org.prgms.kdt.kdtspringorder.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcCustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcCustomerRepository.class);
    private static final String SELECT_BY_NAME_SQL = "select * from customers where name = '?'";
    private static final String SELECT_ALL_SQL = "select * from customers";
    private static final String INSERT_SQL = "INSERT INTO customers(customer_id,name,email) VALUES(UUID_TO_BIN(?),?,?)";
    private static final String UPDATE_BY_ID_SQL = "UPDATE customers SET name = ? WHERE customer_id = UUID_TO_BIN(?)";
    private static final String DELETE_ALL_SQL = "DELETE FROM customers";



    /* SQL inejction 취약
    public List<String> findNames(String name) {
        List<String> names = new ArrayList<>();

        var SELECT_SQL = MessageFormat.format( "select * from customers where name = ''{0}''", name);
        logger.info(SELECT_SQL);
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.createStatement();
                var resultSet = statement.executeQuery(SELECT_SQL);
        ) {
            while (resultSet.next()) {
                var customerName = resultSet.getString("name");
                var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();

                logger.info("customer id -> {} customer name -> {}, createdAt -> {}", customerId, name, createdAt);
                names.add(customerName);
            }
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }

        return names;
    }*/

    public List<String> findNames(String name) {
        List<String> names = new ArrayList<>();

        logger.info(SELECT_BY_NAME_SQL);
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(SELECT_BY_NAME_SQL);
        ) {
            statement.setString(1, name);
            try (var resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    var customerName = resultSet.getString("name");
                    var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                    var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();

                    logger.info("customer id -> {} customer name -> {}, createdAt -> {}", customerId, customerName, createdAt);
                    names.add(customerName);
                }
            }
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }

        return names;
    }

    public List<UUID> findAllIds() {
        List<UUID> uuids = new ArrayList<>();

//        logger.info(SELECT_ALL_SQL);
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(SELECT_ALL_SQL);
                var resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                var customerName = resultSet.getString("name");
                var customerId = toUUID(resultSet.getBytes("customer_id"));
                var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();

//                logger.info("customer id -> {} customer name -> {}, createdAt -> {}", customerId, customerName, createdAt);
                uuids.add(customerId);
            }
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }

        return uuids;
    }

    public List<String> findAllName() {
        List<String> names = new ArrayList<>();

//        logger.info(SELECT_ALL_SQL);
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(SELECT_ALL_SQL);
                var resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                var customerName = resultSet.getString("name");
                var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                var createdAt = resultSet.getTimestamp("create_at").toLocalDateTime();

//                logger.info("customer id -> {} customer name -> {}, createdAt -> {}", customerId, customerName, createdAt);
                names.add(customerName);
            }
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }

        return names;
    }

    public int insertCustomer(UUID customerId, String name, String email) {
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(INSERT_SQL);
        ) {
            statement.setBytes(1, customerId.toString().getBytes());
            statement.setString(2, name);
            statement.setString(3, email);
            return statement.executeUpdate();

        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }
        return 0;
    }

    public int deleteAllCustomers() {
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(DELETE_ALL_SQL);
        ) {
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }
        return 0;
    }

    public int updateCustomerName(UUID customerId, String name) {
        try (
                var connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
                var statement = connection.prepareStatement(UPDATE_BY_ID_SQL);
        ) {
            statement.setString(1, name);
            statement.setBytes(2, customerId.toString().getBytes());
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        }
        return 0;
    }

    private static UUID toUUID(byte[] bytes){
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(),byteBuffer.getLong());
    }

    public void transactionTest(Customer customer){
        String updateNameSql = "UPDATE customers SET name = ? WHERE customer_id = UUID_TO_BIN(?)";
        String updateEmailSql = "UPDATE customers SET email = ? WHERE customer_id = UUID_TO_BIN(?)";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt", "root", "jungki503\\");
            connection.setAutoCommit(false);
            try (
                    var updateNameStatement = connection.prepareStatement(updateNameSql);
                    var updateEmailStatement = connection.prepareStatement(updateEmailSql);
            ) {
                updateNameStatement.setString(1, customer.getName());
                updateNameStatement.setBytes(2, customer.getCustomerId().toString().getBytes());
                updateNameStatement.executeUpdate();

                updateEmailStatement.setString(1,customer.getEmail());
                updateEmailStatement.setBytes(2, customer.getCustomerId().toString().getBytes());
                updateEmailStatement.executeUpdate();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                    connection.close();
                }catch (SQLException throwable){
                    logger.error("Got error while closing connection",throwable);
                }
            }
            logger.error("Got error while closing connection",e);
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException {

        /*
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/order_mgmt","root","jungki503\\");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from customers");
            while(resultSet.next()){
                var name = resultSet.getString("name");
                var customerId = UUID.nameUUIDFromBytes(resultSet.getBytes("customer_id"));
                logger.info("customer id -> {} customer name -> {}", customerId, name);}
        } catch (SQLException e) {
            logger.error("Got Error while closing connection", e);
        } finally {
            try{
                if(connection != null) connection.close();
                if (statement!= null) statement.close();
                if (resultSet!= null) resultSet.close();
            }catch (SQLException exception){
                logger.error("Got Error while closing connection", exception);
            }
        }

         */

//        new JdbcCustomerRepository().findNames("tester01")
//                .forEach(v -> logger.info("Found name : {}", v));

        /* SQL injection
         * sql을 문자열로 작성하게 될 경우 sql injection에 취약
         * prepared statement를 사용하여 방지해야한다.
         * 매번 쿼리를 실행할때 마다 쿼리의 문장을 분석하고 컴파일되고 실행단계를 거치게 된다.
         * 위의 세 단계를 거치면 캐시 후 재사용
         * 처음에 만들어진 쿼리가 고정 되어서 OR절로 트루 시킬 수가 없다
         * */

        /* 이름으로 찾기 test 퀘스천 마크 사용
        new JdbcCustomerRepository().findNames("tester01")
                .forEach(v -> logger.info("Found name : {}", v));
        */

//        var count = customerRepository.deleteAllCustomers();
//        logger.info("deleted count -> {}", count);
//        customerRepository.insertCustomer(UUID.randomUUID(), "new-user", "new-user@gamil.com");

//        UUID customer2 = UUID.randomUUID();
//        customerRepository.insertCustomer(customer2, "new-user2", "new-user2@gamil.com");
//        customerRepository.findAllName().forEach(v -> logger.info("FoundName -> {}", v));
//        customerRepository.updateCustomerName(customer2,"updated-user2");
//        customerRepository.findAllName().forEach(v -> logger.info("FoundName -> {}", v));

        // 쌩뚱 맞은 uuid?
        var customerId = UUID.randomUUID();
//        logger.info("created customerId -> {}", customerId);
//        logger.info("created UUID version -> {}", customerId.version());
//        customerRepository.insertCustomer(customerId,"new-user","new-user@gmail.com");
//        customerRepository.findAllIds().forEach(v -> logger.info("Found customerId : {} and version : {}", v, v.version()));
        var customerRepository = new JdbcCustomerRepository();
        //customerRepository.insertCustomer(customerId, "new-user2","new-user2@gmail.com");
        customerRepository.transactionTest(new Customer(UUID.fromString("e7aa4a24-19fd-47b6-a78c-b833040bb4ee"),"update-user","new-user2@gmail.com", LocalDateTime.now()));

    }
}