package com.dygstudio.SpringBootMvc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: diyaguang
 * @date: 2017/12/28 上午11:18
 * @description: com.dygstudio.SpringBootMvc.Controller
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello(){
        return "index";
    }
}
