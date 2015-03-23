package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class CustomsItem extends APIResource {

	String object_state;
	String object_id;
	String object_owner;
    Object object_created;
    Object object_updated;
    Object description;
    Object quantity;
    Object net_weight;
    Object mass_unit;
    Object value_amount;
    Object value_currency;
    Object origin_country;
    Object tariff_number;
    Object metadata;

    public static CustomsItem create(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return create(params, null);
    }

    public String getInstanceURL() {
        return "";
    }

    public static CustomsItem create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(CustomsItem.class), params, CustomsItem.class, apiKey);
    }

    public static CustomsItem retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static CustomsItem retrieve(String id, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(CustomsItem.class, id), null, CustomsItem.class, apiKey);
    }

    public static CustomsItemCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static CustomsItemCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(CustomsItem.class), params, CustomsItemCollection.class, apiKey);
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

    public String getObject_state() {
        return object_state;
    }

    public void setObject_state(String object_state) {
        this.object_state = object_state;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getQuantity() {
        return quantity;
    }

    public void setQuantity(Object quantity) {
        this.quantity = quantity;
    }

    public Object getNet_weight() {
        return net_weight;
    }

    public void setNet_weight(Object net_weight) {
        this.net_weight = net_weight;
    }

    public Object getMass_unit() {
        return mass_unit;
    }

    public void setMass_unit(Object mass_unit) {
        this.mass_unit = mass_unit;
    }

    public Object getValue_amount() {
        return value_amount;
    }

    public void setValue_amount(Object value_amount) {
        this.value_amount = value_amount;
    }

    public Object getValue_currency() {
        return value_currency;
    }

    public void setValue_currency(Object value_currency) {
        this.value_currency = value_currency;
    }

    public Object getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(Object origin_country) {
        this.origin_country = origin_country;
    }

    public Object getTariff_number() {
        return tariff_number;
    }

    public void setTariff_number(Object tariff_number) {
        this.tariff_number = tariff_number;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

}
