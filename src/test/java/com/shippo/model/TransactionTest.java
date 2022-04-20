package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class TransactionTest extends ShippoTest {

	@Test
	public void testValidCreate() {
		Transaction testObject = createTransactionFixture();
		assertEquals("SUCCESS", testObject.getStatus());
	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidCreate() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Transaction.create(getInvalidObjectMap());
	}

	@Test
	public void testRetrieve() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Transaction testObject = createTransactionFixture();
		Transaction retrievedObject;

		retrievedObject = Transaction.retrieve((String) testObject.objectId);
		assertEquals(testObject.objectId, retrievedObject.objectId);

	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidRetrieve() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Transaction.retrieve("invalid_id");
	}

	@Test
	public void testListAll() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		TransactionCollection objectCollection = Transaction.all(null);
		assertNotNull(objectCollection.getData());
	}

	@Test
	public void testListPageSize() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> objectMap = new HashMap<String, Object>();
		objectMap.put("results", "1"); // one result per page
		objectMap.put("page", "1"); // the first page of results
		TransactionCollection TransactionCollection = Transaction
				.all(objectMap);
		assertEquals(TransactionCollection.getData().size(), 1);
	}

	public static Transaction createTransactionFixture() {
		Map<String, Object> objectMap = new HashMap<String, Object>();
		RateCollection rateCollection = RateTest.createRateCollectionFixture();
		List<Rate> rateList = rateCollection.getData();

		Rate selectedRate = selectTestRate(rateList);

		objectMap.put("rate", selectedRate.getObjectId());
		objectMap.put("metadata", "Customer ID 123456");

		try {
			return Transaction.createSync(objectMap);
		} catch (ShippoException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Make sure we get a test rate.  We are using a test auth token, so it should not be possible to get a non-test rate back,
	// but previous author was uncertain, and I don't know enough about the underlying implementation to be sure.
	private static Rate selectTestRate(List<Rate> rateList) {
		return rateList.stream().filter(new Predicate<Rate>() {
			@Override
			public boolean test(Rate rate) {
				return rate.test != null && rate.test == true;
			}
		}).findAny().orElseThrow();
	}
}
