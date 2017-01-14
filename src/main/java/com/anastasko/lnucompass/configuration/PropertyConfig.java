package com.anastasko.lnucompass.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({
	"classpath:app.properties",
	"classpath:persistence.properties"
})
public class PropertyConfig {

}
