package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import static com.amazon.ask.request.Predicates.requestType;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;

import java.util.Map;
import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(requestType(LaunchRequest.class));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {

    //process the file, get count and kgs variable and into into this string
    String speechText = "Hey Minions, In your todo list for today we have ordering of groceries as one of the thing to do. Would you like to place an order?";

    Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
    sessionAttributes.put(Step.CURRENT_STEP, Step.TODO_LIST );

    return handlerInput.getResponseBuilder()
        .withSpeech(speechText)
        .withSimpleCard("Welcome", speechText)
        .withReprompt(speechText)
        .build();
  }
}
