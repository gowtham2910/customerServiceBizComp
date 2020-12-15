package com.pccw.customer.bizcomp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pccw.customer.bizcomp.CustomerServiceBizcompApplication;


/**
 * Test class for CustomException
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")
class CustomExceptionTest {
	
	/**
	 * Test case for CustomException
	 * 
	 *
	 */
	@Test
	void customExceptionTestCases() throws Exception {
		CustomException customException = new CustomException("No Records",
				"Data not found");
		assertThat(customException.getMessage()).isEqualTo("No Records");
		assertThat(customException.getDetails()).isEqualTo("Data not found");
	}

}
