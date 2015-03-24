package com.shippo;

public abstract class Shippo {

    public static final String LIVE_API_BASE = "https://api.goshippo.com";
    public static final String VERSION = "1.0";
    public static boolean DEBUG = false;

    public static int RATES_REQ_TIMEOUT = 25000; // milliseconds
    public static int TRANSACTION_REQ_TIMEOUT = 25000; // milliseconds

    public static volatile String apiKey;
    public static volatile String apiVersion;

    private static volatile boolean verifySSL = true;
    private static volatile String apiBase = LIVE_API_BASE;

    /**
     * (FOR TESTING ONLY) If you'd like your API requests to hit your own
     * (mocked) server, you can set this up here by overriding the base api URL.
     */
    public static void overrideApiBase(final String overriddenApiBase) {
        apiBase = overriddenApiBase;
    }

    /**
     * (FOR TESTING ONLY) Only disable SSL verification if you're using your own
     * (mocked) server. Disabling verification on shippo.com is not supported
     */
    public static void setVerifySSL(boolean verify) {
        verifySSL = verify;
    }

    public static boolean getVerifySSL() {
        return verifySSL;
    }

    public static String getApiBase() {
        return apiBase;
    }

    public static boolean isDEBUG() {
        return DEBUG;
    }

    public static void setDEBUG(boolean dEBUG) {
        DEBUG = dEBUG;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        Shippo.apiKey = apiKey;
    }
}
