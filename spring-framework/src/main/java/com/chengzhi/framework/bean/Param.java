package com.chengzhi.framework.bean;

import java.util.Map;

public class Param {
	
	private Map<String, Object> paramMap;

	public Param(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	public long getLong(String name){
		return (long) paramMap.get(name);
	}
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
}
