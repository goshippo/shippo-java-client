package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class CustomsItem extends APIResource {

	String objectState;
	String objectStatus;
	String object_purpose;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
    Object description;
    Object quantity;
    Object netWeight;
    Object massUnit;
    Object valueAmount;
    Object valueCurrency;
    Object originCountry;
    Object tariffNumber;
    Object skuCode;
    Object eccnEar99;
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

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
	}

	public String getObjectStatus() {
		return objectStatus;
	}

	public void setObjectStatus(String objectStatus) {
		this.objectStatus = objectStatus;
	}

	public String getObject_purpose() {
		return object_purpose;
	}

	public void setObject_purpose(String object_purpose) {
		this.object_purpose = object_purpose;
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

	public Object getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Object netWeight) {
		this.netWeight = netWeight;
	}

	public Object getMassUnit() {
		return massUnit;
	}

	public void setMassUnit(Object massUnit) {
		this.massUnit = massUnit;
	}

	public Object getValueAmount() {
		return valueAmount;
	}

	public void setValueAmount(Object valueAmount) {
		this.valueAmount = valueAmount;
	}

	public Object getValueCurrency() {
		return valueCurrency;
	}

	public void setValueCurrency(Object valueCurrency) {
		this.valueCurrency = valueCurrency;
	}

	public Object getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(Object originCountry) {
		this.originCountry = originCountry;
	}

	public Object getTariffNumber() {
		return tariffNumber;
	}

	public void setTariffNumber(Object tariffNumber) {
		this.tariffNumber = tariffNumber;
	}

    public Object getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Object skuCode) {
        this.skuCode = skuCode;
    }

	public Object getEccnEar99() {
	    return eccnEar99;
	}

	public void setEccnEar99(Object eccnEar99) {
	    this.eccnEar99 = eccnEar99;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}


}
