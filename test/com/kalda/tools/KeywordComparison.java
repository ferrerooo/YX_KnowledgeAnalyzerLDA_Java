package com.kalda.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.kalda.utils.StringUtil;

public class KeywordComparison {

	
	public static void main(String[] args) {

		
		String[] array = {"maha","anna"};
		ArrayList<String> list = new ArrayList<String>();
		boolean isContained = false;
		
		File readFile = new File("C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\test\\com\\kalda\\tools\\original.txt");
		File readFile1 = new File("C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\test\\com\\kalda\\tools\\threadBaseNoCode.txt");
		File readFile2 = new File("C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\test\\com\\kalda\\tools\\threadBaseWithCode.txt");
		BufferedReader reader = null;
		String str = "";
		
		try {
			
			System.out.println("-- no code ---------------------------------------------");
			
			reader = new BufferedReader(new FileReader(readFile1));
			while ((str = reader.readLine()) != null) {
				isContained = true;
				for (int i=0;i<array.length;i++) {
					
					if (!str.contains(array[i])){
						isContained = false;
						break;
					}
					
				}
				if (isContained == true) {
					list.add(str);
				}				
			}
			
			for (String s:list)
				System.out.println(s);
			
			System.out.println("-- with code ---------------------------------------------");
			
			list = new ArrayList<String>();
			reader = new BufferedReader(new FileReader(readFile2));
			while ((str = reader.readLine()) != null) {
				isContained = true;
				for (int i=0;i<array.length;i++) {
					
					if (!str.contains(array[i])){
						isContained = false;
						break;
					}
					
				}
				if (isContained == true) {
					list.add(str);
				}				
			}
			
			for (String s:list)
				System.out.println(s);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
	}

}
