package com.kalda.downloaddata;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;



import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblUser;

// 这里 只是 存了 user的name和whichcorpus两个字段。没有存postAmount 和 replyAmount
public class SaveJavaRanchForumUser {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {

		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		List<String> nameList = c.loadAllUsers();
		
		List<TblUser> userList = new ArrayList<TblUser>();

		for (int i=0;i<nameList.size();i++) {
			TblUser u = new TblUser();
			u.setUserName(nameList.get(i));
			u.setWhichCorpus("javaranchservlet");
			userList.add(u);
		}
		
		c.saveUserJavaRanch(userList);
	}

}
