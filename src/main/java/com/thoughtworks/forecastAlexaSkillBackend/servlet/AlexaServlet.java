package com.thoughtworks.forecastAlexaSkillBackend.servlet;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.servlet.SkillServlet;
import com.thoughtworks.forecastAlexaSkillBackend.config.AppConfig;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.CancelandStopIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.ErrorHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.FallbackIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.HelloWorldIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.HelpIntentHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.LaunchRequestHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.MyExceptionHandler;
import com.thoughtworks.forecastAlexaSkillBackend.handlers.SessionEndedRequestHandler;
import com.thoughtworks.forecastAlexaSkillBackend.interceptors.LogRequestInterceptor;
import com.thoughtworks.forecastAlexaSkillBackend.interceptors.LogResponseInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlexaServlet extends SkillServlet {

  public AlexaServlet(AppConfig appConfig) {
    super(getSkill(appConfig));
  }

  private static Skill getSkill(AppConfig appConfig) {
    log.info("Initializing AlexaServlet with Skill ID # " + appConfig.getSkillId());
    return Skills.standard()
        .addRequestHandlers(
            new CancelandStopIntentHandler(),
            new HelloWorldIntentHandler(),
            new HelpIntentHandler(),
            new LaunchRequestHandler(),
            new SessionEndedRequestHandler(),
            new FallbackIntentHandler(),
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
