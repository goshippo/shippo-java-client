package com.shippo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;



public class BatchTest extends ShippoTest {

    final String id = "74fb0059c02e49c68df4f634dea90ea1";

    @Test
    public void testValidGet() throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Shippo.setApiKey("shippo_test_4955381329c77d71badf132112936e49d060c3b9");
        Batch batch = Batch.get(id, 0, Batch.ShipmentStatus.PURCHASE_FAILED);
        //Batch batch = Batch.get(id, 0, null);
        System.out.println(batch);
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        //Batch.get("invalid_id");
    }
}

