package com.cloud.system.dubbo;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import com.cloud.common.core.utils.MapstructUtils;
import com.cloud.system.api.RemoteClientService;
import com.cloud.system.api.domain.vo.RemoteClientVo;
import com.cloud.system.domain.vo.SysClientVo;
import com.cloud.system.service.ISysClientService;
import org.springframework.stereotype.Service;

/**
 * 客户端服务
 *
 * @author Michelle.Chung
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteClientServiceImpl implements RemoteClientService {

    private final ISysClientService sysClientService;

    /**
     * 根据客户端id获取客户端详情
     */
    @Override
    public RemoteClientVo queryByClientId(String clientId) {
        SysClientVo vo = sysClientService.queryByClientId(clientId);
        return MapstructUtils.convert(vo, RemoteClientVo.class);
    }

}
