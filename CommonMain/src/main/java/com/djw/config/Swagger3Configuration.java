package com.djw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.*;

/**
 * <pre>Springfox-swagger 3.0.0配置</pre>
 */
@Configuration
@EnableOpenApi
public class Swagger3Configuration implements WebMvcConfigurer {
    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    @Value("${nature.restApi.enabled}")
    private Boolean enable;

    /**
     * 项目应用名
     */
    @Value("${application.name}")
    private String applicationName;

    /**
     * 项目版本信息
     */
    @Value("${application.version}")
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    @Value("${application.description}")
    private String applicationDescription;

    /**
     * http://localhost:8080/swagger-ui/index.html
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")

                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(enable)

                // 将api的元信息设置为包含在json ResourceListing响应中。 
                .apiInfo(apiInfo())

                // 选择哪些接口作为swagger的doc发布
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()

                // 支持的通讯协议集合
                .protocols(newHashSet("https", "http"))

                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())

                // 授权信息全局应用
                .securityContexts(securityContexts());
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Sinhy " + applicationName + " Api Doc")
                .description(applicationDescription)
                .contact(new Contact("JetLi", null, "lilinhai548@163.com"))
                .version("Application Version: " + applicationVersion + ", Spring Boot Version: " + SpringBootVersion.getVersion())
                .build();
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(new ApiKey("BASE_TOKEN", "token", "pass"));
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder().securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")}))).build());
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}
