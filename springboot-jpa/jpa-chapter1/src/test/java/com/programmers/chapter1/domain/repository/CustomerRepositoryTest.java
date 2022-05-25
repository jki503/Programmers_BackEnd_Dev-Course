package com.programmers.chapter1.domain.repository;

import com.programmers.chapter1.domain.Customer;
import com.programmers.chapter1.domain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Slf4j
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @AfterEach
    void setUp() {
    }

    @BeforeEach
    void tearDown() {
        customerRepository.deleteAll();
    }

    @Transactional
    @Test
    void testUpdate(){

        Customer customer = new Customer();

        customer.setId(1L);
        customer.setFirstName("kyungil");
        customer.setLastName("jung");
        customerRepository.save(customer);

        Customer retrieveCustomer  = customerRepository.findById(1L).get();

        retrieveCustomer.setLastName("jungjung");

        Customer updatedCustomer = customerRepository.findById(1L).get();

        assertThat(updatedCustomer.getLastName()).isEqualTo("jungjung");

    }

}