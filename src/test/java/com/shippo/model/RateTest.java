package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

public class RateTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        RateCollection testObject = (RateCollection) getDefaultObject();
        assertNotNull(testObject.getCount());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Rate.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        RateCollection objectCollection = Rate.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        RateCollection RateCollection = Rate.all(objectMap);
        assertEquals(RateCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Shipment testObject = (Shipment) ShipmentTest.getDefaultObject();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("id", testObject.getObjectId());
        objectMap.put("currency_code", "USD");
        objectMap.put("async", false);
        try {
            Shipment.getShippingRates(objectMap);
            // Allow five seconds to pass for server rates generation
            return Shipment.getShippingRates(objectMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
