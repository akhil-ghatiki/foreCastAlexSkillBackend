package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class PlaceOrderIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(intentName("PlaceOrder"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        //TODO get the Add to card orders
        //TODO place the order for the above list of items
        //TODO get the invoice
        String speechText = "Order placed";
        String cardText = "Ordered Items : x,y,z. Total Ammount : 1323";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Orders Placed", cardText)
                .withReprompt(speechText)
                .build();
    }
}
