package com.shippo.model;

import java.util.HashMap;
import java.util.Map;

import com.shippo.Shippo;

import org.junit.Before;

public class ShippoTest {
	@Before
	public void setAPIKey() {
		Shippo.apiKey = "shippo_test_cf1b6d0655e59fc6316880580765066038ef20d8";
		// Some tests make assertions on response models based on whether a test api key is used
		Shippo.apiKeyIsTest = true;
		Shippo.DEBUG = true;
	}

	@Before
    public void setVersion() {
        Shippo.apiVersion = "2018-02-08";
    }

	public Map<String, Object> getInvalidObjectMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

    public static Object nullToEmptyString(Object value) {
        return value == null ? "" : value;
    }
}
