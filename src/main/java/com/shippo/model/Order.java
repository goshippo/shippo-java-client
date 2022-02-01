package com.shippo.model;

import java.util.List;
import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

/**
 * Use this class to create an order. Represents object defined in
 * https://api.goshippo.com/orders endpoint. The endpoint's documentation can be
 * found in https://goshippo.com/docs/reference#orders
 */
public class Order extends APIResource {
	String objectId;
	String objectOwner;
	String orderNumber;
	OrderStatus orderStatus;
	Object placedAt;
	Object fromAddress;
	Object toAddress;
	List<LineItem> lineItems;
	Object shippingCost;
	Object shippingCostCurrency;
	String shippingMethod;
	String shopApp;
	Object totalPrice;
	Object totalTax;
	Object currency;
	List<Transaction> transactions;
	Object weight;
	Object weightUnit;
	String notes;
	Object messages;
	Object metadata;

	public static Order create(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return create(params, null);
	}

	public static Order create(Map<String, Object> params, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.POST, classURL(Order.class), params,
				Order.class, apiKey);
	}

	public static Order retrieve(String id) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return retrieve(id, null);
	}

	public static Order retrieve(String id, String apiKey)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return request(RequestMethod.GET, instanceURL(Order.class, id),
				null, Order.class, apiKey);
	}

	public static OrderCollection all(Map<String, Object> params)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, APIException {
		return all(params, null);
	}

	public static OrderCollection all(Map<String, Object> params,
			String apiKey) throws AuthenticationException,
			InvalidRequestException, APIConnectionException, APIException {
		return request(RequestMethod.GET, classURL(Order.class), params,
				OrderCollection.class, apiKey);
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectOwner() {
		return objectOwner;
	}

	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public enum OrderStatus {
		UNKNOWN, AWAITPAY, PAID, REFUNDED, CANCELLED, PARTIALLY_FULFILLED, SHIPPED
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Object getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(Object placedAt) {
		this.placedAt = placedAt;
	}

	public Object getfromAddress() {
		return fromAddress;
	}

	public void setFromAddress(Object fromAddress) {
		this.fromAddress = fromAddress;
	}

	public Object getToAddress() {
		return toAddress;
	}

	public void setToAddress(Object toAddress) {
		this.toAddress = toAddress;
	}

	public static class LineItem {
		String objectId;
		String title;
		String variantTitle;
		String sku;
		Integer quantity;
		Object totalPrice;
		Object currency;
		Object weight;
		String weightUnit;
		String manufactureCountry;
		Object maxShipTime;
		Object maxDeliveryTime;

		public String getObjectId() {
			return objectId;
		}

		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getVariantTitle() {
			return variantTitle;
		}

		public void setVariantTitle(String variantTitle) {
			this.variantTitle = variantTitle;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Object getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Object totalPrice) {
			this.totalPrice = totalPrice;
		}

		public Object getCurrency() {
			return currency;
		}

		public void setCurrency(Object currency) {
			this.currency = currency;
		}

		public Object getWeight() {
			return weight;
		}

		public void setWeight(Object currency) {
			this.currency = currency;
		}

		public String getWeightUnit() {
			return weightUnit;
		}

		public void setWeightUnit(String weightUnit) {
			this.weightUnit = weightUnit;
		}

		public String getManufactureCountry() {
			return manufactureCountry;
		}

		public void setManufactureCountry(String manufactureCountry) {
			this.manufactureCountry = manufactureCountry;
		}

		public Object getMaxShipTime() {
			return maxShipTime;
		}

		public void setMaxShipTime(Object maxShipTime) {
			this.maxShipTime = maxShipTime;
		}

		public Object getMaxDeliveryTime() {
			return maxDeliveryTime;
		}

		public void setMaxDeliveryTime(Object maxDeliveryTime) {
			this.maxDeliveryTime = maxDeliveryTime;
		}
	}

	public Object getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Object shippingCost) {
		this.shippingCost = shippingCost;
	}

	public Object getShippingCostCurrency() {
		return shippingCostCurrency;
	}

	public void setShippingCostCurrency(Object shippingCostCurrency) {
		this.shippingCostCurrency = shippingCostCurrency;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public String getShopApp() {
		return shopApp;
	}

	public void setShopApp(String shopApp) {
		this.shopApp = shopApp;
	}

	public Object getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Object totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Object getTotalTaxShippingMethod() {
		return totalTax;
	}

	public void setTotalTaxShippingMethod(Object totalTax) {
		this.totalTax = totalTax;
	}

	public Object getCurrency() {
		return currency;
	}

	public void setCurrency(Object currency) {
		this.currency = currency;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Object getWeight() {
		return weight;
	}

	public void setWeight(Object weight) {
		this.weight = weight;
	}

	public Object getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(Object weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Object getMessages() {
		return messages;
	}

	public void setMessages(Object messages) {
		this.messages = messages;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
}
