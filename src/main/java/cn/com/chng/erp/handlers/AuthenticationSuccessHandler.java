package cn.com.chng.erp.handlers;

import cn.com.chng.erp.api.ApiRest;
import cn.com.chng.erp.auth.CustomUserDetails;
import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.utils.GsonUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liuyandong on 2017/5/5.
 */
@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        ApiRest apiRest = new ApiRest();
        apiRest.setSuccessful(true);
        apiRest.setMessage("登录成功！");
        response.getOutputStream().write(GsonUtils.toJson(apiRest).getBytes(Constants.CHARSET_NAME_UTF_8));
    }
}
