package com.thoughtworks.forecastAlexaSkillBackend.model;

import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Cart {

  private String id;

  private Date orderDate;

  private User user;

  private Company company;

  private List<ShoppingItem> cartItems;

}
