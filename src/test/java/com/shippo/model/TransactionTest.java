package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class TransactionTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Transaction testObject = (Transaction) getDefaultObject();
        assertEquals("VALID", testObject.getObject_state());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Transaction.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Transaction testObject = (Transaction) getDefaultObject();
        Transaction retrievedObject;

        retrievedObject = Transaction.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Transaction.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        TransactionCollection objectCollection = Transaction.all(null);
        assertNotNull(objectCollection.getCount());
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        TransactionCollection TransactionCollection = Transaction.all(objectMap);
        assertEquals(TransactionCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        RateCollection rateCollection = (RateCollection) RateTest.getDefaultObject();
        List<Rate> rateList = rateCollection.getData();

        objectMap.put("rate", rateList.get(0).getObject_id());
        objectMap.put("notification_email_from", "true");
        objectMap.put("notification_email_to", "false");
        objectMap.put("notification_email_other", "max@goshippo.com");
        objectMap.put("metadata", "Customer ID 123456");

        try {
            Transaction testObject = Transaction.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
