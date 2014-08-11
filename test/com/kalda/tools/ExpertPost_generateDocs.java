package com.kalda.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class ExpertPost_generateDocs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Set<String> set = new TreeSet<String>();


		File file1 = new File("C:\\Users\\Administrator\\Desktop\\ldatry\\t6.txt");
		File file2;
		File cacheFile;
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		String str1 = null;
		String str2 = "";
		String[] s = null;
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			while ((str1 = reader1.readLine()) != null) {
				
				str1 = str1.trim();
				// here get the name of the corpus doc
				file2 = new File("C:\\Users\\Administrator\\Desktop\\CorpusPostBasedNoCoCde_v2\\"+str1);
				reader2 = new BufferedReader(new FileReader(file2));
				String docContent = "";
				while ((str2 = reader2.readLine()) != null) {
					docContent = docContent+str2;
				}
				cacheFile = new File ("C:\\Users\\Administrator\\Desktop\\ldatry\\corpus_t6\\"+str1);
				writer = new BufferedWriter(new FileWriter(cacheFile));
				writer.write(docContent);
				writer.close();
				//--------------------------------------------
				
				
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
