package com.cheng.web.entity;

import java.io.Serializable;

public class XeFilterDto implements Serializable{
	
	/**
	 * 字段或域定义：<code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	private String ip;
	private Integer count;
	
	public XeFilterDto(String url, String ip, Integer count) {
		this.url = url;
		this.ip = ip;
		this.count = count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
