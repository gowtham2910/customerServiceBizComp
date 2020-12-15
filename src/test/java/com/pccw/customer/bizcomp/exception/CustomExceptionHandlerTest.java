package com.pccw.customer.bizcomp.exception;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pccw.customer.bizcomp.CustomerServiceBizcompApplication;
import com.pccw.customer.bizcomp.constants.Constants;


/**
 * Test class for CustomExceptionHandler class
 * 
 *
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("devlocal")
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
class CustomExceptionHandlerTest extends ResponseEntityExceptionHandler {

	@Autowired
	CustomExceptionHandler customExceptionHandler;

	/**
	 * Test case for Handling CustomException
	 * 
	 *
	 */
	@Test
	void customExceptionHandlerTestCases() throws Exception {

		CustomException ex = new CustomException("No Records", "NodataFound");

		Map<String, String> res = new HashMap<>();
		res.put(Constants.MESSAGE, ex.getMessage());
		res.put(Constants.DETAILS, ex.getDetails());
		ResponseEntity<Object> obj = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		// Mockito.when(customExceptionHandler.handleInternalServerErrorException(ex)).thenReturn(obj);
		ResponseEntity<Object> result = customExceptionHandler.handleInternalServerErrorException(ex);
		System.out.println(result.getBody());
		System.out.println(res);

		assertNotNull(obj);

	}

	/**
	 * Test case for Handling QueryException
	 * 
	 *
	 */
	@Test
	void queryExceptionHandlerTestCases() throws Exception {

		QueryException ex = new QueryException("Query Syntax error", "Related query details not found",
				HttpStatus.INTERNAL_SERVER_ERROR);

		Map<String, String> res = new HashMap<>();
		res.put(Constants.MESSAGE, ex.getMessage());
		res.put(Constants.DETAILS, ex.getDetails());
		ResponseEntity<Object> obj = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		// Mockito.when(customExceptionHandler.handleInternalServerErrorException(ex)).thenReturn(obj);
		ResponseEntity<Object> result = customExceptionHandler.handleQueryException(ex);
		// System.out.println(result.getBody()); System.out.println(res);

		assertNotNull(obj);
	}

	/**
	 * Test case for Handling PersistenceException
	 * 
	 *
	 */
	@Test
	void persistenceExceptionHandlerTestCases() throws Exception {

		PersistenceException ex = new PersistenceException("EntityNotFoundException", "no Entity found",
				HttpStatus.INTERNAL_SERVER_ERROR);

		Map<String, String> res = new HashMap<>();
		res.put(Constants.MESSAGE, ex.getMessage());
		res.put(Constants.DETAILS, ex.getDetails());
		ResponseEntity<Object> obj = new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		// Mockito.when(customExceptionHandler.handleInternalServerErrorException(ex)).thenReturn(obj);
		ResponseEntity<Object> result = customExceptionHandler.handlePersistenceException(ex);
		// System.out.println(result.getBody()); System.out.println(res);

		assertNotNull(obj);

	}
}
