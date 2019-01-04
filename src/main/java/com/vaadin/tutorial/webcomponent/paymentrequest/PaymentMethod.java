package com.vaadin.tutorial.webcomponent.paymentrequest;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

@Tag("payment-method")
@HtmlImport("bower_components/payment-request/payment-method.html")
public class PaymentMethod extends Component {

    public PaymentMethod() {
    }

    private final String PROP_SUPPORTED = "supported";
    public void addSupported(String supported) {
        JsonArray supportedItems;
        if(getElement().hasProperty(PROP_SUPPORTED)) {
            supportedItems =  (JsonArray) getElement().getPropertyRaw(PROP_SUPPORTED);
        }else {
            supportedItems = Json.createArray();
        }
        supportedItems.set(supportedItems.length(), supported);
        getElement().setPropertyJson(PROP_SUPPORTED, supportedItems);
    }
    public String[] getSupported() {
        JsonArray supported = (JsonArray) getElement().getPropertyRaw(PROP_SUPPORTED);
        String[] items = new String[supported.length()];
        for (int i=0; i<supported.length(); i++) {
            items[i] = supported.getString(i);
        }
        return items;
    }

    private final String PROP_DATA = "data";
    public void setData(JsonObject data) {
        getElement().setPropertyJson(PROP_DATA, data);
    }
    public JsonObject getData() {
        return (JsonObject) getElement().getPropertyRaw(PROP_DATA);
    }

    private final String PROP_DICTIONARY = "dictionary";
    public String getDictionary() {
        return getElement().getProperty(PROP_DICTIONARY);
    }
}
