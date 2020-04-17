package com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.ConfirmCheckoutIntent;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.GroceryItemIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
@Slf4j
public class NoIntentHandler implements RequestHandler {
    CartService cartService = new CartService();

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("AMAZON.NoIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("Entering");
        String userEmail = "minions@email.com";//TODO Get UserProfile
        String speechText = "Alright,";
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        if(sessionAttributes.get(Step.CURRENT_STEP) !=null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.GROCERY_ITEM)){
            return handleGroceryItem(handlerInput);
        }

        if(sessionAttributes.get(Step.CURRENT_STEP) !=null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.CONFIRM_CHECKOUT)){
            speechText = speechText.concat(" Clearing the Cart");
            //TODO Clear Add to Cart List
            cartService.clearCart(userEmail);
        }

        //TODO if items in cart with out checkout: ask for checkout
        if ( cartService.getCart(userEmail) != null ) {
            return handleConfirmCheckoutItem(handlerInput);
        }

        else speechText = speechText.concat(" Have a good day");

        return handlerInput.getResponseBuilder()
               .withSpeech(speechText)
               .withSimpleCard("Okay", "Have a good day")
               .build();
    }

    private Optional<Response> handleGroceryItem(HandlerInput handlerInput) {
        return new GroceryItemIntentHandler().handle(handlerInput);
    }

    private Optional<Response> handleConfirmCheckoutItem(HandlerInput handlerInput) {
        return new ConfirmCheckoutIntent().handle(handlerInput);
    }
}
