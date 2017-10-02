package com.anastasko.lnucompass.configuration;

import com.anastasko.lnucompass.component.ListIconsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsondoc.core.pojo.JSONDoc.MethodDisplay;
import org.jsondoc.springmvc.controller.JSONDocController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
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
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan({ "${controller.api.package}", "${controller.package}",  })
public class WebConfig extends WebMvcConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	public static String COMPASS_DIR = (isMac() ? "/Users/" : "/home/") + System.getProperty("user.name") + "/compass";
	public static String API_PACKAGE = "/com/anastasko/lnucompass/api";

	public static boolean isMac(){
		return System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0;
	}

	@Autowired
	private Environment environment;
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*");
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName(environment.getProperty("web.locale"));
		registry.addInterceptor(localeChangeInterceptor);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		logger.info("COMPASS_DIR=" + COMPASS_DIR);
		registry.addResourceHandler("/uploads/**").addResourceLocations("file://" + COMPASS_DIR + "/uploads/");
	}

	@Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jacksonConverter);
		super.configureMessageConverters(converters);
	}

	@Bean
    @Autowired
	public MappingJackson2HttpMessageConverter jacksonConverter(ObjectMapper objectMapper) {
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
		JSONDocController controller = new JSONDocController("1", environment.getProperty("endpoint"), packages);
		controller.setPlaygroundEnabled(true);
		controller.setDisplayMethodAs(MethodDisplay.URI);
		return controller;
	}

	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setSuffix(environment.getProperty("web.view.suffix"));
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding(environment.getProperty("core.encoding"));
		resolver.setCacheable(false);
		return resolver;
	}

	@Bean
	public ISpringTemplateEngine templateEngine() {
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


}
