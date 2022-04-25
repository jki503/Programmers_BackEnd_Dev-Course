package org.prgms.kdt.kdtspringorder.customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerDto(
        UUID customerId,
        String name,
        String email,
        LocalDateTime lastLoginAt,
        LocalDateTime createdAt
) {
    static CustomerDto of(Customer customer) {
        return new CustomerDto(customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getLastLoginAt(),
                customer.getCreatedAt());
    }

    static Customer to(CustomerDto customerDto){
        return  new Customer(
                customerDto.customerId(),
                customerDto.name(),
                customerDto.email(),
                customerDto.lastLoginAt(),
                customerDto.createdAt()
        );
    }
}
