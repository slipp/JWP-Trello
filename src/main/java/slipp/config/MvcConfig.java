package slipp.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;

import slipp.web.helpers.SpringSecurityHelper;
import slipp.web.interceptor.AuthenticationInterceptor;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private HandlebarsViewResolver handlebarsViewResolver;

    @Autowired
    private SpringSecurityHelper springSecurityHelper;

    @PostConstruct
    public void registerHelper() {
        handlebarsViewResolver.registerHelper(SpringSecurityHelper.NAME, springSecurityHelper);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users/loginForm").setViewName("login");
        registry.addViewController("/users/signUp").setViewName("signUp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor());
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
