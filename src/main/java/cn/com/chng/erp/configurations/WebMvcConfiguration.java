package cn.com.chng.erp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * Created by liuyandong on 2017/6/11.
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/").setCacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS).cachePrivate());
        registry.addResourceHandler("/javascripts/**").addResourceLocations("/javascripts/").setCacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS).cachePrivate());
        registry.addResourceHandler("/libraries/**").addResourceLocations("/libraries/").setCacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS).cachePrivate());
        registry.addResourceHandler("/stylesheets/**").addResourceLocations("/stylesheets/").setCacheControl(CacheControl.maxAge(1L, TimeUnit.DAYS).cachePrivate());
    }
}
