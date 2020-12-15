package com.pccw.customer.bizcomp.service;

import java.util.List;

import com.pccw.customer.bizcomp.models.Customer;

/**
 * @author 40003450 This is the interface for defining Customer
 *         services
 */
public interface CustomerService {

	/**
	 * List or find Customer objects.
	 * 
	 * @return List<Customer>
	 */
	public List<Customer> listOfCustomers(String fields, Integer offset, Integer limit);

	/**
	 * Creates a Customer.
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer createCustomer(Customer customer);

	/**
	 * Retrieves a Customer by ID.
	 * 
	 * @param id
	 * @param fields
	 * @return Customer
	 */
	public Customer retrieveCustomerById(String id, String fields);

	/**
	 * Updates partially a Customer.
	 * 
	 * @param id
	 * @param customer
	 * @return Customer
	 */
	public Customer updateCustomer(String id, Customer customer);

	/**
	 * Deletes a Customer.
	 * 
	 * @param id
	 */
	public void deleteCustomer(String id);

}
