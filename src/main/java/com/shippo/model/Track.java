package com.shippo.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.RequestTimeoutException;
import com.shippo.net.APIResource;


public final class Track extends APIResource {
    
	private String carrier;
    private String trackingNumber;
    
    public static class Address {
        private String city;
        private String state;
        private String zip;
        private String country;
		
        @Override
		public String toString() {
			return "Address [city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + "]";
		}
    }

    private Address addressFrom;
    private Address addressTo;
    private Date eta;
    
    public static class ServiceLevel {
    	private String token;
    	private String name;
    	
		public String getToken() {
			return token;
		}
		public String getName() {
			return name;
		}
		@Override
		public String toString() {
			return "ServiceLevel [token=" + token + ", name=" + name + "]";
		}
    }
    
    private ServiceLevel servicelevel;
    private String metadata;

    public static enum TrackingStatus {
        UNKNOWN, DELIVERED, TRANSIT, FAILURE, RETURNED 
    }

    public static class TrackingEvent {
        private Date objectCreated;
        private Date objectUpdated;
        private String objectId;
        private TrackingStatus status;
        private String statusDetails;
        private Date statusDate;
        private Address location;
        
		@Override
		public String toString() {
			return "TrackingEvent [objectCreated=" + objectCreated + ", objectUpdated=" + objectUpdated + ", objectId="
					+ objectId + ", status=" + status + ", statusDetails=" + statusDetails + ", statusDate="
					+ statusDate + ", location=" + location + "]";
		}

		public Date getObjectCreated() {
			return objectCreated;
		}

		public Date getObjectUpdated() {
			return objectUpdated;
		}

		public String getObjectId() {
			return objectId;
		}

		public TrackingStatus getStatus() {
			return status;
		}

		public String getStatusDetails() {
			return statusDetails;
		}

		public Date getStatusDate() {
			return statusDate;
		}

		public Address getLocation() {
			return location;
		}
    }

    private TrackingEvent trackingStatus;
    private TrackingEvent[] trackingHistory;

 	@Override
	public String toString() {
		return "Track [carrier=" + carrier + ", tracking_number=" + trackingNumber + ", from=" + addressFrom + ", to=" + addressTo
				+ ", eta=" + eta + ", serviceLevel=" + servicelevel + ", metadata=" + metadata + ", tracking_status=" + trackingStatus 
				+ ", tracking_history=" + Arrays.toString(trackingHistory) + "]";
	}
	
    public String getCarrier() {
        return carrier;
    }

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public Address getAddressFrom() {
		return addressFrom;
	}

	public Address getAddressTo() {
		return addressTo;
	}

	public Date getETA() {
		return eta;
	}

	public ServiceLevel getServiceLevel() {
		return servicelevel;
	}

	public String getMetadata() {
		return metadata;
	}

	public TrackingEvent getTrackingStatus() {
		return trackingStatus;
	}

	public TrackingEvent[] getTrackingHistory() {
		return trackingHistory;
	}
	
	public static Track getTrackingStatus(String carrier, String trackingNumber, String apiKey)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Track.class, String.format("%s/%s", carrier, trackingNumber)),
				null, Track.class, apiKey);
	}

    //public static Track registerTrackingWebhook(String carrier, String trackingNumber, String metadata, String apiKey)
}
