package com.cheng.web.filter;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.cheng.common.Common;
import com.cheng.web.entity.XeFilterDto;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class XeFilter implements Filter{
	
	private static CacheManager cacheManager =null;
	private static Cache cache =null;
	
	@Override
	public void destroy() {
		cacheManager.shutdown();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request= (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String requestURI = request.getRequestURI();
		String requestIp = Common.toIpAddr(request);
		String pattern = "";
		boolean flag = pattern.matches(requestURI);
		String userAgent = request.getHeader("user-agent");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		Element elementIp = cache.get(requestURI);
		if(elementIp==null){
			Element element = cache.get(requestIp+requestURI);
			if (element==null) {  //此IP第一次访问 次url放入缓存
				XeFilterDto xeFilterDto = new XeFilterDto(requestURI, requestIp, 0);
				Element element2 = new Element(requestIp+requestURI,1);
				cache.put(element2);
				filterChain.doFilter(request, response);
			}else if((int)element.getValue()==1){//时间内访问频次符合屏蔽要求
				Element ele = new Element(requestIp,requestIp); 
				cache.put(ele);
				return;
			}else {//增加访问频次
				int  value = (int) element.getValue();
				Element element2 = new Element(requestIp+requestURI,value+1);
				cache.put(element2);
				filterChain.doFilter(request, response);
			}
		}else {
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		 cacheManager = CacheManager.create("src/main/resources/ehcache.xml");
		 cache = cacheManager.getCache("springBoot-cache");
	}

}
