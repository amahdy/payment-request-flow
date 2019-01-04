package com.vaadin.tutorial.webcomponent.paymentrequest;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("payment-shipping-option")
@HtmlImport("bower_components/payment-request/payment-shipping-option.html")
public class PaymentShippingOption extends Component {

    public PaymentShippingOption() {
    }

    private final String PROP_ID = "id";
    public void setId_(String id) {
        getElement().setProperty(PROP_ID, id);
    }
    public String getId_() {
        return getElement().getProperty(PROP_ID);
    }

    /**
     * This is a human-readable description of the item.
     * The user agent may display this to the user.
     */
    private final String PROP_LABEL = "label";
    public void setLabel(String label) {
        getElement().setProperty(PROP_LABEL, label);
    }
    public String getLabel() {
        return getElement().getProperty(PROP_LABEL);
    }

    /**
     * A valid decimal monetary value containing a monetary amount of the item.
     */
    private final String PROP_VALUE = "value";
    public void setValue(Double value) {
        getElement().setProperty(PROP_VALUE, value);
    }
    public Double getValue() {
        return getElement().getProperty(PROP_VALUE, 0.0);
    }

    /**
     * A string containing a currency identifier of the item.
     * The value of currency can be any string that is valid within
     * the currency system indicated by currencySystem.
     */
    private final String PROP_CURRENCY = "currency";
    public void setCurrency(String currency) {
        getElement().setProperty(PROP_CURRENCY, currency);
    }
    public String getCurrency() {
        return getElement().getProperty(PROP_CURRENCY);
    }

    /**
     * A URL that indicates the currency system
     * that the currency identifier belongs to
     */
    private final String PROP_CURRENCY_SYSTEM = "currencySystem";
    public void setCurrencySystem(String currencySystem) {
        getElement().setProperty(PROP_CURRENCY_SYSTEM, currencySystem);
    }
    public String getCurrencySystem() {
        return getElement().getProperty(PROP_CURRENCY_SYSTEM, "urn:iso:std:iso:4217");
    }

    /**
     * Contain the monetary amount for the item.
     */
    private final String PROP_AMOUNT = "amount";
    public Object getAmount() {
        return getElement().getPropertyRaw(PROP_AMOUNT);
    }

    /**
     * This is set to true to indicate that this is the default
     * selected PaymentShippingOption in a sequence
     */
    private final String PROP_SELECTED = "selected";
    public void setSelected(Boolean selected) {
        getElement().setProperty(PROP_SELECTED, selected);
    }
    public Boolean isSelected() {
        return getElement().getProperty(PROP_SELECTED, Boolean.FALSE);
    }

    private final String PROP_DICTIONARY = "dictionary";
    public String getDictionary() {
        return getElement().getProperty(PROP_DICTIONARY);
    }
}
