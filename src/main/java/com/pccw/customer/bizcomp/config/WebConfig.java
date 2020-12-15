
package com.pccw.customer.bizcomp.config;

/*
 * Copyright (c) 2020,L&T Technology Services.
 * All Rights Reserved.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web Configurations class
 * 
 * @author 40003450
 *
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:undertow-config.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	BuildProperties buildProperties;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods(RequestMethod.GET.toString(),
				RequestMethod.POST.toString(), RequestMethod.PATCH.toString(), RequestMethod.DELETE.toString());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
