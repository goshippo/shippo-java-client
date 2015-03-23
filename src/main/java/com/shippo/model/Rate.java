package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class Rate extends APIResource {
	String object_state;
	String object_status;
	String object_purpose;
	String object_id;
	String object_owner;
    Object object_created;
    Object object_updated;
    Object attributes;
    Object amount_local;
    Object currency_local;
    Object amount;
    Object currency;
    Object provider;
    Object provider_image_75;
    Object provider_image_200;
    Object servicelevel_name;
    Object servicelevel_terms;
    Object days;
    Object duration_terms;
    Object trackable;
    Object insurance;
    Object insurance_amount_local;
    Object insurance_currency_local;
    Object insurance_amount;
    Object insurance_currency;
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

    public String getObject_state() {
        return object_state;
    }

    public void setObject_state(String object_state) {
        this.object_state = object_state;
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

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public Object getAmount_local() {
        return amount_local;
    }

    public void setAmount_local(Object amount_local) {
        this.amount_local = amount_local;
    }

    public Object getCurrency_local() {
        return currency_local;
    }

    public void setCurrency_local(Object currency_local) {
        this.currency_local = currency_local;
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

    public Object getProvider_image_75() {
        return provider_image_75;
    }

    public void setProvider_image_75(Object provider_image_75) {
        this.provider_image_75 = provider_image_75;
    }

    public Object getProvider_image_200() {
        return provider_image_200;
    }

    public void setProvider_image_200(Object provider_image_200) {
        this.provider_image_200 = provider_image_200;
    }

    public Object getServicelevel_name() {
        return servicelevel_name;
    }

    public void setServicelevel_name(Object servicelevel_name) {
        this.servicelevel_name = servicelevel_name;
    }

    public Object getServicelevel_terms() {
        return servicelevel_terms;
    }

    public void setServicelevel_terms(Object servicelevel_terms) {
        this.servicelevel_terms = servicelevel_terms;
    }

    public Object getDays() {
        return days;
    }

    public void setDays(Object days) {
        this.days = days;
    }

    public Object getDuration_terms() {
        return duration_terms;
    }

    public void setDuration_terms(Object duration_terms) {
        this.duration_terms = duration_terms;
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

    public Object getInsurance_amount_local() {
        return insurance_amount_local;
    }

    public void setInsurance_amount_local(Object insurance_amount_local) {
        this.insurance_amount_local = insurance_amount_local;
    }

    public Object getInsurance_currency_local() {
        return insurance_currency_local;
    }

    public void setInsurance_currency_local(Object insurance_currency_local) {
        this.insurance_currency_local = insurance_currency_local;
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

    public Object getMessages() {
        return messages;
    }

    public void setMessages(Object messages) {
        this.messages = messages;
    }

}
