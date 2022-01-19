package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

/**
 * Use this class to schedule a pickup. Represents object defined in
 * https://api.goshippo.com/pickups endpoint. The endpoint's documentation can be
 * found in https://goshippo.com/docs/reference#pickups
 */
public class Pickup extends APIResource {
	String objectId;
	Object objectCreated;
	Object objectUpdated;
	Object carrierAccount;
	Object location;
	Object transactions;
	Object requestedStartTime;
	Object requestedEndTime;
	Object confirmedStartTime;
	Object confirmedEndTime;
	Object cancelByTime;
	Object status;
	Object confirmationCode;
    Object timezone;
    Object messages;
    Object metadata;

	public static Pickup create(Map<String, Object> params)
		throws AuthenticationException, InvalidRequestException,
		APIConnectionException, APIException {
	return create(params, null);
	}

	public static Pickup create(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(Pickup.class), params,
				Pickup.class, apiKey);
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
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

	public Object getCarrierAccount() {
		return carrierAccount;
	}

	public void setCarrierAccount(Object carrierAccount) {
		this.carrierAccount = carrierAccount;
	}

	public Object getLocation() {
		return location;
	}

	public void setLocation(Object location) {
		this.location = location;
	}

	public Object getTransactions() {
		return transactions;
	}

	public void setTransactions(Object transactions) {
		this.transactions = transactions;
	}

	public Object getRequestedStartTime() {
		return requestedStartTime;
	}

	public void setRequestedStartTime(Object requestedStartTime) {
		this.requestedStartTime = requestedStartTime;
	}

	public Object getRequestedEndTime() {
		return requestedEndTime;
	}

	public void setRequestedEndTime(Object requestedEndTime) {
		this.requestedEndTime = requestedEndTime;
	}

	public Object getConfirmedStartTime() {
		return confirmedStartTime;
	}

	public void setConfirmedStartTime(Object confirmedStartTime) {
		this.confirmedStartTime = confirmedStartTime;
	}

	public Object getConfirmedEndTime() {
		return confirmedEndTime;
	}

	public void setConfirmedEndTime(Object confirmedEndTime) {
		this.confirmedEndTime = confirmedEndTime;
	}

	public Object getCancelByTime() {
		return cancelByTime;
	}

	public void setCancelByTime(Object cancelByTime) {
		this.cancelByTime = cancelByTime;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public Object getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(Object confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public Object getTimezone() {
		return timezone;
	}

	public void setTimezone(Object timezone) {
		this.timezone = timezone;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
}
