package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.OrderInvoice;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@Slf4j
public class ConfirmCheckoutIntent implements RequestHandler {
  CartService cartService = new CartService();

  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(intentName("ConfirmCheckoutIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    log.info("Entering");
    Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
    sessionAttributes.put(Step.PREVIOUS_STEP, Step.CONFIRM_CHECKOUT);
    String profileEmail = handlerInput.getServiceClientFactory().getUpsService().getProfileEmail();
    String userEmail = profileEmail;
    OrderInvoice orderPreview = cartService.getCart(userEmail);
    //TODO GET THE ADD TO CART list
    //TODO if no items in add to cart list, return to final exit confirmation Intent
    if(orderPreview == null) {
      return handlerInput.getResponseBuilder()
              .withSpeech("No items in cart to checkout")
              .withSimpleCard("Confirm the Order?", orderPreview.ordersText()+"\nTotal Amount is Rs."+orderPreview.getTotalPrice())
              .withReprompt("No items in cart to checkout")
              .build();
    }
    //TODO Call Order Placed Service with above list to get the Quotation of TOTAL AMOUNT
    String speechText = "Shall i confirm the order for " + orderPreview.ordersText() + "with Total amount of Rs." + orderPreview.getTotalPrice();
    return handlerInput.getResponseBuilder()
      .withSpeech(speechText)
      .withSimpleCard("Confirm the Order?", orderPreview.ordersText()+"\nTotal Amount is Rs."+orderPreview.getTotalPrice())
      .withReprompt(speechText)
      .build();
  }
}
