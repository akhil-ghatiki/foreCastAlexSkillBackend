package com.thoughtworks.forecastAlexaSkillBackend.servlet;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.servlet.SkillServlet;
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

public class AlexaServlet extends SkillServlet {

  public AlexaServlet() {
    super(getSkill());
  }

  private static Skill getSkill() {
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
        .withSkillId("amzn1.ask.skill.0b9395f8-c737-4607-be6b-856dfc7c20bd")
        .build();

  }
}
