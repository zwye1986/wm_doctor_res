package com.pinde.sci;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, MustacheAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.pinde"})
//@PropertySource(value = {"classpath:jdbc.properties", "classpath:log4j.properties", "classpath:pdsci.properties", "classpath:sso.properties"}, ignoreResourceNotFound = true)
@ImportResource({"classpath:spring-context.xml"/*,"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"*/})
//@MapperScan({"com.pinde.sci.dao.**"})
public class PindeSciApplication extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(PindeSciApplication.class);

    public static void main(String[] args) {
        logger.info("PindeSciApplication start...");
        SpringApplication.run(PindeSciApplication.class, args); // 启动Spring Boot应用
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("PindeSciApplication config web.xml load...");
        return builder.sources(PindeSciApplication.class);
    }
}
