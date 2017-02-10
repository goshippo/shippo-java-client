package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.gson.Gson;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;


public class TrackTest extends ShippoTest {

    static String carrier = "usps";
    static String number = "9205590164917312751089";

    @Test
    public void testGet()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, number, null);
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), number);
        assertEquals(track.getTrackingStatus().getStatus(), Track.TrackingStatus.DELIVERED);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetBadCarrier()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo("bad", number, null);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrierNumber()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, "invalid", null);
    }

    @Test
    public void testRegisterWebhook()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.registerTrackingWebhook(carrier, number, "meta", null);
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), number);
        assertEquals(track.getTrackingStatus().getStatus(), Track.TrackingStatus.DELIVERED);
    }
}
