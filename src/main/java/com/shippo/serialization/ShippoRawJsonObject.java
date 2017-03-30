package com.shippo.serialization;

import com.google.gson.JsonObject;

/**
 * Fallback class for when we do not recognize the object that we have received.
 */
public class ShippoRawJsonObject extends ShippoObject {
    JsonObject json;
}
