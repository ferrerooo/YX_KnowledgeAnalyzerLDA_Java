package com.kalda.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadOneFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		File readFile1 = new File("C:\\Users\\Administrator\\Desktop\\corpusfinal_afile_is_apost_withsourcecode\\525-80.txt");
		BufferedReader reader1 = null;
		
		String str1 = "";

		
		try {
			reader1 = new BufferedReader(new FileReader(readFile1));			
			while ((str1 = reader1.readLine()) != null) {
				System.out.println(str1);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				reader1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
