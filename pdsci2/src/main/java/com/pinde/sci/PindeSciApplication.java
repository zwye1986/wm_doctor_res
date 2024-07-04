package com.pinde.sci;

import liquibase.integration.spring.SpringLiquibase;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, MustacheAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.pinde"})
//@PropertySource(value = {"classpath:jdbc.properties", "classpath:log4j.properties", "classpath:pdsci.properties", "classpath:sso.properties"}, ignoreResourceNotFound = true)
@ImportResource({"classpath:spring-context.xml"/*,"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"*/})
//@MapperScan({"com.pinde.sci.dao.**"})
@Configuration
public class PindeSciApplication extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(PindeSciApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        logger.error("PindeSciApplication start...");
        SpringApplication.run(PindeSciApplication.class, args); // 启动Spring Boot应用
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.error("PindeSciApplication config web.xml load...");
        return builder.sources(PindeSciApplication.class);
    }

    /**
     * 加这个的目的是不用在配置文件中配liquibase的数据库连接，这样生产不用改配置
     * @param dataSource
     * @return
     */
    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        logger.error("PindeSciApplication config liquibase...");
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(env.getProperty("liquibase.change-log"));
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setShouldRun(true);
        logger.error("PindeSciApplication config liquibase end...");
        return springLiquibase;
    }

}
