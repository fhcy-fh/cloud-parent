package com.cloud.system.dubbo;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import com.cloud.system.api.RemoteDeptService;
import com.cloud.system.service.ISysDeptService;
import org.springframework.stereotype.Service;

/**
 * 部门服务
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteDeptServiceImpl implements RemoteDeptService {

    private final ISysDeptService sysDeptService;

    @Override
    public String selectDeptNameByIds(String deptIds) {
        return sysDeptService.selectDeptNameByIds(deptIds);
    }
}
