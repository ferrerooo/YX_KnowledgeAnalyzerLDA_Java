package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblReplyJavaRanch;

public class GenerateCorpusJavaRanchForumThreadInFileWithoutSourceCode {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		//File dir = new File("C:\\Users\\Administrator\\Desktop\\corpusfinal_afile_is_athread_withoutSourceCode");
		File dir = new File("C:\\Users\\Administrator\\Desktop\\corpusfinal_afile_is_apost_withoutsourcecode");
		String[] docNames = dir.list();
		
		for (int i=0;i<docNames.length;i++) {
		//for (int i=0;i<1;i++) {
			StringBuffer sb = new StringBuffer();
			//File file = new File("C:\\Users\\Administrator\\Desktop\\corpusfinal_afile_is_athread_withoutSourceCode" + "/" + docNames[i]);
			File file = new File("C:\\Users\\Administrator\\Desktop\\corpusfinal_afile_is_apost_withoutsourcecode" + "/" + docNames[i]);
			BufferedReader reader = null;
			BufferedWriter writer = null;
			String str = new String();
			try {
				reader = new BufferedReader(new FileReader(file));
				
				while ((str = reader.readLine()) != null) {
					//System.out.println(str);
					sb.append(str);
				}
				//if (sb.toString().contains("textarea")) 
				//System.out.println(docNames[i]);
				//System.out.println(sb.toString());			
				
				writer = new BufferedWriter(new FileWriter(file));
				
				if (sb.toString().contains("textarea")) {
					System.out.println(sb.toString());
				}
				if (sb.toString().contains("textarea")) {
					writer.write(" ");
				} else {
					//writer.write(sb.toString().replaceAll("<textarea.*</textarea>", " "));
					writer.write(sb.toString());
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (reader != null) {
					try {
						reader.close();
						writer.close();
					} catch (IOException e1) {
					}
				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
//		List<TblReplyJavaRanch> replyList = new ArrayList<TblReplyJavaRanch>();
//		
//		File file = null;
//		BufferedWriter writer = null;
//		TblReplyJavaRanch r = new TblReplyJavaRanch();
//		
//		
//		System.out.println(replyList.size());
//		
//		//for (int j=1266;j<1267;j++) {
//		//for (int j=0;j<replyList.size();j++) {
//		
//		for (int j=1;j<=111132;j++) {
//			
//			replyList = c.getReplyJavaRanch(j);
//			r = replyList.get(0);
//			
//			try {
//				//String s = r.getAuthor();
//				//s = s.replaceAll(" ", "_");
//				file = new File("C:/Users/Administrator/Desktop/corpusfinal_afile_is_apost_withoutsourcecode/"+ r.getId() +"-"+r.getThreadId()+ ".txt");
//				writer = new BufferedWriter(new FileWriter(file));
//				
//				
//				
//				String sss = ""; // no source code string
//				
//				sss = r.getReplyContent().replaceAll("<textarea.*</textarea>", " ");
//			
////				if(r.getReplyContent().contains("<text")) {
////					System.out.println("before : "+r.getReplyContent());
////					sss = r.getReplyContent();
////					
////					while(sss.contains("<textarea")) {
////						int start = sss.indexOf("<textarea");
////						int end = sss.indexOf("</textarea>");
////						sss = sss.substring(0, start) + sss.substring(end+11, sss.length());
////						//part1 = sss.substring(0, start) + sss.substring(end+11, sss.length());
////						
////					}
////					System.out.println("After  : "+sss);
////				}
//				
//				writer.write(sss);
//				
//				System.out.println("generated file "+j);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		}
//		
//		
//
	}

}
