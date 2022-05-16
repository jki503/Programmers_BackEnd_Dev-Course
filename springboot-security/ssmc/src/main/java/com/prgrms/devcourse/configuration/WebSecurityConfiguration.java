package com.prgrms.devcourse.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        String userPassword = "user123";
        String adminPassword = "admin123";

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode(userPassword))
                .roles("USER")

                .and()
                .withUser("admin")
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/me").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .permitAll()

                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll()

                .and()
                .rememberMe()
                .key("unique")
                .rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*5);
    }


}
