package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class YesIntentHandler  implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.YesIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        if(sessionAttributes.get("currentStep") !=null && sessionAttributes.get("currentStep").equals(Step.GROCERY_LIST)){
            return handlePlaceOrder(handlerInput);
        }
        //TODO: yes can be called for various questions handle accordingly
       return handleGroveryListRequest(handlerInput);
    }

    private Optional<Response> handlePlaceOrder(HandlerInput handlerInput) {
        return new PlaceOrderIntent().handle(handlerInput);
    }

    private Optional<Response> handleGroveryListRequest(HandlerInput handlerInput) {
        return new GroceryListIntentHandler().handle(handlerInput);
    }
}
