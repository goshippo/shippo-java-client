package com.shippo.serialization;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.shippo.model.Address;
import com.shippo.model.Pickup;
import com.shippo.model.ShippoTest;

import org.junit.Test;

public class GsonTest extends ShippoTest {

    @Test
    public void deserializeBooleanShouldBeFalse_whenOmittedFromPayload() {
        // GIVEN an Address payload that does not include the "test" attribute
        String payload = "{\"object_created\": \"2022-04-21T14:55:08.082Z\", \"object_updated\": \"2022-04-21T14:55:08.091Z\", \"object_id\": \"7835b6e686cd4974af071584eecb623e\", \"is_complete\": true, \"validation_results\": {}, \"object_owner\": \"lucas+java@goshippo.com\", \"name\": \"Undefault New Wu\", \"company\": \"Shippo\", \"street_no\": \"215\", \"street1\": \"Clayton St.\", \"street2\": \"\", \"street3\": \"\", \"city\": \"San Francisco\", \"state\": \"CA\", \"zip\": \"94117\", \"country\": \"US\", \"longitude\": null, \"latitude\": null, \"phone\": \"0015553419393\", \"email\": \"test@goshipppo.com\", \"is_residential\": false, \"metadata\": \"Customer ID 123456\"}";

        // WHEN
        Address address = GsonFactory.getGson(Address.class).fromJson(payload, Address.class);

        // THEN
        assertFalse(address.isTest());
    }

    @Test
    public void deserialize_is_test_to_test() throws IOException {
        // GIVEN a Pickup payload with is_test = true
        String payload = getResourceFileAsString("pickup_response_body.json");

        // WHEN
        Pickup pickup = GsonFactory.getGson(Pickup.class).fromJson(payload, Pickup.class);

        // THEN
        assertTrue(pickup.isTest());
    }

    static String getResourceFileAsString(String fileName) {
        return new Scanner(GsonTest.class.getResourceAsStream(fileName), "UTF-8").useDelimiter("\\A").next();
    }
}
