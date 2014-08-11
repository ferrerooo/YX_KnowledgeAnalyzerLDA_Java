package com.kalda.downloaddata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblReplyJavaRanch;

public class V2_generateCorpusJavaRanchPostNoCode {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		
		List<TblReplyJavaRanch> replyList = new ArrayList<TblReplyJavaRanch>();
		
		File file = null;
		BufferedWriter writer = null;
		TblReplyJavaRanch r = new TblReplyJavaRanch();
		
		
		System.out.println(replyList.size());
		
		
		for (int j=1;j<=111132;j++) {
			
			replyList = c.getReplyJavaRanch(j);
			r = replyList.get(0);
			
			try {
							
				String sss = r.getReplyContent();
				
				if (	sss.contains("<textarea") == false && 
						sss.contains("</textarea>")==false &&
						V2_generateCorpusJavaRanchPostNoCode.containJava(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containDumpStack(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containDumpStack2(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containXML(sss) == false ) {
					
					file = new File("C:/Users/Administrator/Desktop/CorpusPostBasedNoCoCde_v2/"+ r.getId() +"-"+r.getThreadId()+ ".txt");	
					writer = new BufferedWriter(new FileWriter(file));
					writer.write(sss);

				}
				
				if (j%10000==0)
					System.out.println("generated file "+j);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static boolean containJava(String str) {
		String tempString = new String(str);
		for (int i=0;i<3;i++) {
			int position = tempString.indexOf("{");
			if (position == -1) {
				return false;
			} else {
				tempString = tempString.substring(position+1);
			}
		}
		return true;
	}
	public static boolean containDumpStack(String str) {
		String tempString = new String(str);
		System.out.println(str);
		for (int i=0;i<5;i++) {
			int position = tempString.indexOf("at");
			if (position == -1) {
				return false;
			} else {
				tempString = tempString.substring(position+2);
			}
		}
		return true;
	}
	public static boolean containDumpStack2(String str) {
		String tempString = new String(str);
		System.out.println(str);
		for (int i=0;i<5;i++) {
			int position = tempString.indexOf(".java");
			if (position == -1) {
				return false;
			} else {
				tempString = tempString.substring(position+5);
			}
		}
		return true;
	}
	public static boolean containXML(String str) {
		String tempString = new String(str);
		for (int i=0;i<3;i++) {
			int position = tempString.indexOf("</");
			if (position == -1) {
				return false;
			} else {
				tempString = tempString.substring(position+2);
			}
		}
		return true;
	}

}
