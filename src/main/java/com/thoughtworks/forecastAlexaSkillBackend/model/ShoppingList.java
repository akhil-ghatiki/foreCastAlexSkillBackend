package com.thoughtworks.forecastAlexaSkillBackend.model;

import java.util.ArrayList;

public class ShoppingList extends ArrayList<ShoppingItem> {

  @Override
  public String toString() {
    String result = "";
    for (ShoppingItem shoppingItem : this) {
      result = result.concat(" " + shoppingItem.toString() + ".");
    }
    return result;
  }
}
