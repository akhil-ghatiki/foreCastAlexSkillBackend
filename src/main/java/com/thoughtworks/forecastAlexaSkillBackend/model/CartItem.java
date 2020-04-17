package com.thoughtworks.forecastAlexaSkillBackend.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CartItem extends ShoppingItem {
    private double price;
    public CartItem(String name, double quantity, String unit, String id, double unitPrice, double price) {
        super(name, quantity, unit, id, unitPrice);
        this.price = price;
    }
    public CartItem(String name, double quantity, String unit, String id, double unitPrice) {
        super(name, quantity, unit, id, unitPrice);
        this.price = quantity * unitPrice;
    }
    public CartItem(ShoppingItem shoppedItem) {
        super(shoppedItem.getName(),shoppedItem.getQuantity(), shoppedItem.getUnit(), shoppedItem.getId(), shoppedItem.getUnitPrice());
        this.price = shoppedItem.getUnitPrice() * shoppedItem.getQuantity();
    }

}
