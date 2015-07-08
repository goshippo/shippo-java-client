package com.shippo;

import java.util.HashMap;
import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.RequestTimeoutException;
import com.shippo.exception.ShippoException;
import com.shippo.model.Address;
import com.shippo.model.AddressCollection;
import com.shippo.model.CarrierAccount;
import com.shippo.model.Rate;
import com.shippo.model.RateCollection;
import com.shippo.model.Shipment;
import com.shippo.model.Transaction;

public class Example {

	public static void main(String[] args) throws ShippoException {
		
		// replace with your Shippo Token
		// don't have one? get more info here (https://goshippo.com/docs/#overview)
		Shippo.setApiKey("924279d5bda97eff28529f264eb14d2646dd3d94");

		// Optional defaults to false
		//Shippo.setDEBUG(true);

		// to address
		Map<String, Object> toAddressMap = new HashMap<String, Object>();
		toAddressMap.put("object_purpose", "PURCHASE");
		toAddressMap.put("name", "Shippo Itle");
		toAddressMap.put("company", "Shippo");
		toAddressMap.put("street1", "215 Clayton St.");
		toAddressMap.put("city", "San Francisco");
		toAddressMap.put("state", "CA");
		toAddressMap.put("zip", "94117");
		toAddressMap.put("country", "US");
		toAddressMap.put("phone", "+1 555 341 9393");
		toAddressMap.put("email", "laura@goshipppo.com");

		// from address
		Map<String, Object> fromAddressMap = new HashMap<String, Object>();
		fromAddressMap.put("object_purpose", "PURCHASE");
		fromAddressMap.put("name", "Mr Hippo");
		fromAddressMap.put("company", "San Diego Zoo");
		fromAddressMap.put("street1", "2920 Zoo Drive");
		fromAddressMap.put("city", "San Diego");
		fromAddressMap.put("state", "CA");
		fromAddressMap.put("zip", "92101");
		fromAddressMap.put("country", "US");
		fromAddressMap.put("email", "hippo@goshipppo.com");
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

		Map<String, Object> shipmentMap = new HashMap<String, Object>();
		shipmentMap.put("address_to", toAddressMap);
		shipmentMap.put("address_from", fromAddressMap);
		shipmentMap.put("parcel", parcelMap);
		shipmentMap.put("object_purpose", "PURCHASE");

		Map<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("carrier", "dhl_express");
		accountMap.put("account_id", "test_java");
		accountMap.put("parameters", "{}");
		for (CarrierAccount account : CarrierAccount.all(accountMap).getData()){
			System.out.println(account);
			System.out.println(account.getObjectId());
			System.out.println(account.getAccountId());
		}
		System.out.println();
		System.out.println(CarrierAccount.create(accountMap));
		// create Shipment object
		System.out.println("Creating Shipment object..");
		Shipment shipment = Shipment.create(shipmentMap);
//
//		// get shipping rates
//		System.out.println(String.format("Generating rates for shipment %s", shipment.getObject_id()));
//		RateCollection rates = Shipment.getShippingRatesSync(shipment.getObject_id());
//
//
//		System.out.println(String.format("Obtainned %d rates for shipment %s ", rates.getCount(), shipment.getObject_id()));;
//		Rate rate = rates.getData().get(0);
//
//		System.out.println("Getting shipping label..");
//		Map<String, Object> transParams = new HashMap<String, Object>();
//		transParams.put("rate", rate.getObject_id());
//		Transaction transaction = Transaction.createSync(transParams);
//
//		if (transaction.getObject_status().equals("SUCCESS")) {
//			System.out.println(String.format("Label url : %s", transaction.getLabel_url()));
//			System.out.println(String.format("Tracking number : %s", transaction.getTracking_number()));
//		} else {
//			System.out.println(String.format("An Error has occured while generating you label. Messages : %s", transaction.getMessages()));
//		}
	}
}
