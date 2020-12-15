package com.pccw.customer.bizcomp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer entity class
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
public class Customer implements Serializable {
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(generator = "system-uuid")
		@GenericGenerator(name = "system-uuid", strategy = "uuid")
		private String customer_id = null;
		private String href = null;
		private String name = null;
		private String status = null;
		private String statusReason = null;
		private TimePeriod validFor = null;
		private String baseType = null;
		private String schemaLocation = null;
		private String type = null;
		
		// bi-directional one-to-many association to ContactMedium
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<ContactMedium> contactMedium = null;
		
		// bi-directional one-to-many association to RelatedParty
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<RelatedParty> relatedParty = null;
		
		// bi-directional one-to-many association to Characteristic
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<Characteristic> characteristic = null;

		// bi-directional one-to-many association to AccountRef
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<AccountRef> accountRef = null;
		
		// bi-directional one-to-many association to PaymentMethodRef
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<PaymentMethodRef> paymentMethodRef = null;
		
		// bi-directional one-to-many association to CreditProfile
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<CreditProfile> creditProfile = null;
		
		// bi-directional one-to-many association to AgreementRef
		@OneToMany(mappedBy = "Customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
		@Fetch(value = FetchMode.SUBSELECT)
		private List<AgreementRef> agreementRef = null;
		
}
