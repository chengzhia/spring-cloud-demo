package com.chengzhi.framework.helper;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.chengzhi.framework.annotation.Resource;
import com.chengzhi.framework.util.ReflectionUtil;

/**
 *实现依赖注入功能 
 */
public final class IocHelper {
	
	static{
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if (!beanMap.isEmpty()) {
			for (Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()) {
				Class<?> beanClass = beanEntity.getKey();
				Object beanInstance = beanEntity.getValue();
				//反射获取当前类下所有字段
				Field[] beanFields = beanClass.getDeclaredFields();
				if(ArrayUtils.isNotEmpty(beanFields)){
					for (Field beanField : beanFields) {
						//判断当前字段是否带有resource注解
						if(beanField.isAnnotationPresent(Resource.class)){
							Class<?> beanFieldClass = beanField.getType();
							//获取字段实例
							Object beanFieldInstance = beanMap.get(beanFieldClass);
							if (beanFieldInstance!=null) {
								ReflectionUtil.setFileld(beanInstance, beanField, beanFieldInstance);
							}
						}
					}
				}
			}
		}
	}
}
