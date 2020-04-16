package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ConfirmCheckoutIntent implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(intentName("ConfirmCheckoutIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
    sessionAttributes.put(Step.CURRENT_STEP, Step.CONFIRM_CHECKOUT);
    //TODO GET THE ADD TO CART list
    //TODO if no items in add to cart list, return to final exit confirmation Intent
    //TODO Call Order Placed Service with above list to get the Quotation of TOTAL AMOUNT
    String speechText = "Shall i confirm the order for xyz and 123 with total amount of Rs.abc";
    return handlerInput.getResponseBuilder()
      .withSpeech(speechText)
      .withSimpleCard("Confirm the Order", speechText)
      .withReprompt(speechText)
      .build();
  }
}
