/**
 * 
 */
package com.shippo.model;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a shipment inside a {@link Batch}
 *
 */
public final class BatchShipment {
	
    @SerializedName("object_id")
    private String id;
    
    public static enum Status {
		INVALID, VALID, INCOMPLETE, TRANSACTION_FAILED
    }

    @SerializedName("object_status")
    private Status status;

    private String carrierAccount;
    private String serviceLevelToken;
    // shipment will be either String containing shipment ID when retrieving or Shipment object
    // when creating batch
    private Object shipment;
    private String transaction;
    private Object messages;
    private String metadata;

    public static BatchShipment createForShipment(Shipment shipment, String carrierAccount, String serviceLevelToken) {
        BatchShipment bs = new BatchShipment();
        bs.shipment = shipment;
        bs.carrierAccount = carrierAccount;
        bs.serviceLevelToken = serviceLevelToken;
        return bs;
    }

	@Override
	public String toString() {
		return "BatchShipment [id=" + id + ", status=" + status + ", carrierAccount=" + carrierAccount
				+ ", serviceLevelToken=" + serviceLevelToken + ", shipment=" + shipment + ", transaction="
				+ transaction + ", messages=" + messages + ", metadata=" + metadata + "]";
	}

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public String getCarrierAccount() {
		return carrierAccount;
	}

	public String getServiceLevelToken() {
		return serviceLevelToken;
	}
    /*
     * Returned object will be either String containing shipment ID when retrieving or 
     * {@link Shipment} object
     */
	public Object getShipment() {
		return shipment;
	}

	public String getTransaction() {
		return transaction;
	}

	public Object getMessages() {
		return messages;
	}

	public String getMetadata() {
		return metadata;
	}
}
