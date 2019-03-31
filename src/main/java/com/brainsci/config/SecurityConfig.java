package com.brainsci.config;

import com.brainsci.security.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class SecurityConfig implements WebMvcConfigurer {
    @Autowired
    private Environment env;
    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new SecurityInterceptor();
    }

    /**
     * 添加拦截器，
     * 添加不需要登录的界面。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(getMyInterceptor()).excludePathPatterns(
                "/",
                "/message/test",
                "/message/online",
                "/login",
                "/signup",
                "/verify",
                "/error",
                "/bsci/**", // 允许vue.js生成的静态网页
//                "/socketServer/**", // websocket服务
                "/**/*.*", // 允许访问一切静态资源
                "/swagger-ui.html",
                "/webjars/springfox-swagger-ui/**",
                "/swagger-resources/**",
                "/v2/api-docs",
                "/configuration/**",
                "/signout",
                "/static/**",
                "/MyFile/**",
                "/live",
                "/login-test");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:" + env.getProperty("filesys.dir"));
        registry.addResourceHandler("/bsci/**").addResourceLocations("file:./frontend/");
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
