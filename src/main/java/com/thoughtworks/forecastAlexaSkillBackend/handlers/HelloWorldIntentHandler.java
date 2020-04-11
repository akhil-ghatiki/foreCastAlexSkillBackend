package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("HelloWorldntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    final String speakOut = "Hello !";
    return input.getResponseBuilder()
        .withSpeech(speakOut)
        .withReprompt(speakOut)
        .build();
  }
}
