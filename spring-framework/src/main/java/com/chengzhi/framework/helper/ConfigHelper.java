package com.chengzhi.framework.helper;

import java.util.Properties;
import com.chengzhi.framework.ConfigConstant;
import com.chengzhi.framework.util.PropsUtils;

public final class ConfigHelper {
	
	private static final Properties CON_PROPERTIES = PropsUtils.loadConfig(ConfigConstant.CONFIG_FILE);

	public static String getJdbcDriver(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.JDBC_DRIVER);
	}
	public static String getJdbcUrl(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.JDBC_URL);
	}
	public static String getJdbcUsername(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.JDBC_USERNAME);
	}
	public static String getJdbcPassword(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.JDBC_PASSWORD);
	}
	public static String getWebBasePackage(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.WEB_BASE_PACKAGE);
	}
	public static String getWebJspPath(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.WEB_JSP_PATH);
	}
	public static String getWebAssetPath(){
		return PropsUtils.getString(CON_PROPERTIES, ConfigConstant.WEB_ASSET_PATH);
	}
}
