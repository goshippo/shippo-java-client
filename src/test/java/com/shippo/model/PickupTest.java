package com.shippo.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

import org.junit.Ignore;
import org.junit.Test;

public class PickupTest extends ShippoTest {
	/*
	 * Creating a pickup currently has (at least) 3 problems
	 * 1. it gets an ERROR status with no message explaining the reason
	 * 2. it returns an `is_test` attribute instead of the standard `test` attribute
	 * 3. the value of `is_test` is false, even when using a test token
	 * Example response:
	 * {
	 * "object_created": "2022-04-22T14:47:17.141Z",
	 * "object_updated": "2022-04-22T14:47:17.629Z",
	 * "object_id": "7867924238494f89bbd24c0bba2fb387",
	 * "carrier_account": "09a25c72f0df461ea8fea8b755356aaf",
	 * "location": {
	 * "instructions": null,
	 * "building_location_type": "Knock on door",
	 * "building_type": null,
	 * "address": {
	 * "object_id": "ccc16f4d9b3c4cec8226cd83f129ce64",
	 * "name": "Undefault New Wu",
	 * "company": "Shippo",
	 * "street1": "Clayton St.",
	 * "street2": "",
	 * "city": "San Francisco",
	 * "state": "CA",
	 * "zip": "94117",
	 * "country": "US",
	 * "phone": "0015553419393",
	 * "email": "test@goshipppo.com"
	 * }
	 * },
	 * "transactions": [
	 * "f352587c9e6846e28fd08eaadb371065"
	 * ],
	 * "requested_start_time": "2022-04-23T16:45:55.815Z",
	 * "requested_end_time": "2022-04-24T16:45:55.815Z",
	 * "confirmed_start_time": null,
	 * "confirmed_end_time": null,
	 * "cancel_by_time": null,
	 * "status": "ERROR",
	 * "confirmation_code": null,
	 * "timezone": "US/Pacific",
	 * "messages": null,
	 * "metadata": "",
	 * "is_test": false
	 * }
	 */
	@Test
	@Ignore("Creating a pickup does not seem to respect test mode")
	public void testValidCreate() throws ShippoException {
		Pickup testObject = createPickupFixture();
		assertEquals("SUCCESS", testObject.getStatus());
	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
			APIException {
		Shipment.create(getInvalidObjectMap());
	}

	public static Pickup createPickupFixture() throws ShippoException {
		RateCollection rateCollection = RateTest.createRateCollectionFixture();
		List<Rate> rateList = rateCollection.getData();
		Rate selectedRate = selectTestRate(rateList);

		String carrier_account = (String) selectedRate.getCarrierAccount();

		Map<String, Object> transParams = new HashMap<String, Object>();
		transParams.put("rate", selectedRate.getObjectId());
		transParams.put("async", false);
		List<String> transactions = new ArrayList<String>();
		Transaction transaction = Transaction.createSync(transParams);
		transactions.add(transaction.getObjectId());

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime startTime = now.plusDays(1);
		LocalDateTime endTime = now.plusDays(2);

		Address addressFrom = AddressTest.createAddressFixture1();
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
		return Pickup.create(pickupParams);
	}
}
