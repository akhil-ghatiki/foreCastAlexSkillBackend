package com.thoughtworks.forecastAlexaSkillBackend.service;

import com.thoughtworks.forecastAlexaSkillBackend.cart.CartList;
import com.thoughtworks.forecastAlexaSkillBackend.model.Cart;
import com.thoughtworks.forecastAlexaSkillBackend.model.Company;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingItem;
import com.thoughtworks.forecastAlexaSkillBackend.model.User;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  private List<ShoppingItem> getCartItems() {
    return CartList.getInstance().getList();
  }

  public Cart getCart() {
    return Cart.builder()
        .cartItems(getCartItems())
        .id("CART6345")
        .orderDate(new Date())
        .user(getUSer())
        .company(getCompany())
        .build();
  }

  private User getUSer() {
    return User.builder()
        .username("xyz")
        .userEmail("xyz@nomail.com")
        .build();
  }

  private Company getCompany() {
    return Company.builder()
        .companyName("RetailMart")
        .companyEmail("contactus@retailmart.com")
        .build();
  }
}
