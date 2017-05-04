package com.cheng.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheng.web.service.HelloService;

@Service
@Transactional
public class HelloServiceImpl implements HelloService{

	@Override
	public void queryHello(String string) {
		System.out.println(string);
	}

}
