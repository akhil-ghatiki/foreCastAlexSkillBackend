package com.thoughtworks.forecastAlexaSkillBackend.controller;

import com.thoughtworks.forecastAlexaSkillBackend.model.OrderInvoice;
import com.thoughtworks.forecastAlexaSkillBackend.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/grocerymart")
@CrossOrigin
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping("{userId}/invoice")
  public ResponseEntity<OrderInvoice> getCart(@PathVariable String userId) {
    OrderInvoice orderInvoice = cartService.getOrderInvoice(userId);
    return new ResponseEntity<>(orderInvoice, HttpStatus.OK);
  }
}
