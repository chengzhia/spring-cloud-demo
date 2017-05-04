package cn.chengzi.controller;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;

@RequestMapping
@Controller
public class FreeMarKerController {
	@RequestMapping("demoFtl")
	public @ResponseBody JSONObject DemoFtl() throws Exception{
		JSONObject jsonObject = new JSONObject();
		//1.创建配置实例Cofiguration  
        Configuration cfg = new Configuration();  
  
        //2.设置模板文件目录  
        cfg.setDirectoryForTemplateLoading(new File("template/main"));  
        //获取模板（template）  
        Template template = cfg.getTemplate("test.ftl");  
        //建立数据模型（Map）  
        Map<String, String> root = new HashMap<String, String>();  
        root.put("name", "cxl");  
        root.put("age", "25");  
        //获取输出流（指定到控制台（标准输出））  
        Writer out = new OutputStreamWriter(System.out);  
        //数据与模板合并（数据+模板=输出）  
        template.process(root, out);  
        out.flush();
        jsonObject.put("msg","OK");
		return jsonObject;  
	}
}
