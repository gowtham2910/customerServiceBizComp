package com.pccw.customer.bizcomp.common;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.pccw.customer.bizcomp.models.Customer;

/**
 * This class is criteria builder class for returning List of Cusotmer
 * @author 40003450
 *
 */
public class CustomerListBuilder implements Specification<Customer> {

	private List<String> customerFields;

	private static final long serialVersionUID = 1L;

	public CustomerListBuilder(List<String> fields) {
		this.customerFields = fields;
	}

	@Override
	public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {

		List<Predicate> predicateList = new ArrayList<>();

		for (String field : customerFields)
			predicateList.add(criteriaBuilder.equal(root.get(field), root.get(field)));

		return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
	}

}
