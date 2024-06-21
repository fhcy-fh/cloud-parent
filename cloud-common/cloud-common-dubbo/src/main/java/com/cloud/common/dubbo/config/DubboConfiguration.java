package com.cloud.common.dubbo.config;

import com.cloud.common.core.factory.YmlPropertySourceFactory;
import com.cloud.common.dubbo.properties.DubboCustomProperties;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * dubbo 配置类
 */
@AutoConfiguration
@EnableConfigurationProperties(DubboCustomProperties.class)
@PropertySource(value = "classpath:common-dubbo.yml", factory = YmlPropertySourceFactory.class)
public class DubboConfiguration {

    @Bean
    public BeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }
}
