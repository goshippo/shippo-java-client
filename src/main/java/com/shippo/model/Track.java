package com.shippo.model;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

/**
 * Use this class to track shipments. Represents object defined in
 * https://api.goshippo.com/tracks endpoint. The endpoint's documentation can be
 * found in https://goshippo.com/docs/reference#tracks
 */
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

	@SerializedName("servicelevel")
	private ServiceLevel serviceLevel;

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
		return "Track [carrier=" + carrier + ", tracking_number=" + trackingNumber + ", addressFrom=" + addressFrom
				+ ", addressTo=" + addressTo + ", eta=" + eta + ", serviceLevel=" + serviceLevel + ", metadata="
				+ metadata + ", tracking_status=" + trackingStatus + ", tracking_history="
				+ Arrays.toString(trackingHistory) + "]";
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
		return serviceLevel;
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

	/**
	 * Return URL that maps given tracking number on given carrier as
	 * https://api.goshippo.com/tracks/<carrier>/<number>
	 */
	private static String trackingNumberURL(String carrier, String trackingNumber)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		try {
			return String.format("%s/%s/%s", classURL(Track.class), urlEncode(carrier), urlEncode(trackingNumber));
		} catch (UnsupportedEncodingException e) {
			throw new InvalidRequestException("Unable to encode parameters to " + CHARSET
					+ ". Please contact support@goshippo.com for assistance.", null, e);
		}
	}

	/**
	 * Get tracking information of any package from given carrier. This
	 * corresponds to https://api.goshippo.com/tracks/<carrier>/<tracking
	 * number> API defined in
	 * https://goshippo.com/docs/reference#tracks-retrieve
	 *
	 * @param carrier
	 *            Name of the carrier (like "usps") tracking the package
	 * @param trackingNumber
	 *            Tracking number provided by the carrier for a package
	 * @return Track object containing tracking info
	 */
	public static Track getTrackingInfo(String carrier, String trackingNumber, String apiKey)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, trackingNumberURL(carrier, trackingNumber), null, Track.class, apiKey);
	}

	/**
	 * Register webhook for tracking shipment of a pacakge. This corresponds to
	 * API defined in https://goshippo.com/docs/reference#tracks-create. Please
	 * note that the webhook where information will be posted need to be already
	 * provided in https://app.goshippo.com
	 *
	 * @param carrier
	 *            Name of the carrier (like "usps") tracking the packing
	 * @param trackingNumber
	 *            Tracking number provided by the carrier for a package
	 * @param metadata
	 *            Generic information related to this tracking
	 * @return Track object containing tracking info
	 */
	public static Track registerTrackingWebhook(String carrier, String trackingNumber, String metadata, String apiKey)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("carrier", carrier);
		params.put("tracking_number", trackingNumber);
		params.put("metadata", metadata);
		return request(RequestMethod.POST, classURLWithTrailingSlash(Track.class), params, Track.class, apiKey);
	}
}
