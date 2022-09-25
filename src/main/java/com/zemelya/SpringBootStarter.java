package com.zemelya;

import com.zemelya.configuration.PersistenceProvidersConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = "com.zemelya")
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({PersistenceProvidersConfiguration.class})
public class SpringBootStarter {

    public static void main(String[] args) {
        SpringApplication.run(com.zemelya.SpringBootStarter.class, args);
    }
}