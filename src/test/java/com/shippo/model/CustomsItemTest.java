package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class CustomsItemTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        CustomsItem testObject = (CustomsItem) getDefaultObject();
        assertEquals(testObject.getObjectState(), "VALID");
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsItem.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsItem testObject = (CustomsItem) getDefaultObject();
        CustomsItem retrievedObject;

        retrievedObject = CustomsItem.retrieve((String) testObject.objectId);
        assertEquals(testObject.objectId, retrievedObject.objectId);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsItem.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsItemCollection objectCollection = CustomsItem.all(null);
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        CustomsItemCollection CustomsItemCollection = CustomsItem.all(objectMap);
        assertEquals(CustomsItemCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("description", "T-Shirt");
        objectMap.put("quantity", "2");
        objectMap.put("net_weight", "400");
        objectMap.put("mass_unit", "g");
        objectMap.put("value_amount", "20");
        objectMap.put("value_currency", "USD");
        objectMap.put("tariff_number", null);
        objectMap.put("origin_country", "US");
        objectMap.put("metadata", "Order ID #123123");

        try {
            CustomsItem testObject = CustomsItem.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}