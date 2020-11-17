package com.alex.poseidon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    //Function for allowing the loading of the static resources
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring()
                .antMatchers("/css/**");
    }

    //Function configuring the HttpSecurity, the different permissions and defaultUrl
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/register", "/postRegister", "/register_account",
                    		"/postRegister_account").permitAll()
                 //   .antMatchers("/home").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/home", true).
                 and()
                 	.logout()
                 	.invalidateHttpSession(true)
                 	.deleteCookies("JSESSIONID")
                    .permitAll();
    }

    //Method for encoding the password used during the authentication
    void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new Pbkdf2PasswordEncoder());
    }

    //Function for encoding password through Pbkdf2PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new Pbkdf2PasswordEncoder();
    }   
}