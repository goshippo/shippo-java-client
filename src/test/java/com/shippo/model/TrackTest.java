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

    final static String carrier = "usps";
    final static String number = "9205590164917312751089";

    private void checkTrack(Track track) {
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), number);
        assertEquals(track.getTrackingStatus().getStatus(), Track.TrackingStatus.DELIVERED);
    }

    @Test
    public void testGet()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, number, null);
        checkTrack(track);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrier()  throws AuthenticationException, InvalidRequestException, 
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
        checkTrack(track);
    }
}
