package com.vaadin.tutorial.webcomponent.paymentrequest;

import java.util.function.Consumer;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.EventData;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.shared.Registration;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;

@Tag("payment-request")
@HtmlImport("bower_components/payment-request/payment-request.html")
public class PaymentRequest extends Component {

    private static class CustomEvent extends ComponentEvent<PaymentRequest> {
        private JsonObject detail;

        public CustomEvent(
            PaymentRequest source, boolean fromClient, JsonObject detail) {
            super(source, fromClient);
            setDetail(detail);
        }

        public JsonObject getDetail() {
            return detail;
        }
        public void setDetail(JsonObject detail) {
            this.detail = detail;
        }
    }

    @DomEvent("response")
    public static class ResponseEvent extends CustomEvent {
        public ResponseEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }
    @DomEvent("request")
    public static class RequestEvent extends CustomEvent {
        public RequestEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }
    @DomEvent("aborted")
    public static class AbortedEvent extends CustomEvent {
        public AbortedEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }
    @DomEvent("error")
    public static class ErrorEvent extends CustomEvent {
        public ErrorEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }
    @DomEvent("can-make-payment")
    public static class CanMakePaymentEvent extends CustomEvent {
        public CanMakePaymentEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }
    @DomEvent("cannot-make-payment")
    public static class CannotMakePaymentEvent extends CustomEvent {
        public CannotMakePaymentEvent(
            PaymentRequest source, boolean fromClient,
            @EventData("event.detail")JsonObject detail) {
            super(source, fromClient, detail);
        }
    }

    public PaymentRequest() {
    }

    /**
     * Fired when user interaction begins for the payment request.
     *
     * @event response
     * @param {PaymentResponse} paymentResponse The payment information to process.
     */
    public Registration addResponseListener(
        ComponentEventListener<ResponseEvent> listener) {
        return addListener(ResponseEvent.class, listener);
    }

    /**
     * Fired when a PaymentRequest is created.
     *
     * @event request
     * @param {PaymentRequest} paymentRequest The payment request.
     */
    public Registration addRequestListener(
        ComponentEventListener<RequestEvent> listener) {
        return addListener(RequestEvent.class, listener);
    }

    /**
     * Fired when the payment request is aborted
    *
    * @event aborted
    */
    public Registration addAbortedListener(
        ComponentEventListener<AbortedEvent> listener) {
        return addListener(AbortedEvent.class, listener);
    }

    /**
     * Fired when payment request generate an error.
    *
    * @event error
    * @param {Error} error The request error.
    */
    public Registration addErrorListener(
        ComponentEventListener<ErrorEvent> listener) {
        return addListener(ErrorEvent.class, listener);
    }

    /**
     * Fired when PaymentRequest object can be used to make a payment.
    *
    * @event can-make-payment
    */
    public Registration addCanMakePaymentListener(
        ComponentEventListener<CanMakePaymentEvent> listener) {
        return addListener(CanMakePaymentEvent.class, listener);
    }

    /**
     * Fired when PaymentRequest object cannot be used to make a payment.
    *
    * @event cannot-make-payment
    */
    public Registration addCannotMakePaymentListener(
        ComponentEventListener<CannotMakePaymentEvent> listener) {
        return addListener(CannotMakePaymentEvent.class, listener);
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
        return getElement().getProperty(PROP_CURRENCY, "EUR");
    }

    /**
     * Contains line items for the payment request that the user agent may display.
     */
    private final String PROP_ITEMS = "items";
    public String[] getItems() {
        JsonArray items = (JsonArray) getElement().getPropertyRaw(PROP_ITEMS);
        String[] allItems = new String[items.length()];
        for (int i=0; i<items.length(); i++) {
            allItems[i] = items.getString(i);
        }
        return allItems;
    }

    /**
     * Contains the total amount of the payment request.
     */
    private final String PROP_TOTAL = "total";
    public JsonObject getTotal() {
        return (JsonObject) getElement().getPropertyRaw(PROP_TOTAL);
    }

    /**
     * Is used to store supported payment methods and
     * any associated payment method specific data for those methods.
     */
    private final String PROP_METHODS = "methods";
    public String[] getMethods() {
        JsonArray methods = (JsonArray) getElement().getPropertyRaw(PROP_METHODS);
        String[] allMethods = new String[methods.length()];
        for (int i=0; i<methods.length(); i++) {
            allMethods[i] = methods.getString(i);
        }
        return allMethods;
    }

    /**
     * Provides information about the requested transaction.
     */
    private final String PROP_DETAILS = "details";
    public String getDetails() {
        return getElement().getProperty(PROP_DETAILS);
    }

    private final String PROP_PAYER_NAME = "payerName";
    public void setPayerName(Boolean payerName) {
        getElement().setProperty(PROP_PAYER_NAME, payerName);
    }
    public Boolean isPayerName() {
        return getElement().getProperty(PROP_PAYER_NAME, Boolean.FALSE);
    }

    private final String PROP_PAYER_EMAIL = "payerEmail";
    public void setPayerEmail(Boolean payerEmail) {
        getElement().setProperty(PROP_PAYER_EMAIL, payerEmail);
    }
    public Boolean isPayerEmail() {
        return getElement().getProperty(PROP_PAYER_EMAIL, Boolean.FALSE);
    }

    private final String PROP_PAYER_PHONE = "payerPhone";
    public void setPayerPhone(Boolean payerPhone) {
        getElement().setProperty(PROP_PAYER_PHONE, payerPhone);
    }
    public Boolean isPayerPhone() {
        return getElement().getProperty(PROP_PAYER_PHONE, Boolean.FALSE);
    }

    private final String PROP_SHIPPING = "shipping";
    public void setShipping(Boolean shipping) {
        getElement().setProperty(PROP_SHIPPING, shipping);
    }
    public Boolean isShipping() {
        return getElement().getProperty(PROP_SHIPPING, Boolean.FALSE);
    }

    private final String PROP_SHIPPING_TYPE = "shippingType";
    public void setShippingType(Boolean shippingType) {
        getElement().setProperty(PROP_SHIPPING_TYPE, shippingType);
    }
    public Boolean isShippingType() {
        return getElement().getProperty(PROP_SHIPPING_TYPE, Boolean.FALSE);
    }

    private final String PROP_OPTIONS = "options";
    public JsonObject getOptions() {
        return (JsonObject) getElement().getPropertyRaw(PROP_OPTIONS);
    }

    private final String PROP_SHIPPING_OPTIONS = "shippingOptions";
    public void addShippingOption(String shippingOption) {
        JsonArray shippingOptions;
        if(getElement().hasProperty(PROP_SHIPPING_OPTIONS)) {
            shippingOptions =  (JsonArray) getElement().getPropertyRaw(PROP_SHIPPING_OPTIONS);
        }else {
            shippingOptions = Json.createArray();
        }
        shippingOptions.set(shippingOptions.length(), shippingOption);
        getElement().setPropertyJson(PROP_SHIPPING_OPTIONS, shippingOptions);
    }
    public String[] getShippingOptions() {
        JsonArray shippingOptions = (JsonArray) getElement().getPropertyRaw(PROP_SHIPPING_OPTIONS);
        String[] options = new String[shippingOptions.length()];
        for (int i=0; i<shippingOptions.length(); i++) {
            options[i] = shippingOptions.getString(i);
        }
        return options;
    }

    private final String PROP_SHIPPING_OPTION_SELECTED = "shippingOptionSelected";
    public void setShippingOptionSelected(String shippingOptionSelected) {
        getElement().setProperty(PROP_SHIPPING_OPTION_SELECTED, shippingOptionSelected);
    }
    public String getShippingOptionSelected() {
        return getElement().getProperty(PROP_SHIPPING_OPTION_SELECTED);
    }

    private final String PROP_SHIPPING_ITEM = "shippingItem";
    public void setShippingItem(JsonObject shippingItem) {
        getElement().setPropertyJson(PROP_SHIPPING_ITEM, shippingItem);
    }
    public JsonObject getShippingItem() {
        return (JsonObject) getElement().getPropertyRaw(PROP_SHIPPING_ITEM);
    }

    private final String PROP_LAST_REQUEST = "lastRequest";
    public void setLastRequest(JsonObject lastRequest) {
        getElement().setPropertyJson(PROP_LAST_REQUEST, lastRequest);
    }
    public JsonObject getLastRequest() {
        return (JsonObject) getElement().getPropertyRaw(PROP_LAST_REQUEST);
    }

    private final String PROP_LAST_RESPONSE = "lastResponse";
    public JsonObject getLastResponse() {
        return (JsonObject) getElement().getPropertyRaw(PROP_LAST_RESPONSE);
    }

    private final String PROP_LAST_ERROR = "lastError";
    public JsonObject getLastError() {
        return (JsonObject) getElement().getPropertyRaw(PROP_LAST_ERROR);
    }

    private final String PROP_LAST_CAN_MAKE_PAYMENT = "lastCanMakePayment";
    public void setLastCanMakePayment(Boolean lastCanMakePayment) {
        getElement().setProperty(PROP_LAST_CAN_MAKE_PAYMENT, lastCanMakePayment);
    }
    public Boolean isLastCanMakePayment() {
        return getElement().getProperty(PROP_LAST_CAN_MAKE_PAYMENT, Boolean.TRUE);
    }

    public void updateLastRequest(String[] methods, String details, JsonObject options) {
        getElement().callFunction("updateLastRequest", methods, details, options);
    }

    public void addRequestListeners() {
        getElement().callFunction("addRequestListeners");
    }

    public void removeRequestListeners() {
        getElement().callFunction("removeRequestListeners");
    }

    /**
     * Method executed when payButton is tapped.
     * You can override it to do something more complex.
     */
    public void buyButtonTap() {
        getElement().callFunction("buyButtonTap");
    }

    /**
     * Determine if the PaymentRequest object can be used to make a payment.
     *
     * @return {Promise}
     */
    public void checkCanMakePayment(String[] methods, JsonObject lastRequest, Consumer<JsonObject> output)  {
        getElement().callFunction("checkCanMakePayment", methods, lastRequest);
        // TODO: return 
    }

    /**
     * Begin user interaction for the payment request.
     *
     * @return {Promise}
     */
    public void show() {
        getElement().callFunction("show");
        // TODO: return
    }

    /**
     * Abort the payment request
     * @return {Promise}
     */
    public void abort(Consumer<JsonObject> output) {
        getElement().callFunction("abort");
        // TODO: return
    }

    public void onShippingAddressChange(JsonArray evt) {
        getElement().callFunction("onShippingAddressChange", evt);
    }

    public void onShippingOptionChange(JsonArray evt) {
        getElement().callFunction("onShippingOptionChange", evt);
    }

    public void updateWithShippingAddress(JsonArray evt) {
        getElement().callFunction("updateWithShippingAddress", evt);
        // TODO: return
    }

    public void updateWithShippingOptions(JsonArray evt) {
        getElement().callFunction("updateWithShippingOptions", evt);
        // TODO: return
    }

    public void changeShippingOption(JsonArray shippingOption) {
        getElement().callFunction("updateWithShippingOptions", shippingOption);
    }
}
