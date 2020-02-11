package com.ananops.provider.config;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.ananops.config.properties.AnanopsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 阿里云对象存储（OSS）配置类
 *
 * @author ananops.com@gmail.com
 */
@Slf4j
@Configuration
public class AliyunOssConfiguration {

    @Resource
    private AnanopsProperties ananOpsProperties;

//    /**
//     * 实例化阿里云对象存储客户端
//     *
//     * @return 对象存储客户端
//     *
//     * @throws ClientException
//     */
//    @Bean
//    public OSS ossClient() throws ClientException {
//        log.info("OSS Bean OSSClient Start");
//        return new OSSClientBuilder().build(ananOpsProperties.getAliyun().getOss().getEndPoint(),
//                ananOpsProperties.getAliyun().getKey().getAccessKeyId(),
//                ananOpsProperties.getAliyun().getKey().getAccessKeySecret());
//    }
}
