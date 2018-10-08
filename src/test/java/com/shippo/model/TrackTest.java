package com.shippo.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;


public class TrackTest extends ShippoTest {

    final static String carrier = "shippo";
    final static String number = "SHIPPO_TRANSIT";

    private void checkTrack(Track track) {
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), number);
        assertEquals(track.getTrackingStatus().getStatus(), Track.TrackingStatus.DELIVERED);
    }

/*    @Test
    public void testGet()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, number, null);
        checkTrack(track);
    }
*/
    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrier()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track.getTrackingInfo("bad", number, null);
    }

    @Test(expected = InvalidRequestException.class)
    public void testGetInvalidCarrierNumber()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.getTrackingInfo(carrier, "invalid", null);
        //These should not get to run as this call now generates an exception.
        //if it does run, the api isnt behaving as expected and this will fail
        assertEquals(track.getCarrier(), carrier);
        assertEquals(track.getTrackingNumber(), "invalid");
        assertEquals(track.getTrackingStatus(), null);
       
    }
/*
    @Test
    public void testRegisterWebhook()  throws AuthenticationException, InvalidRequestException, 
            APIConnectionException, APIException {
        Track track = Track.registerTrackingWebhook(carrier, number, "meta", null);
        checkTrack(track);
    }*/
}
