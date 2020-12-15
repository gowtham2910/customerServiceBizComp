package com.pccw.customer.bizcomp.constants;

/**
 * Class containing String constants used in the project
 * 
 * @author 40003450
 *
 */
public final class Constants {

	private Constants() {

	}

	public static final String CUSTOMER_SERVICE = "/customer";
	public static final String LISTOF_CUSTOMER_PARAMS = "/{fields}/{offset}/{limit}";
	public static final String RETRIEVE_CUSTOMER_PARAMS = "/{id}/{fields}";
	public static final String UPDATE_CUSTOMER_PARAMS = "/{id}/{customer}";
	public static final String CUSTOMER_ID_PARAM = "/{id}";
	public static final String MESSAGE = "message";
	public static final String DETAILS = "details";
	public static final String ACCESS_DENIED = "Access denied";
	public static final String SWAGGER_MSG_LIST_OF_CUSTOMERS = "List or find Customer objects.";
	public static final String SWAGGER_MSG_DELETE = "Deletes the Customer with given Id";
	public static final String SWAGGER_MSG_SAVE = "Creates a Customer.";
	public static final String SWAGGER_MSG_RETRIEVE_CUSTOMERBYID = "Retrieves a Customer by ID.";
	public static final String SWAGGER_MSG_UPDATE_CUSTOMER = " Updates partially a Customer.";
	public static final String SWAGGER_MSG_DELETE_CUSTOMER = " Deletes a Customer.";
	public static final String SUCCESSFUL_MSG = "Successful";	
}
