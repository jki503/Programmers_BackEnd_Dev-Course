package org.prgms.kdt.kdtspringorder.customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    void createCustomers(List<Customer> customerList);
    Customer createCustomer(String email, String name);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomer(UUID customerId);
}
