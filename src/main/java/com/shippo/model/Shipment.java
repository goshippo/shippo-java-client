package com.shippo.model;

import java.util.HashMap;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.RequestTimeoutException;
import com.shippo.net.APIResource;

public class Shipment extends APIResource {

    String object_state;
    String object_status;
    String object_purpose;
    String object_id;
    String object_owner;
    Object object_created;
    Object object_updated;
    Object address_from;
    Object address_to;
    Object address_return;
    Object parcel;
    Object submission_type;
    Object submission_date;
    Object insurance_amount;
    Object insurance_currency;
    Object extra;
    Object customs_declaration;
    Object reference_1;
    Object reference_2;
    Object rates_url;
    Object metadata;
    Object messages;

    public static Shipment create(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public String getInstanceURL() {
        return "";
    }

    public static Shipment create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Shipment.class), params, Shipment.class, apiKey);
    }

    public static RateCollection getShippingRatesSync(String object_id) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException, RequestTimeoutException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", object_id);
        params.put("currency_code", "");
        return getShippingRatesSync(params, null);
    }

    public static RateCollection getShippingRatesSync(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException, RequestTimeoutException {
        return getShippingRatesSync(params, null);
    }

    public static RateCollection getShippingRatesSync(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException,
            RequestTimeoutException {
        String object_id = (String) params.get("id");
        Shipment shipment = retrieve(object_id);
        String object_status = (String) shipment.object_status;
        long startTime = System.currentTimeMillis();

        while (object_status.equals("QUEUED") || object_status.equals("WAITING")) {
            if (System.currentTimeMillis() - startTime > Shippo.RATES_REQ_TIMEOUT) {
                throw new RequestTimeoutException(
                        "A timeout has occured while waiting for your rates to generate. Try retreiving the Shipment object again and check if object_status is updated. If this issue persists, please contact support@goshippo.com");
            }
            shipment = retrieve(object_id);
            object_status = (String) shipment.object_status;
        }

        return Shipment.getShippingRates(params, apiKey);

    }

    public static Shipment retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static Shipment retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Shipment.class, id), null, Shipment.class, apiKey);
    }

    public static ShipmentCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static ShipmentCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Shipment.class), params, ShipmentCollection.class, apiKey);
    }

    public static RateCollection getShippingRates(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return getShippingRates(params, null);
    }

    public static RateCollection getShippingRates(String id, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Shipment.class, id) + "/rates/", null, RateCollection.class,
                apiKey);
    }

    public static RateCollection getShippingRates(String id, String currency_code, String apiKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Shipment.class, id) + "/rates/" + currency_code, null,
                RateCollection.class, apiKey);
    }

    public static RateCollection getShippingRates(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET,
                instanceURL(Shipment.class, (String) params.get("id")) + "/rates/" + params.get("currency_code"), null,
                RateCollection.class, apiKey);
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

    public Object getObject_purpose() {
        return object_purpose;
    }

    public void setObject_purpose(String object_purpose) {
        this.object_purpose = object_purpose;
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

    public Object getAddress_from() {
        return address_from;
    }

    public void setAddress_from(Object address_from) {
        this.address_from = address_from;
    }

    public Object getAddress_to() {
        return address_to;
    }

    public void setAddress_to(Object address_to) {
        this.address_to = address_to;
    }

    public Object getAddress_return() {
        return address_return;
    }

    public void setAddress_return(Object address_return) {
        this.address_return = address_return;
    }

    public Object getParcel() {
        return parcel;
    }

    public void setParcel(Object parcel) {
        this.parcel = parcel;
    }

    public Object getSubmission_type() {
        return submission_type;
    }

    public void setSubmission_type(Object submission_type) {
        this.submission_type = submission_type;
    }

    public Object getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Object submission_date) {
        this.submission_date = submission_date;
    }

    public Object getInsurance_amount() {
        return insurance_amount;
    }

    public void setInsurance_amount(Object insurance_amount) {
        this.insurance_amount = insurance_amount;
    }

    public Object getInsurance_currency() {
        return insurance_currency;
    }

    public void setInsurance_currency(Object insurance_currency) {
        this.insurance_currency = insurance_currency;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public Object getCustoms_declaration() {
        return customs_declaration;
    }

    public void setCustoms_declaration(Object customs_declaration) {
        this.customs_declaration = customs_declaration;
    }

    public Object getReference_1() {
        return reference_1;
    }

    public void setReference_1(Object reference_1) {
        this.reference_1 = reference_1;
    }

    public Object getReference_2() {
        return reference_2;
    }

    public void setReference_2(Object reference_2) {
        this.reference_2 = reference_2;
    }

    public Object getRates_url() {
        return rates_url;
    }

    public void setRates_url(Object rates_url) {
        this.rates_url = rates_url;
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
