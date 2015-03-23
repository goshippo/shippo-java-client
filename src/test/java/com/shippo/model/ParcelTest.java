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

public class ParcelTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Parcel testObject = (Parcel) getDefaultObject();
        assertEquals("VALID", testObject.getObject_state());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Parcel.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Parcel testObject = (Parcel) getDefaultObject();
        Parcel retrievedObject;

        retrievedObject = Parcel.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

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
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        ParcelCollection ParcelCollection = Parcel.all(objectMap);
        assertEquals(ParcelCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
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
            Parcel testObject = Parcel.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
