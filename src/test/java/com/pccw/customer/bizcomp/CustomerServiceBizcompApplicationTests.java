package com.pccw.customer.bizcomp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;


/**
 * test class for CustomerServiceBizcompApplication class
 * 
 * @author 40006154
 *
 */
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")
class CustomerServiceBizcompApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertThat(this.context).isNotNull();
	}
	
	@Test
	void contextLoadsNew() {
		CustomerServiceBizcompApplication.main(new String[] {});
		assertThat(this.context).isNotNull();
	}

}
