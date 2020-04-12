package com.thoughtworks.forecastAlexaSkillBackend.domain;

import com.google.gson.Gson;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ShoppingListReader {
    public ShoppingList suggestedList() {
        String jsonPath= "../suggestedShoppingList.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(jsonPath));
        } catch (FileNotFoundException e) {
        }
        return parse(bufferedReader);
    }

    private ShoppingList parse(BufferedReader metadataReader) {
        try {
            ShoppingList shoppingList = new Gson().fromJson(metadataReader, ShoppingList.class);
            return shoppingList;
        } catch (Exception e) {
            return null;
        }
    }
}
