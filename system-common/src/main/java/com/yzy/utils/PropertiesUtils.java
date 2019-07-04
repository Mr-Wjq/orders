package com.yzy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PropertiesUtils {
	public static Properties getProperties(String propertiesName) throws IOException {
		
        Properties pro = new Properties();//属性集合对象 
        InputStream path =Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName);//获取路径并转换成流
        pro.load(path);
        return pro;
    }
	
}
