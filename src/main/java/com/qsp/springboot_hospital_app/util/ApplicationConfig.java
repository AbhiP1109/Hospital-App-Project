package com.qsp.springboot_hospital_app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

	@Bean
	public Docket getDocket() {
		
		Contact contact = new Contact("Qspider","www.qspider.com","qspider123@gmail.com");
		
		List<VendorExtension> vendorExtensions = new ArrayList<>();
		
		ApiInfo apiInfo = new ApiInfo("Hospital Management","HM Version 1.0", "Version 1.0", "1 year free service", contact, "HOMA123", "www.hospitalmangement.com", vendorExtensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.qsp.springboot_hospital_app")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
		
	}
}
