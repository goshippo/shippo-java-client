package com.shippo.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection of random HTTP utility functions
 */
public final class HttpUtil {

	// Taken from
	// http://stackoverflow.com/questions/13592236/parse-a-uri-string-into-name-value-collection
	/**
	 * Parse given query string of the form
	 * <code>name1=value1&amp;name2=value2</code> and return it as {@link Map}
	 * 
	 * @param query Query string
	 * @return Parsed results
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> queryToParams(String query) throws UnsupportedEncodingException {
		Map<String, String> query_pairs = new HashMap<String, String>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
					URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		return query_pairs;
	}
}