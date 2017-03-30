package com.shippo.model;

import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.RequestTimeoutException;
import com.shippo.net.APIResource;

public class Transaction extends APIResource {

	String status;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
	Object commercialInvoiceUrl;
	Object wasTest;
	Object rate;
	Object trackingNumber;
	Object trackingStatus;
	Object trackingUrlProvider;
	Object labelUrl;
	Object messages;
	Object metadata;

	public static Transaction create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return create(params, null);
	}

	public String getInstanceURL() {
		return "";
	}

	public static Transaction create(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(Transaction.class), params,
				Transaction.class, apiKey);
	}

	public static Transaction createSync(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, RequestTimeoutException {
		return createSync(params, null);
	}

	public static Transaction createSync(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException,
			RequestTimeoutException {

		Transaction transaction = request(RequestMethod.POST,
				classURL(Transaction.class), params, Transaction.class, apiKey);
		String object_id = transaction.objectId;
		String status = transaction.status;
		long startTime = System.currentTimeMillis();

		while (status.equals("QUEUED")
				|| status.equals("WAITING")) {
			if (System.currentTimeMillis() - startTime > Shippo.TRANSACTION_REQ_TIMEOUT) {
				throw new RequestTimeoutException(
						"A timeout has occured while waiting for your label to generate. Try retreiving the Transaction object again and check if status is updated. If this issue persists, please contact support@goshippo.com");
			}
			transaction = retrieve(object_id);
			status = (String) transaction.status;
		}

		return transaction;

	}

	public static Transaction retrieve(String id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static Transaction retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Transaction.class, id),
				null, Transaction.class, apiKey);
	}

	public static TransactionCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return all(params, null);
	}

	public static TransactionCollection all(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, classURL(Transaction.class), params,
				TransactionCollection.class, apiKey);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Object getWasTest() {
		return wasTest;
	}

	public void setWasTest(Object wasTest) {
		this.wasTest = wasTest;
	}

	public Object getRate() {
		return rate;
	}

	public void setRate(Object rate) {
		this.rate = rate;
	}

	public Object getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(Object trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Object getTrackingStatus() {
		return trackingStatus;
	}

	public void setTrackingStatus(Object trackingStatus) {
		this.trackingStatus = trackingStatus;
	}

	public Object getTrackingUrlProvider() {
		return trackingUrlProvider;
	}

	public void setTrackingUrlProvider(Object trackingUrlProvider) {
		this.trackingUrlProvider = trackingUrlProvider;
	}

	public Object getLabelUrl() {
		return labelUrl;
	}

	public void setLabelUrl(Object labelUrl) {
		this.labelUrl = labelUrl;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}
	
	public Object getCommercialInvoiceUrl() {
		return commercialInvoiceUrl;
	}

	public void setCommercialInvoiceUrl(Object commercialInvoiceUrl) {
		this.commercialInvoiceUrl = commercialInvoiceUrl;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

}