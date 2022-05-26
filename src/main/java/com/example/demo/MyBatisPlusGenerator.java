package com.example.demo;

import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.AES;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        Console.log("开始生成 ORM 代码");
        String author = System.getenv("USERNAME");
        String projectPath = System.getProperty("user.dir");
        String key = System.getenv("mpw.key");
        Console.log("当前用户：{}", author);
        Console.log("项目目录：{}", projectPath);
        Console.log("解密密钥：{}", key);

        String datasourceUrl = AES.decrypt("0Wd/lv6jhYKvIXU23s+PRJwNZJLZY3D67iJeWIfdmRh0QMSNs6EXSBNAhIjZGr/4PkDqiV6UgVMxP816sXBAEVIQouROZhAAQ4vex90hto6LmmzouUmpqyLUZ8OSbM/DcDOgrgcz+lLrWV+FSmnoz/XTzQQW0axqHFIoo7PDJww=", key);
        String datasourceUsername = AES.decrypt("AK49uDZzj+WalBXMnNeD5g==", key);
        String datasourcePassword = AES.decrypt("hEhJTgdjw92dsT70OgSXdg==", key);
        Console.log("数据库配置，url:{}", datasourceUrl);
        Console.log("数据库配置，username:{}", datasourceUsername);
        Console.log("数据库配置，password:{}", datasourcePassword);
        FastAutoGenerator.create(datasourceUrl, datasourceUsername, datasourcePassword)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir()// 禁止打开输出目录，默认值:true
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")// 注释日期，默认值: yyyy-MM-dd
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.example.demo") // 设置父包名，默认值:com.baomidou
                            // .moduleName("samples") // 设置父包模块名，默认值:无
                            .entity("entity")// Entity 包名，默认值:entity
                            .service("service")// Service 包名，默认值:service
                            .serviceImpl("service.impl")// Service Impl 包名，默认值:service.impl
                            .mapper("mapper")// Mapper 包名，默认值:mapper
                            .xml("mapper.xml")// Mapper XML 包名，默认值:mapper.xml
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/")); // 路径配置信息
                    // .controller("controller");// Controller 包名，默认值:controller
                })
                .strategyConfig(builder -> {
                    builder
                            // .addInclude("auth_user") // 设置需要生成的表名
                            // .addTablePrefix("t_", "tb_")// 设置过滤表前缀
                            .entityBuilder().disableSerialVersionUID()// 禁用生成 serialVersionUID,默认值:false
                            .enableChainModel()// 开启链式模型,默认值:false
                            .enableLombok()// 开启 lombok 模型,默认值:false
                            .enableTableFieldAnnotation()// 开启生成实体时生成字段注解,默认值:false
                            .enableActiveRecord()// 开启 ActiveRecord 模型,默认值:false
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .mapperBuilder().superClass(BaseMapper.class)// 设置父类
                            .enableMapperAnnotation()// 开启 @Mapper 注解,默认值:false
                            .enableBaseResultMap()// 启用 BaseResultMap 生成,默认值:false
                            .enableBaseColumnList()// 启用 BaseColumnList,默认值:false
                            .serviceBuilder().formatServiceFileName("%sService")
                            .controllerBuilder().enableHyphenStyle()// 开启驼峰转连字符,默认值:false
                            .enableRestStyle();// 开启生成@RestController 控制器,默认值:false
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 引擎模板
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER); //不生成前端控制器, 其实还有另一种方案, 各位看了源码就知道了, 在AbstractTemplateEngine.outputController()方法中
                })
                .execute();
    }


}