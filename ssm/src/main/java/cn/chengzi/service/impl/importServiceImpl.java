package cn.chengzi.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.chengzi.mapper.ImportMaper;
import cn.chengzi.service.ImportService;
@Service
public class importServiceImpl implements ImportService{

	@Resource
	private ImportMaper importMaper;

	public void queryTest() {
		System.out.println("service执行了、、、、、、、、、、、、、、、、、、、、、、、、、");
		int test1 = importMaper.queryTest1();
		System.out.println(test1);
	}
}
