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
 * Test class for QueryException
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")
class QueryExceptionTest {

	/**
	 * Test case for QueryException
	 * 
	 *
	 */
	@Test
	void queryExceptiontestCases() throws Exception{
		QueryException ex = new QueryException("Query Syntax error", "Related query details not found", HttpStatus.INTERNAL_SERVER_ERROR);
		
		assertThat(ex.getMessage()).isEqualTo("Query Syntax error");
		assertThat(ex.getDetails()).isEqualTo("Related query details not found");
		assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
			
	}

}
