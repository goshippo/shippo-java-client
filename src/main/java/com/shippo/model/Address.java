package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

// Class is affected by URL name inconsistencies, see APIResource.java (private static String className(Class<?> clazz))
public class Address extends APIResource {

	String object_state;
	String object_id;
	String object_purpose;
	String object_owner;
    Object object_source;
    Object object_created;
    Object object_updated;
    Object name;
    Object company;
    Object street1;
    Object street_no;
    Object street2;
    Object city;
    Object state;
    Object zip;
    Object country;
    Object phone;
    Object email;
    Object ip;
    Object metadata;
    Object messages;

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

    public String getObject_state() {
        return object_state;
    }

    public void setObject_state(String object_state) {
        this.object_state = object_state;
    }

    public String getObject_purpose() {
        return object_purpose;
    }

    public void setObject_purpose(String object_purpose) {
        this.object_purpose = object_purpose;
    }

    public Object getObject_source() {
        return object_source;
    }

    public void setObject_source(Object object_source) {
        this.object_source = object_source;
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

    public Object getStreet_no() {
        return street_no;
    }

    public void setStreet_no(Object street_no) {
        this.street_no = street_no;
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

    public Object getIp() {
        return ip;
    }

    public void setIp(Object ip) {
        this.ip = ip;
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
