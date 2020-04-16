package com.thoughtworks.forecastAlexaSkillBackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ShoppingItem {

  private String name;
  private double quantity;
  private String unit;
  private String id;
  private double unitPrice;

  public ShoppingItem(String name, float quantity, String unit, String id, float unitPrice) {
    this.name = name;
    this.quantity = quantity;
    this.unit = unit;
    this.id = id;
    this.unitPrice = unitPrice;
  }

  public ShoppingItem(Map<String, Object> constructParams){
    this.name = (String) constructParams.get("name");
    this.quantity = (double) constructParams.get("quantity");
    this.unit = (String) constructParams.get("unit");
    this.id = (String) constructParams.get("id");
    this.unitPrice = (double) constructParams.get("unitPrice");
  }

  private boolean isWhole(double value) {
    return Math.floor(value) == value;
  }

  @Override
  public String toString() {
    String quantityInString;
    if (isWhole(quantity)) {
      quantityInString = String.valueOf((int) (quantity));
    } else {
      quantityInString = String.valueOf(quantity);
    }
    return String.format("%s%s %s", quantityInString
        , unit, name);
  }
}
