package com.chengzhi.framework.helper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import com.chengzhi.framework.annotation.RequestMapping;
import com.chengzhi.framework.bean.Handler;
import com.chengzhi.framework.bean.Request;

public final class ControllerHelper {
	
	private static final Map<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static{
		Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
		if (CollectionUtils.isNotEmpty(controllerClassSet)) {
			for (Class<?> controllerClass : controllerClassSet) {
				//获取controller中的所有方法
				Method[] methods = controllerClass.getDeclaredMethods();
				if (ArrayUtils.isNotEmpty(methods)) {
					for (Method method : methods) {
						//看方法上面是否有reuestMapping注解
						if (method.isAnnotationPresent(RequestMapping.class)) {
							RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
							String mapping = requestMapping.value();
							if(mapping.matches("\\w+:/\\ww*")){
								String[] array = mapping.split("");
								if (ArrayUtils.isNotEmpty(array)&&array.length==2) {
									String requestMethod = array[0];
									String requestPath = array[1];
									Request request = new Request(requestMethod, requestPath);
									Handler handler = new Handler(controllerClass, method);
									ACTION_MAP.put(request, handler);
								}
							}
						}
					}
				}
			}
		}
	}
	public static Handler getHandler(String requestMethod,String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return ACTION_MAP.get(request);
	}
}
