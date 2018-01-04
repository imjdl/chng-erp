package cn.com.chng.erp.handlers;

import cn.com.chng.erp.api.ApiRest;
import cn.com.chng.erp.auth.CustomUserDetails;
import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.constants.SessionConstants;
import cn.com.chng.erp.domains.PowerStation;
import cn.com.chng.erp.domains.SystemUser;
import cn.com.chng.erp.services.SystemUserService;
import cn.com.chng.erp.utils.ApplicationHandler;
import cn.com.chng.erp.utils.GsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

/**
 * Created by liuyandong on 2017/5/5.
 */
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Autowired
    private SystemUserService systemUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        BigInteger userId = customUserDetails.getUserId();
        Map<String, Object> userInfo = systemUserService.obtainUserInfo(userId);
        SystemUser systemUser = (SystemUser) userInfo.get("user");
        systemUserService.updateLoginLog(ApplicationHandler.getRemoteAddress(), systemUser.getLoginCount() + 1, userId);

        HttpSession session = ApplicationHandler.getHttpSession();
        session.setAttribute(SessionConstants.KEY_CODE, systemUser.getCode());
        session.setAttribute(SessionConstants.KEY_USER_NAME, systemUser.getUserName());
        session.setAttribute(SessionConstants.KEY_USER_ID, systemUser.getId());

        session.setAttribute(SessionConstants.KEY_USER_TYPE, systemUser.getUserType());
        if (StringUtils.isNotBlank(systemUser.getMobile())) {
            session.setAttribute(SessionConstants.KEY_MOBILE, systemUser.getMobile());
        }
        if (StringUtils.isNotBlank(systemUser.getEmail())) {
            session.setAttribute(SessionConstants.KEY_EMAIL, systemUser.getEmail());
        }

        PowerStation powerStation = (PowerStation) userInfo.get("powerStation");
        if (powerStation != null) {
            session.setAttribute(SessionConstants.KEY_POWER_STATION_ID, powerStation.getId());
            session.setAttribute(SessionConstants.KEY_POWER_STATION_NAME, powerStation.getName());
            session.setAttribute(SessionConstants.KEY_IS_PHOTOVOLTAIC, powerStation.isPhotovoltaic());
            session.setAttribute(SessionConstants.KEY_IS_WIND_POWER, powerStation.isWindPower());
            session.setAttribute(SessionConstants.KEY_POWER_STATION_NAME, powerStation.getName());
        }

        ApiRest apiRest = new ApiRest();
        apiRest.setSuccessful(true);
        apiRest.setMessage("登录成功！");
        response.getOutputStream().write(GsonUtils.toJson(apiRest).getBytes(Constants.CHARSET_NAME_UTF_8));
    }
}
