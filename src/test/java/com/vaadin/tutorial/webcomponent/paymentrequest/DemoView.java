package com.vaadin.tutorial.webcomponent.paymentrequest;

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

    public DemoView() {
        JsonArray supportedNetwork = Json.createArray();
        supportedNetwork.set(0, "amex");
        supportedNetwork.set(1, "mastercard");
        supportedNetwork.set(2, "visa");

        JsonArray supportedTypes = Json.createArray();
        supportedTypes.set(0, "debit");
        supportedTypes.set(1, "credit");

        JsonObject paymentMethodData = Json.createObject();
        paymentMethodData.put("supportedNetwork", supportedNetwork);
        paymentMethodData.put("supportedTypes", supportedTypes);

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.addSupported("basic-card");
        paymentMethod.setData(paymentMethodData);
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
