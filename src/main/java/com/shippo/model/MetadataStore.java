package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

/**
 * Common interface for Shippo objects that can store metadata.
 */
public interface MetadataStore<T> {
    Map<String, String> getMetadata();

    void setMetadata(Map<String, String> metadata);

    MetadataStore<T> update(Map<String, Object> params) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException;

    MetadataStore<T> update(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException;
}
