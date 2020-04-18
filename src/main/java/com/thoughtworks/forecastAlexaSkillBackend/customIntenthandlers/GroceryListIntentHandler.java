package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.config.Constants;
import com.thoughtworks.forecastAlexaSkillBackend.domain.ShoppingListReader;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@Slf4j
public class GroceryListIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("GroceryListIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("Entering");
        ShoppingListReader shoppingListReader = new ShoppingListReader();
        ShoppingList suggestedShoppingList = shoppingListReader.suggestedList();
        String speechText= "Items that are due in ordering are:";
        speechText = speechText.concat(suggestedShoppingList.toString()).concat(" would you like to proceed?");
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Step.PREVIOUS_STEP, Step.GROCERY_LIST );
        if (suggestedShoppingList != null && !(suggestedShoppingList.size() > 0)) {
            speechText = "No Items are due for ordering. Have a good day.";
            return handlerInput.getResponseBuilder()
                    .withSpeech(speechText)
                    .withSimpleCard("Bye", speechText)
                    .withReprompt(speechText)
                    .withShouldEndSession(true)
                    .build();
        }

        sessionAttributes.put(Constants.GROCERY_LIST, suggestedShoppingList );

        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Grocery List", speechText)
                .withReprompt(speechText)
                .build();
    }
}
