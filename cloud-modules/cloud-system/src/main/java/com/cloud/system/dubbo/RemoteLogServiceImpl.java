package com.cloud.system.dubbo;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import com.cloud.common.core.utils.MapstructUtils;
import com.cloud.system.api.RemoteLogService;
import com.cloud.system.api.domain.bo.RemoteLogininforBo;
import com.cloud.system.api.domain.bo.RemoteOperLogBo;
import com.cloud.system.domain.bo.SysLogininforBo;
import com.cloud.system.domain.bo.SysOperLogBo;
import com.cloud.system.service.ISysLogininforService;
import com.cloud.system.service.ISysOperLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteLogServiceImpl implements RemoteLogService {

    private final ISysOperLogService operLogService;
    private final ISysLogininforService logininforService;

    @Async
    @Override
    public void saveLog(RemoteOperLogBo remoteOperLogBo) {
        SysOperLogBo sysOperLogBo = MapstructUtils.convert(remoteOperLogBo, SysOperLogBo.class);
        operLogService.insertOperlog(sysOperLogBo);
    }

    @Async
    @Override
    public void saveLogininfor(RemoteLogininforBo remoteLogininforBo) {
        SysLogininforBo sysLogininforBo = MapstructUtils.convert(remoteLogininforBo, SysLogininforBo.class);
        logininforService.insertLogininfor(sysLogininforBo);
    }
}
