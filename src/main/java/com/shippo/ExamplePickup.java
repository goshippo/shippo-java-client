package com.shippo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import com.shippo.exception.ShippoException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.model.CustomsDeclaration;
import com.shippo.model.Shipment;
import com.shippo.model.Rate;
import com.shippo.model.Transaction;
import com.shippo.model.Pickup;

public class ExamplePickup {

	public static void main(String[] args) throws ShippoException {

		// replace with your Shippo Token
		// don't have one? get more info here (https://goshippo.com/docs/#overview)
		Shippo.setApiKey("<API-KEY>");
		Shippo.setApiVersion("2018-02-08");

		// Optional defaults to false
		// Shippo.setDEBUG(true);

		// to address
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("name", "Mr Hippo");
		toAddressMap.put("company", "Regents Park");
		toAddressMap.put("street1", "Outer Cir");
		toAddressMap.put("city", "London");
		toAddressMap.put("state", null);
		toAddressMap.put("zip", "NW1 4RY");
		toAddressMap.put("country", "GB");
		toAddressMap.put("phone", "+1 555 341 9393");
		toAddressMap.put("email", "ms-hippo@goshipppo.com");
		toAddressMap.put("is_residential", false);
		toAddressMap.put("metadata", "For Order Number 123");

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

		// parcel
		Map<String, Object> parcelMap = new HashMap<String, Object>();
		parcelMap.put("length", "5");
		parcelMap.put("width", "5");
		parcelMap.put("height", "5");
		parcelMap.put("distance_unit", "in");
		parcelMap.put("weight", "2");
		parcelMap.put("mass_unit", "lb");
		List<Map<String, Object>> parcels = new ArrayList<Map<String, Object>>();
		parcels.add(parcelMap);

		// customs item
		Map<String, Object> customsItemMap = new HashMap<String, Object>();
		customsItemMap.put("description", "T-Shirt");
		customsItemMap.put("quantity", "2");
		customsItemMap.put("net_weight", "400");
		customsItemMap.put("mass_unit", "g");
		customsItemMap.put("value_amount", "20");
		customsItemMap.put("value_currency", "USD");
		customsItemMap.put("origin_country", "US");
		customsItemMap.put("tariff_number", "");
		List<Map<String, Object>> customsItem = new ArrayList<Map<String, Object>>();
		customsItem.add(customsItemMap);

		// customs declaration
		Map<String, Object> customsDeclarationMap = new HashMap<String, Object>();
		customsDeclarationMap.put("contents_type", "MERCHANDISE");
		customsDeclarationMap.put("contents_explanation", "T-Shirt purchase");
		customsDeclarationMap.put("non_delivery_option", "RETURN");
		customsDeclarationMap.put("certify", "true");
		customsDeclarationMap.put("certify_signer", "Mr Hippo");
		customsDeclarationMap.put("items", customsItem);
		System.out.println("Creating customs declaration...");
		CustomsDeclaration customsDeclaration = CustomsDeclaration.create(customsDeclarationMap);

		Map<String, Object> shipmentMap = new HashMap<String, Object>();
		shipmentMap.put("address_to", toAddressMap);
		shipmentMap.put("address_from", fromAddressMap);
		shipmentMap.put("parcels", parcels);
		shipmentMap.put("customs_declaration", customsDeclaration.getObjectId());
		shipmentMap.put("async", false);
		System.out.println("Creating shipment...");
		Shipment shipment = Shipment.create(shipmentMap);

		// select shipping rate for USPS or DHL express
		List<Rate> rates = shipment.getRates();
		List<Rate> filteredRates = new ArrayList<Rate>();
		for (Rate rate : rates) {
			String rateString = ((String) rate.getProvider()).toUpperCase();
			if (rateString.contains("USPS") || rateString.contains("DHL Express")) {
				filteredRates.add(rate);
			}
		}
		if (filteredRates.size() == 0) {
			System.out
					.println("Unable to find a carrier for pickup, check USPS and/or DHL Express is active. Messages.");
			System.exit(0);
		}
		Rate rate = filteredRates.get(0);
		String carrier_account = (String) rate.getCarrierAccount();

		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("rate", rate.getObjectId());
		transParams.put("async", false);
		System.out.println("Purchasing shipment...");
		Transaction transaction = Transaction.create(transParams);
		List<String> transactions = new ArrayList<String>();
		transactions.add(transaction.getObjectId());

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startTime = now.plusDays(3);
		LocalDateTime endTime = now.plusDays(4);

		Map<String, Object> locationParams = new HashMap<String, Object>();
		locationParams.put("building_location_type", "Knock on Door");
		locationParams.put("address", fromAddressMap);

		Map<String, Object> pickupParams = new HashMap<String, Object>();
		pickupParams.put("carrier_account", carrier_account);
		pickupParams.put("transactions", transactions);
		pickupParams.put("location", locationParams);
		pickupParams.put("requested_start_time", String.join("", startTime.toString(), "Z"));
		pickupParams.put("requested_end_time", String.join("", endTime.toString(), "Z"));
		pickupParams.put("is_test", false);
		try {
			System.out.println("Scheduling pickup...");
			Pickup pickup = Pickup.create(pickupParams);
			System.out.println("A pickup has been scheduled for today");
		} catch (InvalidRequestException e) {
			System.out.println("A pickup has already been scheduled for today");
		}
	}
}
