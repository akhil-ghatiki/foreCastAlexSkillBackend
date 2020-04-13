package com.thoughtworks.forecastAlexaSkillBackend.domain;

import com.google.gson.Gson;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingList;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
@Slf4j
public class ShoppingListReader {
    public ShoppingList suggestedList() {
        String jsonPath= "./suggestedShoppingList.json";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(jsonPath));
            ShoppingList shoppingList = parse(bufferedReader);
            bufferedReader.close();
            return shoppingList;
        } catch (FileNotFoundException e) {
            log.error(e.toString());
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
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
