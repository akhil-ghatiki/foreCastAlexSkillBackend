package com.thoughtworks.forecastAlexaSkillBackend.model;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderInvoice {

  private String id;

  private Date orderDate;

  private User user;

  private Company company;

  private Double totalPrice;

  private List<CartItem> orderLineItems;

  public static class OrderInvoiceBuilder {
    public OrderInvoiceBuilder orderLineItems(List<CartItem> cartItems) {
      this.orderLineItems = cartItems;
      double totalPrice = 0;
      for (CartItem cartItem : cartItems) {
        totalPrice = totalPrice + cartItem.getPrice();
      }
      this.totalPrice = totalPrice;
      return this;
    }
  }

}
