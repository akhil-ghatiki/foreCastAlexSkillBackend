package com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.model.Response;
import java.util.Optional;

public class MyExceptionHandler implements ExceptionHandler {

  @Override
  public boolean canHandle(HandlerInput input, Throwable throwable) {
    return throwable instanceof AskSdkException;
  }

  @Override
  public Optional<Response> handle(HandlerInput input, Throwable throwable) {
    return input.getResponseBuilder()
        .withSpeech("Error Message")
        .build();
  }
}
