package com.thoughtworks.forecastAlexaSkillBackend.interceptors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogRequestInterceptor implements RequestInterceptor {

  @Override
  public void process(HandlerInput input) {
    log.info(input.getRequestEnvelope().toString());
  }
}
