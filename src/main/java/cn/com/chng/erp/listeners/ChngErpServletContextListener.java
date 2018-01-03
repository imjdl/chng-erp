package cn.com.chng.erp.listeners;

import cn.com.chng.erp.utils.ApplicationHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ChngErpServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        webApplicationContext.getAutowireCapableBeanFactory().autowireBean(this);
        ApplicationHandler.setApplicationContext(webApplicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
