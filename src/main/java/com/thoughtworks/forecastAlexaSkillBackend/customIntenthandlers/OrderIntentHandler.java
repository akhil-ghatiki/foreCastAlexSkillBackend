package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Slots;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class OrderIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("OrderIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Step.CURRENT_STEP, Step.GROCERY_ITEM );

        //TODO Add to Cart, Item already in Cart

        System.out.println("OrderIntent with slots as :"+requestHelper.getSlot(Slots.ORDER_NAME_SLOT)+requestHelper.getSlot(Slots.CONFIRM_CHECKOUT_SLOT));

        if (requestHelper.getSlot(Slots.CONFIRM_CHECKOUT_SLOT).toString().equals("yes")) {
            return handleConfirmCheckoutItem(handlerInput);
        }
        return handleGroceryList(handlerInput);
    }

    private Optional<Response> handleCheckOut(HandlerInput handlerInput) {
        return new CheckoutIntent().handle(handlerInput);
    }

    private Optional<Response> handleConfirmCheckoutItem(HandlerInput handlerInput) {
        return new ConfirmCheckoutIntent().handle(handlerInput);
    }
    private Optional<Response> handleGroceryList(HandlerInput handlerInput) {
        return new GroceryListIntentHandler().handle(handlerInput);
    }
}
