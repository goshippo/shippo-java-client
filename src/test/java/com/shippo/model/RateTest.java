package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;

import org.junit.Test;

public class RateTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        RateCollection testObject = createRateCollectionFixture();
        assertNotNull(testObject.getData());
        for (Rate rate : testObject.getData()) {
            assertEquals(Shippo.apiKeyIsTest, rate.isTest());
        }
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Rate.retrieve("invalid_id");
    }

    public static RateCollection createRateCollectionFixture() {
        Shipment testObject = (Shipment) ShipmentTest.createShipmentFixture();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("id", testObject.getObjectId());
        objectMap.put("currency_code", "USD");
        objectMap.put("async", false);
        try {
            return Shipment.getShippingRates(objectMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
