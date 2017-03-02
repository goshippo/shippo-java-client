package com.shippo.model;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.junit.Test;

import com.shippo.util.HttpUtil;

public class HttpUtilTest {
		
	@Test
	public void testOne() throws UnsupportedEncodingException {
		Map<String, String> params = HttpUtil.queryToParams("page=1");
		assertEquals(params.size(), 1);
		assertEquals(params.get("page"), "1");
	}
	
	@Test
	public void testMany() throws UnsupportedEncodingException {
		Map<String, String> params = HttpUtil.queryToParams("page=1&results=completed");
		assertEquals(params.size(), 2);
		assertEquals(params.get("page"), "1");
		assertEquals(params.get("results"), "completed");
	}
}
