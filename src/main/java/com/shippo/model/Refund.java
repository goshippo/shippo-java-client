package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.DuplicateRefundRequestException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class Refund extends APIResource {
	String object_state;
	String status;
	String object_purpose;
	String object_id;
	String object_owner;
    Object object_created;
    Object object_updated;
    Object transaction;

    public static Refund create(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            DuplicateRefundRequestException, APIConnectionException, APIException {
        return create(params, null);
    }

    public String getInstanceURL() {
        return "";
    }

    public static Refund create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, DuplicateRefundRequestException, APIConnectionException, APIException {
        try {
            return request(RequestMethod.POST, classURL(Refund.class), params, Refund.class, apiKey);
        } catch (InvalidRequestException ex) {
            if(ex.getMessage().contains(DuplicateRefundRequestException.refundMessagePattern)) {
                throw new DuplicateRefundRequestException(ex.getMessage(), ex.getParam(), ex);
            }
            throw ex;
        }
    }

    public static Refund retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static Refund retrieve(String id, String apiKey) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(Refund.class, id), null, Refund.class, apiKey);
    }

    public static RefundCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static RefundCollection all(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(Refund.class), params, RefundCollection.class, apiKey);
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

    public Object getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getTransaction() {
        return transaction;
    }

    public void setTransaction(Object transaction) {
        this.transaction = transaction;
    }
}
