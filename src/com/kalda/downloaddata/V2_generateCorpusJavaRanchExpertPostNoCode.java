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
import com.kalda.domain.TblUser;

public class V2_generateCorpusJavaRanchExpertPostNoCode {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		List<TblUser> userList = c.loadAllUsersWithPostAndReply();
		List<TblReplyJavaRanch> replyList = new ArrayList<TblReplyJavaRanch>();
		
		File file = null;
		BufferedWriter writer = null;
		
		
		for (int i=0;i<userList.size();i++) {
			//if (i%1000 == 0)
				System.out.println(i);
			// if the user is an expert
			if (userList.get(i).getReplyAmount() > 0 && userList.get(i).getPostAmount() <5) {
				
				
				replyList.addAll(c.getReplyJavaRanch(userList.get(i)));
				
			}	

		}
		

		
		
		
		for (int j=0;j<replyList.size();j++) {
			
			try {
				String s = replyList.get(j).getAuthor();
				s = s.replaceAll(" ", "_");
				
				String sss = replyList.get(j).getReplyContent();
				
				if (	sss.contains("<textarea") == false && 
						sss.contains("</textarea>")==false &&
						V2_generateCorpusJavaRanchPostNoCode.containJava(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containDumpStack(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containDumpStack2(sss) == false &&
						V2_generateCorpusJavaRanchPostNoCode.containXML(sss) == false ) {
					
					file = new File("C:/Users/Administrator/Desktop/CorpusPostBasedNoCoCde_v2_expertpost3000/"+ s +"-"+replyList.get(j).getId() + ".txt");
					writer = new BufferedWriter(new FileWriter(file));
					writer.write(sss);
				}
				
				if (j%1000==0)
					System.out.println("generated file "+j);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (writer != null)
						writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		

	}


}
