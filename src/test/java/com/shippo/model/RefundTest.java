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

public class RefundTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Refund testObject = (Refund) getDefaultObject();
        assertEquals("QUEUED", testObject.getStatus());
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Refund.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        Refund testObject = (Refund) getDefaultObject();
        Refund retrievedObject;

        retrievedObject = Refund.retrieve((String) testObject.object_id);
        assertEquals(testObject.object_id, retrievedObject.object_id);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Refund.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        RefundCollection objectCollection = Refund.all(null);
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        RefundCollection RefundCollection = Refund.all(objectMap);
        assertEquals(RefundCollection.getData().size(), 1);
    }

    public static Object getDefaultObject() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        Transaction transaction = (Transaction) TransactionTest.getDefaultObject();
        objectMap.put("transaction", transaction.getObjectId());

        try {
            Refund testObject = Refund.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
