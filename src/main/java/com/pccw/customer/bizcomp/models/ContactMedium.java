package com.pccw.customer.bizcomp.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ContactMedium entity class
 * 
 * @author 40003450
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactMedium implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	
	private String contact_Medium_id = null;
	private String mediumType = null;
	private Boolean preferred = null;
	private TimePeriod validFor = null;
	private String baseType = null;
	private String schemaLocation = null;
	private String type = null;
	
	// bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer Customer = null;
	
	/*
	 * //bi-directional one-to-one association to ContactMedium
	 * 
	 * @OneToOne(fetch = FetchType.EAGER, mappedBy = "ContactMedium", cascade =
	 * CascadeType.ALL) private List<MediumCharacteristic> mediumCharacteristic =
	 * null;
	 */
	@OneToOne(mappedBy = "contactMedium", cascade = CascadeType.ALL,
			 orphanRemoval = true, fetch = FetchType.EAGER) 
			 private MediumCharacteristic mediumCharacteristic = null;


	
	
}
