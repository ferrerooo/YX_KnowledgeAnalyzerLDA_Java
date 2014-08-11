package com.kalda.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GeneralTwoLDAReusltComp {

	public static int getSameKeyWordNum(String[] s1, String[] s2) {
		int sameKWNum = 0;
		
		for (int i=2; i<s1.length; i++) {
			String temp1 = s1[i];
			for (int j=2; j<s2.length;j++) {
				String temp2 = s2[j];
				if (temp1.equals(temp2)) {
					sameKWNum++;
					break;
				}
			}
			
		}
		return sameKWNum;
	}
	
	
	public static void main(String[] args) {
		
		File readFile2 = new File("C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\test\\com\\kalda\\tools\\postbasenocode120.txt");
		File readFile1 = new File("C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\test\\com\\kalda\\tools\\t3.txt");
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		String str1 = "";
		String str2 = "";
		String[] arr1;
		String[] arr2;
		int sameNum = 5;
		
		try {
			reader1 = new BufferedReader(new FileReader(readFile1));			
			while ((str1 = reader1.readLine()) != null) {
				arr1 = str1.split(" ");
				System.out.println("---------------------------------------------------------------------------");
				System.out.println(str1);
				reader2 = new BufferedReader(new FileReader(readFile2));
				while ((str2 = reader2.readLine()) != null) {
					arr2 = str2.split(" ");
					int sameKeywordNum = GeneralTwoLDAReusltComp.getSameKeyWordNum(arr1, arr2);
					if (sameKeywordNum>=sameNum && sameKeywordNum < 18) {
						System.out.println("\t"+str2);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader1.close();
				reader2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
