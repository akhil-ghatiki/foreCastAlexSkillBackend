package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.services.ups.UpsServiceClient;
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
        UpsServiceClient upsService = handlerInput.getServiceClientFactory().getUpsService();
        String profileEmail = upsService.getProfileEmail();
        String userEmail = profileEmail;
        String userName = upsService.getProfileName();
        OrderInvoice orderInvoice = cartService.checkoutForUser(userEmail, userName);
        String speechText = orderInvoice.toString();
        String cardText = orderInvoice.toString();
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Orders Placed", cardText)
                .withReprompt(speechText)
                .build();
    }
}
