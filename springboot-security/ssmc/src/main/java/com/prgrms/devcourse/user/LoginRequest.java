package com.prgrms.devcourse.user;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginRequest {

    private String principal;

    private String credentials;

    protected LoginRequest(){}

    public LoginRequest(String principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public String getCredentials() {
        return credentials;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("principal", principal)
                .append("credentials", credentials)
                .toString();
    }
}
