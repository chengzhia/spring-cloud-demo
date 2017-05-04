package com.chengzhi.framework.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class Classutil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Classutil.class);
	
	/*
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/*
	 * 加载类
	 */
	public static Class<?> loadClass(String className,boolean isInitialized){
		Class<?> clazz;
		try {
			clazz = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			LOGGER.error("load class failure", e);
			throw new RuntimeException(e);
		}
		return clazz;
	}
	/*
	 * 加载指定包下所有类
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
			while (urls.hasMoreElements()) {
				URL url = (URL) urls.nextElement();
				if(url!=null){
					String protocol = url.getProtocol();
					if(protocol.equals("file")){
						String packagePath = url.getPath().replaceAll("%20","");
						addClass(classSet,packagePath,packageName);
						
					}else if(protocol.equals("jar")){
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if(jarURLConnection!=null){
							JarFile jarFile = jarURLConnection.getJarFile();
							if (jarFile!=null) {
								Enumeration<JarEntry> entries = jarFile.entries();
								while (entries.hasMoreElements()) {
									JarEntry jarEntry = (JarEntry) entries.nextElement();
									String jarEntityname = jarEntry.getName();
									if (jarEntityname.endsWith(".class")) {
									 	String className = jarEntityname.substring(0,jarEntityname.lastIndexOf(".")).replace("/",".");
										doAddClass(classSet,className);
									}
								}
								
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("get class set failure",e);
			throw new RuntimeException(e);
		}
		return classSet;
	}

	private static void doAddClass(Set<Class<?>> classSet, String className) {
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}

	private static void addClass(Set<Class<?>> classSet, String packagePath,String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				return (file.isFile()&&file.getName().endsWith(".class"))||file.isDirectory();
			}
		});
		for (File file : files) {
			String fileName = file.getName();
			if(file.isFile()){
				String className = fileName.substring(0,fileName.lastIndexOf("."));
				if (StringUtils.isNotEmpty(packageName)) {
					className = packageName+"."+className;
				}
				doAddClass(classSet, className);
			}else{
				String subPackagePath = fileName;
				if (StringUtils.isNotEmpty(subPackagePath)) {
					subPackagePath = packagePath+"/"+subPackagePath;
				}
				String subpackageName = fileName;
				if (StringUtils.isNotEmpty(packageName)) {
					subpackageName = packageName+"."+subpackageName;
				}
				addClass(classSet, subPackagePath, subpackageName);
			}
		}
	}
	
}
