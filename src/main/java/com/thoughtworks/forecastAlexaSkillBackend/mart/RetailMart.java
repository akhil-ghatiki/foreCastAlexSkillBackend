package com.thoughtworks.forecastAlexaSkillBackend.mart;

import com.thoughtworks.forecastAlexaSkillBackend.model.OrderInvoice;
import com.thoughtworks.forecastAlexaSkillBackend.model.CartItem;
import com.thoughtworks.forecastAlexaSkillBackend.model.Company;
import com.thoughtworks.forecastAlexaSkillBackend.model.User;

import java.util.*;

public class RetailMart {

  private static RetailMart retailMart = null;

  private static final Map<String, List<CartItem>> carts = new HashMap<>();
  private static final Map<String, OrderInvoice> ordersInvoiceList = new HashMap<>();

  private static final List<CartItem> list = new ArrayList<>();

  public static RetailMart getInstance() {
    if (retailMart == null) {
      retailMart = new RetailMart();
    }
    return retailMart;
  }

  public boolean addToCart(String userId, CartItem item) {
    List<CartItem> cartItems;
    if (carts.containsKey(userId)) {
      cartItems = carts.get(userId);
    } else {
      cartItems = new ArrayList<>();
      carts.put(userId, cartItems);
    }
    return cartItems.add(item);
  }

  public OrderInvoice orderInvoice(String userId) {
    return ordersInvoiceList.get(userId);
  }

  public OrderInvoice checkout(String userEmail, String userName) {
    OrderInvoice orderInvoice = OrderInvoice.builder()
            .orderLineItems(carts.get(userEmail))
            .id(UUID.randomUUID().toString())
            .orderDate(new Date())
            .user(getUser(userEmail, userName))
            .company(getCompany())
            .build();
    ordersInvoiceList.put(userEmail, orderInvoice);
    carts.remove(userEmail);

    return orderInvoice;
  }

  private User getUser(String userEmail, String userName) {
    return User.builder()
            .username(userName)
            .userEmail(userEmail)
            .build();
  }

  private Company getCompany() {
    return Company.builder()
            .companyName("RetailMart")
            .companyEmail("contactus@retailmart.com")
            .build();
  }

  public OrderInvoice getCartFor(String userEmail) {
    List<CartItem> cartItems = carts.get(userEmail);
    if (cartItems == null)
      return null;
    return OrderInvoice.builder().orderLineItems(cartItems).build();
  }

  public boolean clearCartFor(String userEmail) {
    carts.remove(userEmail);
    return true;
  }
}
