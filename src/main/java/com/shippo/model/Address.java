package com.shippo.model;

import java.util.Map;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

// Class is affected by URL name inconsistencies, see APIResource.java (private static String className(Class<?> clazz))
public class Address extends APIResource {

	@SerializedName("is_complete")
	boolean isComplete;

	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
    Object name;
    Object company;
    Object street1;
    Object streetNo;
    Object street2;
    Object city;
    Object state;
    Object zip;
    Object country;
    Object phone;
    Object email;
    Object is_residential;
    Object metadata;
    Object messages;
	ValidationResults validation_results;

    public class ValidationResults {
    	boolean is_valid;
    	List<ValidationMessage> messages;

    	public List<ValidationMessage> getValidationMessages(){ return messages; }
    	public void setValidationMessages(){ this.messages = messages; }
    	public boolean getIsValid(){ return is_valid; }
    	public void setIsValid(){ this.is_valid = is_valid; }
	}

	public class ValidationMessage {
		String source;
		String code;
		String text;

		public String getSource(){ return source; }
		public String getCode(){ return code; }
		public String getText() { return text; }

		public void setSource(){ this.source = source; }
		public void setCode(){ this.code = code; }
		public void setText() { this.text = text; }
	}

    public static Address createForPurchase(String name, String street1, String city, String zip, String state,
                                            String country, String email) {
        Address a = new Address();
        a.name = name;
        a.street1 = street1;
        a.city = city;
        a.zip = zip;
        a.state = state;
        a.country = country;
        a.email = email;
        return a;
    }

    public static Address validate(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return validate(id, null);
    }

    public static Address validate(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Address.class, id) + "/validate", null, Address.class, apiKey);
    }

    public static Address create(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public static Address create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Address.class), params, Address.class, apiKey);
    }

    public static Address retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static Address retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Address.class, id), null, Address.class, apiKey);
    }

    public static AddressCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static AddressCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Address.class), params, AddressCollection.class, apiKey);
    }

	public boolean getIsComplete() {
		return isComplete;
	}

	public void setIsComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectOwner() {
		return objectOwner;
	}

	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}

	public Object getObjectCreated() {
		return objectCreated;
	}

	public void setObjectCreated(Object objectCreated) {
		this.objectCreated = objectCreated;
	}

	public Object getObjectUpdated() {
		return objectUpdated;
	}

	public void setObjectUpdated(Object objectUpdated) {
		this.objectUpdated = objectUpdated;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getCompany() {
		return company;
	}

	public void setCompany(Object company) {
		this.company = company;
	}

	public Object getStreet1() {
		return street1;
	}

	public void setStreet1(Object street1) {
		this.street1 = street1;
	}

	public Object getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(Object streetNo) {
		this.streetNo = streetNo;
	}

	public Object getStreet2() {
		return street2;
	}

	public void setStreet2(Object street2) {
		this.street2 = street2;
	}

	public Object getCity() {
		return city;
	}

	public void setCity(Object city) {
		this.city = city;
	}

	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public Object getZip() {
		return zip;
	}

	public void setZip(Object zip) {
		this.zip = zip;
	}

	public Object getCountry() {
		return country;
	}

	public void setCountry(Object country) {
		this.country = country;
	}

	public Object getPhone() {
		return phone;
	}

	public void setPhone(Object phone) {
		this.phone = phone;
	}

	public Object getEmail() {
		return email;
	}

	public void setEmail(Object email) {
		this.email = email;
	}

	public Object getIs_Residential() { return is_residential; }

	public void setIs_Residential(Object is_residential) { this.is_residential = is_residential; }

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public void setValidationResults(ValidationResults validation_results) { this.validation_results = validation_results;	}

	public ValidationResults getValidationResults() {return validation_results;}
}
