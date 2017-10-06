package com.anastasko.lnucompass.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${controller.api.package}")
    private String api;

    @Value("${controller.done.package}")
    private String done;

    @Value("${project.repo}")
    private String repo;

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(r -> RequestHandlerSelectors.basePackage(api).apply(r) ||
                        RequestHandlerSelectors.basePackage(done).apply(r))
                .paths(s -> true)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LNU Compass")
                .description("Spring REST backend app " + "<br>" + repo)
                .version("2.0")
                .build();
    }

}
