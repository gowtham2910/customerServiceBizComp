package com.pccw.customer.bizcomp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.customer.bizcomp.CustomerServiceBizcompApplication;
import com.pccw.customer.bizcomp.dao.CustomerDao;
import com.pccw.customer.bizcomp.exception.PersistenceException;
import com.pccw.customer.bizcomp.exception.QueryException;
import com.pccw.customer.bizcomp.models.Any;
import com.pccw.customer.bizcomp.models.Customer;
import com.pccw.customer.bizcomp.serviceimpl.CustomerServiceImpl;

import com.ltts.client.MessageBrokerClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")

/**
 * This class is the service implementation Test class
 */
class CustomerServiceImplTest {

	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerDao customerDao;

	@Mock
	MessageBrokerClient client;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	Any any = new Any();

	@Autowired
	private ResourceLoader resourceLoader;
	ObjectMapper obj = new ObjectMapper();

	public String getFile(String location) throws Exception {
		Resource resource = resourceLoader.getResource(location);
		InputStream input = resource.getInputStream();
		byte[] bdata = FileCopyUtils.copyToByteArray(input);
		String data = new String(bdata, StandardCharsets.UTF_8);
		return data;
	}

	/**
	 * Test Case For Creating a Customer.
	 *
	 * @param customer
	 * @return Customer
	 */

	@Test
	void createCustomerTest() throws JsonMappingException, JsonProcessingException, Exception {
		Customer customer = obj.readValue(getFile("classpath:createData.json"),
				new TypeReference<Customer>() {
				});
		Mockito.when(customerDao.save(customer)).thenReturn(customer);
		Customer customer1 = customerServiceImpl.createCustomer(customer);
		assertEquals(customer, customer1);
	}

	/**
	 * Test Case For Creating a Customer caught exception.
	 *
	 * @param customer
	 * @return Customer
	 */

	@Test
	void createCustomerExceptionTest() {
		Customer customer = new Customer();
//		Customer customer = null;
		try {
			Mockito.when(customerDao.save(customer)).thenThrow(PersistenceException.class);
			Customer customer1 = customerServiceImpl.createCustomer(customer);
		} catch (PersistenceException pe) {

		}

	}

	/**
	 * Test Case for List or find Customer objects.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */

	@Test
	void listCustomerTest() throws JsonMappingException, JsonProcessingException, Exception {
		List<Customer> customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<List<Customer>>() {
				});
		Mockito.when(customerDao.listOfCustomers()).thenReturn(customer);
		customerServiceImpl.listOfCustomers(null, null, null);
		assertEquals(2, customer.size());
	}

	/**
	 * Test Case for List or find Customer objects caught Exception.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void listCustomerExceptionTest() {
		/* Customer customer = new Customer(); */
//		Customer customer = null;
		try {
			Mockito.when(customerDao.listOfCustomers()).thenThrow(QueryException.class);
			List<Customer> customer = customerServiceImpl.listOfCustomers(null, null,
					null);
		} catch (QueryException pe) {

		}
	}

	/**
	 * Test Case for List or find Customer objects.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void listCustomerTest1() throws JsonMappingException, JsonProcessingException, Exception {
		List<Customer> customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<List<Customer>>() {
				});
		Mockito.when(customerDao.listOfCustomers()).thenReturn(customer);
		customerServiceImpl.listOfCustomers("field", 2, 3);
		assertEquals(2, customer.size());
	}

	/**
	 * Test Case for List or find Customer objects.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void listCustomerTest2() throws JsonMappingException, JsonProcessingException, Exception {
		List<Customer> customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<List<Customer>>() {
				});
		Mockito.when(customerDao.listOfCustomers()).thenReturn(customer);
		customerServiceImpl.listOfCustomers("field", null, null);
		assertEquals(2, customer.size());
	}

	/**
	 * Test Case for List or find Customer objects.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void listCustomerTest3() throws JsonMappingException, JsonProcessingException, Exception {
		List<Customer> customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<List<Customer>>() {
				});
		Mockito.when(customerDao.listOfCustomers()).thenReturn(customer);
		customerServiceImpl.listOfCustomers(null, 2, 3);
		assertEquals(2, customer.size());
	}

	/**
	 * Test Case Retrieving a Customer by ID.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void RetriveCustomerByIdTest() throws JsonMappingException, JsonProcessingException, Exception {
		Customer[] customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<Customer[]>() {
				});
		Mockito.when(customerDao.retrieveCustomerById("1")).thenReturn(customer[0]);
		Customer customer1 = customerServiceImpl.retrieveCustomerById("1", null);
		assertEquals(customer1, customer[0]);
	}

	/**
	 * Test Case Retrieving a Customer by ID caught Exception.
	 *
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@Test
	void RetriveCustomerByIdExceptionTest() {

		try {
			Mockito.when(customerDao.retrieveCustomerById("1")).thenThrow(QueryException.class);
			Customer customer = customerServiceImpl.retrieveCustomerById("1", null);
		} catch (QueryException pe) {

		}
	}

	/**
	 * Test Case Updating a Customer.
	 *
	 * @param id
	 * @param customer
	 * @return Customer
	 */

	@Test
	void updateCustomerById() throws JsonMappingException, JsonProcessingException, Exception {

		Customer customer = obj.readValue(getFile("classpath:updateData.json"),
				new TypeReference<Customer>() {
				});
		Mockito.when(customerDao.existsById("1")).thenReturn(Boolean.TRUE);
		Mockito.when(customerDao.save(customer)).thenReturn(customer);
		Customer customer1 = customerServiceImpl.updateCustomer("1", customer);
		assertEquals(customer, customer1);
	}

	/**
	 * Test Case Updating a Customer caught Exception.
	 *
	 * @param id
	 * @param customer
	 * @return Customer
	 */

	@Test
	void updateCustomerByIdExceptionTest() {
		Customer customer = new Customer();
//		Customer customer = null;
		try {
			Mockito.when(customerDao.existsById("4")).thenThrow(PersistenceException.class);
			Mockito.when(customerDao.save(customer)).thenThrow(PersistenceException.class);
			Customer customer1 = customerServiceImpl.updateCustomer("4", customer);
		} catch (PersistenceException pe) {

		}
	}

	/**
	 * Test case for Deleting a Customer.
	 *
	 * @param id
	 */

	@Test
	void deleteCustomerTest() throws Exception {
		Customer customer = obj.readValue(getFile("classpath:updateData.json"),
				new TypeReference<Customer>() {
				});

		String customer_id = "1";
		doNothing().when(customerDao).deleteCustomer(customer_id);
		customerServiceImpl.deleteCustomer(customer_id);
		assertEquals(customer_id, customer.getCustomer_id());
	}

	/**
	 * Test case for Deleting a Customer caught Exception.
	 * 
	 * @param id
	 */

	@Test
	void deleteSpecByIdExceptionTest() {
		PersistenceException ex = new PersistenceException("EntityNotFoundException", "no Entity found",
				HttpStatus.INTERNAL_SERVER_ERROR);
		Customer customer = new Customer();
//		Customer customer = null;
		try {
			Mockito.doThrow(ex).when(customerDao).deleteCustomer(Mockito.any());
			customerServiceImpl.deleteCustomer("1");
		} catch (PersistenceException pe) {

		}
	}
}
