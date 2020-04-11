package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;

public class ErrorHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return true;
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    final String speakOut = "Error in processing the request";
    return input.getResponseBuilder()
        .withSpeech(speakOut)
        .withReprompt(speakOut)
        .build();
  }
}
