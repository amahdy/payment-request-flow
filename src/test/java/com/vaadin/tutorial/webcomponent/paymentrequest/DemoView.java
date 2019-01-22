package com.vaadin.tutorial.webcomponent.paymentrequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.Route;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

@Route("")
public class DemoView extends Div {

    private JsonArray createArray(List<String> array) {
        int i=0;
        JsonArray items = Json.createArray();
        for(String item: array) {
            items.set(i++, item);
        }

        return items;
    }

    private JsonObject createObject(Map<String, List<String>> map) {
        JsonObject items = Json.createObject();
        for(HashMap.Entry<String, List<String>> entry : map.entrySet()) {
            items.put(entry.getKey(), createArray(entry.getValue()));
        }

        return items;
    }

    public DemoView() {

        Map<String, List<String>> paymentMethodData = new HashMap<>();
        paymentMethodData.put("supportedNetworks",
            Arrays.asList("amex", "mastercard", "visa"));
        paymentMethodData.put("supportedTypes",
            Arrays.asList("debit", "credit"));

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.addSupported("basic-card");
        paymentMethod.setData(createObject(paymentMethodData));
        paymentMethod.getElement().setAttribute("slot", "method");

        PaymentItem paymentItem = new PaymentItem();
        paymentItem.setLabel("Item 1");
        paymentItem.setCurrency("EUR");
        paymentItem.setValue(1337D);

        PaymentItem paymentTotal = new PaymentItem();
        paymentTotal.getElement().setAttribute("slot", "total");

        Button buyButton = new Button("Buy");
        buyButton.getElement().setAttribute("id", "buyButton");

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setLabel("Total");
        paymentRequest.setCurrency("EUR");

        paymentRequest.getElement()
            .appendChild(paymentMethod.getElement())
            .appendChild(paymentItem.getElement())
            .appendChild(paymentTotal.getElement())
            .appendChild(buyButton.getElement());

        paymentRequest.addResponseListener(e -> {
            paymentRequest.getElement().executeJavaScript("this.lastResponse.complete()", "");

            JsonObject creditCardDetails = e.getDetail().get("details");
            (new Notification(new Html("<p>Server received the following information:<br>"
                + "<b>Card Number:</b> " + creditCardDetails.getString("cardNumber") + "<br>"
                + "<b>CVC:</b> " + creditCardDetails.getString("cardSecurityCode") + "<br>"
                + "<b>Full Name:</b> " + creditCardDetails.getString("cardholderName") + "<br>"
                + "<b>Expiry Month:</b> " + creditCardDetails.getString("expiryMonth") + "<br>"
                + "<b>Expiry Year:</b> " + creditCardDetails.getString("expiryYear") + "</p>"))).open();
        });

        add(paymentRequest);
    }
}
