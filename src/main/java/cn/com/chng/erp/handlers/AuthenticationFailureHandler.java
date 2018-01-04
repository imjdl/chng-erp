package cn.com.chng.erp.handlers;

import cn.com.chng.erp.api.ApiRest;
import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.utils.GsonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liuyandong on 2017/5/5.
 */
@Component
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ApiRest apiRest = new ApiRest();
        apiRest.setSuccessful(false);
        apiRest.setError("用户名或密码错误！");
        response.getOutputStream().write(GsonUtils.toJson(apiRest).getBytes(Constants.CHARSET_NAME_UTF_8));
    }
}
