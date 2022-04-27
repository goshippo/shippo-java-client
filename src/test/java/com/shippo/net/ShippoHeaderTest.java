package com.shippo.net;

import static org.junit.Assert.*;

import com.shippo.Shippo;
import com.shippo.model.Address;
import com.shippo.model.ShippoTest;
import java.util.Map;
import org.junit.Test;

public class ShippoHeaderTest extends ShippoTest {

	@Test
	public void testHeaders() {
		String apiKey = "testAPIKey";
		String apiVersion = "testAPIVersion";
		Shippo.setApiKey(apiKey);
		Shippo.setApiVersion(apiVersion);
		Map<String, String> headers = APIResource.getHeaders(null, Address.class);
		assertEquals(headers.get("Authorization"), String.format("ShippoToken %s", apiKey));
		assertEquals(headers.get("Accept"), "application/json");
		assertEquals(headers.get("Shippo-API-Version"), apiVersion);
	}
}