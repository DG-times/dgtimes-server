package com.v1.dgtimes.config.security;

import com.v1.dgtimes.config.security.userdetail.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@RequiredArgsConstructor
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/**")
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
        ;
    }
}
