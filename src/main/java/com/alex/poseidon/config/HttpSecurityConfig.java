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
        http.authorizeRequests()
                //Permit access to all for the login and main home page
                .antMatchers("/", "/login").permitAll()
                //Permit access to /user/* to only users with Authority ADMIN
                .antMatchers("/user/*").hasAuthority("ADMIN")
                //Authentication request parameters
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //Definition of the default Success URL (redirecting to RedirectController)
                .defaultSuccessUrl("/default", true).
                and()
                //Configuration of the logout
                .logout()
                .logoutUrl("/app-logout")
                //Definition of the default logout success URL (redirecting to HomeController)
                .logoutSuccessUrl("/")
                //Invalidate the current HTTP session and its cookies
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                //Definition of the default URL in case of exception of an access denied
                .exceptionHandling()
                .accessDeniedPage("/app/error");

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