package com.thoughtworks.forecastAlexaSkillBackend.domain;

import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;
import org.junit.jupiter.api.Test;

class ShoppingListReaderTest {
    @Test
    public void dummy(){
//        ShoppingList read =
//                new ShoppingListReader().suggestedList();

        float qty = 2.0f;
        double x = qty - Math.round(qty);
        qty = 2.4f;
        double y = qty - Math.round(qty);


    }

    public boolean isWhole(double value) {
        return Math.floor(value) == value;
    }



}
