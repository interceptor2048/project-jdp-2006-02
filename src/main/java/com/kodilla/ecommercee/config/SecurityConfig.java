package com.kodilla.ecommercee.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean//sztucznie stworzeni użytkownicy z rolami moderator i user
    public UserDetailsService userDetailsService() {
        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("mod")
                .password("12345")
                .roles("MODERATOR")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("123456")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(moderator, user);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/v1/user").hasAnyRole("USER", "MODERATOR")
                //ograniczenia dostępu do poszczególnych metod crudowych
                .antMatchers(HttpMethod.GET, "/v1/user/getMessage").hasRole("MODERATOR")
                .and()
                .formLogin().permitAll()//login - wywoła za każdym razem jak będziemy chcieli uzyskac dostęp do metody
                .and()
                .logout().permitAll()//logout pod adresem localhost:8080/logout
                .and()
                .csrf().disable();//dodanie żeby można było strzelać z postmana
    }
}
