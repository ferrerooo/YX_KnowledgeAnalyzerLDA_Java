package com.kalda.business;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import com.kalda.utils.DocTopicListComparator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.dto.corpus.NewCorpusTrainResult;

public class TestCorpusMgrBO {
	
	private static ApplicationContext factory;
	private static CorpusMgrBO c;

	public static void main(String[] args) {
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO)factory.getBean("CorpusMgrBO");
		
		c.getReplyContentWithSourceCode();
		
		List<String> list = c.loadAllUsers();
		String []arr;
		int count = 0;
		for (String str:list) {
			count++;
			if (count%2000 == 0) {
				System.out.println("--------------------------------------");
			}
			arr = str.split(" ");
			for (int i=0;i<arr.length;i++) {				
				System.out.println(arr[i].toLowerCase());
			}
		}
			
		
		/*
		// ---------- test function: New corpus Training. Give a document, this can output the corresponding topic rank  -------------
		String ldaRunDocName = "model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20";
		String newCorpus = "Explicit destruction is not a feature of the Java language. The destroy() method of a class is not use to garbage-collect it, it is invoked when the garbage collector is about to destroy the object.  Not quite. destroy() is not a Java language feature: this method is declared in javax.servlet.GenericServlet and is invoked by the servlet container when it decides to discard an instance of a servlet class. From the API docs on javax.servlet.destroy(): Called by the servlet container to indicate to a servlet that the servlet is being taken out of service. This method is only called once all threads within the servlet's service method have exited or after a timeout period has passed. After the servlet container calls this method, it will not call the service method again on this servlet. This method gives the servlet an opportunity to clean up any resources that are being held (for example, memory, file handles, threads) and make sure that any persistent state is synchronized with the servlet's current state in memory.  Whoops. I did it again. \"finalize()\" is the method I was referring to. \"destroy()\" provides a similar function, but at the container's discretion, not the garbage collector's.";
		List<NewCorpusTrainResult> list = c.trainNewCorpus(ldaRunDocName, newCorpus);
		System.out.println();
		
		DocTopicListComparator comp = new DocTopicListComparator();
		Collections.sort(list,comp);
		
		// output topic rank list
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i).getTopicNum()+" "+list.get(i).getProbability());
		}
		
		// output adjacent topic probability gap list
		Double gap = new Double(0);
		DecimalFormat df = new DecimalFormat("0.00000000000"); 
		for (int i=0; i<list.size()-1;i++) {
			gap = (list.get(i).getProbability() - list.get(i+1).getProbability());
			String num = df.format(gap); 
			System.out.println("topic"+list.get(i).getTopicNum()+"-"+"topic"+list.get(i+1).getTopicNum()+" "+num);
		}
		*/

	}

}
