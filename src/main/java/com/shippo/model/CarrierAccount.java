package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class CarrierAccount extends APIResource {

	String objectId;
	String accountId;
	String carrier;
	Boolean test;
	Boolean active;
	Object parameters;

	public static CarrierAccount create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return create(params, null);
	}

	public static CarrierAccount retrieve(String id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static CarrierAccount update(String id, Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return update(id, params, null);
	}

	public static CarrierAccount create(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(CarrierAccount.class),
				params, CarrierAccount.class, apiKey);
	}

	public static CarrierAccount update(String id, Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.PUT,
				instanceURL(CarrierAccount.class, id), params,
				CarrierAccount.class, apiKey);
	}

	public static CarrierAccount retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET,
				instanceURL(CarrierAccount.class, id), null,
				CarrierAccount.class, apiKey);
	}

	public static CarrierAccountCollection all()
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return all(null);
	}

	public static CarrierAccountCollection all(String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, classURL(CarrierAccount.class),
				null, CarrierAccountCollection.class, apiKey);
	}

	public String getInstanceURL() {
		return "";
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Object getParameters() {
		return parameters;
	}

	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}
}
