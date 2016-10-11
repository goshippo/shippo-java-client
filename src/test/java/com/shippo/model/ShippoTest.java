package com.shippo.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import com.shippo.Shippo;

public class ShippoTest {
    static boolean VERBOSE_TESTING = true;

    @Before
    public void setAPIKey() {
        Shippo.apiKey = "2633bb26af6f9b9171a44f36059b5d86a54e06d7";
    }

    public Map<String, Object> getInvalidObjectMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        return map;
    }
}
