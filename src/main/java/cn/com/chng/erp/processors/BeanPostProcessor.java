package cn.com.chng.erp.processors;

import cn.com.chng.erp.auth.HuaNengFilterInvocationSecurityMetadataSource;
import org.springframework.beans.BeansException;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by liuyandong on 2017/6/21.
 */
@Component
public class BeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {
    private FilterSecurityInterceptor filterSecurityInterceptor = null;
    private HuaNengFilterInvocationSecurityMetadataSource huaNengFilterInvocationSecurityMetadataSource = null;
    private boolean setCompleted = false;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof FilterSecurityInterceptor) {
            filterSecurityInterceptor = (FilterSecurityInterceptor) bean;
        }

        if (bean instanceof HuaNengFilterInvocationSecurityMetadataSource) {
            huaNengFilterInvocationSecurityMetadataSource = (HuaNengFilterInvocationSecurityMetadataSource) bean;
        }

        if (filterSecurityInterceptor != null && huaNengFilterInvocationSecurityMetadataSource != null && !setCompleted) {
            filterSecurityInterceptor.setSecurityMetadataSource(huaNengFilterInvocationSecurityMetadataSource);
            setCompleted = true;
        }
        return bean;
    }
}
