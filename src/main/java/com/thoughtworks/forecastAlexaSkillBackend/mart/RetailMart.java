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
    return carts.get(userId).add(item);
  }

  public OrderInvoice orderInvoice(String userId) {
    return ordersInvoiceList.get(userId);
  }

  public OrderInvoice checkout(String userEmail) {
    OrderInvoice orderInvoice = OrderInvoice.builder()
            .orderLineItems(carts.get(userEmail))
            .id(UUID.randomUUID().toString())
            .orderDate(new Date())
            .user(getUser(userEmail))
            .company(getCompany())
            .build();
    ordersInvoiceList.put(userEmail, orderInvoice);

    return orderInvoice;
  }

  private User getUser(String userEmail) {
    return User.builder()
            .username(userEmail.split("@")[0])
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
    return OrderInvoice.builder().orderLineItems(carts.get(userEmail)).build();
  }

  public boolean clearCartFor(String userEmail) {
    carts.remove(userEmail);
    return true;
  }
}
