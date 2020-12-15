package com.pccw.customer.bizcomp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pccw.customer.bizcomp.constants.Constants;
import com.pccw.customer.bizcomp.exception.CustomException;
import com.pccw.customer.bizcomp.models.Customer;
import com.pccw.customer.bizcomp.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is RestController class in Customer BizComp
 * 
 * @author 40003450
 *
 */
@Slf4j
@RestController
public class CustomerBizCompController {

	@Autowired
	CustomerService customerService;

	RestTemplate restTemplate;

	/**
	 * List or find Customer objects.
	 * 
	 * @param fields
	 * @param offset
	 * @param limit
	 * @return List<Customer>
	 */
	@GetMapping(value = Constants.CUSTOMER_SERVICE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> listOfCustomers(
			@RequestParam(value = "fields", required = false) String fields,
			@RequestParam(value = "offset", required = false) Integer offset,
			@RequestParam(value = "limit", required = false) Integer limit) {

		List<Customer> customerList = new ArrayList<>();
		try {
			customerList = customerService.listOfCustomers(fields, offset, limit);
		} catch (CustomException ce) {
			log.error(ce.getDetails());
			throw ce;
		}

		return new ResponseEntity<>(customerList, HttpStatus.OK);

	}

	/**
	 * Creates a Customer.
	 * 
	 * @param customer
	 * @return Customer
	 */
	@PostMapping(value = Constants.CUSTOMER_SERVICE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer customerRes = null;
		try {
			customerRes = customerService.createCustomer(customer);
		} catch (CustomException ce) {
			log.error(ce.getDetails());
			throw ce;
		}

		return new ResponseEntity<>(customerRes, HttpStatus.OK);

	}

	/**
	 * Retrieves a Customer by ID.
	 * 
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	@GetMapping(value = Constants.CUSTOMER_SERVICE + Constants.CUSTOMER_ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> retrieveCustomerById(
			@PathVariable(value = "id", required = true) String id,
			@RequestParam(value = "fields", required = false) String fields) {
		Customer customerRes = null;
		try {
			customerRes = customerService.retrieveCustomerById(id, fields);
		} catch (CustomException ce) {
			log.error(ce.getDetails());
			throw ce;
		}

		return new ResponseEntity<>(customerRes, HttpStatus.OK);

	}

	/**
	 * Updates partially a Customer.
	 * 
	 * @param id
	 * @param customer
	 * @return Customer
	 */
	@PatchMapping(value = Constants.CUSTOMER_SERVICE + Constants.CUSTOMER_ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable(value = "id", required = true) String id, @RequestBody Customer customer) {
		Customer customerRes = null;
		try {
			customerRes = customerService.updateCustomer(id, customer);
		} catch (CustomException ce) {
			log.error(ce.getDetails());
			throw ce;
		}

		return new ResponseEntity<>(customerRes, HttpStatus.OK);

	}

	/**
	 * Deletes a Customer.
	 * 
	 * @param id
	 */
	@DeleteMapping(value = Constants.CUSTOMER_SERVICE + Constants.CUSTOMER_ID_PARAM)
	public void deleteCustomer(@PathVariable(value = "id", required = true) String id) {
		try {
			customerService.deleteCustomer(id);
		} catch (CustomException ce) {
			log.error(ce.getDetails());
			throw ce;
		}
	}

}