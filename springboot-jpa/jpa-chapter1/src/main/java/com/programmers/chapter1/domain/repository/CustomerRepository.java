package com.programmers.chapter1.domain.repository;

import com.programmers.chapter1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
