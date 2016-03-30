package com.project.traffic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.project.traffic.mail.SSLMailUtil;

public class FileUtil {
	private static final Logger LOGGER = Logger.getLogger(FileUtil.class);
	public static String readFile(String fileName){
		String result = "";
		
		ClassLoader classLoader = FileUtil.class.getClassLoader();
		try {
		    result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		} catch (IOException e) {
			LOGGER.info("Reading file failed",e);
		}
			
		return result;
	}
	

	private static Properties properties = null;

	public static String getvalue(String key) throws IOException{

		if(properties == null){
			properties = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			properties.load(loader.getResourceAsStream("mail.properties"));
			properties.load(loader.getResourceAsStream("aws.properties"));
			properties.load(loader.getResourceAsStream("database.properties"));
		}
		return properties.getProperty(key).trim();
	}

}
