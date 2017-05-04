package com.chengzhi.framework.helper;

import java.util.HashSet;
import java.util.Set;

import com.chengzhi.framework.annotation.Controller;
import com.chengzhi.framework.annotation.Service;
import com.chengzhi.framework.util.Classutil;

public final class ClassHelper {
	
	private static final Set<Class<?>> CLASS_SET;
	
	static{
		String basePckage = ConfigHelper.getWebBasePackage();
		CLASS_SET = Classutil.getClassSet(basePckage);
	}
	/*
	 * 获取包下所有类
	 */
	public static Set<Class<?>> getClassSet(){
		return CLASS_SET;
	}
	/*
	 * 获取所有的service
	 */
	public static Set<Class<?>> getServiceClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Service.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	/*
	 * 获取所有的controller
	 */
	public static Set<Class<?>> getControllerClassSet(){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		for (Class<?> cls : CLASS_SET) {
			if(cls.isAnnotationPresent(Controller.class)){
				classSet.add(cls);
			}
		}
		return classSet;
	}
	/*
	 * 获取所有的Bean类
	 */
	public static Set<Class<?>> getBeanClassSet(){
		Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
		beanClassSet.addAll(getServiceClassSet());
		beanClassSet.addAll(getControllerClassSet());
		return beanClassSet;
	}
}
