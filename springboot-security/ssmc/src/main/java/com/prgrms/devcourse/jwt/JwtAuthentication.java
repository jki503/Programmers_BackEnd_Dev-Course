package com.prgrms.devcourse.jwt;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class JwtAuthentication {

    public final String token;

    public final String username;

    public JwtAuthentication(String token, String username) {
        checkArgument(isNotEmpty(token), "token must be provided");
        checkArgument(isNotEmpty(username), "username must be provided");

        this.token = token;
        this.username = username;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("token", token)
                .append("username", username)
                .toString();
    }
}
