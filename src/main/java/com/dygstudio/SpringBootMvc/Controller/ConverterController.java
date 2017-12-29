package com.dygstudio.SpringBootMvc.Controller;

import com.dygstudio.SpringBootMvc.Domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: diyaguang
 * @date: 2017/12/29 下午2:44
 * @description: com.dygstudio.SpringBootMvc.Controller
 */
@Controller
public class ConverterController {

    //指定返回的没体力型为自定义的媒体类型 application/x-wisely
    @RequestMapping(value = "/convert",produces = "application/x-wisely")
    public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }
}
