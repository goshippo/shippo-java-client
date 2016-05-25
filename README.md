#Shippo Java API wrapper

Shippo is a shipping API that connects you with multiple shipping carriers (such as USPS, UPS, DHL, Canada Post, Australia Post, UberRUSH and many [others](https://goshippo.com/shipping-carriers/)) through one interface.

Our API provides in depth support of carrier functionality. Here are just some of the features we support for USPS, FedEx and UPS via the API.

For most major carriers (USPS, UPS, FedEx and most others) our API supports:

* Shipping rates & labels
* Tracking
	
* For USPS, the API additionally supports:
	* US Address validation
	* Scan forms
	* Additional services: signature, certified mail, delivery confirmation, and others

* For FedEx, the API additionally supports:
	* Signature and adult signature confirmation
	* FedEx Smartpost

* For UPS, the API additionally supports:
	* Signature and adult signature confirmation
	* UPS Mail Innovations
	* UPS SurePost

The complete list of carrier supported features is [here](https://goshippo.com/shipping-api/carriers).

###About Shippo
Connect with multiple different carriers, get discounted shipping labels, track parcels, and much more with just one integration. You can use your own carrier accounts or take advantage of our deeply discounted rates. Using Shippo makes it easy to deal with multiple carrier integrations,  rate shopping, tracking and other parts of the shipping workflow. We provide the API and dashboard for all your shipping needs.

The API is free to use. You only pay when you print a live label from a carrier.  Use test labels during development to avoid all fees.

You do need a Shippo account to use our API. Don't have an account? Sign up at [https://goshippo.com/](https://goshippo.com/).

Requirements
============

* Java 1.5 and later

* [Shippo account](https://goshippo.com/) - free to sign up, free to use the API

Installation
============

### Maven users

Add this dependency to your project's POM:

    <dependency>
        <groupId>com.goshippo</groupId>
        <artifactId>shippo-java-client</artifactId>
        <version>1.1.4</version>
    </dependency>


### Others

You'll need to manually install the following JARs:

* The latest Shippo JAR from <https://github.com/goshippo/shippo-java-client/releases>
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

### Developing with Eclipse

You can also load the source into Eclipse, simply clone this repository. Then open up Eclipse (the latest eclipse comes with maven).
Within eclipse, File --> Import
In the dialog window that appears, chose "Existing Maven Projects", from there on, follow the wizard, and you will have imported the project.
You can then run Example.java, or any of the individual JUnit test cases. 


Usage
=====

Check out the file Example.java for more examples, alternatively you can look through all of the test classes for each particular model for examples.
For useful debugging information including headers, server raw response etc, set Shippo.setDEBUG(true);

Below is a brief code example:

    /* Code Example */
        public static void main(String[] args) {
    
        Shippo.apiKey = "<Your Shippo authToken>";
        
        Map<String, Object> addressMap = new HashMap<String, Object>();
		addressMap.put("object_purpose", "PURCHASE");
		addressMap.put("name", "Shippo Itle");
		addressMap.put("company", "Shippo");
		addressMap.put("street1", "215 Clayton St.");
		addressMap.put("city", "San Francisco");
		addressMap.put("state", "CA");
		addressMap.put("zip", "94117");
		addressMap.put("country", "US");
		addressMap.put("phone", "+1 555 341 9393");
		addressMap.put("email", "laura@goshipppo.com")

        try {
            Address address = Address.create(addressMap);
            System.out.println(address.toString());
            System.out.println("Zip code: " + address.getZip());
        } catch (ShippoException e) {
            e.printStackTrace();
        }
    }
    


Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=ShippoTest#testAddressCreate`.

You can also run the tests in eclipse.

## Documentation

Please see [https://goshippo.com/shipping-api/](https://goshippo.com/shipping-api/) for up-to-date documentation.

