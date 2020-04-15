package com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

public class CancelAndStopIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
        .withSpeech("goodbye")
        .withSimpleCard("HelloWorld", "goodbye")
        .build();
  }
}
