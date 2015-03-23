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

public class ManifestTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Manifest testObject = (Manifest) getDefaultObject();
        assertEquals("NOTRANSACTIONS", testObject.getObject_status());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Manifest.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Manifest testObject = (Manifest) getDefaultObject();
        Manifest retrievedObject;

        retrievedObject = Manifest.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Manifest.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        ManifestCollection objectCollection = Manifest.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        ManifestCollection ManifestCollection = Manifest.all(objectMap);
        assertEquals(ManifestCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        Address testAddress = (Address) AddressTest.getDefaultObject();

        objectMap.put("provider", "USPS");
        objectMap.put("submission_date", "2014-05-16T23:59:59Z");
        objectMap.put("address_from", testAddress.getObject_id());

        try {
            Manifest testObject = Manifest.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
