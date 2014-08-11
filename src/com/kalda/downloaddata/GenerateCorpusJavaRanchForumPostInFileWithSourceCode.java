package com.kalda.downloaddata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.domain.TblReplyJavaRanch;
import com.kalda.domain.TblUser;
import com.kalda.business.CorpusMgrBO;

public class GenerateCorpusJavaRanchForumPostInFileWithSourceCode {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		
		List<TblReplyJavaRanch> replyList = new ArrayList<TblReplyJavaRanch>();
		
		File file = null;
		BufferedWriter writer = null;
		
		replyList = c.getReplyJavaRanch();
		
		System.out.println(replyList.size());
		
		for (int j=0;j<replyList.size();j++) {
			
			try {
				//String s = replyList.get(j).getAuthor();
				//s = s.replaceAll(" ", "_");
				file = new File("C:/Users/Administrator/Desktop/corpusfinal_afile_is_apost_withsourcecode/"+ replyList.get(j).getId() +"-"+replyList.get(j).getThreadId()+ ".txt");
				writer = new BufferedWriter(new FileWriter(file));
				writer.write(replyList.get(j).getReplyContent());
				
				System.out.println("generated file "+j);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		

	}

}
