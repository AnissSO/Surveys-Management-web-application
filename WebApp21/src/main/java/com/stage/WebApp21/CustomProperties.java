package com.stage.WebApp21;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.stage.web-app21")
public class CustomProperties {
  
	private String  apiUrl;
	
}
