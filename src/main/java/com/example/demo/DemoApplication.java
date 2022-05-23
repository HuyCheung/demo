package com.example.demo;

import com.example.demo.util.NetUtil;
import lombok.SneakyThrows;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 演示应用程序
 *
 * @author Huy Cheung
 * @date 2022/05/20
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(DemoApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = NetUtil.getServerIp();
        String port = env.getProperty("server.port");
        port = port == null ? "8080" : port;
        String path = env.getProperty("server.servlet.context-path");
        path = path == null ? "" : path;
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application Demo is running! Access URLs:\n\t" +
                "本地访问地址: \thttp://localhost:" + port + path + "/\n\t" +
                "外部访问地址: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Knife4j文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------\n");
    }

}