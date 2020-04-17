package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.config.Constants;
import com.thoughtworks.forecastAlexaSkillBackend.domain.ShoppingListReader;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingItem;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@Slf4j
public class GroceryItemIntentHandler implements RequestHandler {
    CartService cartService = new CartService();

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("GroceryItemIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("Entering");
        String userEmail = "minions@email.com";//TODO Get UserProfile
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Step.CURRENT_STEP, Step.GROCERY_ITEM );
        ShoppingList suggestedShoppingList = sessionAttributes.get(Constants.GROCERY_LIST) != null
                ? ShoppingList.BuildShoppingList((List<Map<String, Object>>) sessionAttributes.get(Constants.GROCERY_LIST))
                : new ShoppingListReader().suggestedList();

        //Entry into Grocery Items Loop, set the index to 0
        if ( sessionAttributes.get(Step.CURRENT_INDEX) == null )
            sessionAttributes.put(Step.CURRENT_INDEX, 0 );

        int currentItemIndex = (int) sessionAttributes.get(Step.CURRENT_INDEX);

        //All the items are asked for ordering
        if ( suggestedShoppingList.size() == currentItemIndex ) {
            sessionAttributes.remove(Step.CURRENT_INDEX);
            return handleConfirmCheckoutItem(handlerInput);
        }
        ShoppingItem suggestedItem = suggestedShoppingList.get(currentItemIndex);

        if ( currentItemIndex != 0 ) {
            //TODO if the current item in the list is already in AddToCart move to next suggestion
            if (cartService.alreadyInCart(userEmail, suggestedItem)) {
                return handle(handlerInput);
            }
            //TODO Add to Cart
            cartService.addToCart(userEmail, suggestedItem);
        }
        sessionAttributes.put(Step.CURRENT_INDEX, currentItemIndex+1 );
        String speechText = "Would you like to order "
                .concat(suggestedItem.toString() + " ?");
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Order?", speechText)
                .withReprompt(speechText)
                .build();
    }

    private Optional<Response> handleConfirmCheckoutItem(HandlerInput handlerInput) {
        return new ConfirmCheckoutIntent().handle(handlerInput);
    }
}
