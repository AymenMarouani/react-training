package org.training.project.taskmanager.configuration;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SuppressWarnings(value = "unused")
public class SwaggerConfiguration {

  @Bean
  public Docket docket() {
    return new Docket(SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("org.training.project.taskmanager.controller"))
        .paths(PathSelectors.any())
        .build();
  }
}
