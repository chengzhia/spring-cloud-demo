package cn.chengzi.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.chengzi.service.ImportService;

@Controller
@RequestMapping("import")
public class ImportController {
//	@Value("${DEMO_QQ_MM}")
//	private String DEMO_QQ_MM;
	
	@Resource
	private ImportService importService;
	@RequestMapping("test")
	public void Test1(){
		System.out.println("controller执行了、、、、、、、、、、、、、、、、、、、、、、、、、");
		importService.queryTest();
		
	}
}
