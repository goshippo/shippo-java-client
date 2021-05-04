package com.shippo.model;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class ManifestTest extends ShippoTest {

    @Test
    public void testValidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Manifest testObject = (Manifest) getDefaultObject();
        assertEquals("QUEUED", testObject.getStatus());
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

        retrievedObject = Manifest.retrieve((String) testObject.objectId);
        assertEquals(testObject.objectId, retrievedObject.objectId);

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

    public static Object getDefaultObject() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        TimeZone tz = TimeZone.getTimeZone("PST");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        String nowTime = df.format(new Date());
        Map<String, Object> objectMap = new HashMap<String, Object>();
        Address testAddress = (Address) AddressTest.getDefaultObject();
        Transaction transaction = (Transaction) TransactionTest.getDefaultObject();
        List<String> transactions = new ArrayList<String>();
        transactions.add(transaction.objectId);
        CarrierAccount usps_account = CarrierAccount.getByCarrier("usps");
        objectMap.put("carrier_account", usps_account.getObjectId());
        objectMap.put("shipment_date", nowTime);
        objectMap.put("address_from", testAddress.getObjectId());
        objectMap.put("transactions", transactions);

        try {
            Manifest testObject = Manifest.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
