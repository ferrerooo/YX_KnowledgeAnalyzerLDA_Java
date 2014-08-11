package com.kalda.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ExpertPost_getFileName {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Set<String> set = new TreeSet<String>();


		File file1 = new File("C:\\Users\\Administrator\\Desktop\\tReadFrom.txt");
		File cacheFile = new File("C:\\Users\\Administrator\\Desktop\\t3.txt");
		BufferedReader reader1 = null;
		BufferedWriter writer = null;
		String str1 = null;
		String[] s = null;
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			writer = new BufferedWriter(new FileWriter(cacheFile));
			while ((str1 = reader1.readLine()) != null) {
				
				str1 = str1.trim();
				
				set.add(str1);				
				
			}
			
			for (String str : set) {  
				writer.write(str+"\n");
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (reader1 !=null)
					reader1.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
