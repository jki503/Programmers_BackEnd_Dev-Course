package org.prgms.kdt.kdtspringorder.customer;

import java.time.LocalDateTime;
import java.util.UUID;

public class Customer {
    private final UUID customerID; 
    private String name;
    private final String email;
    private LocalDateTime lastLoginAt;
    private final LocalDateTime createdAt;
    
    public Customer(UUID customerID, String name, String email, LocalDateTime createdAt) {
        validateName(name);
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Customer(UUID customerID, String name, String email, LocalDateTime lastLoginAt, LocalDateTime createdAt) {
        validateName(name);
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
    }

    public void changeName(String name){
        validateName(name);
        this.name = name;
    }

    public void login(){
        this.lastLoginAt = LocalDateTime.now();
    }

    private void validateName(String name) {
        if(name.isBlank())
            throw new RuntimeException("Name should not be blank");
    }

    public UUID getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
