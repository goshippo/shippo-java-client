package com.shippo.model;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
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

    // TODO: maybe use existing shipment class?
    public static class Shipment {
        // Either id or rest of attributes will exist
        String id;
        Address from;
        Address to;
        Parcel parcel;

        private Shipment(String id) {
            this.id = id;
        }

        public Shipment(Address from, Address to, Parcel parcel) {
            this.from = from;
            this.to = to;
        }

		@Override
		public String toString() {
			return "Shipment [id=" + id + ", from=" + from + ", to=" + to + ", parcel=" + parcel + ", carrierAccount="
					+ carrierAccount + ", serviceLevelToken=" + serviceLevelToken + "]";
		}
    }

    public static class ShipmentDeserializer implements JsonDeserializer<Shipment> {
        public Shipment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
            if (json instanceof JsonPrimitive) {
                Shipment s = new Shipment(((JsonPrimitive)json).getAsString());
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
        private Object messages;

        public static BatchShipment createForShipment(Shipment shipment, String carrierAccount, String serviceLevelToken) {
            BatchShipment bs = new BatchShipment();
            bs.shipment = shipment;
            bs.carrierAccount = carrierAccount;
            bs.serviceLevelToken = serviceLevelToken;
            return bs;
        }

		@Override
		public String toString() {
			return "BatchShipment [metadata=" + metadata + ", carrierAccount=" + carrierAccount + ", serviceLevelToken="
					+ serviceLevelToken + ", shipment=" + shipment + ", transaction=" + transaction + ", objectId="
					+ objectId + ", objectStatus=" + objectStatus + ", messages=" + messages + "]";
		}
    }

    public static class BatchShipmentCollection {
        private String next;
        private String previous;

        @SerializedName("results")
        private BatchShipment[] shipments;

		@Override
		public String toString() {
			return "BatchShipmentCollection [next=" + next + ", shipments=" + Arrays.toString(shipments) + "]";
		}
    }

    private BatchShipmentCollection batchShipments;

    public static class Counts {
        private int creationSucceeded;
        private int creationFailed;
        private int purchaseSucceeded;
        private int purchaseFailed;

        @Override
		public String toString() {
			return "Counts [creationSucceeded=" + creationSucceeded + ", creationFailed=" + creationFailed
					+ ", purchaseSucceeded=" + purchaseSucceeded + ", purchaseFailed=" + purchaseFailed + "]";
		}
    }

    private Counts objectResults;

    @SerializedName("label_url")
    private String[] labelURLs;

	@Override
	public String toString() {
		return "Batch [objectId=" + objectId + ", objectOwner=" + objectOwner + ", objectStatus=" + objectStatus
				+ ", objectCreated=" + objectCreated + ", objectUpdated=" + objectUpdated + ", metadata=" + metadata
				+ ", defaultCarrierAccount=" + defaultCarrierAccount + ", defaultServiceLevelToken="
				+ defaultServiceLevelToken + ", labelFileType=" + labelFileType + ", batchShipments=" + batchShipments
				+ ", objectResults=" + objectResults + ", labelURLs=" + Arrays.toString(labelURLs) + "]";
	}

    private static class BatchCollection {
        private int count;
        private String previous;
        private String next;
        @SerializedName("results") private Batch[] array;

        public Batch[] getBatchArray() {
            return array;
        }
    }

    /**
     * Get all batches created in the account.
     *
     * @return Array of Batch object
     */
    public static Batch[] all() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        BatchCollection coll = request(RequestMethod.GET, classURL(Batch.class), null, BatchCollection.class, null);
        return coll.getBatchArray();
    }

    /**
     * Create a batch. This function corresponds to POSTing to https://api.goshippo.com/batches endpoint
     * documented at https://goshippo.com/docs/reference#batches-create
     *
     * @param carrierAccount    Carrier account ID used if not provided in individual shipments
     * @param serviceLevelToken Associated service level token used if not provided in shipments
     * @param labelFileType     Print format of the label
     * @param metadata          Optional user content associated with the batch
     * @param shipments         Array of {@link Shipment} objects that will be added to the batch
     * @return                  The newly created Batch object
     */
    public static Batch create(String carrierAccount, String serviceLevelToken, LabelFileType labelFileType,
                               String metadata, BatchShipment[] shipments) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("default_carrier_account", carrierAccount);
        params.put("default_servicelevel_token", serviceLevelToken);
        params.put("label_filetype", labelFileType.toString());
        params.put("batch_shipments", shipments);
        return request(RequestMethod.POST, classURL(Batch.class), params, Batch.class, null);
    }

    public static enum ShipmentStatus {
        PURCHASE_SUCCEEDED("purchase_succeeded"),
        PURCHASE_FAILED("purchase_failed"),
        CREATION_SUCCEEDED("creation_succeeded"),
        CREATION_FAILED("creation_failed");

        private final String apiText;
        ShipmentStatus(String apiText) {
            this.apiText = apiText;
        }
        @Override
        public String toString() {
            return apiText;
        }
    }

    /**
     * Get batch based on its ID. Optionally filter shipments inside the batch using <code>page</code>
     * and <code>objectResults</code> parameter. This function corresponds to
     * https://api.goshippo.com/batches/<BATCH OBJECT ID> endpoint
     * documented at https://goshippo.com/docs/reference#batches-retrieve
     *
     * @param id            ID of batch to retrieve
     * @param page:         the page to return. Is ignored if it is 0.
     * @param objectResults filter shipments that have this status. Can be null if filtering is not required.
     * @return              The Batch object
     */
    public static Batch get(String id, int page, ShipmentStatus objectResults)
        throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> params = new HashMap<String, Object>();
        if (page != 0) {
            params.put("page", page);
        }
        if (objectResults != null) {
            params.put("object_results", objectResults.toString());
        }
        return request(RequestMethod.GET, instanceURL(Batch.class, id), params, Batch.class, null);
    }

    private static class BatchShipmentId {
        @SerializedName("shipment") private String id;
        public BatchShipmentId(String id) {
            this.id = id;
        }
    }

    /**
     * Add shipments to an existing batch provided by id. This takes shipment IDs which means shipments
     * should have been already been created using {@link Shipment.create}. This method corresponds
     * to https://api.goshippo.com/batches/<BATCH OBJECT ID>/add_shipments endpoint defined in
     * https://goshippo.com/docs/reference#batches-add-shipments
     *
     * @param shipmentIds   Array of shipment Ids to be added to the batch
     * @return              The Batch object after shipments have been added
     */
    public static Batch addShipments(String id, String[] shipmentIds)
        throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        BatchShipmentId[] array = new BatchShipmentId[shipmentIds.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new BatchShipmentId(shipmentIds[i]);
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("__list", array);
        return request(RequestMethod.POST,
                       instanceURL(Batch.class, id) + "/add_shipments",
                       params,
                       Batch.class,
                       null);
    }

    public static Batch removeShipments(String id,  String[] shipmentIds)
    { return null; }

    public static Batch purchase(String id)
    { return null; }

}
