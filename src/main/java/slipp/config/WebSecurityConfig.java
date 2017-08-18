package slipp.config;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.extern.slf4j.Slf4j;

public abstract class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    abstract void configureProfile(HttpSecurity http) throws Exception;

    @Resource(name = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        configureProfile(http);
        
        http
                .authorizeRequests()
                    .antMatchers("/api/admin").hasRole("ADMIN")
                    .antMatchers("/api/boards").hasRole("USER")
                    .antMatchers("/").permitAll()
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/users/loginForm")
                    .loginProcessingUrl("/users/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                    .logoutSuccessUrl("/")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Configuration
    @EnableWebSecurity
    @EnableOAuth2Sso
    @Profile({"local", "dev", "prod"})
    @Slf4j
    static class NotTestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureProfile(HttpSecurity http) throws Exception {
            log.info("enable csrf test profile");
        }
    }

    @Configuration
    @EnableWebSecurity
    @EnableOAuth2Sso
    @Profile("test")
    @Slf4j
    static class TestWebSecurityConfig extends WebSecurityConfig {
        @Override
        void configureProfile(HttpSecurity http) throws Exception {
            log.info("disable csrf test profile");
            http.csrf().disable();
            
            http.httpBasic();
        }
    }
}
