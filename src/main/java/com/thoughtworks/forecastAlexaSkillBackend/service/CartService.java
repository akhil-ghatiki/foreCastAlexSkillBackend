package com.thoughtworks.forecastAlexaSkillBackend.service;

import com.thoughtworks.forecastAlexaSkillBackend.mart.RetailMart;
import com.thoughtworks.forecastAlexaSkillBackend.model.*;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class CartService {

  public OrderInvoice getCart(String userEmail) {
    return RetailMart.getInstance().getCartFor(userEmail);
  }

  public boolean addToCart(String userEmail, ShoppingItem item) {
    return RetailMart.getInstance().addToCart(userEmail, new CartItem(item));
  }

  public boolean clearCart(String userEmail) {
    return RetailMart.getInstance().clearCartFor(userEmail);
  }

  public boolean alreadyInCart(String userEmail, ShoppingItem item) {
    long noOfMatchedCartItems = RetailMart.getInstance()
            .getCartFor(userEmail).getOrderLineItems()
            .stream().filter(matchProductName(item.getName()))
            .count();
    return noOfMatchedCartItems > 0;
  }

  public OrderInvoice checkoutForUser(String userIdEmail) {
    return RetailMart.getInstance().checkout(userIdEmail);
  }

  public static Predicate<CartItem> matchProductName(String name) {
    return p -> p.getName().equalsIgnoreCase(name);
  }

  public OrderInvoice getOrderInvoice(String userId) {
    return RetailMart.getInstance().orderInvoice(userId);
  }
}
