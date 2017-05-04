package com.chengzhi.framework.util;

import java.io.InputStream;
import java.util.Properties;

public class PropsUtils {
	
	private static Properties pros = null;
	
	public static Properties loadConfig(String fileName){
		 try {
			pros = new Properties();
			 InputStream in = PropsUtils.class.getResourceAsStream(fileName);  
			 pros.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pros;
	}
	
	public static String getString(Properties properties,String fileKey){
		return properties.getProperty(fileKey);
	}
}

