package com.vaadin.tutorial.webcomponent.paymentrequest;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import elemental.json.Json;
import elemental.json.JsonArray;

@Tag("payment-address")
@HtmlImport("bower_components/payment-request/payment-address.html")
public class PaymentAddress extends Component {

    public PaymentAddress() {
    }

    private final String PROP_COUNTRY = "country";
    public void setCountry(String country) {
        getElement().setProperty(PROP_COUNTRY, country);
    }
    public String getCountry() {
        return getElement().getProperty(PROP_COUNTRY);
    }

    private final String PROP_ADDRESS_LINE = "addressLine";
    public void addAddressLine(String addressLine) {
        JsonArray addressLines;
        if(getElement().hasProperty(PROP_ADDRESS_LINE)) {
            addressLines =  (JsonArray) getElement().getPropertyRaw(PROP_ADDRESS_LINE);
        }else {
            addressLines = Json.createArray();
        }
        addressLines.set(addressLines.length(), addressLine);
        getElement().setPropertyJson(PROP_ADDRESS_LINE, addressLines);
    }
    public String[] getAddressLines() {
        JsonArray addressLines = (JsonArray) getElement().getPropertyRaw(PROP_ADDRESS_LINE);
        String[] lines = new String[addressLines.length()];
        for (int i=0; i<addressLines.length(); i++) {
            lines[i] = addressLines.getString(i);
        }
        return lines;
    }

    private final String PROP_REGION = "region";
    public void setRegion(String region) {
        getElement().setProperty(PROP_REGION, region);
    }
    public String getRegion() {
        return getElement().getProperty(PROP_REGION);
    }

    private final String PROP_CITY = "city";
    public void setCity(String city) {
        getElement().setProperty(PROP_CITY, city);
    }
    public String getCity() {
        return getElement().getProperty(PROP_CITY);
    }

    private final String PROP_DEPENDENT_LOCALITY = "dependentLocality";
    public void setDependentLocality(String dependentLocality) {
        getElement().setProperty(PROP_DEPENDENT_LOCALITY, dependentLocality);
    }
    public String getDependentLocality() {
        return getElement().getProperty(PROP_DEPENDENT_LOCALITY);
    }

    private final String PROP_POSTAL_CODE = "postalCode";
    public void setPostalCode(String postalCode) {
        getElement().setProperty(PROP_POSTAL_CODE, postalCode);
    }
    public String getPostalCode() {
        return getElement().getProperty(PROP_POSTAL_CODE);
    }

    private final String PROP_SORTING_CODE = "sortingCode";
    public void setSortingCode(String sortingCode) {
        getElement().setProperty(PROP_SORTING_CODE, sortingCode);
    }
    public String getSortingCode() {
        return getElement().getProperty(PROP_SORTING_CODE);
    }

    private final String PROP_LANGUAGE_CODE = "languageCode";
    public void setLanguageCode(String languageCode) {
        getElement().setProperty(PROP_LANGUAGE_CODE, languageCode);
    }
    public String getLanguageCode() {
        return getElement().getProperty(PROP_LANGUAGE_CODE);
    }

    private final String PROP_ORGANIZATION = "organization";
    public void setOrganization(String organization) {
        getElement().setProperty(PROP_ORGANIZATION, organization);
    }
    public String getOrganization() {
        return getElement().getProperty(PROP_ORGANIZATION);
    }

    private final String PROP_RECIPIENT = "recipient";
    public void setRecipient(String recipient) {
        getElement().setProperty(PROP_RECIPIENT, recipient);
    }
    public String getRecipient() {
        return getElement().getProperty(PROP_RECIPIENT);
    }

    private final String PROP_PHONE = "phone";
    public void setPhone(String phone) {
        getElement().setProperty(PROP_PHONE, phone);
    }
    public String getPhone() {
        return getElement().getProperty(PROP_PHONE);
    }
}
