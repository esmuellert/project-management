package com.jrp.pma.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    final
    DataSource dataSource;

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfiguration(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM user_accounts WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user_accounts WHERE username = ?")
                .passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasAnyRole("ADMIN", "USER")     // priority according to sequence
                .antMatchers("/employees/new").hasAnyRole("ADMIN", "USER")
                .antMatchers("/employees/save").hasRole("ADMIN")   // also can be hasRole()
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/projects/update").hasRole("ADMIN")
                .antMatchers("/projects/delete").hasRole("ADMIN")
                .antMatchers("/employees/update").hasRole("ADMIN")
                .antMatchers("/employees/delete").hasRole("ADMIN")
                .antMatchers("/", "/**").permitAll()
                .and().formLogin();
    }
}
