package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class CustomsDeclaration extends APIResource {

	String objectState;
	String objectId;
	String objectOwner;
    Object objectCreated;
    Object objectUpdated;
    Object exporterReference;
    Object importerReference;
    Object contentsType;
    Object contentsExplanation;
    Object invoice;
    Object license;
    Object certificate;
    Object notes;
    Object eelPfc;
    Object aesItn;
    Object nonDeliveryOption;
    Object certify;
    Object certifySigner;
    Object disclaimer;
    Object incoterm;
    Object items;
    Object metadata;
    Object addressImport;

    public static CustomsDeclaration create(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return create(params, null);
    }

    public String getInstanceURL() {
        return "";
    }

    public static CustomsDeclaration create(Map<String, Object> params, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.POST, classURL(CustomsDeclaration.class), params, CustomsDeclaration.class, apiKey);
    }

    public static CustomsDeclaration retrieve(String id) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return retrieve(id, null);
    }

    public static CustomsDeclaration retrieve(String id, String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, instanceURL(CustomsDeclaration.class, id), null, CustomsDeclaration.class,
                apiKey);
    }

    public static CustomsDeclarationCollection all(Map<String, Object> params) throws AuthenticationException,
            InvalidRequestException, APIConnectionException, APIException {
        return all(params, null);
    }

    public static CustomsDeclarationCollection all(Map<String, Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return request(RequestMethod.GET, classURL(CustomsDeclaration.class), params,
                CustomsDeclarationCollection.class, apiKey);
    }

	public String getObjectState() {
		return objectState;
	}

	public void setObjectState(String objectState) {
		this.objectState = objectState;
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

	public Object getObjectCreated() {
		return objectCreated;
	}

	public void setObjectCreated(Object objectCreated) {
		this.objectCreated = objectCreated;
	}

	public Object getObjectUpdated() {
		return objectUpdated;
	}

	public void setObjectUpdated(Object objectUpdated) {
		this.objectUpdated = objectUpdated;
	}

	public Object getExporterReference() {
		return exporterReference;
	}

	public void setExporterReference(Object exporterReference) {
		this.exporterReference = exporterReference;
	}

	public Object getImporterReference() {
		return importerReference;
	}

	public void setImporterReference(Object importerReference) {
		this.importerReference = importerReference;
	}

	public Object getContentsType() {
		return contentsType;
	}

	public void setContentsType(Object contentsType) {
		this.contentsType = contentsType;
	}

	public Object getContentsExplanation() {
		return contentsExplanation;
	}

	public void setContentsExplanation(Object contentsExplanation) {
		this.contentsExplanation = contentsExplanation;
	}

	public Object getInvoice() {
		return invoice;
	}

	public void setInvoice(Object invoice) {
		this.invoice = invoice;
	}

	public Object getLicense() {
		return license;
	}

	public void setLicense(Object license) {
		this.license = license;
	}

	public Object getCertificate() {
		return certificate;
	}

	public void setCertificate(Object certificate) {
		this.certificate = certificate;
	}

	public Object getNotes() {
		return notes;
	}

	public void setNotes(Object notes) {
		this.notes = notes;
	}

	public Object getEelPfc() {
		return eelPfc;
	}

	public void setEelPfc(Object eelPfc) {
		this.eelPfc = eelPfc;
	}

	public Object getAesItn() {
		return aesItn;
	}

	public void setAesItn(Object aesItn) {
		this.aesItn = aesItn;
	}

	public Object getNonDeliveryOption() {
		return nonDeliveryOption;
	}

	public void setNonDeliveryOption(Object nonDeliveryOption) {
		this.nonDeliveryOption = nonDeliveryOption;
	}

	public Object getCertify() {
		return certify;
	}

	public void setCertify(Object certify) {
		this.certify = certify;
	}

	public Object getCertifySigner() {
		return certifySigner;
	}

	public void setCertifySigner(Object certifySigner) {
		this.certifySigner = certifySigner;
	}

	public Object getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(Object disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Object getIncoterm() {
		return incoterm;
	}

	public void setIncoterm(Object incoterm) {
		this.incoterm = incoterm;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}

	public Object getAdressImport() {
		return metadata;
	}

	public void setAddressImport(Object addressImport) {
		this.addressImport = addressImport;
	}
}
