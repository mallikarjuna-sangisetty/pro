package com.project.traffic.util;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class FileUtilTest {
	
	@Test
	public void readFile(){
		System.out.println(FileUtil.readFile("bodyContent.html"));
	}
	
	@Test 
	public void getvalue() throws IOException{
		FileUtil.getvalue("accessKey");
	}
}
