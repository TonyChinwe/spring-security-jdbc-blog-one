package com.phisoft.springsecurityjdbcblogone.projectconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(){
        return new JdbcUserDetailsManager(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private DataSource dataSource(){
        DriverManagerDataSource managerDataSource=new DriverManagerDataSource();
        managerDataSource.setUrl("jdbc:mysql://localhost/security2");
        managerDataSource.setPassword("root");
        managerDataSource.setUsername("root");
        return managerDataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/save").permitAll()
        .anyRequest().authenticated();
    }
}
