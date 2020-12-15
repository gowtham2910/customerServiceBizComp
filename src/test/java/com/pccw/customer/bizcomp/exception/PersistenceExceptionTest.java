package com.pccw.customer.bizcomp.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pccw.customer.bizcomp.CustomerServiceBizcompApplication;


/**
 * Test class for PersistenceException
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")
class PersistenceExceptionTest {

	/**
	 * Test case for PersistenceException
	 * 
	 *
	 */
	@Test
	void PersistentExceTestCases() throws Exception{
		PersistenceException ex = new PersistenceException("EntityNotFoundException", "no Entity found", HttpStatus.INTERNAL_SERVER_ERROR);
		assertThat(ex.getMessage()).isEqualTo("EntityNotFoundException");
		assertThat(ex.getDetails()).isEqualTo("no Entity found");
		assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			
	}

}
