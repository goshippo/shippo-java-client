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
    private static Map<String, Object> objectMap;
    static {
        objectMap = new HashMap<String, Object>();
        objectMap.put("certify_signer", "Laura Behrens Wu");
        objectMap.put("certify", "true");
        objectMap.put("non_delivery_option", "ABANDON");
        objectMap.put("contents_type", "MERCHANDISE");
        objectMap.put("contents_explanation", "T-Shirt purchase");
        objectMap.put("exporter_reference", "Exporter Reference");
        objectMap.put("importer_reference", "Importer Reference");
        objectMap.put("invoice", "#123123");
        objectMap.put("license", "License");
        objectMap.put("certificate", "Certificate");
        objectMap.put("notes", "Notes");
        objectMap.put("eel_pfc", "NOEEI_30_37_a");
        objectMap.put("aes_itn", "X20180426506889");
        objectMap.put("incoterm", "DDP");
        objectMap.put("b13a_filing_option", "FILED_ELECTRONICALLY");
        objectMap.put("b13a_number", "AA9999202008311");
        objectMap.put("metadata", "Order ID #123123");

        Map<String, Object> invoicedChargesMap = new HashMap<String, Object>();
        invoicedChargesMap.put("total_shipping", "1.23");
        invoicedChargesMap.put("total_taxes", "4.56");
        invoicedChargesMap.put("total_duties", "78.90");
        invoicedChargesMap.put("other_fees", "9.87");
        invoicedChargesMap.put("currency", "USD");
        objectMap.put("invoiced_charges", invoicedChargesMap);

        Map<String, Object> addressImporterMap = new HashMap<String, Object>();
        addressImporterMap.put("country", "US");
        objectMap.put("address_importer", addressImporterMap);

        Map<String, Object> taxIdMap = new HashMap<String, Object>();
        taxIdMap.put("number", "123456789");
        taxIdMap.put("type", "EIN");
        Map<String, Object> exporterIdentificationMap = new HashMap<String, Object>();
        exporterIdentificationMap.put("tax_id", taxIdMap);
        exporterIdentificationMap.put("eori_number", "PL123456789");
        objectMap.put("exporter_identification", exporterIdentificationMap);
    }

    @Test
    public void testValidCreate() {
		Shippo.setDEBUG(true);
        CustomsDeclaration testObject = (CustomsDeclaration) getDefaultObject();
        assertEquals(testObject.getObjectState(), "VALID");

        assertEquals(testObject.getCertifySigner(), objectMap.get("certify_signer"));
        assertEquals(testObject.getCertify(), Boolean.parseBoolean((String) objectMap.get("certify")));
        assertEquals(testObject.getNonDeliveryOption(), objectMap.get("non_delivery_option"));
        assertEquals(testObject.getContentsType(), objectMap.get("contents_type"));
        assertEquals(testObject.getContentsExplanation(), objectMap.get("contents_explanation"));
        assertEquals(testObject.getExporterReference(), nullToEmptyString(objectMap.get("exporter_reference")));
        assertEquals(testObject.getImporterReference(), nullToEmptyString(objectMap.get("importer_reference")));
        assertEquals(testObject.getInvoice(), nullToEmptyString(objectMap.get("invoice")));
        assertEquals(testObject.getLicense(), nullToEmptyString(objectMap.get("license")));
        assertEquals(testObject.getCertificate(), nullToEmptyString(objectMap.get("certificate")));
        assertEquals(testObject.getNotes(), nullToEmptyString(objectMap.get("notes")));
        assertEquals(testObject.getEelPfc(), nullToEmptyString(objectMap.get("eel_pfc")));
        assertEquals(testObject.getAesItn(), nullToEmptyString(objectMap.get("aes_itn")));
        assertEquals(testObject.getIncoterm(), nullToEmptyString(objectMap.get("incoterm")));
        assertEquals(testObject.getB13aFilingOption(), nullToEmptyString(objectMap.get("b13a_filing_option")));
        assertEquals(testObject.getB13aNumber(), nullToEmptyString(objectMap.get("b13a_number")));
        assertEquals(testObject.getMetadata(), nullToEmptyString(objectMap.get("metadata")));
        assertEquals(testObject.getInvoicedCharges(), objectMap.get("invoiced_charges"));
        assertEquals(testObject.getExporterIdentification(), objectMap.get("exporter_identification"));

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
        CustomsDeclaration testObject = (CustomsDeclaration) getDefaultObject();
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

    public static Object getDefaultObject() {
        CustomsItem customsItem = (CustomsItem) CustomsItemTest.getDefaultObject();
        String[] arr = {customsItem.getObjectId()};
        objectMap.put("items", arr);

        try {
            CustomsDeclaration testObject = CustomsDeclaration.create(objectMap);
            return testObject;
        } catch (ShippoException e) {
            e.printStackTrace();
        }
        return null;
    }
}