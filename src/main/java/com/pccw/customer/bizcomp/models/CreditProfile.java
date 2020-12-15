package com.pccw.customer.bizcomp.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreditProfile entity class
 * @author 40003450
 *
 */
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
@Entity
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditProfile implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		private String creditProfile_id = null;
		
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		private LocalDateTime creditProfileDate = null;
		private String creditRiskRating = null;
		private TimePeriod validFor = null;
		private String baseType = null;
		private String schemaLocation = null;  
		private String type = null;
		
		//bi-directional many-to-one association to Customer
		@ManyToOne
		@JoinColumn(name = "customer_id")
		private Customer Customer = null;
}
