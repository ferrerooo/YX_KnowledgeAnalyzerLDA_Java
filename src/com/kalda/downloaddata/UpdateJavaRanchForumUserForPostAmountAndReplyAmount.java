package com.kalda.downloaddata;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import com.kalda.domain.TblUser;
import com.kalda.business.CorpusMgrBO;

public class UpdateJavaRanchForumUserForPostAmountAndReplyAmount {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {

		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		List<TblUser> userList = c.loadAllUsersWithPostAndReply();
		
		c.updateUserJavaRanchForPostAmountAndReplyAmount(userList);

	}

}
