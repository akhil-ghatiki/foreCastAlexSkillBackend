package com.thoughtworks.forecastAlexaSkillBackend.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(requestType(LaunchRequest.class));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    //process the file, get count and kgs variable and into into this string
    //String speechText = "Hello User, {count} people have purchased {kgs} of sugar. Do u want to place order as well ?";
    String speechText = "Hello Minnions!! Do You want to go over your grocery list? ";

    return input.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard("HelloWorld", speechText)
        .withReprompt(speechText)
        .build();
  }
}
