package com.thoughtworks.forecastAlexaSkillBackend;

import com.thoughtworks.forecastAlexaSkillBackend.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class ForecastAlexaSkillBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForecastAlexaSkillBackendApplication.class, args);
	}

}
