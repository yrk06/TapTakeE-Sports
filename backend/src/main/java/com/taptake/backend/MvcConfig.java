package com.taptake.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.taptake.backend.service.LoggerMiddleware;
import com.taptake.backend.service.ResourceBlockedMiddleware;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("taptake.yrk06.com.br");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerMiddleware());

        // Disable this when testing in localhost
        // registry.addInterceptor(new
        // ResourceBlockedMiddleware()).addPathPatterns("/api/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        String spa = "forward:/index.html";
        registry.addViewController("/").setViewName(spa);
        registry.addViewController("/{x:[\\w\\-]+}").setViewName(spa);
        registry.addViewController("/{x:^(?!api$)(?!index.html$)(?!static$)(?!assets$).*$}/**").setViewName(spa);
    }
}
