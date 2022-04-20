package com.shippo.model;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;

import com.shippo.Shippo;

public class ShippoTest {
	@Before
	public void setAPIKey() {
		Shippo.apiKey = "shippo_test_cf1b6d0655e59fc6316880580765066038ef20d8";
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
