package com.thoughtworks.forecastAlexaSkillBackend.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "appconfig")
@Configuration
@EnableConfigurationProperties
public class AppConfig {
    private String skillId;
}
