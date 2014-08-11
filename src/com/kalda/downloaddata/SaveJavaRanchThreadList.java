package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblThreadJavaRanch;

public class SaveJavaRanchThreadList {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");

		
		File file = new File("C:/Users/Administrator/Desktop/javaranchservlet/threadlist.txt");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			int count = -1;
			while ((str = reader.readLine()) != null) {
				count++;
				if (count >=18225) {
				s = str.split(";;;");
				TblThreadJavaRanch tjr = new TblThreadJavaRanch();
				
				tjr.setId(null);
				tjr.setTitle(s[0]);
				tjr.setThreadId(new Integer(s[1]));
				tjr.setThreadURL(s[2]);
				tjr.setAnswerAmount(new Integer(s[3]));
				tjr.setComponent(s[4]);
				tjr.setAuthor(s[5]);
				
				c.saveThreadJavaRanch(tjr);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
		
	}

}
