package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;



public class BatchTest extends ShippoTest {

    final String id = "74fb0059c02e49c68df4f634dea90ea1";

    @Before public void set() {
        Shippo.setApiKey("shippo_test_4955381329c77d71badf132112936e49d060c3b9");
    }

    @Test
    public void testAll() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Batch[] batches = Batch.all();
        System.out.println(Arrays.toString(batches));
    }

    @Test
    public void testValidGet() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Batch batch = Batch.get(id, 1, Batch.ShipmentStatus.CREATION_SUCCEEDED);
        System.out.println(batch);
    }
    
    @Test
    public void testValidGetParams() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Batch batch = Batch.get(id, 1, Batch.ShipmentStatus.CREATION_SUCCEEDED);
        System.out.println(batch);
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidGet() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Batch.get("invalid_id", 0, null);
    }

    @Test
    public void testAddShipments() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        String[] shipmentIds = {"c1a4824aed7e472f9a2ee521be17c52b"};
        Batch batch = Batch.addShipments(id, shipmentIds);
        System.out.println(batch);
    }

    @Test
    public void testCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {

        //Shipment shipment = Shipment.createForBatch(from, to, parcel, "fedex", "token");
        //Batch.BatchShipment bs = Batch.BatchShipment.createForShipment(shipment);
        //Batch batch = Batch.addShipments(id, shipmentIds);
        //System.out.println(batch);
    }
}

