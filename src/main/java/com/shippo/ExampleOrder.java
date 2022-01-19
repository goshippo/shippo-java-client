package com.shippo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

import com.shippo.exception.ShippoException;
import com.shippo.model.Order;

public class ExampleOrder {

	public static void main(String[] args) throws ShippoException {
		
		// replace with your Shippo Token
		// don't have one? get more info here (https://goshippo.com/docs/#overview)
		Shippo.setApiKey("shippo_test_cf1b6d0655e59fc6316880580765066038ef20d8");
		Shippo.setApiVersion("2018-02-08");

		// Optional defaults to false
		//Shippo.setDEBUG(true);

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
        orderParams.put("to_address", toAddressMap);
        orderParams.put("from_address", fromAddressMap);
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

		Order order = Order.create(orderParams);
		System.out.println(String.format("An order has been created: %s", order.getObjectId()));
	}
}
