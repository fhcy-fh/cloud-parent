package com.cloud.common.translation.core.impl;

import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import com.cloud.common.translation.annotation.TranslationType;
import com.cloud.common.translation.constant.TransConstant;
import com.cloud.common.translation.core.TranslationInterface;
import com.cloud.resource.api.RemoteFileService;

/**
 * OSS翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.OSS_ID_TO_URL)
public class OssUrlTranslationImpl implements TranslationInterface<String> {

    @DubboReference(mock = "true")
    private RemoteFileService ossService;

    @Override
    public String translation(Object key, String other) {
        return ossService.selectUrlByIds(key.toString());
    }
}
