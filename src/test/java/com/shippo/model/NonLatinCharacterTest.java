package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.exception.ShippoException;

public class NonLatinCharacterTest extends ShippoTest {

    @Test
    public void testCharacterHandlin() {
        Address testObject = null;
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("object_purpose", "QUOTE");
        objectMap.put("name", "Πατατα");
        objectMap.put("company", "Shippo");
        objectMap.put("street1", "Clayton St.");
        objectMap.put("street_no", "215");
        objectMap.put("street2", null);
        objectMap.put("city", "San Francisco");
        objectMap.put("state", "CA");
        objectMap.put("zip", "94117");
        objectMap.put("country", "US");
        objectMap.put("phone", "+1 555 341 9393");
        objectMap.put("email", "test@goshipppo.com");
        objectMap.put("metadata", "Customer ID 123456");

        try {
            testObject = Address.create(objectMap);
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        assertEquals("Πατατα", testObject.getName());
    }
}
