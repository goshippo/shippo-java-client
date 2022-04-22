package com.shippo.model;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;
import com.shippo.Shippo;

public class Rate extends APIResource {
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object shipment;
	Object attributes;
	Object amountLocal;
	Object currencyLocal;
	Object amount;
	Object currency;
	Object provider;
	Object provider_image_75;
	Object provider_image_200;
	Object carrier_account;
	Object servicelevel;
	Object days;
	Object estimated_days;
	Object durationTerms;
	Object messages;
	Boolean test;

	public static Rate retrieve(String id) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static Rate retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Rate.class, id), null, Rate.class, apiKey);
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

	public Object getCarrierAccount() {
		return carrier_account;
	}

	public void setCarrierAccount(Object carrier_account) {
		this.carrier_account = carrier_account;
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

	public Object getServicelevel() {
		return servicelevel;
	}

	public void setServicelevel(Object servicelevel) {
		this.servicelevel = servicelevel;
	}

	public Object getDays() {
		if (Shippo.apiVersion.compareTo("2017-08-01") < 0) {
			return days;
		} else {
			return estimated_days;
		}
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

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	
}
