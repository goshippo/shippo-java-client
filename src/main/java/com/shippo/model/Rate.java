package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class Rate extends APIResource {
	String objectState;
	String objectStatus;
	String objectPurpose;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
	Object shipment;
    Object attributes;
    Object amountLocal;
    Object currencyLocal;
    Object amount;
    Object currency;
    Object provider;
    Object provider_image_75;
    Object provider_image_200;
    Object servicelevelName;
    Object servicelevelTerms;
    Object days;
    Object durationTerms;
    Object trackable;
    Object insurance;
    Object insuranceAmountLocal;
    Object insuranceCurrencyLocal;
    Object insuranceAmount;
    Object insuranceCurrency;
    Object messages;

    public static Rate retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static Rate retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);
    }

    public static RateCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static RateCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Rate.class), params, RateCollection.class, apiKey);
    }

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}

	public String getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}

	public String getObjectPurpose() {
		return objectPurpose;
	}

	public void setObject_purpose(String objectPurpose) {
		this.objectPurpose = objectPurpose;
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

	public Object getShipment() {
		return shipment;
	}

	public void setShipment(Object shipment) {
		this.shipment = shipment;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public Object getAmountLocal() {
		return amountLocal;
	}

	public void setAmountLocal(Object amountLocal) {
		this.amountLocal = amountLocal;
	}

	public Object getCurrencyLocal() {
		return currencyLocal;
	}

	public void setCurrencyLocal(Object currencyLocal) {
		this.currencyLocal = currencyLocal;
	}

	public Object getAmount() {
		return amount;
	}

	public void setAmount(Object amount) {
		this.amount = amount;
	}

	public Object getCurrency() {
		return currency;
	}

	public void setCurrency(Object currency) {
		this.currency = currency;
	}

	public Object getProvider() {
		return provider;
	}

	public void setProvider(Object provider) {
		this.provider = provider;
	}

	public Object getProviderImage75() {
		return provider_image_75;
	}

	public void setProviderImage75(Object providerImage75) {
		this.provider_image_75 = providerImage75;
	}

	public Object getProvider_image_200() {
		return provider_image_200;
	}

	public void setProvider_image_200(Object provider_image_200) {
		this.provider_image_200 = provider_image_200;
	}

	public Object getServicelevelName() {
		return servicelevelName;
	}

	public void setServicelevelName(Object servicelevelName) {
		this.servicelevelName = servicelevelName;
	}

	public Object getServicelevelTerms() {
		return servicelevelTerms;
	}

	public void setServicelevelTerms(Object servicelevelTerms) {
		this.servicelevelTerms = servicelevelTerms;
	}

	public Object getDays() {
		return days;
	}

	public void setDays(Object days) {
		this.days = days;
	}

	public Object getDuration_terms() {
		return durationTerms;
	}

	public void setDuration_terms(Object durationTerms) {
		this.durationTerms = durationTerms;
	}

	public Object getTrackable() {
		return trackable;
	}

	public void setTrackable(Object trackable) {
		this.trackable = trackable;
	}

	public Object getInsurance() {
		return insurance;
	}

	public void setInsurance(Object insurance) {
		this.insurance = insurance;
	}

	public Object getInsuranceAmountLocal() {
		return insuranceAmountLocal;
	}

	public void setInsuranceAmountLocal(Object insuranceAmountLocal) {
		this.insuranceAmountLocal = insuranceAmountLocal;
	}

	public Object getInsuranceCurrencyLocal() {
		return insuranceCurrencyLocal;
	}

	public void setInsuranceCurrencyLocal(Object insuranceCurrencyLocal) {
		this.insuranceCurrencyLocal = insuranceCurrencyLocal;
	}

	public Object getInsuranceAmount() {
		return insuranceAmount;
	}

	public void setInsuranceAmount(Object insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}

	public Object getInsuranceCurrency() {
		return insuranceCurrency;
	}

	public void setInsuranceCurrency(Object insuranceCurrency) {
		this.insuranceCurrency = insuranceCurrency;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

}
