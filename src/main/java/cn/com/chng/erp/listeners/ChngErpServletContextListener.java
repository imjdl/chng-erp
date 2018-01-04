package cn.com.chng.erp.listeners;

import cn.com.chng.erp.constants.Constants;
import cn.com.chng.erp.domains.Configuration;
import cn.com.chng.erp.services.ConfigurationService;
import cn.com.chng.erp.utils.ApplicationHandler;
import cn.com.chng.erp.utils.ConfigurationUtils;
import cn.com.chng.erp.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.List;

@WebListener
public class ChngErpServletContextListener implements ServletContextListener {
    @Autowired
    private ConfigurationService configurationService;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        webApplicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        ApplicationHandler.setApplicationContext(webApplicationContext);

        try {
            List<Configuration> configurations = configurationService.findAllByDeploymentEnvironment(ConfigurationUtils.getConfiguration(Constants.SERVICE_NAME));
            ConfigurationUtils.loadConfigurations(configurations);
        } catch (IOException e) {
            LogUtils.error("初始化配置失败", this.getClass().getSimpleName(), "contextInitialized", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
