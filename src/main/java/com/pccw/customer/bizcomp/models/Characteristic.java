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
 * Characteristic entity class
 * @author 40003450
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Characteristic implements Serializable {
		private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		private String characteristic_id = null;
		private String name = null;
		private String valueType = null;
		private String value = null;
		private String baseType = null;
		private String schemaLocation = null;  
		private String type = null;
		  
		//bi-directional many-to-one association to MediumCharacteristic
		@ManyToOne
		@JoinColumn(name = "customer_id")
		private Customer Customer = null;
}
