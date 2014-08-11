package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.business.CorpusMgrBO;
import com.kalda.domain.TblThreadStackoverflow;

public class DownloadStackoverflowEveryThread {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;
	
	public static void main(String[] args) {
		
		factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO) factory.getBean("CorpusMgrBO");


		File file = null;
		BufferedWriter writer = null;
		
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		String tempString = "";
		
		//  i from 1 to 452300
		for (int i = 50000; i < 200000; i++) {
			
			try {

				tempString = "";

				TblThreadStackoverflow tsf = c.getThreadStackoverflow(i);
				String url = tsf.getThreadURL().replaceAll("stackoverflow.com", "");
				//System.out.println(url);
				
				int filenameNum = tsf.getId();
				if (filenameNum <10 ){
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/00000"+ tsf.getId() + ".txt");
				}else if (filenameNum >= 10 && filenameNum < 100) {
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/0000"+ tsf.getId() + ".txt");
				} else if (filenameNum >= 100 && filenameNum < 1000) {
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/000"+ tsf.getId() + ".txt");
				} else if (filenameNum >= 1000 && filenameNum < 10000) {
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/00"+ tsf.getId() + ".txt");					
				} else if (filenameNum >= 10000 && filenameNum < 100000) {
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/0"+ tsf.getId() + ".txt");
				} else {
					file = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/"+ tsf.getId() + ".txt");
				}
				
				//System.out.println(file.getAbsolutePath());

				writer = new BufferedWriter(new FileWriter(file));

				socket = new Socket("stackoverflow.com", 80);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				out.println("GET " + url + " HTTP/1.1");
				//out.println("GET "+"/t/598173/java/java/Design-App-Customisation-Customers"+" HTTP/1.1");
				out.println("Host:	stackoverflow.com");
				out.println("Accept: text/html, application/xhtml+xml, */*");
				out.println("Accept-Language: en-US");
				out.println("Accept-Encoding: \r\n");
				out.println("Accept-Charset: utf-8;q=0.7,*;q=0.7");
				out.println("Keep-Alive: 300");
				out.println("Connection: Keep-Alive");
				out.println("");

				while ((tempString = in.readLine()) != null) {
					
					if (tempString.contains("&#39;")) {
						tempString = tempString.replaceAll("&#39;", "'");
					}
					if (tempString.contains("&gt;")) {
						tempString = tempString.replaceAll("&gt;", ">");
					}
					if (tempString.contains("&lt;")) {
						tempString = tempString.replaceAll("&lt;", "<");
					}
					if (tempString.contains("&amp;")) {
						tempString = tempString.replaceAll("&amp;", "&");
					}
					if (tempString.contains("&nbsp;")) {
						tempString = tempString.replaceAll("&nbsp;", " ");
					}
					if (tempString.contains("&ndash;")) {
						tempString = tempString.replaceAll("&ndash;", "-");
					}
					if (tempString.contains("&ldquo;")) {
						tempString = tempString.replace("&ldquo;","\"");
					}
					if (tempString.contains("&hellip;")) {
						tempString = tempString.replace("&hellip;","...");
					}
					if (tempString.contains("&rdquo;")) { 
						tempString = tempString.replace("&rdquo;","\"");
					}
					//System.out.println(tempString);
					writer.write(tempString + "\n");
				}
				System.out.println("thread " + i);
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					writer.close();
					out.close();
					in.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
