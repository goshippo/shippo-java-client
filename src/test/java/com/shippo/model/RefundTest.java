package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.DuplicateRefundRequestException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

import org.junit.Test;

public class RefundTest extends ShippoTest {

    @Test
    public void testValidCreate() {
        Refund testObject = createRefundFixture();
        assertEquals("QUEUED", testObject.getStatus());
        assertEquals(Shippo.apiKeyIsTest, testObject.isTest());
    }

    @Test(expected = DuplicateRefundRequestException.class)
    public void testDoubleRefundRequest() throws ShippoException {
        // GIVEN a transaction
        Transaction transaction = TransactionTest.createTransactionFixture();
        
        // AND a refund has already been requested for that transaction
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("transaction", transaction.getObjectId());
        Refund refund1 = Refund.create(objectMap);

        // WHEN request another refund for same transaction
        Refund refund2 = Refund.create(objectMap);

        // THEN should get informative exception
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
        Refund testObject = createRefundFixture();
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

    public static Refund createRefundFixture() {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        Transaction transaction = TransactionTest.createTransactionFixture();
        objectMap.put("transaction", transaction.getObjectId());

        try {
            return Refund.create(objectMap);
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
