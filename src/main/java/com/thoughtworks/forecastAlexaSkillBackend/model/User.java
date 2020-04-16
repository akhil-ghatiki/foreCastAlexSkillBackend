package com.thoughtworks.forecastAlexaSkillBackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {

  private String username;

  private String userEmail;

}
