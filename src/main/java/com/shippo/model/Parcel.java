package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class Parcel extends APIResource {

	String objectState;
	String objectStatus;
	String objectId;
	String objectOwner;
	Object objectCreated;
	Object objectUpdated;
    Object length;
    Object width;
    Object height;
    Object distanceUnit;
    Object weight;
    Object massUnit;
    Object metadata;

    public static Parcel createForShipment(double length, double width, double height, DistanceUnit distanceUnit,
                                           double weight, MassUnit massUnit) {
        Parcel p = new Parcel();
        p.length = length;
        p.width = width;
        p.height = height;
        p.distanceUnit = (Object)distanceUnit.toString();
        p.weight = weight;
        p.massUnit = (Object)massUnit.toString();
        return p;
    }

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

	public Object getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(Object distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public Object getWeight() {
		return weight;
	}

	public void setWeight(Object weight) {
		this.weight = weight;
	}

	public Object getMassUnit() {
		return massUnit;
	}

	public void setMassUnit(Object massUnit) {
		this.massUnit = massUnit;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

}
