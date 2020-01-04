package com.ananops;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * SPC(加盟服务商中心)应用
 *
 * Created by bingyueduan on 2019/12/27.
 */
@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
public class AnanOpsSpcApplication {

    /**
     * SPC应用入口
     *
     * @param args 输入参数
     */
    public static void main(String[] args) {
        SpringApplication.run(AnanOpsSpcApplication.class, args);
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();

        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:/liquibase/index.xml");

        return springLiquibase;
    }
}
