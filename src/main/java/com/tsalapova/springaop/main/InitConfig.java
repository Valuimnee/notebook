package com.tsalapova.springaop.main;

import com.tsalapova.springaop.dao.UserDao;
import com.tsalapova.springaop.dao.impl.UserDaoImpl;
import com.tsalapova.springaop.service.UserService;
import com.tsalapova.springaop.service.impl.UserServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author TsalapovaMD
 * @version 1.0, 3/14/2018
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tsalapova.springaop")
public class InitConfig implements WebMvcConfigurer {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("errmes");
        return messageSource;
    }

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoImpl();
    }
}
