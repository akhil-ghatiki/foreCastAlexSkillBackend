package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.model.OrderInvoice;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@Slf4j
public class CheckoutIntent implements RequestHandler {
    CartService cartService = new CartService();

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
            return handlerInput.matches(intentName("CheckoutIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("Entering");
        String userEmail = "minions@email.com";//TODO Get UserProfile
        OrderInvoice orderInvoice = cartService.checkoutForUser(userEmail);
        String speechText = orderInvoice.toString();
        String cardText = orderInvoice.toString();
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Orders Placed", cardText)
                .withReprompt(speechText)
                .build();
    }
}
