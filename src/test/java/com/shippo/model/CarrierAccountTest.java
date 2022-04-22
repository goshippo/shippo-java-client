package com.shippo.model;

import static org.junit.Assert.assertEquals;

import com.shippo.Shippo;
import com.shippo.exception.ShippoException;

import org.junit.Test;

public class CarrierAccountTest extends ShippoTest {

    @Test
    public void testRetrieveValid() throws ShippoException {
        // WHEN
        CarrierAccount usps_account = CarrierAccount.getByCarrier("usps");

        // EXPECT
        assertEquals(Shippo.apiKeyIsTest, usps_account.isTest());
    }

}
