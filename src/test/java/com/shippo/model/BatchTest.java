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
    public void testRemoveShipments() throws AuthenticationException, InvalidRequestException, APIConnectionException,
    	APIException {
        String[] shipmentIds = {"c2f2d35033624fbba6350597f3642cf8"};
        Batch batch = Batch.removeShipments(id, shipmentIds);
        System.out.println(batch);
    }
    
    @Test
    public void testCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
    	Address from = Address.createForPurchase("Hippo", "Hippo st", "SFO", "91234", "CA", "USA", "hippo@g.com");
    	Address to = Address.createForPurchase("SHippo", "SHippo st", "LA", "91234", "CA", "USA", "shippo@g.com");
    	Parcel parcel = Parcel.createForShipment(2, 3, 3, "cm", 23, "g");
        Shipment shipment = Shipment.createForBatch(from, to, parcel);
        Batch.BatchShipment[] shipments = {Batch.BatchShipment.createForShipment(shipment, null, null)};
        Batch batch = Batch.create("09a25c72f0df461ea8fea8b755356aaf", "usps_priority", LabelFileType.PDF, null, shipments);
        System.out.println(batch);
    }

    @Test
    public void testPurchase() throws AuthenticationException, InvalidRequestException, APIConnectionException,
    	APIException {
        Batch batch = Batch.purchase("fdd7bd30dc4d4cd8ac7e72eeb7440e5e");
        System.out.println(batch);
    }

}

