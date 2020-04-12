package com.thoughtworks.forecastAlexaSkillBackend.config;

import com.amazon.ask.servlet.ServletConstants;
import com.thoughtworks.forecastAlexaSkillBackend.servlet.AlexaServlet;
import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Autowired
  AppConfig appConfig;
  @Bean
  public ServletRegistrationBean<HttpServlet> alexaServlet() {
    loadProperties();
    ServletRegistrationBean<HttpServlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
    servletServletRegistrationBean.setServlet(new AlexaServlet(appConfig));
    servletServletRegistrationBean.addUrlMappings("/alexa/*");
    servletServletRegistrationBean.setLoadOnStartup(1);
    return servletServletRegistrationBean;
  }

  private void loadProperties() {
    System.setProperty(ServletConstants.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "150000");
    System.setProperty(ServletConstants.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true");
    System.setProperty(Constants.SSL_KEYSTORE_FILE_PATH_KEY, Constants.SSL_KEYSTORE_FILE_PATH_KEY);
    System.setProperty(Constants.SSL_KEYSTORE_PASSWORD_KEY, Constants.SSL_KEYSTORE_PASSWORD_KEY);
  }

}
