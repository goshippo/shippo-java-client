package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.List;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.Batch.BatchStatus;

public class BatchTest extends ShippoTest {

	final String id = "dd442663c24843068977704b1bd7d91d";

/*
	@Test 
	public void testAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException,
			UnsupportedEncodingException, MalformedURLException {
		Batch[] batches = Batch.all();
		Batch batch = null;
		for (Batch b : batches) {
			if (b.getId().equals(id)) {
				batch = b;
				break;
			}
		}
		assertEquals(batch.getStatus(), Batch.BatchStatus.VALID);
	}

	@Test
	public void testValidGet()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Batch batch = Batch.get(id, 0, null);
		assertEquals(batch.getStatus(), Batch.BatchStatus.VALID);
	}

	@Test
	public void testValidGetParams()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Batch batch = Batch.get(id, 1, Batch.ShipmentStatus.CREATION_SUCCEEDED);
		for (BatchShipment bs : batch.getBatchShipments().getShipments()) {
			if (bs.getStatus() != BatchShipment.Status.VALID) {
				fail(String.format("BatchShipment %s is not VALID. Its status is %s", bs.getId(), bs.getStatus()));
			}
		}
	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidGet()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Batch.get("invalid_id", 0, null);
	}

	private BatchShipment addShipment()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Shipment shipment = (Shipment) ShipmentTest.getDefaultObject();
		String[] ids = { (String) shipment.getObjectId() };
		Batch batch = Batch.addShipments(id, ids);
		for (BatchShipment bs : batch.getBatchShipments().getShipments()) {
			if (bs.getShipment().equals(ids[0])) {
				return bs;
			}
		}
		return null;
	}

	@Test
	public void testAddShipments()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		int beforeAddCount = Batch.get(id, 0, null).getBatchShipments().getCount();
		assertNotNull(addShipment());
		int afterAddCount = Batch.get(id, 0, null).getBatchShipments().getCount();
		assertEquals(beforeAddCount + 1, afterAddCount);
	}

	@Test
	public void testRemoveShipments()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		BatchShipment batchShipment = addShipment();
		String[] bsObjectIds = { batchShipment.getId() };
		int beforeRemoveCount = Batch.get(id, 0, null).getBatchShipments().getCount();
		Batch batch = Batch.removeShipments(id, bsObjectIds);
		for (BatchShipment bs : batch.getBatchShipments().getShipments()) {
			if (bs.getId().equals(bsObjectIds[0])) {
				fail(String.format("BatchShipment with object ID %s not removed from batch %s", bsObjectIds[0],
						batch.getId()));
			}
		}
		int afterRemoveCount = Batch.get(id, 0, null).getBatchShipments().getCount();
		assertEquals(beforeRemoveCount - 1, afterRemoveCount);
	}
*/
	private Batch createBatch()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Address from = Address.createForPurchase("Undefault New Wu", "Clayton St.", "San Francisco", "94117", "CA",
				"USA", "random@example.com");
		Address to = Address.createForPurchase("Yo", "965 Mission St", "San Francisco", "94103", "CA", "USA",
				"shippo@g.com");
		Parcel parcel = Parcel.createForShipment(2, 3, 3, "cm", 23, "g");
		List<Parcel> parcels = new ArrayList<Parcel>();
		parcels.add(parcel);
		Shipment shipment = Shipment.createForBatch(from, to, parcels);
		BatchShipment[] shipments = { BatchShipment.createForShipment(shipment, null, null) };
		return Batch.create("09a25c72f0df461ea8fea8b755356aaf", "usps_priority", LabelFileType.PDF, null, shipments);
	}
/*
	@Test
	public void testCreate()
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		assertEquals(createBatch().getStatus(), Batch.BatchStatus.VALIDATING);
	}
*/
	@Test
	public void testPurchase() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			APIException, TimeoutException {
		String batchId = createBatch().getId();
		waitForBatchStatus(batchId, Batch.BatchStatus.VALID, 60);
		Batch batch = Batch.purchase(batchId);
		assertEquals(batch.getStatus(), Batch.BatchStatus.PURCHASING);
	}

	private void waitForBatchStatus(String batchId, BatchStatus status, int times) throws TimeoutException,
			AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		while (times > 0) {
			Batch batch = Batch.get(batchId, 0, null);
			if (batch.getStatus().equals(status)) {
				return;
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}

			times--;
		}
		throw new TimeoutException(String.format("Timed out waiting for batch %s to become %s", batchId, status));
	}
}