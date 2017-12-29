package com.dygstudio.SpringBootMvc.Config;

import com.dygstudio.SpringBootMvc.Interceptor.DemoInterceptor;
import com.dygstudio.SpringBootMvc.MessageConverter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @author: diyaguang
 * @date: 2017/12/28 上午10:50
 * @description: com.dygstudio.SpringBootMvc.Config
 * 继承 WebMvconfigurerAdapter 类，重写其方法可对 Spring MVC 进行配置
 * addResourceLocations 指的是文件访问的目录，AddResourceHandler 指的是对外暴露的访问路径。
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.dygstudio.SpringBootMvc.*")
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return  viewResolver;
    }

    //MultipartResolver 配置处理上传文件的配置
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000);
        //使传入的文件名称或数据可以正常中文，需要修改字节编码
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    //※※※※※※ 特别重要的，对静态资源进行过滤的方法，需要这个类继承 WebMvcConfigurerAdapter，并重写 addResourceHandlers方法。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }

    //设置页面转向默认的跳转对应规则，也就是 url 与 view 的对应关系，就是对应 RequestMapping 上配置的对应。
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/index").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
    }

    //配置拦截器的 Bean
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    //重写 addInterceptors 方法，注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(demoInterceptor());
    }

    /** 配置自定义的 HttpMessageConverter
     * 其中，使用 重写 extendMessageConverters 这个方法，这个方法不覆盖默认注册的 HttpMessageConverter
     * **/
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(converter());
    }
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}
