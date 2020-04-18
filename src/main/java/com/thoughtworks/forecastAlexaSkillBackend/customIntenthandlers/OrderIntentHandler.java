package com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.RequestHelper;
import com.thoughtworks.forecastAlexaSkillBackend.config.Constants;
import com.thoughtworks.forecastAlexaSkillBackend.domain.ShoppingListReader;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Slots;
import com.thoughtworks.forecastAlexaSkillBackend.domain.Step;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingItem;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

@Slf4j
public class OrderIntentHandler implements RequestHandler {
    CartService cartService = new CartService();

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("OrderIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        log.info("Entering");
        String profileEmail = handlerInput.getServiceClientFactory().getUpsService().getProfileEmail();
        String userEmail = profileEmail;
        RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Step.CURRENT_STEP, Step.GROCERY_ITEM );

        String orderName = requestHelper.getSlot(Slots.ORDER_NAME_SLOT).get().getValue();
        Double orderQuantity = Double.valueOf(requestHelper.getSlot(Slots.ORDER_QUANTITY_SLOT).get().getValue());

        ShoppingList suggestedShoppingList = sessionAttributes.get(Constants.GROCERY_LIST) != null
                ? ShoppingList.BuildShoppingList((List<Map<String, Object>>) sessionAttributes.get(Constants.GROCERY_LIST))
                : new ShoppingListReader().suggestedList();
        Optional<ShoppingItem> productAvailable = suggestedShoppingList.stream().filter(item -> item.getName().equalsIgnoreCase(orderName)).findFirst();
        if ( !productAvailable.isPresent() ) {
            return handlerInput.getResponseBuilder()
                    .withSpeech("Incorrect Order Information. Say 'I want to order' to retry ")
                    .withSimpleCard("!!!!", "Incorrect Order Information")
                    .build();
        }
        ShoppingItem itemInSuggestion = productAvailable.get();
        itemInSuggestion.setQuantity(orderQuantity);
        //TODO Add to Cart, Item already in Cart
        cartService.addToCart(userEmail, new ShoppingItem(
                orderName,
                orderQuantity,
                itemInSuggestion.getUnit(),
                itemInSuggestion.getUnitPrice()
                ));

        System.out.println("OrderIntent with slots as :"+requestHelper.getSlot(Slots.ORDER_NAME_SLOT)+requestHelper.getSlot(Slots.CONFIRM_CHECKOUT_SLOT));

        if (requestHelper.getSlot(Slots.CONFIRM_CHECKOUT_SLOT).get().getValue().equals("yes")) {
            return handleConfirmCheckoutItem(handlerInput);
        }
        return handleGroceryList(handlerInput);
    }

    private Optional<Response> handleConfirmCheckoutItem(HandlerInput handlerInput) {
        return new ConfirmCheckoutIntent().handle(handlerInput);
    }
    private Optional<Response> handleGroceryList(HandlerInput handlerInput) {
        return new GroceryListIntentHandler().handle(handlerInput);
    }
}
