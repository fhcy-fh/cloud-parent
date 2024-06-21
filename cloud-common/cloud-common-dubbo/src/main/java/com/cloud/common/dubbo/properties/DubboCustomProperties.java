package com.cloud.common.dubbo.properties;

import lombok.Data;
import com.cloud.common.dubbo.enumd.RequestLogEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 自定义配置
 *
 * @author Lion Li
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "dubbo.custom")
public class DubboCustomProperties {

    private Boolean requestLog;

    private RequestLogEnum logLevel;

}
