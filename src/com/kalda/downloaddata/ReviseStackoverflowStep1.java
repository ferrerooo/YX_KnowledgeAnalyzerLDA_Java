package com.kalda.downloaddata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.kalda.utils.StringUtil;

// 把分隔符 ‘；；；’变为‘zcy’。 替换了一些符号字符
public class ReviseStackoverflowStep1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		File fileInit = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/threadlist_v1.txt");
		File fileTarget = new File("C:/Users/Administrator/Desktop/stackjava corpus initial/threadlist_v2.txt");
		BufferedReader reader = null;
		BufferedWriter writer = null;
		String str = null;
		
		try {
			reader = new BufferedReader(new FileReader(fileInit));
			writer = new BufferedWriter(new FileWriter(fileTarget));
			writer.write("");
			while ((str = reader.readLine()) != null) {
				if (str.contains("&#39;")) {
					str = str.replaceAll("&#39;", "'");
				}
				if (str.contains("&gt;")) {
					str = str.replaceAll("&gt;", ">");
				}
				if (str.contains("&lt;")) {
					str = str.replaceAll("&lt;", "<");
				}
				if (str.contains("&amp;")) {
					str = str.replaceAll("&amp;", "&");
				}
				if (str.contains(";;;;")) {
					str = str.replaceAll(";;;;", ";zcy");
				}
				if (str.contains(";;;")) {
					str = str.replaceAll(";;;", "zcy");
				}
				if (str.contains("zcynull")) {
					str = str.replaceAll("zcynull", "zcy0");
				}
//				if (str.contains("&#225;")) {
//					str = str.replaceAll("&#225;", "a");
//				}
//				if (str.contains("&#228;")) {
//					str = str.replaceAll("&#228;", "a");
//				}
//				if (str.contains("&#237;")) {
//					str = str.replaceAll("&#237;", "i");
//				}
//				if (str.contains("&#241;")) {
//					str = str.replaceAll("&#241;", "n");
//				}
//				if (str.contains("&#223;")) {
//					str = str.replaceAll("&#223;", "r");
//				}
//				if (str.contains("&mdash;")) {
//					str = str.replaceAll("&mdash;", "-");
//				}
//				if (str.contains("&#236;")) {
//					str = str.replaceAll("&#236;", "i");
//				}
//				if (str.contains("&#233;")) {
//					str = str.replaceAll("&#233;", "e");
//				}
//				if (str.contains("&#224;")) {
//					str = str.replaceAll("&#224;", "a");
//				}
//				if (str.contains("&#246;")) {
//					str = str.replaceAll("&#246;", "o");
//				}
//				if (str.contains("&#233;")) {
//					str = str.replaceAll("&#233;", "e");
//				}
//				if (str.contains("&#243;")) {
//					str = str.replaceAll("&#243;", "o");
//				}
//				if (str.contains("&#235;")) {
//					str = str.replaceAll("&#235;", "e");
//				}
//				if (str.contains("&#253;")) {
//					str = str.replaceAll("&#253;", "y");
//				}
//				if (str.contains("&#231;")) {
//					str = str.replaceAll("&#231;", "c");
//				}
//				if (str.contains("&#214;")) {
//					str = str.replaceAll("&#214;", "O");
//				}
//				if (str.contains("&#201;")) {
//					str = str.replaceAll("&#201;", "E");
//				}
//				if (str.contains("&#252;")) {
//					str = str.replaceAll("&#252;", "u");
//				}
//				if (str.contains("&#232;")) {
//					str = str.replaceAll("&#232;", "e");
//				}
//				if (str.contains("&#234;")) {
//					str = str.replaceAll("&#234;", "e");
//				}
//				if (str.contains("&#238;")) {
//					str = str.replaceAll("&#238;", "i");
//				}
//				if (str.contains("&#239;")) {
//					str = str.replaceAll("&#239;", "i");
//				}
//				if (str.contains("&#244;")) {
//					str = str.replaceAll("&#244;", "o");
//				}
//				if (str.contains("&#249;")) {
//					str = str.replaceAll("&#249;", "u");
//				}
//				if (str.contains("&#230;")) {
//					str = str.replaceAll("&#230;", "a");
//				}
//				if (str.contains("&#252;")) {
//					str = str.replaceAll("&#252;", "u");
//				}				
//				if (str.contains("&#227;")) {
//					str = str.replaceAll("&#227;", "a");
//				}				
//				if (str.contains("&#176;")) {
//					str = str.replaceAll("&#176;", "");
//				}
//				if (str.contains("&#205;")) {
//					str = str.replaceAll("&#205;", "I");
//				}
//				if (str.contains("&#160;")) {
//					str = str.replaceAll("&#160;", "");
//				}
//				if (str.contains("&#248;")) {
//					str = str.replaceAll("&#248;", "o");
//				}
//				if (str.contains("&#245;")) {
//					str = str.replaceAll("&#245;", "o");
//				}
//				if (str.contains("&#250;")) {
//					str = str.replaceAll("&#250;", "u");
//				}
//				if (str.contains("&#226;")) {
//					str = str.replaceAll("&#226;", "a");
//				}
//				if (str.contains("&#180;")) {
//					str = str.replaceAll("&#180;", "");
//				}
//				if (str.contains("&#220;")) {
//					str = str.replaceAll("&#220;", "U");
//				}
//				if (str.contains("&#242;")) {
//					str = str.replaceAll("&#242;", "o");
//				}
//				if (str.contains("&#187;") || str.contains("&#222;")) {
//					str = str.replaceAll("&#187;", "");
//				}
//				if (str.contains("&#191;")) {
//					str = str.replaceAll("&#191;", "");
//				}
//				if (str.contains("&#222;")) {
//					str = str.replaceAll("&#222;", "");
//				}
//				if (str.contains("&#209;")) {
//					str = str.replaceAll("&#209;", "N");
//				}
				
				
				
				writer.write(str+"\n");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
