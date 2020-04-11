package com.thoughtworks.forecastAlexaSkillBackend.interceptors;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.ResponseInterceptor;
import com.amazon.ask.model.Response;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogResponseInterceptor implements ResponseInterceptor {

  @Override
  public void process(HandlerInput input, Optional<Response> response) {
    log.info(response.toString());
  }
}
