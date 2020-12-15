package com.pccw.customer.bizcomp.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * MediumCharacteristic entity class
 * @author 40003450
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediumCharacteristic implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		private String medium_Characteristic_id = null;
		private String city = null;
		private String contactType = null;
		private String country = null;
		private String emailAddress = null;
		private String faxNumber = null;
		private String phoneNumber = null;
		private String postCode = null;
		private String socialNetworkId = null;
		private String stateOrProvince = null;
		private String street1 = null;
		private String street2 = null;
		private String baseType = null;
		private String schemaLocation = null;  
		private String type = null;
		
		//bi-directional one-to-one association to ContactMedium
		@OneToOne
		@JoinColumn(name = "contact_Medium_id")
		private ContactMedium contactMedium = null;
		 
		 
}
