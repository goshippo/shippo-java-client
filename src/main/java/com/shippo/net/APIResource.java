package com.shippo.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.serialization.GsonFactory;
import com.shippo.serialization.ShippoObject;


public abstract class APIResource extends ShippoObject {

	private static String className(Class<?> clazz) {
		String className = clazz.getSimpleName().toLowerCase()
				.replace("$", " ");

		// Special case class names
		if (className.equals("address")) {
			return "addresse";
		} else if (className.equals("customsitem")) {
			return "customs/item";
		} else if (className.equals("customsdeclaration")) {
			return "customs/declaration";
		} else if (className.equals("carrieraccount")) {
			return "carrier_account";
		} else if (className.equals("batch")) {
			return "batche";
		} else {
			return className;
		}
	}

	protected static String singleClassURL(Class<?> clazz) {
		return String.format("%s/%s", Shippo.getApiBase(), className(clazz));
	}

	protected static String classURL(Class<?> clazz) {
		return String.format("%ss", singleClassURL(clazz));
	}

	protected static String classURLWithTrailingSlash(Class<?> clazz) {
		return String.format("%ss/", singleClassURL(clazz));
	}

	protected static String instanceURL(Class<?> clazz, String id)
			throws InvalidRequestException {
		try {
			return String.format("%s/%s", classURL(clazz), urlEncode(id));
		} catch (UnsupportedEncodingException e) {
			throw new InvalidRequestException("Unable to encode parameters to "
					+ CHARSET
					+ ". Please contact support@goshippo.com for assistance.",
					null, e);
		}
	}

	public static final String CHARSET = "UTF-8";

	private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";

	/*
	 * Set this property to override your environment's default
	 * URLStreamHandler; Settings the property should not be needed in most
	 * environments.
	 */
	private static final String CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME = "com.shippo.net.customURLStreamHandler";

	protected enum RequestMethod {
		GET, POST, PUT
	}

	protected static String urlEncode(String str)
			throws UnsupportedEncodingException {
		// Preserve original behavior that passing null for an object id will
		// lead
		// to us actually making a request to /v1/foo/null
		if (str == null) {
			return null;
		} else {
			return URLEncoder.encode(str, CHARSET);
		}
	}

	private static String urlEncodePair(String k, String v)
			throws UnsupportedEncodingException {
		return String.format("%s=%s", urlEncode(k), urlEncode(v));
	}

	static Map<String, String> getHeaders(String apiKey, Class<?> clazz) {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept-Charset", CHARSET);
		headers.put("User-Agent",
				String.format("Shippo/v1 JavaBindings/%s", Shippo.VERSION));

		if (apiKey == null) {
			apiKey = Shippo.apiKey;
		}

		headers.put("Authorization", String.format("ShippoToken %s", apiKey));
		headers.put("Accept", "application/json");

		// debug headers
		String[] propertyNames = { "os.name", "os.version", "os.arch",
				"java.version", "java.vendor", "java.vm.version",
				"java.vm.vendor" };
		Map<String, String> propertyMap = new HashMap<String, String>();
		for (String propertyName : propertyNames) {
			propertyMap.put(propertyName, System.getProperty(propertyName));
		}
		// propertyMap.put("bindings.version", Shippo.VERSION);
		// propertyMap.put("lang", "Java");
		// propertyMap.put("publisher", "Shippo");
		headers.put("User-Agent", GsonFactory.getGson(clazz).toJson(propertyMap));
		headers.put("Content-Type", "application/json");
		if (Shippo.apiVersion != null) {
		    headers.put("Shippo-API-Version", Shippo.apiVersion);
		}
		return headers;
	}

	private static java.net.HttpURLConnection createShippoConnection(
			String url, String apiKey, Class<?> clazz) throws IOException {
		URL shippoURL;
		String customURLStreamHandlerClassName = System.getProperty(
				CUSTOM_URL_STREAM_HANDLER_PROPERTY_NAME, null);
		if (customURLStreamHandlerClassName != null) {
			// instantiate the custom handler provided
			try {
				@SuppressWarnings("unchecked")
				Class<URLStreamHandler> urlClass = (Class<URLStreamHandler>) Class
						.forName(customURLStreamHandlerClassName);
				Constructor<URLStreamHandler> constructor = urlClass
						.getConstructor();
				URLStreamHandler customHandler = constructor.newInstance();
				shippoURL = new URL(null, url, customHandler);
			} catch (ClassNotFoundException e) {
				throw new IOException(e);
			} catch (SecurityException e) {
				throw new IOException(e);
			} catch (NoSuchMethodException e) {
				throw new IOException(e);
			} catch (IllegalArgumentException e) {
				throw new IOException(e);
			} catch (InstantiationException e) {
				throw new IOException(e);
			} catch (IllegalAccessException e) {
				throw new IOException(e);
			} catch (InvocationTargetException e) {
				throw new IOException(e);
			}
		} else {
			shippoURL = new URL(url);
		}
		java.net.HttpURLConnection conn = (java.net.HttpURLConnection) shippoURL
				.openConnection();
		conn.setConnectTimeout(30 * 1000);
		conn.setReadTimeout(80 * 1000);
		conn.setUseCaches(false);
		for (Map.Entry<String, String> header : getHeaders(apiKey, clazz).entrySet()) {
			conn.setRequestProperty(header.getKey(), header.getValue());
		}

		return conn;
	}

	private static void throwInvalidCertificateException()
			throws APIConnectionException {
		throw new APIConnectionException(
				"Invalid server certificate. You tried to connect to a server that has a revoked SSL certificate, which means we cannot securely send data to that server. Please email support@goshippo.com if you need help connecting to the correct API server.");
	}

	private static void checkSSLCert(java.net.HttpURLConnection hconn)
			throws IOException, APIConnectionException {
		 if (!Shippo.getVerifySSL() &&
		 !hconn.getURL().getHost().equals("api.shippo.com")) {
		 return;
		 }

		 javax.net.ssl.HttpsURLConnection conn =
		 (javax.net.ssl.HttpsURLConnection) hconn;
		 conn.connect();

		 Certificate[] certs = conn.getServerCertificates();

		 try {
		 MessageDigest md = MessageDigest.getInstance("SHA-1");

		 byte[] der = certs[0].getEncoded();
		 md.update(der);
		 byte[] digest = md.digest();

		 byte[] revokedCertDigest = {};

		 if (Arrays.equals(digest, revokedCertDigest)) {
		 throwInvalidCertificateException();
		 }

		 } catch (NoSuchAlgorithmException e) {
		 throw new RuntimeException(e);
		 } catch (CertificateEncodingException e) {
		 throwInvalidCertificateException();
		 }
	}

	private static String formatURL(String url, String query) {
		if (query == null || query.isEmpty()) {
			return url;
		} else {
			// In some cases, URL can already contain a question mark (eg,
			// upcoming invoice lines)
			String separator = url.contains("?") ? "&" : "?";
			return String.format("%s%s%s", url, separator, query);
		}
	}

	private static java.net.HttpURLConnection createGetConnection(String url,
			String query, String apiKey, Class<?> clazz) throws IOException,
			APIConnectionException {
		if (Shippo.isDEBUG()) {
			System.out.println("GET URL: " + url);
		}
		String getURL = formatURL(url, query);
		java.net.HttpURLConnection conn = createShippoConnection(getURL, apiKey, clazz);
		conn.setRequestMethod("GET");

		checkSSLCert(conn);

		return conn;
	}

	private static java.net.HttpURLConnection createPostPutConnection(String url,
			String query, RequestMethod method, String apiKey, Class<?> clazz) throws IOException,
			APIConnectionException {
		if (Shippo.isDEBUG()) {
			System.out.println("POST URL: " + url);
		}

		java.net.HttpURLConnection conn = createShippoConnection(url, apiKey, clazz);
		conn.setDoOutput(true);
		conn.setRequestMethod(method.toString());
		conn.setRequestProperty("Content-Type", "application/json");

		checkSSLCert(conn);

		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;

	}

	private static java.net.HttpURLConnection createPutConnection(String url,
			String query, String apiKey, Class<?> clazz) throws IOException,
			APIConnectionException {
		if (Shippo.isDEBUG()) {
			System.out.println("PUT URL: " + url);
		}

		java.net.HttpURLConnection conn = createShippoConnection(url, apiKey, clazz);
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json");

		checkSSLCert(conn);

		OutputStream output = null;
		try {
			output = conn.getOutputStream();
			output.write(query.getBytes(CHARSET));
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return conn;

	}

	private static String mapToJson(Map<String, Object> params, Class<?> clazz) {
		Gson gson = GsonFactory.getGson(clazz);
		if (params == null) {
			return gson.toJson(new HashMap<String, Object>());
		}
        // hack to serialize list instead of object
        Object o = params.get("__list");
        if (o != null) {
            return gson.toJson(o);
        }
		return gson.toJson(params);
	}

	private static Map<String, String> flattenParams(Map<String, Object> params)
			throws InvalidRequestException {
		if (params == null) {
			return new HashMap<String, String>();
		}
		Map<String, String> flatParams = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Map<?, ?>) {
				Map<String, Object> flatNestedMap = new HashMap<String, Object>();
				Map<?, ?> nestedMap = (Map<?, ?>) value;
				for (Map.Entry<?, ?> nestedEntry : nestedMap.entrySet()) {
					flatNestedMap.put(
							String.format("%s[%s]", key, nestedEntry.getKey()),
							nestedEntry.getValue());
				}
				flatParams.putAll(flattenParams(flatNestedMap));
			} else if (value == null) {
				flatParams.put(key, "");
			} else {
				flatParams.put(key, value.toString());
			}
		}
		return flatParams;
	}

	// represents Errors returned as JSON
	@SuppressWarnings("unused")
	private static class ErrorContainer {
		private APIResource.Error error;
	}

	private static class Error {
		@SuppressWarnings("unused")
		String type;
		String message;
		@SuppressWarnings("unused")
		String code;
		String param;
	}

	@SuppressWarnings("resource")
	private static String getResponseBody(InputStream responseStream)
			throws IOException {
		// \A is the beginning of the stream boundary
		String rBody = new Scanner(responseStream, CHARSET).useDelimiter("\\A")
				.next(); //

		responseStream.close();
		return rBody;
	}

	private static ShippoResponse makeURLConnectionRequest(
			APIResource.RequestMethod method, String url, String query,
			String apiKey, Class<?> clazz) throws APIConnectionException {
		java.net.HttpURLConnection conn = null;

		// Print Information about the Connection
		if (Shippo.isDEBUG()) {
			System.out.println("URL: " + url);
			System.out.println("Query: " + query);
			System.out.println("API Key: " + apiKey);
		}

		try {
			if(method.equals(RequestMethod.GET)){
				conn = createGetConnection(url, query, apiKey, clazz);
			}
			else if (method.equals(RequestMethod.POST) || method.equals(RequestMethod.PUT)) {
				conn = createPostPutConnection(url, query, method, apiKey, clazz);
			}else{
				throw new APIConnectionException(
						String.format(
								"Unrecognized HTTP method %s. "
										+ "This indicates a bug in the Shippo bindings. Please contact "
										+ "support@goshippo.com for assistance.",
								method));
			}

			// Trigger the Request
			int rCode = conn.getResponseCode();

			String rBody;
			Map<String, List<String>> headers;
			
			if (rCode >= 200 && rCode < 300) {
				rBody = getResponseBody(conn.getInputStream());
			}
			else if(rCode == 429){
				rBody = "Too many requests";
			}
			else {
				try{
				rBody = getResponseBody(conn.getErrorStream());
				}
				catch(NullPointerException e){
					rBody = "";
				}
			}
			headers = conn.getHeaderFields();

			// /////////////////////////////////////////////////////////////////
			// PRINT RESULTS
			// /////////////////////////////////////////////////////////////////
			if (Shippo.isDEBUG()) {
				System.out.println("Headers: ");
				for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
					System.out.println(entry.getKey() + " : "
							+ entry.getValue());
				}
				System.out.println("Response Code: " + rCode);
				System.out.println("Reponse Body: " + rBody);
			}

			return new ShippoResponse(rCode, rBody, headers);
		} catch (IOException e) {
			throw new APIConnectionException(
					String.format(
							"IOException during API request to Shippo (%s): %s "
									+ "Please check your internet connection and try again. If this problem persists,"
									+ "you should check Shippo's service status at http://status.goshippo.com/,"
									+ " or let us know at support@goshippo.com.",
							Shippo.getApiBase(), e.getMessage()), e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	protected static <T> T request(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		String originalDNSCacheTTL = null;
		Boolean allowedToSetTTL = true;
		try {
			originalDNSCacheTTL = java.security.Security
					.getProperty(DNS_CACHE_TTL_PROPERTY_NAME);
			// disable DNS cache
			java.security.Security
					.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "0");
		} catch (SecurityException se) {
			allowedToSetTTL = false;
		}

		try {
			return _request(method, url, params, clazz, apiKey);
		} finally {
			if (allowedToSetTTL) {
				if (originalDNSCacheTTL == null) {
					// value unspecified by implementation
					// DNS_CACHE_TTL_PROPERTY_NAME of -1 = cache forever
					java.security.Security.setProperty(
							DNS_CACHE_TTL_PROPERTY_NAME, "-1");
				} else {
					java.security.Security.setProperty(
							DNS_CACHE_TTL_PROPERTY_NAME, originalDNSCacheTTL);
				}
			}
		}
	}

	protected static <T> T _request(APIResource.RequestMethod method,
			String url, Map<String, Object> params, Class<T> clazz,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		if ((Shippo.apiKey == null || Shippo.apiKey.length() == 0)
				&& (apiKey == null || apiKey.length() == 0)) {
			throw new AuthenticationException(
					"No API key provided. (HINT: set your API key using 'Shippo.apiKey = <API-KEY>'. "
							+ "You can generate API keys from the Shippo web interface. "
							+ "See https://goshippo.com/docs for details or email support@goshippo.com if you have questions.");
		}

		if (apiKey == null) {
			apiKey = Shippo.apiKey;
		}

		String query;
		try {
			query = createQuery(params, method, clazz);
		} catch (UnsupportedEncodingException e) {
			 throw new InvalidRequestException("Unable to encode parameters to " + CHARSET
	                    + ". Please contact support@shippo.com for assistance.", null, e);
		}
		ShippoResponse response = makeURLConnectionRequest(method, url, query, apiKey, clazz);

		int rCode = response.responseCode;
		String rBody = response.responseBody;

		if (rCode < 200 || rCode >= 300) {
			handleAPIError(rBody, rCode);
		}
		return GsonFactory.getGson(clazz).fromJson(rBody, clazz);
	}

	private static void handleAPIError(String rBody, int rCode)
			throws InvalidRequestException, AuthenticationException,
			APIException {

		// Current API does not support JSON Based error response bodies
		// APIResource.Error error = GSON.fromJson(rBody,
		// APIResource.ErrorContainer.class).error;
		APIResource.Error error = new Error();
		error.message = rBody;
		error.code = rCode + "";

		switch (rCode) {
		case 400:
			throw new InvalidRequestException(error.message, error.param, null);
		case 404:
			throw new InvalidRequestException(error.message, error.param, null);
		case 401:
			throw new AuthenticationException(error.message);
		default:
			throw new APIException(error.message, null);
		}
	}

    private static String createGETQuery(Map<String, Object> params, Class<?> clazz)
    		throws UnsupportedEncodingException, InvalidRequestException {
		Map<String, String> flatParams = flattenParams(params);
		StringBuilder queryStringBuffer = new StringBuilder();
		for (Map.Entry<String, String> entry : flatParams.entrySet()) {
		    if (queryStringBuffer.length() > 0) {
		        queryStringBuffer.append("&");
		    }
		    queryStringBuffer.append(urlEncodePair(entry.getKey(), entry.getValue()));
		}
		return queryStringBuffer.toString();
	}

    private static String createQuery(Map<String, Object> params, APIResource.RequestMethod method,
    		Class<?> clazz) throws UnsupportedEncodingException, InvalidRequestException {
		switch (method) {
		case GET:
			return createGETQuery(params, clazz);
		case POST:
			return mapToJson(params, clazz);
		default:
			return mapToJson(params, clazz);
    }

}
}
