package com.example.demo.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {




    @Bean //simple security filter chain for testing purposes
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .antMatcher("/*").authorizeRequests().anyRequest().authenticated()
                .and().formLogin().loginProcessingUrl("/loginlogin").permitAll()

        ;


        return http.build();
                }



    @Bean
    public PasswordEncoder encoder()
    {
    return NoOpPasswordEncoder.getInstance();//no password encoder for testing purposes
    }

}
