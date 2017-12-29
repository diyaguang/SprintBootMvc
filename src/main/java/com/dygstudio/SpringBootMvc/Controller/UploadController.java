package com.dygstudio.SpringBootMvc.Controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: diyaguang
 * @date: 2017/12/29 上午10:40
 * @description: com.dygstudio.SpringBootMvc.Controller
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(MultipartFile file, HttpServletRequest request){
        try {
            String pathStr = request.getServletContext().getRealPath("//upload/");
            File path = new File(pathStr);
            if(!path.exists())
                path.mkdirs();
            FileUtils.writeByteArrayToFile(new File(pathStr+file.getOriginalFilename()),file.getBytes());
            return "ok";
        }catch (IOException e){
            e.printStackTrace();
            return "wrong";
        }
    }
}
