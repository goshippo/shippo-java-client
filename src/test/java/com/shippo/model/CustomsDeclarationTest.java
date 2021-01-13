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
        objectMap.put("exporter_reference", null);
        objectMap.put("importer_reference", null);
        objectMap.put("invoice", "#123123");
        objectMap.put("license", null);
        objectMap.put("certificate", null);
        objectMap.put("notes", null);
        objectMap.put("eel_pfc", "NOEEI_30_37_a");
        objectMap.put("aes_itn", null);
        objectMap.put("incoterm", null);
        objectMap.put("b13a_filing_option", "FILED_ELECTRONICALLY");
        objectMap.put("b13a_number", "AA9999202008311");
        objectMap.put("metadata", "Order ID #123123");
        objectMap.put("disclaimer", null);
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
        assertEquals(testObject.getDisclaimer(), nullToEmptyString(objectMap.get("disclaimer")));
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