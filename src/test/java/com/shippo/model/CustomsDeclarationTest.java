package com.shippo.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.shippo.Shippo;
import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.exception.ShippoException;

public class CustomsDeclarationTest extends ShippoTest {

    // test data
    private static Map<String, Object> customsDeclarationRequestMap;
    static {
        customsDeclarationRequestMap = new HashMap<String, Object>();
        customsDeclarationRequestMap.put("certify_signer", "Laura Behrens Wu");
        customsDeclarationRequestMap.put("certify", "true");
        customsDeclarationRequestMap.put("non_delivery_option", "ABANDON");
        customsDeclarationRequestMap.put("contents_type", "MERCHANDISE");
        customsDeclarationRequestMap.put("contents_explanation", "T-Shirt purchase");
        customsDeclarationRequestMap.put("exporter_reference", "Exporter Reference");
        customsDeclarationRequestMap.put("importer_reference", "Importer Reference");
        customsDeclarationRequestMap.put("invoice", "#123123");
        customsDeclarationRequestMap.put("license", "License");
        customsDeclarationRequestMap.put("certificate", "Certificate");
        customsDeclarationRequestMap.put("notes", "Notes");
        customsDeclarationRequestMap.put("eel_pfc", "NOEEI_30_37_a");
        customsDeclarationRequestMap.put("aes_itn", "X20180426506889");
        customsDeclarationRequestMap.put("incoterm", "DDP");
        customsDeclarationRequestMap.put("b13a_filing_option", "FILED_ELECTRONICALLY");
        customsDeclarationRequestMap.put("b13a_number", "AA9999202008311");
        customsDeclarationRequestMap.put("metadata", "Order ID #123123");

        Map<String, Object> invoicedChargesMap = new HashMap<String, Object>();
        invoicedChargesMap.put("total_shipping", "1.23");
        invoicedChargesMap.put("total_taxes", "4.56");
        invoicedChargesMap.put("total_duties", "78.90");
        invoicedChargesMap.put("other_fees", "9.87");
        invoicedChargesMap.put("currency", "USD");
        customsDeclarationRequestMap.put("invoiced_charges", invoicedChargesMap);

        Map<String, Object> addressImporterMap = new HashMap<String, Object>();
        addressImporterMap.put("country", "US");
        customsDeclarationRequestMap.put("address_importer", addressImporterMap);

        Map<String, Object> taxIdMap = new HashMap<String, Object>();
        taxIdMap.put("number", "123456789");
        taxIdMap.put("type", "EIN");
        Map<String, Object> exporterIdentificationMap = new HashMap<String, Object>();
        exporterIdentificationMap.put("tax_id", taxIdMap);
        exporterIdentificationMap.put("eori_number", "PL123456789");
        customsDeclarationRequestMap.put("exporter_identification", exporterIdentificationMap);
    }

    @Test
    public void testValidCreate() {
        Shippo.setDEBUG(true);
        CustomsDeclaration testObject = createCustomsDeclarationFixture();
        assertEquals(testObject.getObjectState(), "VALID");
        assertEquals(Shippo.apiKeyIsTest, testObject.isTest());

        assertEquals(testObject.getCertifySigner(), customsDeclarationRequestMap.get("certify_signer"));
        assertEquals(testObject.getCertify(),
                Boolean.parseBoolean((String) customsDeclarationRequestMap.get("certify")));
        assertEquals(testObject.getNonDeliveryOption(), customsDeclarationRequestMap.get("non_delivery_option"));
        assertEquals(testObject.getContentsType(), customsDeclarationRequestMap.get("contents_type"));
        assertEquals(testObject.getContentsExplanation(), customsDeclarationRequestMap.get("contents_explanation"));
        assertEquals(testObject.getExporterReference(),
                nullToEmptyString(customsDeclarationRequestMap.get("exporter_reference")));
        assertEquals(testObject.getImporterReference(),
                nullToEmptyString(customsDeclarationRequestMap.get("importer_reference")));
        assertEquals(testObject.getInvoice(), nullToEmptyString(customsDeclarationRequestMap.get("invoice")));
        assertEquals(testObject.getLicense(), nullToEmptyString(customsDeclarationRequestMap.get("license")));
        assertEquals(testObject.getCertificate(), nullToEmptyString(customsDeclarationRequestMap.get("certificate")));
        assertEquals(testObject.getNotes(), nullToEmptyString(customsDeclarationRequestMap.get("notes")));
        assertEquals(testObject.getEelPfc(), nullToEmptyString(customsDeclarationRequestMap.get("eel_pfc")));
        assertEquals(testObject.getAesItn(), nullToEmptyString(customsDeclarationRequestMap.get("aes_itn")));
        assertEquals(testObject.getIncoterm(), nullToEmptyString(customsDeclarationRequestMap.get("incoterm")));
        assertEquals(testObject.getB13aFilingOption(),
                nullToEmptyString(customsDeclarationRequestMap.get("b13a_filing_option")));
        assertEquals(testObject.getB13aNumber(), nullToEmptyString(customsDeclarationRequestMap.get("b13a_number")));
        assertEquals(testObject.getMetadata(), nullToEmptyString(customsDeclarationRequestMap.get("metadata")));
        assertEquals(testObject.getInvoicedCharges(), customsDeclarationRequestMap.get("invoiced_charges"));
        assertEquals(testObject.getExporterIdentification(),
                customsDeclarationRequestMap.get("exporter_identification"));

        // address_importer should return a new Address ID, which is a nonempty string
        Object addressImporter = testObject.getAddressImporter();
        assertTrue(addressImporter instanceof String && ((String) addressImporter).length() > 0);
    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidCreate() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclaration.create(getInvalidObjectMap());
    }

    @Test
    public void testRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Shippo.setDEBUG(true);
        CustomsDeclaration testObject = createCustomsDeclarationFixture();
        CustomsDeclaration retrievedObject;

        retrievedObject = CustomsDeclaration.retrieve((String) testObject.objectId);

        assertEquals(testObject.objectId, retrievedObject.objectId);

    }

    @Test(expected = InvalidRequestException.class)
    public void testInvalidRetrieve() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclaration.retrieve("invalid_id");
    }

    @Test
    public void testListAll() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        CustomsDeclarationCollection objectCollection = CustomsDeclaration.all(null);
        assertNotNull(objectCollection.getData());
    }

    @Test
    public void testListPageSize() throws AuthenticationException, InvalidRequestException, APIConnectionException,
            APIException {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        objectMap.put("results", "1"); // one result per page
        objectMap.put("page", "1"); // the first page of results
        CustomsDeclarationCollection CustomsDeclarationCollection = CustomsDeclaration.all(objectMap);
        assertEquals(CustomsDeclarationCollection.getData().size(), 1);
    }

    public static CustomsDeclaration createCustomsDeclarationFixture() {
        CustomsItem customsItem = CustomsItemTest.createCustomsItemFixture();
        String[] arr = { customsItem.getObjectId() };
        customsDeclarationRequestMap.put("items", arr);

        try {
            return CustomsDeclaration.create(customsDeclarationRequestMap);
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}