package com.me.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author ZhouChuGang
 * @version 1.0
 * @project saas-common-core
 * @date 2020/5/4 12:24
 * @Description 跨域过滤器配置
 */
@Slf4j
@Configuration
public class CorsFilterConfiguration {

    public CorsFilterConfiguration() {
        log.info("==========注入跨域过滤器=============");
    }

    @Bean("corsFilter")
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // #允许向该服务器提交请求的URI，*表示全部允许
        config.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许cookies跨域
        config.setAllowCredentials(false);
        // #允许访问的头信息,*表示全部
        config.addAllowedHeader(CorsConfiguration.ALL);
        // 允许提交请求的方法，*表示全部允许
        config.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Autowired
    @Qualifier("corsFilter")
    private CorsFilter corsFilter;

    /**
     * 配置跨域过滤器
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter);
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
