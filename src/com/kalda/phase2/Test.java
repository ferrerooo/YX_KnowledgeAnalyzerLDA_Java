package com.kalda.phase2;

import java.io.*;

public class Test {

	public static String PATH = "C:\\Users\\Administrator\\Desktop\\ldaoutput_forToolPaper\\model ---- alpha-0.5 beta-0.1 ntopics-20 niters-01000 twords-20.phi";
	
	public static String PATH1 = "C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\src\\models\\casestudy\\model ---- alpha-0.5 beta-0.1 ntopics-10 niters-02000 twords-20.phi";
	public static String PATH2 = "C:\\Users\\Administrator\\VD J2EE\\workspaceForEclipseJUNO\\YX_KnowledgeAnalyzerLDA_Java\\src\\models\\casestudy\\model ---- alpha-0.5 beta-0.1 ntopics-10 niters-02000 twords-20_1.phi";
			
	public static void main(String[] args) throws IOException {
		
		Test t = new Test();
		
		t.compare(PATH1, PATH2);

	}
	
	public void getSumPerLine() throws IOException{
		
		File file = new File(PATH);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = "";
		double total = 0;
		while ((str = br.readLine())!=null) {
			String[] arr = str.trim().split(" ");
			for (String s: arr) {
				total += Double.valueOf(s);
			}
			System.out.println(total);
			total = 0;
		}
		br.close();
	}
	
	// compare two LDA runs
	public void compare(String ldaoutput1_path, String ldaoutput2_path) throws IOException {
		
		File file1 = new File(ldaoutput1_path);
		File file2 = new File(ldaoutput2_path);
		
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		
		String str1 ="";
		
		while ( (str1 = br1.readLine())!= null ) {
			
			String[] arr1 = str1.trim().split(" ");
			double[] darr1 = getDoubleArr(arr1);
			
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			String str2 = "";
			while ((str2 = br2.readLine())!=null) {
				String[] arr2 = str2.trim().split(" ");
				double[] darr2 = getDoubleArr(arr2);
				double klVal = KLDivergenceCalculator.getKLDivergenceVectorSpaceDistance(darr1, darr2, darr1.length);
				System.out.print(klVal+"\t");
			}
			System.out.println();
			br2.close();
			
		}
		
		br1.close();
		
	}
	private double[] getDoubleArr(String[] arr) {
		double[] darr = new double[arr.length];
		for (int i=0;i<arr.length; i++) {
			darr[i] = Double.valueOf(arr[i]);
		}
		return darr;
	}
	
}
