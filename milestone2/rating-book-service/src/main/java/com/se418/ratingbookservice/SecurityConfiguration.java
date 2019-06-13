package com.se418.ratingbookservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) {
        try{
            http.httpBasic().disable().authorizeRequests()
                    .antMatchers("/rate").hasAnyRole("USER", "ADMIN").anyRequest()
                    .authenticated().and().csrf().disable();
        }catch (Exception e){
            System.out.println("Exception thrown: " + e);
        }
    }
}
