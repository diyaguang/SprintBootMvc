package com.dygstudio.SpringBootMvc.MessageConverter;

import com.dygstudio.SpringBootMvc.Domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: diyaguang
 * @date: 2017/12/29 下午2:02
 * @description: com.dygstudio.SpringBootMvc.MessageConverter
 * 继承 AbstractHttpMessageConverter 来实现自定义的 HttpMessageConverter
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {


    //新建一个自定义的媒体类型
    public MyMessageConverter(){
        super(new MediaType("application","x-wisely", Charset.forName("UTF-8")));
    }

    //重写，处理请求的数据，将内容转换为 DemoObj对象。
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(),Charset.forName("UTF-8"));
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    //表明本 HttpMessageConvert 只处理 DemoOjb类型
    @Override
    protected boolean supports(Class<?> clazz){
        return DemoObj.class.isAssignableFrom(clazz);
    }

    //重写，处理如何输出数据到 response .
    @Override
    protected void writeInternal(DemoObj obj, HttpOutputMessage outputMessage) throws IOException,HttpMessageNotWritableException{
        String out = "hello:"+obj.getId()+"-"+obj.getName();
        outputMessage.getBody().write(out.getBytes());
    }
}
