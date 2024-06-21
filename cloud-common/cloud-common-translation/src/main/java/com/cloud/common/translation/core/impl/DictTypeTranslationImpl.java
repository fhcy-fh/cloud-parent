package com.cloud.common.translation.core.impl;

import lombok.AllArgsConstructor;
import com.cloud.common.core.service.DictService;
import com.cloud.common.core.utils.StringUtils;
import com.cloud.common.translation.annotation.TranslationType;
import com.cloud.common.translation.constant.TransConstant;
import com.cloud.common.translation.core.TranslationInterface;

/**
 * 字典翻译实现
 *
 * @author Lion Li
 */
@AllArgsConstructor
@TranslationType(type = TransConstant.DICT_TYPE_TO_LABEL)
public class DictTypeTranslationImpl implements TranslationInterface<String> {

    private final DictService dictService;

    @Override
    public String translation(Object key, String other) {
        if (key instanceof String && StringUtils.isNotBlank(other)) {
            return dictService.getDictLabel(other, key.toString());
        }
        return null;
    }
}
