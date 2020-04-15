package com.thoughtworks.forecastAlexaSkillBackend.servlet;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.servlet.SkillServlet;
import com.thoughtworks.forecastAlexaSkillBackend.customIntenthandlers.*;
import com.thoughtworks.forecastAlexaSkillBackend.defaultIntentHandlers.*;
import com.thoughtworks.forecastAlexaSkillBackend.interceptors.LogRequestInterceptor;
import com.thoughtworks.forecastAlexaSkillBackend.interceptors.LogResponseInterceptor;
import lombok.extern.slf4j.Slf4j;
import com.thoughtworks.forecastAlexaSkillBackend.config.AppConfig;

@Slf4j
public class AlexaServlet extends SkillServlet {

  public AlexaServlet(AppConfig appConfig) {
    super(getSkill(appConfig));
  }

  private static Skill getSkill(AppConfig appConfig) {
    log.info("Initializing AlexaServlet with Skill ID # " + appConfig.getSkillId());
    return Skills.standard()
        .addRequestHandlers(
            new CancelAndStopIntentHandler(),
            new HelpIntentHandler(),
            new LaunchRequestHandler(),
            new SessionEndedRequestHandler(),
            new FallbackIntentHandler(),
            new GroceryListIntentHandler(),
            new GroceryItemIntentHandler(),
            new YesIntentHandler(),
            new NoIntentHandler(),
            new ErrorHandler())
        .addExceptionHandler(new MyExceptionHandler())
        .addRequestInterceptors(
            new LogRequestInterceptor())
        .addResponseInterceptors(
            new LogResponseInterceptor())
        .withSkillId(appConfig.getSkillId())
        .build();

  }
}
