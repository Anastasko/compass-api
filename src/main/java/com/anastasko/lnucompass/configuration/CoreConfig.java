package com.anastasko.lnucompass.configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

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
		    InetSocketAddress address = new InetSocketAddress("194.44.198.39", 3128);
		    Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
		    factory.setProxy(proxy);
		    restTemplate.setRequestFactory(factory);
		}
		return restTemplate;
	}
		
}
