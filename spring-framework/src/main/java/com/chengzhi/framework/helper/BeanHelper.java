package com.chengzhi.framework.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chengzhi.framework.util.ReflectionUtil;

public final class BeanHelper {
	
	private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	static{
		Set<Class<?>> beClassesSet = ClassHelper.getBeanClassSet();
		for (Class<?> beanClass : beClassesSet) {
			Object object = ReflectionUtil.newInstace(beanClass);
			BEAN_MAP.put(beanClass, object);
		}
	}
	/*
	 * 获取bean映射
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	/**
	 * 获取bean实例
	 */
	public static <T> T getBean(Class<T> cls){
		if (!BEAN_MAP.containsKey(cls)) {
			throw new RuntimeException("can not get bean by class:"+cls);
		}
		return (T)BEAN_MAP.get(cls);
	}
}
