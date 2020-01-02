package com.jbn.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("ctx",sce.getServletContext().getContextPath());
        System.out.println("创建成功，ctx被放入context中");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
