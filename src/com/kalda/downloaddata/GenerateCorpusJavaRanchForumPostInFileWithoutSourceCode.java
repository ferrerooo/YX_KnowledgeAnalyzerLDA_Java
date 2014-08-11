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

public class GenerateCorpusJavaRanchForumPostInFileWithoutSourceCode {

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

				file = new File("C:/Users/Administrator/Desktop/corpusfinal_afile_is_apost_withoutsourcecode/"+ r.getId() +"-"+r.getThreadId()+ ".txt");
				writer = new BufferedWriter(new FileWriter(file));
				
				
				
				String sss = ""; // no source code string
				
				sss = r.getReplyContent().replaceAll("<textarea.*</textarea>", " ");
				
				writer.write(sss);
				
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
