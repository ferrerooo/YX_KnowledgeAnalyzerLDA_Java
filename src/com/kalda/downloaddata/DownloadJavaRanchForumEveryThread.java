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
import com.kalda.domain.TblThreadJavaRanch;

public class DownloadJavaRanchForumEveryThread {

	private static ApplicationContext factory;
	private static CorpusMgrBO c;

	public static void main(String[] args) {

		factory = new FileSystemXmlApplicationContext(
				"WebContent/WEB-INF/applicationContext.xml");
		c = (CorpusMgrBO) factory.getBean("CorpusMgrBO");

		File file = null;
		BufferedWriter writer = null;

		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;

		String tempString = "";
		
		//for (int i = 21230; i <= 24798; i++) {
			
			int i=553;
			try {

				tempString = "";

				TblThreadJavaRanch tjr = c.getThreadJavaRanch(i);
				String url = tjr.getThreadURL().replaceAll("http://www.coderanch.com/", "");
				System.out.println(url);

				file = new File("C:/Users/Administrator/Desktop/javaranchservlet/"+ tjr.getId() + ".txt");
				System.out.println(file.getAbsolutePath());

				writer = new BufferedWriter(new FileWriter(file));

				socket = new Socket("www.coderanch.com", 80);
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				//out.println("GET " + url + " HTTP/1.1");
				out.println("GET "+"/t/598173/java/java/Design-App-Customisation-Customers"+" HTTP/1.1");
				out.println("Host:	www.coderanch.com");
				out.println("Accept: text/html, application/xhtml+xml, */*");
				out.println("Accept-Language: en-US");
				out.println("Accept-Encoding: \r\n");
				out.println("Accept-Charset: utf-8;q=0.7,*;q=0.7");
				out.println("Keep-Alive: 300");
				out.println("Connection: Keep-Alive");
				out.println("");

				while ((tempString = in.readLine()) != null) {
					System.out.println(tempString);
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
		//}

	}

}
