package com.pccw.customer.bizcomp.dao.custom;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.pccw.customer.bizcomp.models.Customer;

/**
 * @author 40003450
 *
 */
@NoRepositoryBean
public interface CustomerCustomDao {
	
	@Query("FROM Customer")
	List<Customer> listOfCustomers();
	
	@Query("FROM Customer")
	List<Customer> listOfCustomers(Specification<Customer> customer, Pageable pageable);
	
	@Query("FROM Customer")
	List<Customer> listOfCustomers(Specification<Customer> customer);
	
	@Query("FROM Customer")
	List<Customer> listOfCustomers(Pageable pageable);
}
