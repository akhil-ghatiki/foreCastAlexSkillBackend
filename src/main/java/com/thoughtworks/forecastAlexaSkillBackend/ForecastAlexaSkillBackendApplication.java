package com.thoughtworks.forecastAlexaSkillBackend;

import com.thoughtworks.forecastAlexaSkillBackend.cart.CartList;
import com.thoughtworks.forecastAlexaSkillBackend.config.AppConfig;
import com.thoughtworks.forecastAlexaSkillBackend.model.ShoppingItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ForecastAlexaSkillBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(ForecastAlexaSkillBackendApplication.class, args);

    // TODO this should be removed.
    ShoppingItem defaultItem = new ShoppingItem("MainTestName", 50, "lit", "2", 500);
    CartList.getInstance().getList().add(defaultItem);
  }

}
