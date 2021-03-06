package com.prgrms.devcourse.user;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long id;

    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Column(name = "passwd", nullable = false)
    private String passwd;

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    public void checkPassword(PasswordEncoder passwordEncoder, String credentials){
        if(!passwordEncoder.matches(credentials, passwd)){
            throw new IllegalArgumentException("Bad Credentials");
        }
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPasswd() {
        return passwd;
    }

    public Group getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("loginId", loginId)
                .append("passwd", passwd)
                .append("group", group)
                .toString();
    }
}
