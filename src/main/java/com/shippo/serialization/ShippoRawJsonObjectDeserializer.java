package com.shippo.serialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ShippoRawJsonObjectDeserializer implements JsonDeserializer<ShippoRawJsonObject> {
    public ShippoRawJsonObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        ShippoRawJsonObject object = new ShippoRawJsonObject();
        object.json = json.getAsJsonObject();
        return object;
    }

}
