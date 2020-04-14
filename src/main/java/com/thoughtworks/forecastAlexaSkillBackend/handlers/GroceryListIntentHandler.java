package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.ShoppingListReader;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GroceryListIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("grocery_list"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        ShoppingListReader shoppingListReader = new ShoppingListReader();
        ShoppingList suggestedShoppingList = shoppingListReader.suggestedList();
        String speechText= "Items that are due in ordering are:";
        speechText = speechText.concat(suggestedShoppingList.toString()).concat(" Do you want to place an order for these?");
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("currentStep", Step.GROCERY_LIST );
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }
}
