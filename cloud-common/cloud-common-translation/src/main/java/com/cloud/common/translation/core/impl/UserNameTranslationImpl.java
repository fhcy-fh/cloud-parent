package com.cloud.common.translation.core.impl;

import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import com.cloud.common.translation.annotation.TranslationType;
import com.cloud.common.translation.constant.TransConstant;
import com.cloud.common.translation.core.TranslationInterface;
import com.cloud.system.api.RemoteUserService;

/**
 * 用户名翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.USER_ID_TO_NAME)
public class UserNameTranslationImpl implements TranslationInterface<String> {

    @DubboReference
    private RemoteUserService remoteUserService;

    @Override
    public String translation(Object key, String other) {
        return remoteUserService.selectUserNameById((Long) key);
    }
}
