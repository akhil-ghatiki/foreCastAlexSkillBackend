package com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers;


import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

// 2018-July-09: AMAZON.FallackIntent is only currently available in en-US locale.
//              This handler will not be triggered except in that locale, so it can be
//              safely deployed for any locale.
public class FallbackIntentHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("AMAZON.FallbackIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
        .withSpeech("fallback")
        .withSimpleCard("HelloWorld", "fallback")
        .withReprompt("fallback")
        .build();
  }
}
