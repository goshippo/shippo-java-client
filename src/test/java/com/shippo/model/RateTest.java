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
        assertNotNull(testObject.getData());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Rate.retrieve("invalid_id");
    }

    public static Object getDefaultObject() {
        Shipment testObject = (Shipment) ShipmentTest.getDefaultObject();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("id", testObject.getObjectId());
        objectMap.put("currency_code", "USD");
        try {
            Shipment.getShippingRates(objectMap);
            // Allow five seconds to pass for server rates generation
            Thread.sleep(5000);
            return Shipment.getShippingRates(objectMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
