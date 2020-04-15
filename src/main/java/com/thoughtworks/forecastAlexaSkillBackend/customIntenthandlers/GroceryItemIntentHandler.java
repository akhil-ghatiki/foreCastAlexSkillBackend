package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.config.Constants;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GroceryItemIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("grocery_item"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Would you like to order ";
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put("currentStep", Step.GROCERY_ITEM );
        ShoppingList suggestedShoppingList = sessionAttributes.get(Constants.GROCERIES_LIST) != null
                ? ShoppingList.BuildShoppingList((List<Map<String, String>>) sessionAttributes.get(Constants.GROCERIES_LIST))
                : null;

        //Entry into Grocery Items Loop, set the index to 0
        if ( sessionAttributes.get("currentShoppingItem") == null )
            sessionAttributes.put("currentShoppingItem", 0 );

        int currentItemIndex = (int) sessionAttributes.get("currentShoppingItem");

        //All the items are asked for ordering
        if ( suggestedShoppingList.size() == currentItemIndex+1 ) {
            sessionAttributes.remove("currentShoppingItem");
            handleConfirmPlaceOrderItem(handlerInput);
        }

        speechText.concat(suggestedShoppingList.get(currentItemIndex).getName() + " ?");
        //TODO Add to Cart

        //if more items in the grocery list
        if ( suggestedShoppingList.size() > currentItemIndex+1 )
            sessionAttributes.put("currentShoppingItem", currentItemIndex+1 );


        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(speechText)
                .build();
    }

    private Optional<Response> handleConfirmPlaceOrderItem(HandlerInput handlerInput) {
        return new ConfirmPlaceOrderIntent().handle(handlerInput);
    }
}
