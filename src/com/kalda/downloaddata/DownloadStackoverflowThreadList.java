package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.kalda.domain.TblThreadStackoverflow;
import com.kalda.utils.StringUtil;

public class DownloadStackoverflowThreadList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DownloadStackoverflowThreadList sog = new DownloadStackoverflowThreadList();
		for (int i=4785;i<9046;i++) {
			sog.fetchData(i);
		}
		//sog.fetchData(1182);
	}
	
	private void fetchData(int num) {
		Socket socket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		String tempString = "";
		String selectedString = "";
		
		List<TblThreadStackoverflow> list = new ArrayList<TblThreadStackoverflow>();
		
		try {
			
			socket = new Socket("stackoverflow.com", 80);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println("GET /questions/tagged/java?page="+num+"&sort=newest&pagesize=50 HTTP/1.1");
			out.println("Host:	stackoverflow.com");
			out.println("Accept: text/html, application/xhtml+xml, */*");
			out.println("Accept-Language: en-US");
			out.println("Accept-Encoding: \r\n");
			out.println("Accept-Charset: utf-8;q=0.7,*;q=0.7");
			out.println("Keep-Alive: 300");
			out.println("Proxy-Connection: keep-alive");
			out.println("");
			
			boolean load = false;
			
			TblThreadStackoverflow tts = null;
			
			while ((tempString = in.readLine()) != null) {
				
				//System.out.println(tempString);
				if (tempString.contains("question-summary") && tempString.contains("class") && tempString.contains("div")) {
					load = true;
				}
				if (tempString.contains("cbt") && tempString.contains("class") && tempString.contains("br")) {
					load = false;
				}
				if (load == true) {
					String s = tempString.trim();
					selectedString = selectedString + s+"\n";
					
					// ************* cal threadID ***************
					if (s.contains("question-summary")) {
						
						if (tts!=null) {
							list.add(tts);
						}						
						//System.out.println(s);
						tts = new TblThreadStackoverflow();
						tts.setComponent("java");
						int start = s.indexOf("question-summary-")+17;
						int end = s.indexOf("\">");
						//System.out.println(s.substring(start, end));
						tts.setThreadId(new Integer(s.substring(start, end)));
					}
					// ************* cal title ***************
					if (s.contains("question-hyperlink")) {
						//System.out.println(s);
						int start = s.indexOf("question-hyperlink") + 20;
						int end = s.indexOf("</a>");
						//System.out.println(s.substring(start, end));
						String ss = s.substring(start, end);
						if (ss.contains("&ldquo;")) {
							// handle special chars "&ldquo;"
							ss = ss.replace("&ldquo;","\"");
						}
						if (ss.contains("&hellip;")) {  
							// handle special chars "&ldquo;"
							ss = ss.replace("&hellip;","...");
						}
						if (ss.contains("&rdquo;")) {  
							// handle special chars "&ldquo;"
							ss = ss.replace("&rdquo;","\"");
						}
						tts.setTitle(ss);
					}
					// ************* cal threadURL ***************
					if (s.contains("question-hyperlink")) {
						//System.out.println(s);
						int start = s.indexOf("href=") + 6;
						int end = s.indexOf("class=\"question-hyperlink\"")-2;
						//System.out.println(s.substring(start, end));
						tts.setThreadURL("stackoverflow.com"+s.substring(start, end));
						//System.out.println(tts.getThreadURL());
					}
					// ************* cal voteAmount ***************
					if (s.contains("vote-count-post")) {
						//System.out.println(s);
						int start = s.indexOf("strong") + 7;
						int end = s.indexOf("</strong>");
						//System.out.println(s.substring(start, end));
						tts.setVoteAmount(new Integer(s.substring(start, end)));
					}
					// ************* cal answerAmount ***************
					if (s.contains("answers") && s.contains("<strong>")) {
						//System.out.println(s);
						int start = s.indexOf("strong") + 7;
						int end = s.indexOf("</strong>");
						//System.out.println(s.substring(start, end));
						tts.setAnswerAmount(new Integer(s.substring(start, end)));
					}
					// ************* cal answerStatus ***************
					if (s.contains("status") && s.contains("answer") && s.contains("class") && s.contains("div")) {
						//System.out.println(s);
						int start = s.indexOf("class=") + 7;
						int end = s.indexOf("\">");
						//System.out.println(s.substring(start, end));
						tts.setAnswerStatus(s.substring(start, end));
					}
					// ************* cal author ***************
					if (s.contains("href=\"/users/") && !s.contains("img") ) {
						//System.out.println(s);
						int start = s.indexOf("\">") + 2;
						int end = s.indexOf("</a>");
						//System.out.println(s.substring(start, end));
						tts.setAuthor(s.substring(start, end));
					}
					// ************* cal reputationScore ***************
					if (s.contains("reputation-score")) {
						//System.out.println(s);
						int start = s.indexOf("ltr")+5;
						int end = s.indexOf("</span>");
						//System.out.println(s.substring(start, end));
						String ss = s.substring(start, end);
						if (ss.contains(",")) {
							ss = ss.replaceAll(",", "");
						}
						if (ss.contains("k")){
							ss = ss.replaceAll("k", "");
							//System.out.println(ss);
							Double d = (new Double(ss)*1000);
							d = StringUtil.She4ru5ReturnDouble(0, d);
							ss = d.toString();
							//System.out.println(ss);
							ss = ss.substring(0, ss.length()-2);	
							//System.out.println(ss);
						}
						tts.setReputationScore(new Integer(ss));
					}
				}
			}
			// add the last object
			list.add(tts);
			//System.out.println(selectedString);
			System.out.println(list.size());
			
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
		
		// save into local file
		
		File cacheFile = new File("C:/Users/Administrator/Desktop/stackoverflowjava/threadlist.txt");
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(cacheFile, true));
			for (int i=0;i<list.size();i++) {
				writer.write(	list.get(i).getTitle()+";;;"+
								list.get(i).getThreadId()+";;;"+
								list.get(i).getThreadURL()+";;;"+
								list.get(i).getVoteAmount()+";;;"+
								list.get(i).getAnswerAmount()+";;;"+
								list.get(i).getAnswerStatus()+";;;"+
								list.get(i).getComponent()+";;;"+
								list.get(i).getAuthor()+";;;"+
								list.get(i).getReputationScore()+"\n");
			}
			
			System.out.println("num="+num+"  inserted "+list.size()+" threads! " + "total num is "+(num+1)*50);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
