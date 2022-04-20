package com.shippo.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class OrderTest extends ShippoTest {

	@Test
	public void testValidCreate() {
		Order testObject = (Order) getDefaultObject();
		assertEquals("PAID", testObject.getOrderStatus().toString());
	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidCreate() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Order.create(getInvalidObjectMap());
	}

	@Test
	public void testRetrieve() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Order testObject = (Order) getDefaultObject();
		Order retrievedObject = Order.retrieve((String) testObject.getObjectId());
		assertEquals(testObject.getObjectId(), retrievedObject.getObjectId());
	}

	@Test(expected = InvalidRequestException.class)
	public void testInvalidRetrieve() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Order.retrieve("invalid_id");
	}

	@Test
	public void testListAll() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		OrderCollection objectCollection = Order.all(null);
		assertNotNull(objectCollection.getData());
	}

	@Test
	public void testListPageSize() throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> objectMap = new HashMap<String, Object>();
		objectMap.put("results", "1"); // one result per page
		objectMap.put("page", "1"); // the first page of results
		OrderCollection OrderCollection = Order
				.all(objectMap);
		assertEquals(OrderCollection.getData().size(), 1);
	}

	public static Object getDefaultObject() {
		Address addressFrom = AddressTest.createAddressFixture1();
		Address addressTo = AddressTest.createAddressFixture2();

		Map<String, Object> lineItem = new HashMap<String, Object>();
		lineItem.put("title", "Demo Line Item Object");
		lineItem.put("sku", "demo_1234");
		lineItem.put("quantity", 2);
		lineItem.put("total_price", 2.34);
		lineItem.put("currency", "USD");
		lineItem.put("weight", 25.45);
		lineItem.put("weight_unit", "lb");
		lineItem.put("manufacture_country", "US");
		List<Map<String, Object>> lineItems = new ArrayList<Map<String, Object>>();
		lineItems.add(lineItem);

		LocalDateTime now = LocalDateTime.now();
		Map<String, Object> orderParams = new HashMap<String, Object>();
		orderParams.put("name", "Ms Hippo");
		orderParams.put("order_number", now.toString());
		orderParams.put("order_status", "PAID");
		orderParams.put("to_address", addressTo);
		orderParams.put("from_address", addressFrom);
		orderParams.put("line_items", lineItems);
		orderParams.put("placed_at", String.join("", now.toString(), "Z"));
		orderParams.put("weight", 10.0);
		orderParams.put("weight_unit", "lb");
		orderParams.put("shipping_method", "ground");
		orderParams.put("shipping_cost", 1.23);
		orderParams.put("shipping_cost_currency", "USD");
		orderParams.put("subtotal_price", 2.34);
		orderParams.put("total_price", 6.14);
		orderParams.put("total_tax", 2.57);
		orderParams.put("currency", "USD");

		try {
			Order testObject = Order.create(orderParams);
			return testObject;
		} catch (ShippoException e) {
			e.printStackTrace();
		}
		return null;
	}
}
