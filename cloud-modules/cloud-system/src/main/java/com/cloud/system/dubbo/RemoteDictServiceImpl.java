package com.cloud.system.dubbo;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import com.cloud.common.core.utils.MapstructUtils;
import com.cloud.system.api.RemoteDictService;
import com.cloud.system.api.domain.vo.RemoteDictDataVo;
import com.cloud.system.domain.vo.SysDictDataVo;
import com.cloud.system.service.ISysDictTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典服务
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteDictServiceImpl implements RemoteDictService {

    private final ISysDictTypeService sysDictTypeService;


    @Override
    public List<RemoteDictDataVo> selectDictDataByType(String dictType) {
        List<SysDictDataVo> list = sysDictTypeService.selectDictDataByType(dictType);
        return MapstructUtils.convert(list, RemoteDictDataVo.class);
    }

}
