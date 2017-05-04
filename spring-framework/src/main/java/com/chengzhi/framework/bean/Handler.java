package com.chengzhi.framework.bean;

import java.lang.reflect.Method;

public class Handler {
	
	private Class<?> controllerClass;
	
	private Method actionMethod;

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	public Handler(Class<?> controllerClass, Method actionMethod) {
		this.controllerClass = controllerClass;
		this.actionMethod = actionMethod;
	}
}
