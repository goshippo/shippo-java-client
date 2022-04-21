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
import com.shippo.exception.ShippoException;

import org.junit.Test;

public class ParcelTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Parcel testObject = createParcelFixture();
        assertEquals("VALID", testObject.getObjectState());
		assertEquals(Shippo.apiKeyIsTest, testObject.isTest());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Parcel.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Parcel testObject = createParcelFixture();
        Parcel retrievedObject;

        retrievedObject = Parcel.retrieve((String) testObject.objectId);
        assertEquals(testObject.objectId, retrievedObject.objectId);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Parcel.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        ParcelCollection objectCollection = Parcel.all(null);
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "2"); // two results per page
        objectMap.put("page", "1"); // the first page of results
        ParcelCollection ParcelCollection = Parcel.all(objectMap);
        assertEquals(ParcelCollection.getData().size(), 2);
    }

    public static Parcel createParcelFixture() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("length", "5");
        objectMap.put("width", "5");
        objectMap.put("height", "5");
        objectMap.put("distance_unit", "cm");
        objectMap.put("weight", "2");
        objectMap.put("mass_unit", "lb");
        objectMap.put("template", null);
        objectMap.put("metadata", "Customer ID 123456");

        try {
            return Parcel.create(objectMap);
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
