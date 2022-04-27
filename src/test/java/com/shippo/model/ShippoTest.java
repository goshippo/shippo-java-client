package com.shippo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.shippo.Shippo;

import org.junit.Before;

public class ShippoTest {
	@Before
	public void setAPIKey() {
		Shippo.apiKey = "shippo_test_cf1b6d0655e59fc6316880580765066038ef20d8";
		// Some tests make assertions on response models based on whether a test api key
		// is used
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

	// Make sure we get a test rate. We are using a test auth token, so it should
	// not be possible to get a non-test rate back,
	// but previous author was uncertain, and I don't know enough about the
	// underlying implementation to be sure.
	static Rate selectTestRate(List<Rate> rateList) {
		return rateList.stream().filter(new Predicate<Rate>() {
			@Override
			public boolean test(Rate rate) {
				return rate.isTest();
			}
		}).findAny().orElseThrow();
	}
}
