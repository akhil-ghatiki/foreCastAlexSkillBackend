package com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers;

import static com.amazon.ask.request.Predicates.intentName;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.CheckoutIntent;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.GroceryItemIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.GroceryListIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

@Slf4j
public class YesIntentHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(intentName("AMAZON.YesIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    log.info("Entering");
    Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
    if(sessionAttributes.get(Step.CURRENT_STEP) != null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.CONFIRM_CHECKOUT)){
        return handleCheckout(handlerInput);
    }
    if(sessionAttributes.get(Step.CURRENT_STEP) != null
        && (sessionAttributes.get(Step.CURRENT_STEP).equals(Step.GROCERY_ITEM)
            || sessionAttributes.get(Step.CURRENT_STEP).equals(Step.GROCERY_LIST))){
        return handleGroceryItemRequest(handlerInput);
    }
    if(sessionAttributes.get(Step.CURRENT_STEP) != null && sessionAttributes.get(Step.CURRENT_STEP).equals(Step.TODO_LIST)){
        return handleGroceryListRequest(handlerInput);
    }
    //TODO: yes can be called for various questions handle accordingly
   return null;
  }

  private Optional<Response> handleGroceryItemRequest(HandlerInput handlerInput) {
    return new GroceryItemIntentHandler().handle(handlerInput);
  }
  private Optional<Response> handleCheckout(HandlerInput handlerInput) {
    return new CheckoutIntent().handle(handlerInput);
  }
  private Optional<Response> handleGroceryListRequest(HandlerInput handlerInput) {
    return new GroceryListIntentHandler().handle(handlerInput);
  }
}
