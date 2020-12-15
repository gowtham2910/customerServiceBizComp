package com.pccw.customer.bizcomp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pccw.customer.bizcomp.dao.custom.CustomerCustomDao;
import com.pccw.customer.bizcomp.models.Customer;

/**
 * This is DAO class for performing CRUD operations on Customer data
 * model
 * 
 * @author 40003450
 */
public interface CustomerDao extends JpaRepository<Customer, String>, CustomerCustomDao {	

	/**
	 * Query to retrieve a Customer based on the given id
	 * 
	 * @return
	 */
	@Query("FROM Customer c where c.id=:id")
	Customer retrieveCustomerById(@Param("id") String id);

	/**
	 * Query to delete a Customer based on the given id
	 * 
	 * @return
	 */
	@Modifying
	@Query("DELETE FROM Customer c where c.id=:id")
	void deleteCustomer(@Param("id") String id);

}
