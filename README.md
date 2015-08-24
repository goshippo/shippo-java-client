# Shippo Java Bindings

You can sign up for a Shippo account at https://goshippo.com

Requirements
============

Java 1.5 and later

Installation
============

### Maven users

Add this dependency to your project's POM:

    <dependency>
        <groupId>com.goshippo</groupId>
        <artifactId>shippo-java-client</artifactId>
        <version>1.1.1-SNAPSHOT</version>
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
