package com.pccw.customer.bizcomp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RelatedParty entity class
 * @author 40003450
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedParty implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		private String relatedParty_id = null;
		private String href = null;  
		private String name = null;
		private String role = null;
		private String baseType = null;
		private String schemaLocation = null;  
		private String type = null;
		private String referredType = null; 
		  
		//bi-directional many-to-one association to Customer
		@ManyToOne
		@JoinColumn(name = "customer_id")
		private Customer Customer = null;
}

