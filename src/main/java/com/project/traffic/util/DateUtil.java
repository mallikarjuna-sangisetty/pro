package com.project.traffic.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate(){
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(date);
		return modifiedDate;
	}
}
