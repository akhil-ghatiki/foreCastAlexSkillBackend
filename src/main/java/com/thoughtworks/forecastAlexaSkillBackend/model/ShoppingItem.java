package com.thoughtworks.forecastAlexaSkillBackend.model;

import com.google.gson.internal.$Gson$Types;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
