package com.cloud.common.seata.config;

import com.cloud.common.core.factory.YmlPropertySourceFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * seata 配置
 *
 * @author Lion Li
 */
@AutoConfiguration
@PropertySource(value = "classpath:common-seata.yml", factory = YmlPropertySourceFactory.class)
public class SeataConfiguration {

}
