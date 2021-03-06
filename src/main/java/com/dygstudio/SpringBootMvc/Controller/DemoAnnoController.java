package com.dygstudio.SpringBootMvc.Controller;

import com.dygstudio.SpringBootMvc.Domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: diyaguang
 * @date: 2017/12/28 下午2:23
 * @description: com.dygstudio.SpringBootMvc.Controller
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index(HttpServletRequest request){
        return "url:"+request.getRequestURL()+" can access";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str,HttpServletRequest request){
        return "url:"+request.getRequestURL()+" can access,str: "+str;
    }

    //常规的 request 获取参数 /anno/requestParam?id=1
    @RequestMapping(value = "/requestParam",produces="text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
        return "url:"+request.getRequestURL()+" can access,id:"+id;
    }

    //演示 解释参数到对象，访问路径为 /anno/obj?id=1&name=xx
    @RequestMapping(value = "/obj",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj,HttpServletRequest request){
        return "url:"+request.getRequestURL()+" can access, obj id"+obj.getId()+" obj name:"+obj.getName();
    }

    @RequestMapping(value = {"/name1","/name2"},produces = "text/plain;charset=UTF-8")
    public @ResponseBody String remove(HttpServletRequest request){
        return "url:"+request.getRequestURL()+" can access";
    }
}
