package com.cloud.auth.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import com.cloud.auth.domain.vo.LoginVo;
import com.cloud.auth.form.EmailLoginBody;
import com.cloud.auth.service.IAuthStrategy;
import com.cloud.auth.service.SysLoginService;
import com.cloud.common.core.constant.Constants;
import com.cloud.common.core.constant.GlobalConstants;
import com.cloud.common.core.enums.LoginType;
import com.cloud.common.core.exception.user.CaptchaExpireException;
import com.cloud.common.core.utils.MessageUtils;
import com.cloud.common.core.utils.StringUtils;
import com.cloud.common.core.utils.ValidatorUtils;
import com.cloud.common.json.utils.JsonUtils;
import com.cloud.common.redis.utils.RedisUtils;
import com.cloud.common.satoken.utils.LoginHelper;
import com.cloud.system.api.RemoteUserService;
import com.cloud.system.api.domain.vo.RemoteClientVo;
import com.cloud.system.api.model.LoginUser;
import org.springframework.stereotype.Service;

/**
 * 邮件认证策略
 *
 * @author Michelle.Chung
 */
@Slf4j
@Service("email" + IAuthStrategy.BASE_NAME)
@RequiredArgsConstructor
public class EmailAuthStrategy implements IAuthStrategy {

    private final SysLoginService loginService;

    @DubboReference
    private RemoteUserService remoteUserService;

    @Override
    public LoginVo login(String body, RemoteClientVo client) {
        EmailLoginBody loginBody = JsonUtils.parseObject(body, EmailLoginBody.class);
        ValidatorUtils.validate(loginBody);
        String tenantId = loginBody.getTenantId();
        String email = loginBody.getEmail();
        String emailCode = loginBody.getEmailCode();

        // 通过邮箱查找用户
        LoginUser loginUser = remoteUserService.getUserInfoByEmail(email, tenantId);
        loginService.checkLogin(LoginType.EMAIL, tenantId, loginUser.getUsername(), () -> !validateEmailCode(tenantId, email, emailCode));
        loginUser.setClientKey(client.getClientKey());
        loginUser.setDeviceType(client.getDeviceType());
        SaLoginModel model = new SaLoginModel();
        model.setDevice(client.getDeviceType());
        // 自定义分配 不同用户体系 不同 token 授权时间 不设置默认走全局 yml 配置
        // 例如: 后台用户30分钟过期 app用户1天过期
        model.setTimeout(client.getTimeout());
        model.setActiveTimeout(client.getActiveTimeout());
        model.setExtra(LoginHelper.CLIENT_KEY, client.getClientId());
        // 生成token
        LoginHelper.login(loginUser, model);

        LoginVo loginVo = new LoginVo();
        loginVo.setAccessToken(StpUtil.getTokenValue());
        loginVo.setExpireIn(StpUtil.getTokenTimeout());
        loginVo.setClientId(client.getClientId());
        return loginVo;
    }

    /**
     * 校验邮箱验证码
     */
    private boolean validateEmailCode(String tenantId, String email, String emailCode) {
        String code = RedisUtils.getCacheObject(GlobalConstants.CAPTCHA_CODE_KEY + email);
        if (StringUtils.isBlank(code)) {
            loginService.recordLogininfor(tenantId, email, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire"));
            throw new CaptchaExpireException();
        }
        return code.equals(emailCode);
    }

}
