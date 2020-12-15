package com.pccw.customer.bizcomp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot application class
 * 
 * @author 40003450
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.pccw.*,com.ltts.*")
public class CustomerServiceBizcompApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceBizcompApplication.class, args);
	}

}
