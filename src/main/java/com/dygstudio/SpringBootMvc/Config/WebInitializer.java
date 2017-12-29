package com.dygstudio.SpringBootMvc.Config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author: diyaguang
 * @date: 2017/12/28 上午11:10
 * @description: com.dygstudio.SpringBootMvc.Config
 * WebApplicationInitializer 是 spring 提供用来配置 Servlet3.0+ 配置的接口，从而实现了替代 web.xml 的位置。
 * 实现此接口将会自动被 SpringServletContainerInitializer（用来启动 Servlet3.0容器）获取到。
 * 新建 WebApplicationContext，注册配置类，并将其和当前 servletContext 关联。
 * 注册 Spring MVC 的 DispatcherServlet
 */
public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException{
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext);

        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }
}
