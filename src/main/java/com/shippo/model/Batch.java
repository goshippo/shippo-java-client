package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;


public final class Batch extends APIResource {

    private String objectId;
    private String objectOwner;

    public static enum BatchStatus {
        VALIDATING, VALID, INVALID, PURCHASING, PURCHASED
    }

    private BatchStatus objectStatus;
    private Date objectCreated;
    private Date objectUpdated;
    private String metadata;
    private String defaultCarrierAccount;
    private String defaultServiceLevelToken;

    public static enum LabelFileType {
        PNG, PDF, PDF_4X6, ZPLII 
    }

    private LabelFileType labelFileType;

    public static class Shipment {
        // Either id or rest of attributes will exist
        String id;
        Address from;
        Address to;
        Parcel parcel;
        String carrierAccount;
        String serviceLevelToken;

        public Shipment(Address from, Address to, Parcel parcel, String carrierAccount, 
                        String serviceLevelToken) {
            this.from = from;
            this.to = to;
            this.carrierAccount = carrierAccount;
            this.serviceLevelToken = serviceLevelToken;
        }
    }

    public static class ShipmentSerializer implements JsonSerializer<Shipment> {
        public Shipment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            if (json instanceof JsonPrimitive) {
                Shipment s = new Shipment();
                s.id = (JsonPrimitive)json.getAsString();
                return s;
            } 
            return GSON.fromJson(json, Shipment.class);
        }
    }
    
    public static class BatchShipment {

        private String metadata;
        private String carrierAccount;
        private String serviceLevelToken;
        private Shipment shipment;
        private String transaction;
        private String objectId;
        private String objectStatus;
        private String[] messages;

        public static BatchShipment createForShipment(Shipment shipment) {
            BatchShipment bs = new BatchShipment();
            bs.shipment = shipment;
            return bs;
        }
    }

    public static class BatchShipments {
        private String next;
        private Sting previous;

        @SerializedName("results")
        private BatchShipments[] shipments;
    }

    private BatchShipments batchShipments;

    public static class Counts {
        private int creationSucceeded;
        private int creationFailed;
        private int purchaseSucceeded;
        private int purchaseFailed;
    }
        
    private Counts objectResults;

    @SerializedName("label_url")
    private String[] labelURLs;

    public static Batch[] all()
    { }

    public static Batch create(String defaultCarrierAccount, String defaultServiceLevelToken, LabelFileType labelFileType,
                               String metadata, BatchShipment[] shipments)
    { }

    public static enum ShipmentStatus {
        PURCHASE_SUCCEEDED, PURCHASE_FAILED, CREATION_SUCCEEDED, CREATION_FAILED
    }

    public static Batch get(String id, Integer page, ShipmentStatus objectResults) {
        return request(RequestMethod.GET, instanceURL(Batch.class, id), null, Batch.class, null);
    }

    public static Batch addShipments(String id, String[] shipmentIds)
    { }

    public static Batch removeShipments(String id,  String[] shipmentIds)
    { }

    public static Batch purchase(String id)
    { }
}
