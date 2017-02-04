package com.anastasko.lnucompass.configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.anastasko.lnucompass.implementation.PropertyServiceImpl;
import com.anastasko.lnucompass.infrastructure.PropertyService;

@Configuration
@ComponentScan({"${service.impl.api.package}", "${service.impl.package}"})
public class CoreConfig {

	@Bean
	PropertyService properties() {
		return new PropertyServiceImpl();
	}

	@Bean
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getTypeFactory().clearCache();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

	/**
	 * must use if proxy
     *
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		if ("true".equals(properties().get("use.proxy"))){
			SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		    InetSocketAddress address = new InetSocketAddress(
		    		properties().get("proxy.host"), 
		    		Integer.parseInt(properties().get("proxy.port")));
		    Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
		    factory.setProxy(proxy);
		    restTemplate.setRequestFactory(factory);
		}
		return restTemplate;
	}
		
}
