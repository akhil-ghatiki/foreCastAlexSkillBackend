package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

public class PlaceOrderIntent implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(intentName("PlaceOrder"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    String speechText = "Order placed";
    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard("HelloWorld", speechText)
        .withReprompt(speechText)
        .build();
  }
}
