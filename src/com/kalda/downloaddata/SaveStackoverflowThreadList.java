package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblThreadStackoverflow;

public class SaveStackoverflowThreadList {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {

		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");

		File file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/threadlist_v2.txt");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			int count = -1;
			while ((str = reader.readLine()) != null) {
				count++;
				// lines from 1 to 452300
				if (count >=300000 && count <452300) {
				s = str.split("zcy");
				TblThreadStackoverflow tsf = new TblThreadStackoverflow();
				
				tsf.setId(null);
				tsf.setTitle(s[0]);
				tsf.setThreadId(new Integer(s[1]));
				tsf.setThreadURL(s[2]);
				tsf.setVoteAmount(new Integer(s[3]));
				tsf.setAnswerAmount(new Integer(s[4]));
				tsf.setAnswerStatus(s[5]);
				tsf.setComponent(s[6]);
				tsf.setAuthor(s[7]);
				tsf.setReputationScore(new Integer(s[8]));
				
				c.saveThreadStackoverflow(tsf);
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
