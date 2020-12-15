package com.pccw.customer.bizcomp.serviceimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ltts.client.MessageBrokerClient;
import com.pccw.customer.bizcomp.common.CustomerListBuilder;
import com.pccw.customer.bizcomp.dao.CustomerDao;
import com.pccw.customer.bizcomp.exception.PersistenceException;
import com.pccw.customer.bizcomp.exception.QueryException;
import com.pccw.customer.bizcomp.models.Customer;
import com.pccw.customer.bizcomp.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 40003450 This class is the service implementation class which
 *         interacts with DB to return the expected result
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired 
	CustomerDao customerDao;
	
	protected MessageBrokerClient client;
	private String createTopic = "create.topic"; 
	private String deleteTopic = "delete.topic";
	
	@Override
	public List<Customer> listOfCustomers(String fields, Integer offset, Integer limit) {
		 List<Customer> customerList = new ArrayList<>();
	        Pageable pageable = null;
	        try {
	            log.info("List of Customers in biz");
	            if (null != offset && null != limit) {
	                pageable = PageRequest.of(offset, limit);
	            }
	            
	            if(null != fields) {
	            	List<String> fieldsList =  Arrays.asList(fields.split(","));
	            	CustomerListBuilder customeListBuilder = new CustomerListBuilder(fieldsList);
	            	if(null!=pageable) {
	            		customerList = customerDao.listOfCustomers(customeListBuilder, pageable);
	            	}else {
	            		customerList = customerDao.listOfCustomers(customeListBuilder);
	            	}
	            }else {
	            	if(null!=pageable) {
	            		customerList = customerDao.listOfCustomers(pageable);
	            	}
	            	else {
	            		customerList = customerDao.listOfCustomers();
	            	}
	            }
	        } catch (QueryException pe) {
	            log.error(pe.getDetails(), pe);
	            throw pe;
	        }
	        return customerList;
	}
	
	@Override
	public Customer createCustomer(Customer customer) {
		Customer custom;
        try {
            log.info("Creating Customer in biz");
            custom = customerDao.save(customer);
			publish(createTopic, custom);
        } catch (PersistenceException pe) {
            log.error(pe.getDetails(), pe);
            throw pe;
        }
        return custom;
	}
	
	@Override
	public Customer retrieveCustomerById(String id, String fields) {
		Customer customer;
        try {
            log.info("Retrieve Customer By Id in biz");
            customer = customerDao.retrieveCustomerById(id);
        } catch (QueryException pe) {
            log.error(pe.getDetails(), pe);
            throw pe;
        }
        return customer;

	}
	
	@Override
	public Customer updateCustomer(String id, Customer customer) {
		Customer custom = null;
		try {
			log.info("update customer in biz");
			if (customerDao.existsById(id))
				custom = customerDao.save(customer);
		} catch (PersistenceException pe) {
			log.error(pe.getDetails(), pe);
			throw pe;
		}
		return custom;
	}
	
	@Override
	@Transactional
	public void deleteCustomer(String id) {
		try {			
		    log.info("Delete Customer in biz");
		    Customer customer = retrieveCustomerById(id, "");
			customerDao.deleteCustomer(id);
			publish(deleteTopic, customer);
        } catch (PersistenceException pe) {
            log.error(pe.getDetails(), pe);
            throw pe;
        }
	}
	
	private void publish(String topic, Customer customer) {
		try {
			client.produce(topic, customer);
			log.info("Customer published successfully to the topic: " + topic ); 
		} catch (Exception e) {
			 log.error("Exception " + e.getMessage());
		}
	}

}
