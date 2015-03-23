package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class Parcel extends APIResource {

    String object_state;
    String object_status;
    String object_purpose;
    String object_id;
    String object_owner;
    Object object_created;
    Object object_updated;
    Object length;
    Object width;
    Object height;
    Object distance_unit;
    Object weight;
    Object mass_unit;
    Object metadata;

    public static Parcel create(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return create(params, null);
    }

    public String getInstanceURL() {
        return "";
    }

    public static Parcel create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(Parcel.class), params, Parcel.class, apiKey);
    }

    public static Parcel retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static Parcel retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Parcel.class, id), null, Parcel.class, apiKey);
    }

    public static ParcelCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static ParcelCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Parcel.class), params, ParcelCollection.class, apiKey);
    }

    public String getObject_state() {
        return object_state;
    }

    public void setObject_state(String object_state) {
        this.object_state = object_state;
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

    public Object getLength() {
        return length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public Object getDistance_unit() {
        return distance_unit;
    }

    public void setDistance_unit(Object distance_unit) {
        this.distance_unit = distance_unit;
    }

    public Object getWeight() {
        return weight;
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public Object getMass_unit() {
        return mass_unit;
    }

    public void setMass_unit(Object mass_unit) {
        this.mass_unit = mass_unit;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

}
