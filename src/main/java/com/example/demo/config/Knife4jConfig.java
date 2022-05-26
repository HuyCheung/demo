package com.example.demo.config;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * knife4j配置
 *
 * @author Huy Cheung
 * @date 2022/04/07
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public Docket createRestApi() {
        String url = "http://" + NetUtil.getLocalhostStr() + ":" + SpringUtil.getProperty("server.port") + SpringUtil.getProperty("server.servlet.context-path") + "/doc.html";
        String applicationName = SpringUtil.getApplicationName();
        UserInfo userInfo = SystemUtil.getUserInfo();
        return new Docket(DocumentationType.OAS_30)
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(true)
                // 将api的元信息设置为包含在json ResourceListing响应中。
                .apiInfo(new ApiInfoBuilder()
                        .title(String.format("%s 接口文档", applicationName))
                        // 描述
                        .description(String.format("%s API管理", applicationName))
                        .contact(new Contact(userInfo.getName(), "", ""))
                        .version("1.0")
                        .build())
                // 分组名称
                // 选择哪些接口作为swagger的doc发布
                .select()
                // 要扫描的API(Controller)基础包
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
}