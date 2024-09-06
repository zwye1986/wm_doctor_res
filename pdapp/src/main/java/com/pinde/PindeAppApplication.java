package com.pinde;

import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.sql.DataSource;

@SpringBootApplication(exclude = { FreeMarkerAutoConfiguration.class, MustacheAutoConfiguration.class, TransactionAutoConfiguration.class, QuartzAutoConfiguration.class })
//@ComponentScan(basePackages = {"com.pinde"})
@PropertySource(value = {"classpath:jdbc.properties", "classpath:log4j.properties", "classpath:pdapp.properties", "classpath:sso.properties"}, ignoreResourceNotFound = true)
@ImportResource({"classpath:spring-context.xml"/*,"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"*/})
//@MapperScan({"com.pinde.sci.dao.**"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600, redisNamespace = "pdsci-jsres-app")
public class PindeAppApplication extends SpringBootServletInitializer {
    private static Logger logger = LoggerFactory.getLogger(PindeAppApplication.class);

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        logger.info("PindeSciApplication start...");
        try {
            SpringApplication.run(PindeAppApplication.class, args); // 启动Spring Boot应用
        }catch (Exception e) {
            logger.error("PindeSciApplication start error.", e);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        logger.info("PindeSciApplication config web.xml load...");
        SpringApplicationBuilder springApplicationBuilder = null;
        try {
            springApplicationBuilder = builder.sources(PindeAppApplication.class);
        }catch (Exception e) {
            logger.error("PindeSciApplication start error.", e);
        }

        return springApplicationBuilder;
    }

    /**
     * 加这个的目的是不用在配置文件中配liquibase的数据库连接，这样生产不用改配置
     * @param dataSource
     * @return
     */
    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        logger.info("PindeSciApplication config liquibase...");
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(env.getProperty("liquibase.change-log"));
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setShouldRun(true);
        logger.info("PindeSciApplication config liquibase end...");
        return springLiquibase;
    }
}
