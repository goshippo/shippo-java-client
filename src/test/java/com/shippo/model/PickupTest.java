package com.shippo.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.time.LocalDateTime;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class PickupTest extends ShippoTest {
	/**
	 * Intentionally commented out as this test could result in a purchase of a
	 * non test label depending on your carrier account settings
	 *
	 * To use this test, please make sure that test mode is enabled for the
	 * default rate object used
	 *
	 * @Test
	 *       public void testValidCreate() {
	 *       try {
	 *       Pickup testObject = (Pickup) getDefaultObject();
	 *       assertEquals("SUCCESS", testObject.getStatus());
	 *       } catch (InvalidRequestException e) {
	 *       assertTrue(true);
	 *       } catch (Exception e){
	 *       assertTrue(false);
	 *       }
	 *       }
	 **/

	@Test(expected = InvalidRequestException.class)
	public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			APIException {
		Shipment.create(getInvalidObjectMap());
	}

	public static Object getDefaultObject() throws InvalidRequestException {
		Map<String, Object> objectMap = new HashMap<String, Object>();
		RateCollection rateCollection = (RateCollection) RateTest
				.getDefaultObject();
		List<Rate> rateList = rateCollection.getData();
		objectMap.put("rate", rateList.get(0).getObjectId());
		objectMap.put("metadata", "Customer ID 123456");

		List<Rate> filteredRates = new ArrayList<Rate>();
		for (Rate rate : rateList) {
			String rateString = ((String) rate.getProvider()).toUpperCase();
			if (rateString.contains("USPS") || rateString.contains("DHL Express")) {
				filteredRates.add(rate);
			}
		}
		if (filteredRates.size() == 0) {
			return null;
		}
		Rate rate = filteredRates.get(0);
		String carrier_account = (String) rate.getCarrierAccount();

		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("rate", rate.getObjectId());
		transParams.put("async", false);

		List<String> transactions = new ArrayList<String>();
		try {
			Transaction transaction = Transaction.createSync(transParams);
			transactions.add(transaction.getObjectId());
		} catch (ShippoException e) {
			e.printStackTrace();
			return null;
		}

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startTime = now.plusDays(1);
		LocalDateTime endTime = now.plusDays(2);

		Address addressFrom = (Address) AddressTest.getDefaultObject();
		Map<String, Object> locationParams = new HashMap<String, Object>();
		locationParams.put("building_location_type", "Knock on Door");
		locationParams.put("address", addressFrom);

		Map<String, Object> pickupParams = new HashMap<String, Object>();
		pickupParams.put("carrier_account", carrier_account);
		pickupParams.put("transactions", transactions);
		pickupParams.put("location", locationParams);
		pickupParams.put("requested_start_time", String.join("", startTime.toString(), "Z"));
		pickupParams.put("requested_end_time", String.join("", endTime.toString(), "Z"));
		pickupParams.put("is_test", false);
		try {
			Pickup pickup = Pickup.create(pickupParams);
			return pickup;
		} catch (InvalidRequestException e) {
			throw new InvalidRequestException("Pickup already scheduled.", null, e);
		} catch (ShippoException e) {
			e.printStackTrace();
		}
		return null;
	}
}
