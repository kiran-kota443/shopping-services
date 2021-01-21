/**
 * 
 */
package com.shopping.kart.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.shopping.kart.interceptor.LoggerInterceptor;
import com.shopping.kart.interceptor.RestInterceptor;

/**
 * @author Kiran Kumar Kota
 *
 * Jan 21, 2021 11:55:26 AM
 *
 * TODO
 *
 * 
 */
@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

	@Autowired
	LoggerInterceptor logger;
	
	@Autowired
	RestInterceptor restInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logger);
		registry.addInterceptor(restInterceptor);
	}

}
