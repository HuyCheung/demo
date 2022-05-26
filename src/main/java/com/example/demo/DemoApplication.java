package com.example.demo;

import cn.hutool.core.net.NetUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.UserInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 演示应用程序
 *
 * @author Huy Cheung
 * @date 2022/05/20
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@Import(SpringUtil.class)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        String applicationName = SpringUtil.getApplicationName();
        UserInfo userInfo = SystemUtil.getUserInfo();
        String ip = NetUtil.getLocalhostStr();
        String port = SpringUtil.getProperty("server.port");
        String path = SpringUtil.getProperty("server.servlet.context-path");
        OsInfo osInfo = SystemUtil.getOsInfo();
        System.out.printf("""
                                                
                        ------------------------------------------------------------------------------------------------
                        \t😀 Hello %s, %s is running by %s! Access URLs:
                        \t本地访问地址: \thttp://localhost:%s%s/
                        \t外部访问地址: \thttp://%s:%s%s/
                        \tKnife4j文档: \thttp://%s:%s%s/doc.html
                        ------------------------------------------------------------------------------------------------
                                                
                        """,
                userInfo.getName(), applicationName, osInfo.getName(), port, path, ip, port, path, ip, port, path);
    }

}