package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.Attribute;

import com.kalda.domain.TblThread;
import com.kalda.domain.TblThreadJavaRanch;
import com.kalda.utils.StringUtil;

public class DownloadJavaRanchForumThreadList {

	
	public static void main(String[] args) {
		DownloadJavaRanchForumThreadList sog = new DownloadJavaRanchForumThreadList();
		// 24920
		
		//sog.fetchData(1);
		for (int i=24920;i<=24920;i=i+40) {
			sog.fetchData(i);
		}
	}
	
	private ArrayList<TblThread> fetchData(int num) {
		
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		String tempString = "";
		String selectedString = "";
		
		List<TblThreadJavaRanch> list = new ArrayList<TblThreadJavaRanch>();
		
		try {
			
			socket = new Socket("www.coderanch.com", 80);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println("GET /forums/forums/show/"+num+"/7 HTTP/1.1");
			out.println("Host:	www.coderanch.com");
			out.println("Accept: text/html, application/xhtml+xml, */*");
			out.println("Accept-Language: en-US");
			out.println("Accept-Encoding: \r\n");
			out.println("Accept-Charset: utf-8;q=0.7,*;q=0.7");
			out.println("Keep-Alive: 300");
			out.println("Connection: Keep-Alive");
			out.println("");
			
			boolean load = false;
			
			while ((tempString = in.readLine()) != null) {
				//System.out.println(tempString);
				if (tempString.contains("subject") && tempString.contains("class") && tempString.contains("div")) {
					load = true;
				}
				if (tempString.contains("pagination") && tempString.contains("class") && tempString.contains("div")) {
					load = false;
				}
				
				if (load == true) {
					selectedString = selectedString + tempString.trim()+"\n";
				}
			}

			selectedString = selectedString.replaceAll("<br />", "");
			selectedString = selectedString.replaceAll("<br /", "");
			selectedString = "<xml>" + selectedString + "</xml>";
			System.out.println(selectedString);
			//---------------------- xml parsing ----------------------------------
			
			
			
			SAXReader reader = new SAXReader();
			Document document = reader.read(new ByteArrayInputStream(selectedString.getBytes()));
			Element rootElement = document.getRootElement();
			
			TblThreadJavaRanch ttjr = null;
			
			for (java.util.Iterator i = rootElement.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				
				for (java.util.Iterator j = element.attributeIterator(); j.hasNext(); ) {
					
					Attribute attribute = (Attribute) j.next();
					//System.out.println(attribute.getName() + "--" + attribute.getValue());
					if ("subject".equals(attribute.getValue())) {
						ttjr = new TblThreadJavaRanch();
						ttjr.setComponent("servlet");
						for (java.util.Iterator k = element.elementIterator(); k.hasNext(); ) {
							Element e = (Element) k.next();
							
							for (java.util.Iterator m = e.attributeIterator(); m.hasNext();) {
								Attribute attr = (Attribute) m.next();
								//System.out.println(attr.getName() + "--" + attr.getValue());
								ttjr.setThreadURL("http://www.coderanch.com/" + attr.getValue().trim());
								
								int end = attr.getValue().indexOf("/Servlets/");
								ttjr.setThreadId(new Integer(attr.getValue().substring(3, end)));
								
							}
							//System.out.println(e.getTextTrim());
							ttjr.setTitle(e.getTextTrim());
							
						}
					}
					if ("mobilePostText".equals(attribute.getValue())) {
						//System.out.println(element.getTextTrim());
						// calculate anwser number
						int end = element.getTextTrim().indexOf("Started by:");
						String answerNum = element.getTextTrim().substring(16, end);
						ttjr.setAnswerAmount(new Integer(answerNum.trim()));
						// calculate auther
						int start = element.getTextTrim().indexOf("Started by:");
						end = element.getTextTrim().indexOf("Last post:");
						String author = element.getTextTrim().substring(start+12, end);
						ttjr.setAuthor(author.trim());
						
						list.add(ttjr);
					}
				}
				
			}
			//--------------------------------------------------------
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//----- save into local file --------------------------------------------
		
		File cacheFile = new File("C:/Users/Administrator/Desktop/javaranchservlet/threadlist.txt");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(cacheFile, true));
			for (int i=0;i<list.size();i++) {
				writer.write(	list.get(i).getTitle()+";;;"+
								list.get(i).getThreadId()+";;;"+
								list.get(i).getThreadURL()+";;;"+
								list.get(i).getAnswerAmount()+";;;"+
								list.get(i).getComponent()+";;;"+
								list.get(i).getAuthor()+"\n");
			}
			
			System.out.println("num="+num+"  inserted "+list.size()+" threads!");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
