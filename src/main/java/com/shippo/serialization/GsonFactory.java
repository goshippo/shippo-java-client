package com.shippo.serialization;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This class generates and stores all the Gson deserializers. Each APIResource
 * class can use
 * this class to pick the right deserializer for themselves.
 */
public class GsonFactory {
	public static final Gson DEFAULT_GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.registerTypeAdapter(ShippoRawJsonObject.class,
					new ShippoRawJsonObjectDeserializer())
			.create();

	/**
	 * Shipment serializer uses a different deserializer so it can do special
	 * handling for addresses.
	 */
	public static final Gson SHIPMENT_GSON = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.registerTypeAdapter(com.shippo.model.Shipment.class, new ShipmentDeserializer())
			.registerTypeAdapter(ShippoRawJsonObject.class,
					new ShippoRawJsonObjectDeserializer())
			.create();

	public static Gson getGson(Class className) {
		if (className == com.shippo.model.Shipment.class) {
			return SHIPMENT_GSON;
		} else {
			return DEFAULT_GSON;
		}
	}
}