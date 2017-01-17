package com.anastasko.lnucompass.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.springmvc.controller.JSONDocController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.servlet.ServletContext;

@Configuration
@EnableWebMvc
@ComponentScan({ "${controller.api.package}", "${controller.package}",  })
public class WebConfig extends WebMvcConfigurerAdapter {
	
	public static String COMPASS_DIR = "/home/" + System.getProperty("user.name") + "/compass/";
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
    }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/login").setViewName("login");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(environment.getProperty("web.locale"));
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String[] resources = { 
				environment.getProperty("web.resources"), 
				environment.getProperty("web.webjars")};
		for (String resource : resources) {
			registry.addResourceHandler(resource + "**").addResourceLocations(resource);
		}
		System.out.println(System.getProperty("user.name"));
		registry.addResourceHandler("/uploads/**").addResourceLocations("file://" + COMPASS_DIR + "uploads/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonConverter());
		super.configureMessageConverters(converters);
	}

	@Bean
	MappingJackson2HttpMessageConverter jacksonConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getTypeFactory().clearCache();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(StringUtils.parseLocaleString(environment.getProperty("web.locale.default")));
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(environment.getProperty("web.message.messages"),
				environment.getProperty("web.message.validation"));
		messageSource.setDefaultEncoding(environment.getProperty("core.encoding"));
		messageSource.setCacheSeconds(-1);
		return messageSource;
	}

	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix(environment.getProperty("web.view.prefix"));
		resolver.setSuffix(environment.getProperty("web.view.suffix"));
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding(environment.getProperty("core.encoding"));
		resolver.setCacheable(false);
		return resolver;
	}

	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding(environment.getProperty("core.encoding"));
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] { "*" });
		viewResolver.setCache(false);
		return viewResolver;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JSONDocController documentationController(){
		List<String> packages = new ArrayList<>();
		packages.add(environment.getRequiredProperty("controller.api.package"));
		packages.add(environment.getRequiredProperty("model.view.api.package"));
		packages.add(environment.getRequiredProperty("controller.done.package"));
		packages.add(environment.getRequiredProperty("model.view.package"));
		JSONDocController controller = new JSONDocController("1", environment.getProperty("endpoint") + servletContext.getContextPath(), packages);
		controller.setPlaygroundEnabled(true);
		controller.setDisplayMethodAs(MethodDisplay.URI);
		return controller;
	}

	@Autowired
	private ServletContext servletContext;

}
