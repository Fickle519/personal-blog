package com.exh.myblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyBlogWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private HandlerInterceptor handlerInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/login").excludePathPatterns("/admin/dist/**").excludePathPatterns("/admin/plugins/**");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
