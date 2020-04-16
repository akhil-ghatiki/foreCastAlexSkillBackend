package com.thoughtworks.forecastAlexaSkillBackend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Company {

  private String companyName;

  private String companyEmail;

}
