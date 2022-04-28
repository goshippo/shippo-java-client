[![CircleCI](https://circleci.com/gh/goshippo/shippo-java-client/tree/master.svg?style=svg)](https://circleci.com/gh/goshippo/shippo-java-client/tree/master)

# Shippo Java API wrapper

Shippo is a shipping API that connects you with [multiple shipping carriers](https://goshippo.com/carriers/) (such as USPS, UPS, DHL, Canada Post, Australia Post, UberRUSH and many others) through one interface.

Print a shipping label in 10 mins using our default USPS and DHL Express accounts. No need to register for a carrier account to get started.

Requirements
============

* Shippo API Version: 2018-02-08. For older versions, please use an older release.
* Java 1.7 and later

* [Shippo account](https://goshippo.com/) - free to sign up, free to use the API. Only pay to print a live label, test labels are free.

Installation
============

### Maven users

Add this dependency to your project's POM:
```java
    <dependency>
        <groupId>com.goshippo</groupId>
        <artifactId>shippo-java-client</artifactId>
        <version>3.2.0</version>
    </dependency>
```

### Others

You'll need to manually install the following JARs:

* The latest Shippo JAR from <https://github.com/goshippo/shippo-java-client/releases>
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

### Developing with Eclipse

You can also load the source into Eclipse, simply clone this repository. Then open up Eclipse (the latest eclipse comes with maven).
Within eclipse, File --> Import
In the dialog window that appears, chose "Existing Maven Projects", from there on, follow the wizard, and you will have imported the project.
You can then run [Example.java](/src/main/java/com/shippo/Example.java), or any of the individual JUnit test cases.


Usage
=====

Check out the file [Example.java](/src/main/java/com/shippo/Example.java) for more examples, alternatively you can look through all of the [test classes](/src/test/java/com/shippo/model) for each particular model for examples.
For useful debugging information including headers, server raw response etc, set Shippo.setDEBUG(true);

Below is a brief code example:
```java
    /* Code Example */
    public static void main(String[] args) {

        Shippo.apiKey = "<Your Shippo authToken>";

        Map<String, Object> addressMap = new HashMap<String, Object>();
		addressMap.put("name", "Shippo Itle");
		addressMap.put("company", "Shippo");
		addressMap.put("street1", "215 Clayton St.");
		addressMap.put("city", "San Francisco");
		addressMap.put("state", "CA");
		addressMap.put("zip", "94117");
		addressMap.put("country", "US");
		addressMap.put("phone", "+1 555 341 9393");
		addressMap.put("email", "test@goshipppo.com");

        try {
            Address address = Address.create(addressMap);
            System.out.println(address.toString());
            System.out.println("Zip code: " + address.getZip());
        } catch (ShippoException e) {
            e.printStackTrace();
        }
    }
```


Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=ShippoTest#testAddressCreate` or for an entire file `-D test=AddressTest`.

You can also run the tests in eclipse.

## Documentation

Please see [https://goshippo.com/docs](https://goshippo.com/docs) for up-to-date documentation.

## About Shippo

Connect with multiple different carriers, get discounted shipping labels, track parcels, and much more with just one integration. You can use your own carrier accounts or take advantage of our discounted rates with the USPS and DHL Express. Using Shippo makes it easy to deal with multiple carrier integrations, rate shopping, tracking and other parts of the shipping workflow. We provide the API and dashboard for all your shipping needs.

## Supported Features

The Shippo API provides in depth support of carrier and shipping functionalities. Here are just some of the features we support through the API:

* Shipping rates & labels
* Tracking for any shipment with just the tracking number
* Batch label generation
* Multi-piece shipments
* Manifests and SCAN forms
* Customs declaration and commercial invoicing
* Address verification
* Signature and adult signature confirmation
* Consolidator support including:
	* DHL eCommerce
	* UPS Mail Innovations
	* FedEx Smartpost
* Additional services: cash-on-delivery, certified mail, delivery confirmation, and more.
