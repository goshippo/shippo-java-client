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
import com.shippo.exception.ShippoException;

public class ShipmentTest extends ShippoTest {

    @Test
    public void testValidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Shipment testObject = (Shipment) getDefaultObject();
        assertEquals("VALID", testObject.getObject_state());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Shipment.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Shipment testObject = (Shipment) getDefaultObject();
        Shipment retrievedObject;

        retrievedObject = Shipment.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Shipment.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        ShipmentCollection objectCollection = Shipment.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        ShipmentCollection ShipmentCollection = Shipment.all(objectMap);
        assertEquals(ShipmentCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        // Create prerequisite helper objects
        Address addressFrom = (Address) AddressTest.getDefaultObject();
        Address addressTo = (Address) AddressTest.getDefaultObject();
        Parcel parcel = (Parcel) ParcelTest.getDefaultObject();
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("object_purpose", "QUOTE");
        objectMap.put("address_from", addressFrom.getObject_id());
        objectMap.put("address_to", addressTo.getObject_id());
        objectMap.put("parcel", parcel.getObject_id());
        objectMap.put("submission_type", "PICKUP");
        objectMap.put("submission_date", "2013-12-03T12:00:00.000Z");
        objectMap.put("insurance_amount", "30");
        objectMap.put("insurance_currency", "USD");
        objectMap.put("extra", "{signature_confirmation: true}");
        objectMap.put("customs_declaration", null);
        objectMap.put("reference_1", null);
        objectMap.put("reference_2", null);
        objectMap.put("metadata", "Customer ID 123456");

        try {
            Shipment testObject = Shipment.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
