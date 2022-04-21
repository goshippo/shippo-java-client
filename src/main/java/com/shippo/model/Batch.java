package com.shippo.model;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;
import com.shippo.util.HttpUtil;

/**
 * Represents <code>/batches</code> endpoint documented at
 * https://goshippo.com/docs/reference#batches
 */
public final class Batch extends APIResource {

	@SerializedName("object_id")
	private String id;

	@SerializedName("object_owner")
	private String owner;

	public static enum BatchStatus {
		VALIDATING, VALID, INVALID, PURCHASING, PURCHASED
	}

	@SerializedName("status")
	private BatchStatus status;

	@SerializedName("object_created")
	private Date created;

	@SerializedName("object_updated")
	private Date updated;

	@SerializedName("metadata")
	private String metadata;

	@SerializedName("default_carrier_account")
	private String defaultCarrierAccount;

	@SerializedName("default_servicelevel_token")
	private String defaultServiceLevelToken;

	@SerializedName("label_filetype")
	private LabelFileType labelFileType;

	Boolean test;

	public static class BatchShipmentCollection {
		private int count;
		private String next;
		private String previous;

		@SerializedName("results")
		private BatchShipment[] shipments;

		@Override
		public String toString() {
			return "BatchShipmentCollection [next=" + next + ", shipments=" + Arrays.toString(shipments) + "]";
		}

		public String getNext() {
			return next;
		}

		public String getPrevious() {
			return previous;
		}

		public BatchShipment[] getShipments() {
			return shipments;
		}

		public int getCount() {
			return count;
		}
	}

	@SerializedName("batch_shipments")
	private BatchShipmentCollection batchShipments;

	@SerializedName("label_url")
	private String[] labelURLs;

	public static class Counts {

		@SerializedName("creation_succeeded")
		private int creationSucceeded;

		@SerializedName("creation_failed")
		private int creationFailed;

		@SerializedName("purchase_succeeded")
		private int purchaseSucceeded;

		@SerializedName("purchase_failed")
		private int purchaseFailed;

		@Override
		public String toString() {
			return "Counts [creationSucceeded=" + creationSucceeded + ", creationFailed=" + creationFailed
					+ ", purchaseSucceeded=" + purchaseSucceeded + ", purchaseFailed=" + purchaseFailed + "]";
		}

		public int getCreationSucceeded() {
			return creationSucceeded;
		}

		public int getCreationFailed() {
			return creationFailed;
		}

		public int getPurchaseSucceeded() {
			return purchaseSucceeded;
		}

		public int getPurchaseFailed() {
			return purchaseFailed;
		}
	}

	@SerializedName("object_results")
	private Counts objectResults;

	@Override
	public String toString() {
		return "Batch [id=" + id + ", owner=" + owner + ", status=" + status + ", created=" + created + ", updated="
				+ updated + ", metadata=" + metadata + ", defaultCarrierAccount=" + defaultCarrierAccount
				+ ", defaultServiceLevelToken=" + defaultServiceLevelToken + ", labelFileType=" + labelFileType
				+ ", batchShipments=" + batchShipments + ", labelURLs=" + Arrays.toString(labelURLs)
				+ ", objectResults=" + objectResults + "]";
	}

	private static class BatchCollection {
		private String next;
		@SerializedName("results")
		private Batch[] array;
	}

	/**
	 * Get all batches created in the account.
	 *
	 * @return Array of Batch object
	 */
	public static Batch[] all() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			APIException, UnsupportedEncodingException, MalformedURLException {
		List<Batch> all_batches = new ArrayList<Batch>();
		Map<String, String> params = new HashMap<String, String>();
		while (true) {
			Map<String, Object> reqParams = new HashMap<String, Object>(params);
			BatchCollection coll = request(RequestMethod.GET, classURL(Batch.class), reqParams, BatchCollection.class,
					null);
			if (coll.array.length == 0) {
				break;
			}
			all_batches.addAll(Arrays.asList(coll.array));
			if (coll.next == null) {
				break;
			}
			params = HttpUtil.queryToParams(new URL(coll.next).getQuery());
		}
		Batch[] array = new Batch[all_batches.size()];
		all_batches.toArray(array);
		return array;
	}

	/**
	 * Create a batch. This function corresponds to POSTing to
	 * https://api.goshippo.com/batches endpoint documented at
	 * https://goshippo.com/docs/reference#batches-create
	 *
	 * @param carrierAccount
	 *            Carrier account ID used if not provided in individual
	 *            shipments
	 * @param serviceLevelToken
	 *            Associated service level token used if not provided in
	 *            shipments
	 * @param labelFileType
	 *            Print format of the label
	 * @param metadata
	 *            Optional user content associated with the batch
	 * @param shipments
	 *            Array of {@link BatchShipment} objects that will be added to
	 *            the batch
	 * @return The newly created Batch object
	 */
	public static Batch create(String carrierAccount, String serviceLevelToken, LabelFileType labelFileType,
			String metadata, BatchShipment[] shipments)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("default_carrier_account", carrierAccount);
		params.put("default_servicelevel_token", serviceLevelToken);
		params.put("label_filetype", labelFileType.toString());
		params.put("batch_shipments", shipments);
		return request(RequestMethod.POST, classURL(Batch.class), params, Batch.class, null);
	}

	public static enum ShipmentStatus {
		PURCHASE_SUCCEEDED("purchase_succeeded"), PURCHASE_FAILED("purchase_failed"), CREATION_SUCCEEDED(
				"creation_succeeded"), CREATION_FAILED("creation_failed");

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
	 * Get batch based on its ID. Optionally filter shipments inside the batch
	 * using <code>page</code> and <code>objectResults</code> parameter. This
	 * function corresponds to https://api.goshippo.com/batches/<i>BATCH OBJECT ID</i>
	 * endpoint documented at https://goshippo.com/docs/reference#batches-retrieve
	 *
	 * @param id
	 *            ID of batch to retrieve
	 * @param page:
	 *            the page to return. Is ignored if it is 0.
	 * @param objectResults
	 *            filter shipments that have this status. Can be null if
	 *            filtering is not required.
	 * @return The Batch object
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
		@SerializedName("shipment")
		private String id;

		public BatchShipmentId(String id) {
			this.id = id;
		}
	}

	/**
	 * Add shipments to an existing batch provided by id. This takes shipment
	 * IDs which means shipments should have been already been created using
	 * {@link Shipment#create}. This method corresponds to
	 * https://api.goshippo.com/batches/<i>BATCH OBJECT ID</i>/add_shipments endpoint
	 * defined in https://goshippo.com/docs/reference#batches-add-shipments
	 *
	 * @param id
	 *            Batch object ID
	 * @param shipmentIds
	 *            Array of shipment Ids to be added to the batch
	 * @return The Batch object after shipments have been added
	 */
	public static Batch addShipments(String id, String[] shipmentIds)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		BatchShipmentId[] array = new BatchShipmentId[shipmentIds.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = new BatchShipmentId(shipmentIds[i]);
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("__list", array);
		return request(RequestMethod.POST, instanceURL(Batch.class, id) + "/add_shipments", params, Batch.class, null);
	}

	/**
	 * Remove shipments to an existing batch provided by id. This method
	 * corresponds to https://api.goshippo.com/batches/<i>BATCH OBJECT ID</i>/remove_shipments
	 * endpoint defined in https://goshippo.com/docs/reference#batches-remove-shipments
	 *
	 * @param id
	 *            Batch object ID
	 * @param shipmentIds
	 *            Array of shipment Ids to be removed from the batch
	 * @return The Batch object after shipments have been removed
	 */
	public static Batch removeShipments(String id, String[] shipmentIds)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("__list", shipmentIds);
		return request(RequestMethod.POST, instanceURL(Batch.class, id) + "/remove_shipments", params, Batch.class,
				null);
	}

	/**
	 * Purchase batch provided by id. This method corresponds to
	 * https://api.goshippo.com/batches/<i>BATCH OBJECT ID</i>/purchase endpoint
	 * defined in https://goshippo.com/docs/reference#batches-purchase
	 *
	 * @param id
	 *            Batch object ID
	 * @return The Batch object after purchasing
	 */
	public static Batch purchase(String id)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.POST, instanceURL(Batch.class, id) + "/purchase", null, Batch.class, null);
	}

	public String getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public BatchStatus getStatus() {
		return status;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getMetadata() {
		return metadata;
	}

	public String getDefaultCarrierAccount() {
		return defaultCarrierAccount;
	}

	public String getDefaultServiceLevelToken() {
		return defaultServiceLevelToken;
	}

	public LabelFileType getLabelFileType() {
		return labelFileType;
	}

	public BatchShipmentCollection getBatchShipments() {
		return batchShipments;
	}

	public String[] getLabelURLs() {
		return labelURLs;
	}

	public Counts getObjectResults() {
		return objectResults;
	}

}
