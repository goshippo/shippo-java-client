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

	String object_state;
	String object_status;
	String object_purpose;
	String object_id;
	String object_owner;
	Object object_created;
	Object object_updated;
	Object was_test;
	Object rate;
	Object tracking_number;
	Object tracking_status;
	Object tracking_url_provider;
	Object label_url;
	Object messages;
	Object customs_note;
	Object submission_note;
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
		String object_id = transaction.object_id;
		String object_status = transaction.object_status;
		long startTime = System.currentTimeMillis();

		while (object_status.equals("QUEUED")
				|| object_status.equals("WAITING")) {
			if (System.currentTimeMillis() - startTime > Shippo.TRANSACTION_REQ_TIMEOUT) {
				throw new RequestTimeoutException(
						"A timeout has occured while waiting for your label to generate. Try retreiving the Transaction object again and check if object_status is updated. If this issue persists, please contact support@goshippo.com");
			}
			transaction = retrieve(object_id);
			object_status = (String) transaction.object_status;
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

	public String getObject_state() {
		return object_state;
	}

	public void setObject_state(String object_state) {
		this.object_state = object_state;
	}

	public Object getObject_status() {
		return object_status;
	}

	public void setObject_status(String object_status) {
		this.object_status = object_status;
	}

	public Object getObject_created() {
		return object_created;
	}

	public void setObject_created(Object object_created) {
		this.object_created = object_created;
	}

	public Object getObject_updated() {
		return object_updated;
	}

	public void setObject_updated(Object object_updated) {
		this.object_updated = object_updated;
	}

	public String getObject_id() {
		return object_id;
	}

	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}

	public String getObject_owner() {
		return object_owner;
	}

	public void setObject_owner(String object_owner) {
		this.object_owner = object_owner;
	}

	public Object getWas_test() {
		return was_test;
	}

	public void setWas_test(Object was_test) {
		this.was_test = was_test;
	}

	public Object getRate() {
		return rate;
	}

	public void setRate(Object rate) {
		this.rate = rate;
	}

	public Object getTracking_number() {
		return tracking_number;
	}

	public void setTracking_number(Object tracking_number) {
		this.tracking_number = tracking_number;
	}

	public Object getTracking_status() {
		return tracking_status;
	}

	public void setTracking_status(Object tracking_status) {
		this.tracking_status = tracking_status;
	}

	public Object getTracking_url_provider() {
		return tracking_url_provider;
	}

	public void setTracking_url_provider(Object tracking_url_provider) {
		this.tracking_url_provider = tracking_url_provider;
	}

	public Object getLabel_url() {
		return label_url;
	}

	public void setLabel_url(Object label_url) {
		this.label_url = label_url;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public Object getCustoms_note() {
		return customs_note;
	}

	public void setCustoms_note(Object customs_note) {
		this.customs_note = customs_note;
	}

	public Object getSubmission_note() {
		return submission_note;
	}

	public void setSubmission_note(Object submission_note) {
		this.submission_note = submission_note;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

}