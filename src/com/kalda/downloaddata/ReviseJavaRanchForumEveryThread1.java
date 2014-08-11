package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 首先把 每个帖子 返回的所有html中 ， 和内容无关的东西去掉
public class ReviseJavaRanchForumEveryThread1 {

	public static void main(String[] args) {

		File dir = new File("C:/Users/Administrator/Desktop/corpus initial");
		//File dir = new File("C:/Users/Administrator/Desktop/javaranchtemp");

		File[] files = dir.listFiles();
		
//		for (int i=0;i<files.length;i++) {
//			System.out.println(files[i].getName());
//		}

		BufferedReader reader = null;
		BufferedWriter writer = null;

		String str = null;

		for (int i = 0; i < files.length; i++) {
			System.out.println(i);
			try {

				str = "";
				boolean added = false;
				File file2 = null;
				
				Integer filenameNum = 0;
				String filename = files[i].getName().substring(0, files[i].getName().length()-4);
				filenameNum = new Integer(filename);
				
				if (filenameNum<10) {
					file2 = new File("C:/Users/Administrator/Desktop/corpus reply author/0000"+filenameNum+".txt");
				}
				if (filenameNum>=10 && filenameNum<100) {
					file2 = new File("C:/Users/Administrator/Desktop/corpus reply author/000"+filenameNum+".txt");
				}
				if (filenameNum>=100 && filenameNum<1000) {
					file2 = new File("C:/Users/Administrator/Desktop/corpus reply author/00"+filenameNum+".txt");
				}
				if (filenameNum>=1000 && filenameNum<10000) {
					file2 = new File("C:/Users/Administrator/Desktop/corpus reply author/0"+filenameNum+".txt");
				}
				if (filenameNum>=10000) {
					file2 = new File("C:/Users/Administrator/Desktop/corpus reply author/"+filenameNum+".txt");
				}
				
				reader = new BufferedReader(new FileReader(files[i]));
				writer = new BufferedWriter(new FileWriter(file2));
				writer.write("");
				
				while ((str = reader.readLine()) != null) {
					str = str.trim();
					if(str.contains("div class=\"mobilePostBody\"")) {
						added = true;
					}
					if (str.contains("=\"Reply")) {
						added = false;
						//writer.write("</div>");
					}
					
					if (added == true) {
						
						if (str.contains("</a>")) {
							str = str.replaceAll("</a>", "");
						}
						if (str.contains("<br />")) {
							str = str.replaceAll("<br />", "");
						}
						if (str.contains("<div>")) {
							str = str.replaceAll("<div>", "");
						}
						if (str.contains("</div>")) {
							str = str.replaceAll("</div>", "");
						}
						if (str.contains("</b>")) {
							str = str.replaceAll("</b>", "");
						}
						if (str.contains("<blockquote>")) {
							str = str.replaceAll("<blockquote>", "");
						}
						if (str.contains("</blockquote>")) {
							str = str.replaceAll("</blockquote>", "");
						}
						if (str.contains("<cite>")) {
							str = str.replaceAll("<cite>.*</cite>", "");
						}
//						if (str.contains("</cite>")) {
//							str = str.replaceAll("</cite>", "");
//						}
						if (str.contains("<b>")) {
							str = str.replaceAll("<b>", "");
						}
						if (str.contains("<i>")) {
							str = str.replaceAll("<i>", "");
						}
						if (str.contains("</i>")) {
							str = str.replaceAll("</i>", "");
						}
						if (str.contains("&nbsp;")) {
							str = str.replaceAll("&nbsp;", "");
						}
						if (str.contains("Post by: ")) {
							str = str.substring(9, str.indexOf(","));
						}
						if (str.contains("<img")) {
							str = str.replaceAll("<img.*>", "");
						}
						if (str.contains("<a class=")) {
							str = str.replaceAll("<a class=.*>", "");
						}
						if (str.contains("<a href=")) {
							str = str.replaceAll("<a href=.*>", "");
						}
						if (str.contains("<A HREF=")) {
							str = str.replaceAll("<A HREF=.*>", "");
						}						
						if (str.contains("&ldquo;")) {
							// handle special chars "&ldquo;"
							str = str.replace("&ldquo;","\"");
						}
						if (str.contains("&hellip;")) {  
							// handle special chars "&ldquo;"
							str = str.replace("&hellip;","...");
						}
						if (str.contains("&rdquo;")) {  
							// handle special chars "&ldquo;"
							str = str.replace("&rdquo;","\"");
						}
						if (str.contains("&gt;")) {
							str = str.replaceAll("&gt;", ">");
						}
						if (str.contains("&lt;")) {
							str = str.replaceAll("&lt;", "<");
						}
						if (str.contains("on <span class=\"lastPostTime\">")) {
							str = "";
						}
						if (str.contains("<blockquote class=\"uncited\">")) {
							str = str.replaceAll("<blockquote class=\"uncited\">", "");
						}
						if ("".equals(str)) {
							
						} else {
							writer.write(str+"\n");
						}						
					}					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
