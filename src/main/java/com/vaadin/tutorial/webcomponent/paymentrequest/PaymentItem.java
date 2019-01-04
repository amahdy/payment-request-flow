package com.vaadin.tutorial.webcomponent.paymentrequest;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

@Tag("payment-item")
@HtmlImport("bower_components/payment-request/payment-item.html")
public class PaymentItem extends Component {

    public PaymentItem() {
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
     * When set to true this flag means that the amount member is not final.
     * This is commonly used to show items such as shipping or tax amounts
     * that depend upon selection of shipping address or shipping option.
     */
    private final String PROP_PENDING = "pending";
    public void setPending(Boolean pending) {
        getElement().setProperty(PROP_PENDING, pending);
    }
    public Boolean isPending() {
        return getElement().getProperty(PROP_PENDING, Boolean.FALSE);
    }

    private final String PROP_DICTIONARY = "dictionary";
    public String getDictionary() {
        return getElement().getProperty(PROP_DICTIONARY);
    }
}
