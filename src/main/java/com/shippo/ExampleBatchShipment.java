package com.shippo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.shippo.exception.ShippoException;
import com.shippo.model.Address;
import com.shippo.model.Batch;
import com.shippo.model.Batch.BatchShipmentCollection;
import com.shippo.model.Batch.BatchStatus;
import com.shippo.model.BatchShipment;
import com.shippo.model.LabelFileType;
import com.shippo.model.Parcel;
import com.shippo.model.Shipment;

public class ExampleBatchShipment {

	public static void main(String[] args) throws ShippoException {

		// replace with your Shippo Token
		// don't have one? get more info here (https://goshippo.com/docs/#overview)
		Shippo.setApiKey("<API-KEY>");
		Shippo.setApiVersion("2018-02-08");
		// default carrier account
		String defaultCarrierAccount = "<YOUR CARRIER ACCOUNT PRIVATE KEY>";

		// Optional defaults to false
		// Shippo.setDEBUG(true);

		// to address
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("name", "Mr Hippo");
		toAddressMap.put("company", "Shippo");
		toAddressMap.put("street1", "215 Clayton St.");
		toAddressMap.put("city", "San Francisco");
		toAddressMap.put("state", "CA");
		toAddressMap.put("zip", "94117");
		toAddressMap.put("country", "US");
		toAddressMap.put("phone", "+1 555 341 9393");
		toAddressMap.put("email", "mrhippo@goshipppo.com");
		Address toAddress = Address.create(toAddressMap);

		// from address
		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("name", "Ms Hippo");
		fromAddressMap.put("company", "San Diego Zoo");
		fromAddressMap.put("street1", "2920 Zoo Drive");
		fromAddressMap.put("city", "San Diego");
		fromAddressMap.put("state", "CA");
		fromAddressMap.put("zip", "92101");
		fromAddressMap.put("country", "US");
		fromAddressMap.put("email", "mshippo@goshipppo.com");
		fromAddressMap.put("phone", "+1 619 231 1515");
		fromAddressMap.put("metadata", "Customer ID 123456");
		Address fromAddress = Address.create(fromAddressMap);

		// parcel
		Map<String, Object> parcelMap = new HashMap<String, Object>();
		parcelMap.put("length", "5");
		parcelMap.put("width", "5");
		parcelMap.put("height", "5");
		parcelMap.put("distance_unit", "in");
		parcelMap.put("weight", "2");
		parcelMap.put("mass_unit", "lb");
		Parcel parcel = Parcel.create(parcelMap);
		List<Parcel> parcels = new ArrayList<Parcel>();
		parcels.add(parcel);

		// shipment
		Shipment firstShipment = Shipment.createForBatch(fromAddress, toAddress, parcels);
		BatchShipment[] batchShipments = { BatchShipment.createForShipment(firstShipment, null, null) };

		// batch shipment
		String serviceLevelToken = "usps_priority";
		System.out.println("Creating first shipment..");
		Batch batch = Batch.create(defaultCarrierAccount, serviceLevelToken, LabelFileType.PDF, null, batchShipments);
		if (batch.getStatus().equals(BatchStatus.VALID) || batch.getStatus().equals(BatchStatus.VALIDATING)) {
			System.out.println(String.format("Batch created with id: %s", batch.getId()));
		} else {
			System.out.println(String.format("An Error has occured while creating the first batch shipment."));
			System.exit(0);
		}

		// 2nd to address
		Map<String, Object> toAddress2Map = new HashMap<String, Object>();
		toAddress2Map.put("name", "Mrs Hippo");
		toAddress2Map.put("company", "");
		toAddress2Map.put("street1", "Broadway 1");
		toAddress2Map.put("city", "New York");
		toAddress2Map.put("state", "NY");
		toAddress2Map.put("zip", "10007");
		toAddress2Map.put("country", "US");
		toAddress2Map.put("phone", "4151234567");
		toAddress2Map.put("email", "mrshippo@goshipppo.com");

		// 2nd from address
		Map<String, Object> fromAddress2Map = new HashMap<String, Object>();
		fromAddress2Map.put("name", "Mr Hippo");
		fromAddress2Map.put("company", "");
		fromAddress2Map.put("street1", "965 Mission St");
		fromAddress2Map.put("street2", "Ste 201");
		fromAddress2Map.put("city", "San Francisco");
		fromAddress2Map.put("state", "CA");
		fromAddress2Map.put("zip", "94103");
		fromAddress2Map.put("country", "US");
		fromAddress2Map.put("email", "mrhippo@goshippo.com");
		fromAddress2Map.put("phone", "4151234567");

		// 2nd parcel
		Map<String, Object> parcel2Map = new HashMap<String, Object>();
		parcel2Map.put("length", "5");
		parcel2Map.put("width", "5");
		parcel2Map.put("height", "5");
		parcel2Map.put("distance_unit", "in");
		parcel2Map.put("weight", "15");
		parcel2Map.put("mass_unit", "oz");
		List<Map<String, Object>> parcels2 = new ArrayList<Map<String, Object>>();
		parcels2.add(parcel2Map);

		// 2nd shipment
		Map<String, Object> secondShipmentMap = new HashMap<String, Object>();
		secondShipmentMap.put("address_to", toAddressMap);
		secondShipmentMap.put("address_from", fromAddressMap);
		secondShipmentMap.put("parcels", parcels2);
		Shipment shipment2 = Shipment.create(secondShipmentMap);

		if (shipment2.getStatus().equals("SUCCESS")) {
			System.out.println(String.format("Shipment created with id: %s", shipment2.getObjectId()));
		} else {
			System.out.println(String.format("An Error has occured while creating the 2nd shipment. Messages : %s",
					shipment2.getMessages()));
			System.exit(0);
		}

		// Example of retrieving a batch object to check its validation status
		// For complete reference to the retrieve endpoint:
		// https://goshippo.com/docs/reference#batches-retrieve
		// This method of polling the batch validation status is for demo purposes only
		// In practice it is advised to use the batch-create webhook in the user api
		// dashboard: https://app.goshippo.com/api/
		int maxTimeout = 10;
		int counter = 0;
		while (counter < maxTimeout) {
			Batch retrievedBatch = Batch.get(batch.getId(), 0, null);
			if (retrievedBatch.getStatus().equals(BatchStatus.VALID)) {
				break;
			} else {
				counter++;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Batch retrievedBatch2 = Batch.get(batch.getId(), 0, null);
		if (retrievedBatch2.getStatus().equals(BatchStatus.VALID)) {
			System.out.println(String.format("Batch object %s has status %s ", retrievedBatch2.getId(),
					retrievedBatch2.getStatus()));
		} else {
			System.out.println(String.format("An Error has occured validating the batch."));
			System.exit(0);
		}

		// example adding a shipment to batch
		String[] shipmentsToAdd = { shipment2.getObjectId() };
		Batch addedShipment = Batch.addShipments(batch.getId(), shipmentsToAdd);
		if (addedShipment.getStatus().equals(BatchStatus.VALID)) {
			System.out.println(
					String.format("Batch now contains shipments : %s ", addedShipment.getBatchShipments().toString()));
		} else {
			System.out.println(String.format("An Error has occured adding a shipment."));
			System.exit(0);
		}

		// example removing a shipment from batch
		BatchShipmentCollection batchCollection = addedShipment.getBatchShipments();
		BatchShipment[] batchShipment = batchCollection.getShipments();
		String[] shipmentsToRemove = { batchShipment[0].getId().toString() };
		Batch removedShipment = Batch.removeShipments(batch.getId(), shipmentsToRemove);
		if (removedShipment.getStatus().equals(BatchStatus.VALID)) {
			System.out.println(String.format("Batch now contains shipments : %s ",
					removedShipment.getBatchShipments().toString()));
		} else {
			System.out.println(String.format("An Error has occured removing a shipment."));
			System.exit(0);
		}

		// purchase batch shipment
		Batch.purchase(batch.getId());
		while (counter < maxTimeout) {
			Batch retrievedBatch3 = Batch.get(batch.getId(), 0, null);
			if (retrievedBatch3.getStatus().equals(BatchStatus.PURCHASED)) {
				break;
			} else {
				counter++;
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		Batch retrievedBatch4 = Batch.get(batch.getId(), 0, null);
		if (retrievedBatch4.getStatus().equals(BatchStatus.PURCHASED)) {
			System.out.println(String.format("Batch object %s has status %s ", retrievedBatch4.getId(),
					retrievedBatch4.getStatus()));
		} else {
			System.out.println(String.format("An Error has occured purchasing the batch shipment."));
			System.exit(0);
		}
	}
}
