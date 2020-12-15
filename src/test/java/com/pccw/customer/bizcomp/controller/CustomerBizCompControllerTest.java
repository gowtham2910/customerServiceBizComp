package com.pccw.customer.bizcomp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pccw.customer.bizcomp.CustomerServiceBizcompApplication;
import com.pccw.customer.bizcomp.exception.CustomException;
import com.pccw.customer.bizcomp.models.Customer;
import com.pccw.customer.bizcomp.serviceimpl.CustomerServiceImpl;

/**
 * This class is RestController Test class in Customer BizComp
 * 
 * @author 40006154
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerServiceBizcompApplication.class)
@ActiveProfiles("devlocal")
class CustomerBizCompControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	@Autowired
	CustomerBizCompController customerBizCompController;

	@MockBean
	CustomerServiceImpl customerService;

	@BeforeEach
	public void setUp() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}

	@Autowired
	private ResourceLoader resourceLoader;
	ObjectMapper obj = new ObjectMapper();

	/**
	 * To get the Json input file for test case
	 * 
	 * @param location
	 * @return
	 * @throws Exception
	 */
	public String getFile(String location) throws Exception {
		Resource resource = resourceLoader.getResource(location);
		InputStream input = resource.getInputStream();
		byte[] bdata = FileCopyUtils.copyToByteArray(input);
		String data = new String(bdata, StandardCharsets.UTF_8);
		return data;
	}

	/**
	 * Test Case for List or find Customer objects.
	 * 
	 * @throws Exception
	 */
	@Test
	void getAllCustomerTest() throws Exception {

		List<Customer> customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<List<Customer>>() {
				});
		when(customerService.listOfCustomers(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(customer);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/customer");
		this.mockMvc.perform(builder.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print()).andReturn();
	}

	/**
	 * Test Case for List or find Customer objects caught Exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void getAllCustomerExceptionTest() throws Exception {

		CustomException customException = new CustomException("No records", "No data Found");

		try {
			when(customerService.listOfCustomers(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenThrow(customException);
			customerBizCompController.listOfCustomers(null, null, null);

			// .andExpect(MockMvcResultMatchers.status().isInternalServerError());
		} catch (CustomException ex) {
			assertEquals(ex, customException);
		}

	}

	/**
	 * Test case for Creating a Customer.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	void saveCustomerTest() throws JsonMappingException, JsonProcessingException, Exception {

		Customer customer = obj.readValue(getFile("classpath:createData.json"),
				new TypeReference<Customer>() {
				});
		when(customerService.createCustomer(Mockito.any(Customer.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer")
				.accept(MediaType.APPLICATION_JSON).content(getFile("classpath:createData.json"))
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andDo(print())
				.andExpect(content().json(getFile("classpath:createCheckData.json")));

	}

	/**
	 * Test case for Creating a Customer caught Exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void saveCustomerExceptionTest() throws Exception {

		Customer customer = null;

		CustomException customException = new CustomException("No records", "No data Found");

		try {
			when(customerService.createCustomer(customer)).thenThrow(customException);
			customerBizCompController.createCustomer(customer);

			// .andExpect(MockMvcResultMatchers.status().isInternalServerError());
		} catch (CustomException ex) {
			assertEquals(ex, customException);
		}

	}

	/**
	 * Test case for Retrieving a Customer by ID.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	void retrieveByIdTest() throws JsonMappingException, JsonProcessingException, Exception {
		Customer[] customer = obj.readValue(getFile("classpath:data.json"),
				new TypeReference<Customer[]>() {
				});
		when(customerService.retrieveCustomerById(Mockito.any(), Mockito.any())).thenReturn(customer[1]);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/customer/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE);
		this.mockMvc.perform(builder.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.customer_id", is("2")))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}

	/**
	 * Test case for Retrieving a Customer by ID caught Exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void retrieveCustomerExceptionTest() throws Exception {

		CustomException customException = new CustomException("No records", "No data Found");

		try {
			when(customerService.retrieveCustomerById(Mockito.any(), Mockito.any()))
					.thenThrow(customException);
			customerBizCompController.retrieveCustomerById("1", null);

			// .andExpect(MockMvcResultMatchers.status().isInternalServerError());
		} catch (CustomException ex) {
			assertEquals(ex, customException);
		}

	}

	/**
	 * Test case for Updating partially a Customer.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	void updateCustomerTest() throws JsonMappingException, JsonProcessingException, Exception {
		Customer customer = obj.readValue(getFile("classpath:updateData.json"),
				new TypeReference<Customer>() {
				});
		when(customerService.updateCustomer(Mockito.any(), Mockito.any(Customer.class)))
				.thenReturn(customer);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/customer/1");
		this.mockMvc
				.perform(builder.accept(MediaType.APPLICATION_JSON).content(getFile("classpath:updateData.json"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect((ResultMatcher) jsonPath("$.customer_id", is("1"))).andDo(print())
				.andExpect(content().json(getFile("classpath:UpdateCheckData.json")));

	}

	/**
	 * Test case for Updating partially a Customer caught Exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void updateCustomerExceptionTest() throws Exception {

		Customer customer = null;

		CustomException customException = new CustomException("No records", "No data Found");

		try {

			when(customerService.updateCustomer(Mockito.any(), Mockito.any())).thenThrow(customException);
			customerBizCompController.updateCustomer("1", customer);

		} catch (CustomException ex) {
			assertEquals(ex, customException);
		}

	}

	/**
	 * Test case for Deleting a Customer.
	 * 
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws Exception
	 */
	@Test
	void deleteCustomerTest() throws JsonMappingException, JsonProcessingException, Exception {
		
		doNothing().when(customerService).deleteCustomer(Mockito.any());
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/customer/2");
		this.mockMvc
				.perform(builder.accept(MediaType.APPLICATION_JSON).content(getFile("classpath:updateData.json"))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * Test case for Deleting a Customer caught exception.
	 * 
	 * @throws Exception
	 */
	@Test
	void deleteCustomerExceptionTest() throws Exception {

		CustomException customException = new CustomException("No Records", "Data not found");
		try {
			Mockito.doThrow(customException).when(customerService).deleteCustomer(Mockito.any());
			customerBizCompController.deleteCustomer(null);

		} catch (CustomException e) {
			assertEquals(e, customException);
		}

	}
}
