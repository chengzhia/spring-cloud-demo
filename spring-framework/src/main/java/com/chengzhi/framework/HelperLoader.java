package com.chengzhi.framework;

import com.chengzhi.framework.helper.BeanHelper;
import com.chengzhi.framework.helper.ClassHelper;
import com.chengzhi.framework.helper.ControllerHelper;
import com.chengzhi.framework.helper.IocHelper;
import com.chengzhi.framework.util.Classutil;

public final class HelperLoader {
	
	public static void init(){
		Class<?>[] classList = {ClassHelper.class,BeanHelper.class,
				IocHelper.class,ControllerHelper.class};
		for (Class<?> cls : classList) {
			Classutil.loadClass(cls.getName(),false);
		}
	}

}
