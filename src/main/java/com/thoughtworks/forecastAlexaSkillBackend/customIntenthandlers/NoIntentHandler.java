package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NoIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.NoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Alright,";
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        if(sessionAttributes.get(Step.CURRENT_STEP) !=null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.GROCERY_ITEM)){
            return handleGroceryItem(handlerInput);
        }

        if(sessionAttributes.get(Step.CURRENT_STEP) !=null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.CONFIRM_ORDER)){
            speechText = speechText.concat(" Clearing the Cart");
            //TODO Clear Add to Cart List
        }
        speechText = speechText.concat(", Have a good day");
       return handlerInput.getResponseBuilder()
               .withSpeech(speechText)
               .withSimpleCard("Okay", "Have a good day")
               .build();
    }

    private Optional<Response> handleGroceryItem(HandlerInput handlerInput) {
        return new GroceryItemIntentHandler().handle(handlerInput);
    }

}
