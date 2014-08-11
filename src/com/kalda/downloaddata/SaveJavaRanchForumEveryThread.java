package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblReplyJavaRanch;

//有些帖子回复的太长了，放不到数据库里。 thread id 7994(thread没进table)， 960(缩减了code，并成功入表)， 1084(code没进table)
public class SaveJavaRanchForumEveryThread {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		File dir = new File("C:/Users/Administrator/Desktop/corpus reply author");
		File[] files = dir.listFiles();
		
		List<TblReplyJavaRanch> list = new ArrayList<TblReplyJavaRanch>();
		
		//for (int i=0;i<files.length;i++) {
		for (int i=22000; i<24798;i++) {
			
			System.out.println(files[i].getName());
			
			File file = new File("C:/Users/Administrator/Desktop/corpus reply author/"+files[i].getName());
			BufferedReader reader = null;
			String thread = "";
			String str = null;
			String[] authorreply = null;
			boolean isauthor = false;
			boolean isreply = false;
			
			try {
				
				reader = new BufferedReader(new FileReader(file));
				
				while ((str = reader.readLine()) != null) {
					thread = thread	+ str;			
				}
				
				authorreply = thread.split("<div class=\"mobilePostBody\">");
				
				for (int j=1;j<authorreply.length;j++) {
					
					TblReplyJavaRanch rjr = new TblReplyJavaRanch();
					
					String ar = authorreply[j];
					
					String[] aarr = ar.split("<div class=\"mobilePostText\">");
					
					rjr.setAuthor(aarr[0]);
					rjr.setReplyContent(aarr[1]);
					rjr.setReplySequenceNum(j-1);
					rjr.setThreadId(i+1);
					
					list.add(rjr);
					
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
		
		// save the list into database
		for (int m=0;m<list.size();m++) {
			
			c.saveReplyJavaRanch(list.get(m));
			System.out.println("#####save thread "+list.get(m).getThreadId()+" reply "+list.get(m).getReplySequenceNum());
			
		}
		
	}

}
