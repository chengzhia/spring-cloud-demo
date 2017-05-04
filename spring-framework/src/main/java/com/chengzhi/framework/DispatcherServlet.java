package com.chengzhi.framework;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.chengzhi.framework.bean.Data;
import com.chengzhi.framework.bean.Handler;
import com.chengzhi.framework.bean.Param;
import com.chengzhi.framework.bean.View;
import com.chengzhi.framework.helper.BeanHelper;
import com.chengzhi.framework.helper.ConfigHelper;
import com.chengzhi.framework.helper.ControllerHelper;
import com.chengzhi.framework.util.CodecUtil;
import com.chengzhi.framework.util.ReflectionUtil;
import com.chengzhi.framework.util.StreamUtil;
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/*",loadOnStartup=0)
public class DispatcherServlet extends HttpServlet{

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		HelperLoader.init();
		ServletContext servletContext = servletConfig.getServletContext();
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigHelper.getWebJspPath()+"*");
		ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
		defaultServlet.addMapping(ConfigHelper.getWebAssetPath()+"*");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestMethod = request.getMethod().toLowerCase();
		String requestPath = request.getPathInfo();
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		while (handler!=null) {
			Class<?> controllerClass = handler.getControllerClass();
			Object controllerBean = BeanHelper.getBean(controllerClass);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String paramValue = request.getParameter(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
			if (StringUtils.isNoneEmpty(body)) {
				String[] params = StringUtils.split(body, "&");
				if (ArrayUtils.isNotEmpty(params)) {
					for (String param : params) {
						String[] array = StringUtils.split(param,"=");
						if (ArrayUtils.isNotEmpty(array)&&array.length==2) {
							String paramName = array[0];
							String paramValue = array[1];
							paramMap.put(paramName, paramValue);
						}
					}
				}
			}
			Param param = new Param(paramMap);
			Method actionMethod = handler.getActionMethod();
			Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
			if (result instanceof View) {
				View view = (View)result;
				String path = view.getPath();
				if (StringUtils.isNotEmpty(path)) {
					if (path.startsWith("/")) {
						response.sendRedirect(request.getContextPath()+path);
					} else {
						Map<String, Object> model = view.getModel();
						for (Map.Entry<String, Object> entry : model.entrySet()) {
							request.setAttribute(entry.getKey(), entry.getValue());;
						}
						request.getRequestDispatcher(ConfigHelper.getWebJspPath()+path).forward(request,response);
					}
				}
			} else if(result instanceof Data){
				Data data = (Data) result;
				Object model = data.getModel();
				if (model!=null) {
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					PrintWriter writer = response.getWriter();
					String json = JSON.toJSONString(model);
					writer.write(json);
					writer.flush();
					writer.close();
				}
			}
		}
	}
}
