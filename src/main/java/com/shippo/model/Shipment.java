package com.shippo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.RequestTimeoutException;
import com.shippo.net.APIResource;


public class Shipment extends APIResource {

	String status;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
	Object addressFrom;
	Object addressTo;
	Object addressReturn;
	Object parcel;
	Object shipmentDate;
	Object extra;
	Object customsDeclaration;
	Object ratesUrl;
	Object metadata;
	Object messages;
	List<Rate> rates;

    public static Shipment createForBatch(Address from, Address to, Parcel parcel) {
        Shipment s = new Shipment();
        s.addressFrom = from;
        s.addressTo = to;
        s.parcel = parcel;
        return s;
    }

	public static Shipment create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return create(params, null);
	}

	public String getInstanceURL() {
		return "";
	}

	public static Shipment create(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(Shipment.class), params,
				Shipment.class, apiKey);
	}

	public static RateCollection getShippingRatesSync(String object_id)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, RequestTimeoutException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", object_id);
		params.put("currency_code", "");
		return getShippingRatesSync(params, null);
	}

	public static RateCollection getShippingRatesSync(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, RequestTimeoutException {
		return getShippingRatesSync(params, null);
	}

	public static RateCollection getShippingRatesSync(
			Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException, RequestTimeoutException {
		String object_id = (String) params.get("id");
		Shipment shipment = retrieve(object_id);
		String status = shipment.status;
		long startTime = System.currentTimeMillis();

		while (status.equals("QUEUED")
				|| status.equals("WAITING")) {
			if (System.currentTimeMillis() - startTime > Shippo.RATES_REQ_TIMEOUT) {
				throw new RequestTimeoutException(
						"A timeout has occured while waiting for your rates to generate. Try retreiving the Shipment object again and check if status is updated. If this issue persists, please contact support@goshippo.com");
			}
			shipment = retrieve(object_id);
			status = (String) shipment.status;
		}

		return Shipment.getShippingRates(params, apiKey);

	}

	public static Shipment retrieve(String id) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static Shipment retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Shipment.class, id),
				null, Shipment.class, apiKey);
	}

	public static ShipmentCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return all(params, null);
	}

	public static ShipmentCollection all(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, classURL(Shipment.class), params,
				ShipmentCollection.class, apiKey);
	}

	public static RateCollection getShippingRates(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return getShippingRates(params, null);
	}

	public static RateCollection getShippingRates(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Shipment.class, id)
				+ "/rates/", null, RateCollection.class, apiKey);
	}

	public static RateCollection getShippingRates(String id,
			String currency_code, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Shipment.class, id)
				+ "/rates/" + currency_code, null, RateCollection.class, apiKey);
	}

	public static RateCollection getShippingRates(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET,
				instanceURL(Shipment.class, (String) params.get("id"))
						+ "/rates/" + params.get("currency_code"), null,
				RateCollection.class, apiKey);
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

	public Object getObjectUpdated() {
		return objectUpdated;
	}

	public void setObjectUpdated(Object objectUpdated) {
		this.objectUpdated = objectUpdated;
	}

	public Object getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(Object addressFrom) {
		this.addressFrom = addressFrom;
	}

	public Object getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(Object addressTo) {
		this.addressTo = addressTo;
	}

	public Object getAddressReturn() {
		return addressReturn;
	}

	public void setAddressReturn(Object addressReturn) {
		this.addressReturn = addressReturn;
	}

	public Object getParcel() {
		return parcel;
	}

	public void setParcel(Object parcel) {
		this.parcel = parcel;
	}

	public Object getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Object shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public Object getExtra() {
		return extra;
	}

	public void setExtra(Object extra) {
		this.extra = extra;
	}

	public Object getCustomsDeclaration() {
		return customsDeclaration;
	}

	public void setCustomsDeclaration(Object customsDeclaration) {
		this.customsDeclaration = customsDeclaration;
	}

	public Object getRatesUrl() {
		return ratesUrl;
	}

	public void setRatesUrl(Object ratesUrl) {
		this.ratesUrl = ratesUrl;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

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
}
