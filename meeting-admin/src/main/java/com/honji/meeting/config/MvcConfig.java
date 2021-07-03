package com.honji.meeting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Autowired
    private SessionTimeoutInterceptor sessionTimeoutInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionTimeoutInterceptor)
                .excludePathPatterns("/AdminLTE-2.4.18/**", "/nth-tabs/**", "/tableExport/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/admin/login");
    }

}
