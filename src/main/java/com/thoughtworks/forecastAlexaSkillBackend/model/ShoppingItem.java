package com.thoughtworks.forecastAlexaSkillBackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingItem {
    private String name;
    private double quantity;
    private String unit;
    private String id;
    private double price;

    public ShoppingItem(String name, float quantity, String unit, String id, float price){
        this.name=name;
        this.quantity=quantity;
        this.unit=unit;
        this.id=id;
        this.price=price;
    }

    private boolean isWhole(double value) {
        return Math.floor(value) == value;
    }

    @Override
    public String toString() {
        String quantityInString;
        if (isWhole(quantity)) {
            quantityInString= String.valueOf((int) (quantity));
        }
       else {
           quantityInString= String.valueOf(quantity);
        }
        return String.format("%s%s %s", quantityInString
                , unit, name);
    }
}
