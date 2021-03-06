package com.example.epi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enable;

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本项目采用指定扫描的包路径来定义指定要建立API的目录。
     * @return
     */
    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.epi.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setHeaderToken())
                .enable(enable);
    }

    /**
     * API相关信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("疫情项目后台管理系统")
                .description("疫情系统后端接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * 在header中添加token，为了在用swagger 测试接口的时候添加头部信息
     * @return
     */
    private List<Parameter> setHeaderToken() {
        List<Parameter> parameters = new ArrayList<>();

        ParameterBuilder accessTokenBuilder = new ParameterBuilder();
        accessTokenBuilder.name("authorization").description("程序员自测的时候动态传输AccessToken 入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);

        ParameterBuilder refreshTokenBuilder = new ParameterBuilder();
        refreshTokenBuilder.name("refreshToken").description("程序员自测的时候动态传输refreshToken 入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);

        parameters.add(accessTokenBuilder.build());
        parameters.add(refreshTokenBuilder.build());

        return parameters;
    }
}
