package com.thoughtworks.forecastAlexaSkillBackend.cart;

import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingItem;
import java.util.ArrayList;
import java.util.List;

public class CartList {

  private static CartList cartList = null;

  private static final List<ShoppingItem> list = new ArrayList<>();

  private CartList() {
    // TODO this should be removed.
    ShoppingItem defaultItem = new ShoppingItem("testName", 5, "kg", "1", 50);
    list.add(defaultItem);
  }

  public static CartList getInstance() {
    if (cartList == null) {
      cartList = new CartList();
    }
    return cartList;
  }

  public List<ShoppingItem> getList() {
    return list;
  }

  public void updateCartWith(ShoppingItem item) {
    list.add(item);
  }
}
