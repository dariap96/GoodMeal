package com.goodmeal.security;

import com.goodmeal.security.userdata.UserServiceImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() { return new UserServiceImplementation(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/register", "/*.js", "/*.css", "/login", "/", "/favicon.ico").permitAll()
                .antMatchers(
                         "/api/user/**",
                                    "/update-password-by-admin/**",
                                    "/recipe_rating/remove-by-admin/**",
                                    "/userinfo/{\\d+}",
                                    "/grant-admin-access/**",
                                    "/disable-admin-access/**")
                .hasRole("ADMIN")
                .anyRequest().authenticated().and().logout().invalidateHttpSession(true).logoutUrl("/logout").permitAll()
                .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/bootstrap/**", "/css/**", "/resources/**", "/static/**","/js/**", "/img/**", "/icon/**");

    }
}
