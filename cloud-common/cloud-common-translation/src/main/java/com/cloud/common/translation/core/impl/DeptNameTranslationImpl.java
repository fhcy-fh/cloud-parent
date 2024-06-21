package com.cloud.common.translation.core.impl;

import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import com.cloud.common.translation.annotation.TranslationType;
import com.cloud.common.translation.constant.TransConstant;
import com.cloud.common.translation.core.TranslationInterface;
import com.cloud.system.api.RemoteDeptService;

/**
 * 部门翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DEPT_ID_TO_NAME)
public class DeptNameTranslationImpl implements TranslationInterface<String> {

    @DubboReference
    private RemoteDeptService remoteDeptService;

    @Override
    public String translation(Object key, String other) {
        return remoteDeptService.selectDeptNameByIds(key.toString());
    }
}
