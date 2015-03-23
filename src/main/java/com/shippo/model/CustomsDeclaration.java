package com.shippo.model;

import java.util.Map;

import com.shippo.exception.APIConnectionException;
import com.shippo.exception.APIException;
import com.shippo.exception.AuthenticationException;
import com.shippo.exception.InvalidRequestException;
import com.shippo.net.APIResource;

public class CustomsDeclaration extends APIResource {

	String object_state;
	String object_id;
	String object_owner;
    Object object_created;
    Object object_updated;
    Object exporter_reference;
    Object importer_reference;
    Object contents_type;
    Object contents_explanation;
    Object invoice;
    Object license;
    Object certificate;
    Object notes;
    Object eel_pfc;
    Object aes_itn;
    Object non_delivery_option;
    Object certify;
    Object certify_signer;
    Object disclaimer;
    Object incoterm;
    Object items;
    Object metadata;

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

    public Object getObject_created() {
        return object_created;
    }

    public void setObject_created(Object object_created) {
        this.object_created = object_created;
    }

    public Object getObject_updated() {
        return object_updated;
    }

    public void setObject_updated(Object object_updated) {
        this.object_updated = object_updated;
    }

    public String getObject_id() {
        return object_id;
    }

    public void setObject_id(String object_id) {
        this.object_id = object_id;
    }

    public String getObject_owner() {
        return object_owner;
    }

    public void setObject_owner(String object_owner) {
        this.object_owner = object_owner;
    }

    public String getObject_state() {
        return object_state;
    }

    public void setObject_state(String object_state) {
        this.object_state = object_state;
    }

    public Object getExporter_reference() {
        return exporter_reference;
    }

    public void setExporter_reference(Object exporter_reference) {
        this.exporter_reference = exporter_reference;
    }

    public Object getImporter_reference() {
        return importer_reference;
    }

    public void setImporter_reference(Object importer_reference) {
        this.importer_reference = importer_reference;
    }

    public Object getContents_type() {
        return contents_type;
    }

    public void setContents_type(Object contents_type) {
        this.contents_type = contents_type;
    }

    public Object getContents_explanation() {
        return contents_explanation;
    }

    public void setContents_explanation(Object contents_explanation) {
        this.contents_explanation = contents_explanation;
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

    public Object getEel_pfc() {
        return eel_pfc;
    }

    public void setEel_pfc(Object eel_pfc) {
        this.eel_pfc = eel_pfc;
    }

    public Object getAes_itn() {
        return aes_itn;
    }

    public void setAes_itn(Object aes_itn) {
        this.aes_itn = aes_itn;
    }

    public Object getNon_delivery_option() {
        return non_delivery_option;
    }

    public void setNon_delivery_option(Object non_delivery_option) {
        this.non_delivery_option = non_delivery_option;
    }

    public Object getCertify() {
        return certify;
    }

    public void setCertify(Object certify) {
        this.certify = certify;
    }

    public Object getCertify_signer() {
        return certify_signer;
    }

    public void setCertify_signer(Object certify_signer) {
        this.certify_signer = certify_signer;
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

}
