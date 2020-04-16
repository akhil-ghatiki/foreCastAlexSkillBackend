package com.thoughtworks.forecastAlexaSkillBackend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShoppingList extends ArrayList<ShoppingItem> {
  @Override
  public String toString() {
    String result = "";
    for(ShoppingItem shoppingItem : this){
      result= result.concat(" " + shoppingItem.toString() + ".");
    }
    return result;
  }

  public static ShoppingList BuildShoppingList(List<Map<String,Object>> shoppingItems) {
    ShoppingList shoppingList = new ShoppingList();
    for (int i=0 ; i< shoppingItems.size();i++) {
      shoppingList.add(new ShoppingItem(shoppingItems.get(i)));
    }
    return shoppingList;
  }
}
