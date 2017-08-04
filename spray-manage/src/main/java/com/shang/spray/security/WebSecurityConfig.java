package com.shang.spray.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * info:SecurityConfig权限认证
 * Created by shang on 16/7/27.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("***************** WebSecurityConfig.configure *************************");
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/assets/**", "/", "/login","/api/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/main")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.logout().logoutSuccessUrl("/login");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new Md5PasswordEncoder());
    }
}
