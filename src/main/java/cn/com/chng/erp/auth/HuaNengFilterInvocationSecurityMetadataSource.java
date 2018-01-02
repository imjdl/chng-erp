package cn.com.chng.erp.auth;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.SystemPrivilege;
import cn.com.chng.erp.services.SystemPrivilegeService;
import cn.com.chng.erp.utils.AuthorizationUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by liuyandong on 2017/3/30.
 */
@Component
public class HuaNengFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private SystemPrivilegeService systemPrivilegeService;
    private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    public Map<RequestMatcher, Collection<ConfigAttribute>> getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(Map<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        this.requestMap = requestMap;
    }

    public void init() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        List<SystemPrivilege> systemPrivileges = systemPrivilegeService.findAll(Constants.SERVICE_NAME_ERP);
        for (SystemPrivilege systemPrivilege : systemPrivileges) {
            String controllerName = systemPrivilege.getControllerName();
            String actionName = systemPrivilege.getActionName();
            if (StringUtils.isBlank(controllerName) || StringUtils.isBlank(actionName)) {
                continue;
            }
            RequestMatcher requestMatcher = new AntPathRequestMatcher("/" + controllerName + "/" + actionName);
            List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
            int privilegeType = systemPrivilege.getPrivilegeType();
            if (privilegeType == Constants.SYSTEM_PRIVILEGE_TYPE_PERMIT_ALL) {
                configAttributes.add(new SecurityConfig(Constants.PERMIT_ALL));
            } else if (privilegeType == Constants.SYSTEM_PRIVILEGE_TYPE_HAS_AUTHORITY) {
                configAttributes.add(new SecurityConfig(String.format(Constants.HAS_AUTHORITY_FORMAT, systemPrivilege.getPrivilegeCode())));
            } else if (privilegeType == Constants.SYSTEM_PRIVILEGE_TYPE_AUTHENTICATED) {
                configAttributes.add(new SecurityConfig(Constants.AUTHENTICATED));
            }
            requestMap.put(requestMatcher, configAttributes);
        }
        this.requestMap = AuthorizationUtils.processMap(requestMap, new SpelExpressionParser());
    }

    public HuaNengFilterInvocationSecurityMetadataSource() {

    }

    public HuaNengFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
        this.requestMap = requestMap;
    }


    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
