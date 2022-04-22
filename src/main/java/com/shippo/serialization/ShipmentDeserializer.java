package com.shippo.serialization;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;


public class ShipmentDeserializer implements JsonDeserializer<com.shippo.model.Shipment> {
    public com.shippo.model.Shipment deserialize(
    		JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        com.shippo.model.Shipment shipment = GsonFactory.DEFAULT_GSON
        	.fromJson(json, com.shippo.model.Shipment.class);
        JsonObject jsonObject = json.getAsJsonObject();
        com.shippo.model.Address addressTo = this.getAddress(jsonObject, "address_to");
        if (addressTo != null) {
        	shipment.setAddressTo(addressTo);
        }
        com.shippo.model.Address addressFrom = this.getAddress(jsonObject, "address_from");
        if (addressFrom != null) {
        	shipment.setAddressFrom(addressFrom);
        }
        com.shippo.model.Address addressReturn = this.getAddress(jsonObject, "address_return");
        if (addressReturn != null) {
        	shipment.setAddressReturn(addressReturn);
        }
 		return shipment;
    }

    private com.shippo.model.Address getAddress(JsonObject jsonObject, String address_key) {
    	if (jsonObject.has(address_key)) {
    		JsonElement elem = jsonObject.get(address_key);
    		if ((elem != null) && !elem.isJsonNull()) {
    			String valuesString = elem.toString();
    			if ((valuesString != null) && !valuesString.isEmpty()) {
    				try {
    					return GsonFactory.DEFAULT_GSON.fromJson(
    						valuesString, com.shippo.model.Address.class);
    				} catch (JsonSyntaxException ex) {
    					// Expected if it's not an address type, return raw.
    				}
    			}
    		}
    	}
    	return null;
    }
}