package com.demo.teastore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket newApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(teaStoreAppInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(Predicates.not(PathSelectors.regex("/error")))
				.build();
	}
	
	private ApiInfo teaStoreAppInfo() {
		return new ApiInfoBuilder().title("Tea Store API Documentation").description("Documentation of the endpoints exposed by Tea Store service")
				.termsOfServiceUrl("For more information contact Lixar repesentative!").contact(new Contact("Tea-Store development team", "", ""))
				.license("For more information contact Lixar repesentative!").version("1.0").build();
	}
}
