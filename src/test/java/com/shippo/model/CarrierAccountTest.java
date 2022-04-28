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

    // TODO: throw a better exception
    // Ideally something like CarrierAccountNotFound, but at least InvalidRequest
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRetrieveInvalid() throws ShippoException {
        // WHEN
        CarrierAccount.getByCarrier("rando-bad");

        // EXPECT exception
    }

}
