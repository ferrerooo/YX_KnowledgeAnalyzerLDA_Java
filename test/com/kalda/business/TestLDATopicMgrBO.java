package com.kalda.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.dto.PhiLine;
import com.kalda.dto.WordInTopic;
import com.kalda.dto.ldaanalysis.ThetaGrid;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold;
import com.kalda.utils.ThetaGridComparator;

public class TestLDATopicMgrBO {

	private static ApplicationContext factory=new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");;
	private static LDATopicMgrBO c = (LDATopicMgrBO)factory.getBean("LDATopicMgrBO");;

	
	public static void main(String[] args) {
		
		//factory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		//c = (LDATopicMgrBO)factory.getBean("LDATopicMgrBO");
		TestLDATopicMgrBO tltm = new TestLDATopicMgrBO();
		
		//tltm.testQuantitativeTopicCorrespondDocAlgorithm1();
		
		//tltm.testQuantitativeTopicCorrespondDocAlgorithm2();

		//tltm.testQuantitativeTopicCorrespondDocAlgorithm3();
		
		//tltm.testQuantitativeTopicCorrespondDocAlgorithm4();
		
		tltm.testQuantitativeTopicTheta();
		
	}
	
	/***********************************************************************************/
	/*  test function: quantitativeTopicCorrespondDoc..   given a topic, check the docs*/
	/*************************pure gap calculation**************************************/
	/***********************************************************************************/
	public void testQuantitativeTopicCorrespondDocAlgorithm1(){
		ThetaThreshold tt = new ThetaThreshold();
		tt.setLdaRunDocName("model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20");
		tt.setkValue(10);
		int topicNum = 104;
		List<TopicDocListWithThetaThreshold> list = c.quantitativeTopicCorrespondDoc(tt, topicNum);
		DecimalFormat df = new DecimalFormat("0.00000000000"); 
		
		System.out.println("Mean Value \t\t"+tt.getMeanValue());
		System.out.println("StandardDeviation \t"+tt.getStandDeviationValue());
		System.out.println("KeyValue \t\t"+tt.getkValue());
		System.out.println("Threshold \t\t"+tt.getDeviationValue());
		System.out.println("Document Amount \t"+list.size());
		
		System.out.println("== The probability list AND difference between adjacent documents == ");
		
		Double gapBetweenAdjacentDoc = new Double( 0);
		for (int i=0;i<list.size()-1;i++) {
		//for (int i=100000; i<list.size()-1; i++){
			gapBetweenAdjacentDoc = list.get(i).getTopicProbabilityForThisDoc() - list.get(i+1).getTopicProbabilityForThisDoc();
			String num = df.format(gapBetweenAdjacentDoc); 
			//if(gapBetweenAdjacentDoc >0.001)
			System.out.println(i+"\t"+list.get(i).getTopicProbabilityForThisDoc() +" \t\t "+ num+ "\t");
		}
	}
	
	/*****************************************************************/
	/*test function: quantitativeTopicCorrespondDoc..   **************/
	/***********************Dynamic part*****Professor's algorithm****/
	/*****************************************************************/
	public void testQuantitativeTopicCorrespondDocAlgorithm2() {
		ThetaThreshold tt = new ThetaThreshold();
		tt.setLdaRunDocName("model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20");
		tt.setkValue(1);
		int topicNum = 0;
		List<TopicDocListWithThetaThreshold> list = c.quantitativeTopicCorrespondDoc(tt, topicNum);
		DecimalFormat df = new DecimalFormat("0.00000000000"); 
		
		System.out.println("=================================================");
		System.out.println("\tTotal statistics for this topic and all the documents");
		System.out.println("Mean Value \t\t"+tt.getMeanValue());
		System.out.println("StandardDeviation \t"+tt.getStandDeviationValue());
		System.out.println("Threshold \t\t"+tt.getDeviationValue());
		System.out.println("Document Amount \t"+list.size());
		System.out.println("=================================================");	
		System.out.println("\nkeyvalue for calculating gap is 1.");
		
		int gapKeyValue = 1;
		Double gapBetweenAdjacentDoc = new Double( 0);
		System.out.println(0+"\t"+list.get(0).getTopicProbabilityForThisDoc());
		for (int i=1;i<list.size()-1;i++) {
			if(i == 1000 || i== 2000 ||i==3000||i==4000||i==5000||i==6000||i==7000||i==8000||i==9000||i==10000) {
				System.out.println();
			}
			Double nmeanValue = new Double(0);
			Double nstandardDeviation = new Double(0);
			Double nthreshold = new Double(0);
			Double totalProbablityOfCurrentTopic = new Double(0);  // temp value for calculation
			Double sumOfMeanDistance = new Double(0); // temp value for calculation
			// calculate mean value
			for (int j=0;j<i+1;j++) {			
				totalProbablityOfCurrentTopic = totalProbablityOfCurrentTopic + list.get(j).getTopicProbabilityForThisDoc();
			}
			nmeanValue = (double)totalProbablityOfCurrentTopic / (i+1);
			for (int j=0;j<i+1;j++) {
				Double temp = list.get(j).getTopicProbabilityForThisDoc();
				sumOfMeanDistance = sumOfMeanDistance + Math.pow(temp-nmeanValue, 2);
			}
			nstandardDeviation = Math.sqrt(sumOfMeanDistance/(i));
			nthreshold = nmeanValue + gapKeyValue * nstandardDeviation;
			
			gapBetweenAdjacentDoc = list.get(i).getTopicProbabilityForThisDoc() - list.get(i+1).getTopicProbabilityForThisDoc();
			String num = df.format(gapBetweenAdjacentDoc);
			System.out.println(i+"\t"+list.get(i).getTopicProbabilityForThisDoc() +" \t"+ num+"\t"+nthreshold+"\t"+nmeanValue+"\t"+nstandardDeviation);
		}
	}
	
	/*****************************************************************/
	/*test function: quantitativeTopicCorrespondDoc..   **************/
	/**********Dynamic part*****Professor's new algorithm: gap=Ei-Pi+1**/
	/*****************************************************************/
	public void testQuantitativeTopicCorrespondDocAlgorithm3() {
		ThetaThreshold tt = new ThetaThreshold();
		tt.setLdaRunDocName("model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20");
		tt.setkValue(1);
		int topicNum = 23;
		List<TopicDocListWithThetaThreshold> list = c.quantitativeTopicCorrespondDoc(tt, topicNum);
		DecimalFormat df = new DecimalFormat("0.00000000000"); 
		
		System.out.println("=================================================");
		System.out.println("\tTotal statistics for this topic and all the documents");
		System.out.println("Mean Value \t\t"+tt.getMeanValue());
		System.out.println("StandardDeviation \t"+tt.getStandDeviationValue());
		System.out.println("Threshold \t\t"+tt.getDeviationValue());
		System.out.println("Document Amount \t"+list.size());
		System.out.println("=================================================");	
		System.out.println("\nkeyvalue for calculating gap is 1.");
		
		System.out.println("\nIndex\t'DocPro'\t'Gap'\t'Threshold'\t\t'meanvalue'\t\t'StandardDevi'\t'WhichIsBigger'");
		int gapKeyValue = 2;
		Double gapBetweenAdjacentDoc = new Double( 0);
		System.out.println(0+"\t"+list.get(0).getTopicProbabilityForThisDoc());
		for (int i=1;i<list.size()-1;i++) {
			if(i == 800 || i== 1600 ||i==3000||i==4000||i==5000||i==6000||i==7000||i==8000||i==9000||i==10000) {
				System.out.println();
			}
			Double nmeanValue = new Double(0);
			Double nstandardDeviation = new Double(0);
			Double nthreshold = new Double(0);
			Double totalProbablityOfCurrentTopic = new Double(0);  // temp value for calculation
			Double sumOfMeanDistance = new Double(0); // temp value for calculation
			// calculate mean value
			for (int j=0;j<i+1;j++) {			
				totalProbablityOfCurrentTopic = totalProbablityOfCurrentTopic + list.get(j).getTopicProbabilityForThisDoc();
			}
			nmeanValue = (double)totalProbablityOfCurrentTopic / (i+1);
			for (int j=0;j<i+1;j++) {
				Double temp = list.get(j).getTopicProbabilityForThisDoc();
				sumOfMeanDistance = sumOfMeanDistance + Math.pow(temp-nmeanValue, 2);
			}
			nstandardDeviation = Math.sqrt(sumOfMeanDistance/(i));
			nthreshold = nmeanValue + gapKeyValue * nstandardDeviation;
			
			//gapBetweenAdjacentDoc = list.get(i).getTopicProbabilityForThisDoc() - list.get(i+1).getTopicProbabilityForThisDoc();
			gapBetweenAdjacentDoc =  nmeanValue - list.get(i+1).getTopicProbabilityForThisDoc();
			String num = df.format(gapBetweenAdjacentDoc);
			System.out.print(i+"\t"+list.get(i).getTopicProbabilityForThisDoc() +" \t"+ num+"\t"+nthreshold+"\t"+nmeanValue+"\t"+nstandardDeviation+"\t");
			if (gapBetweenAdjacentDoc > nstandardDeviation * gapKeyValue)
				System.out.println("gap");
			else 
				System.out.println("standardDevi");
		}
	}
	
	/*****************************************************************/
	/*test function: quantitativeTopicCorrespondDoc..   **************/
	/***********************Dynamic part*******My algorithm   ********/
	/*************************only focus on the adjacent two**********/
	public void testQuantitativeTopicCorrespondDocAlgorithm4() {
		ThetaThreshold tt = new ThetaThreshold();
		tt.setLdaRunDocName("model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20");
		tt.setkValue(1);
		int topicNum = 0;
		List<TopicDocListWithThetaThreshold> list = c.quantitativeTopicCorrespondDoc(tt, topicNum);
		DecimalFormat df = new DecimalFormat("0.00000"); 
		
		System.out.println("=================================================");
		System.out.println("\tTotal statistics for this topic and all the documents");
		System.out.println("Mean Value \t\t"+tt.getMeanValue());
		System.out.println("StandardDeviation \t"+tt.getStandDeviationValue());
		System.out.println("Threshold \t\t"+tt.getDeviationValue());
		System.out.println("Document Amount \t"+list.size());
		System.out.println("=================================================");	
		System.out.println("\nkeyvalue for calculating gap is 1.");
		
		int gapKeyValue = 1;
		Double gapBetweenAdjacentDoc = new Double( 0);
		System.out.println(0+"\t"+list.get(0).getTopicProbabilityForThisDoc());
		for (int i=1;i<list.size()-1;i++) {
			if(i == 1000 || i== 2000 ||i==3000||i==4000||i==5000||i==6000||i==7000||i==8000||i==9000||i==10000) {
				System.out.println();
			}
			Double nmeanValue = new Double(0);
			Double nstandardDeviation = new Double(0);
			Double nthreshold = new Double(0);
			Double totalProbablityOfCurrentTopic = new Double(0);  // temp value for calculation
			Double sumOfMeanDistance = new Double(0); // temp value for calculation
			// calculate mean value
			for (int j=i-1;j<i+1;j++) {			
				totalProbablityOfCurrentTopic = totalProbablityOfCurrentTopic + list.get(j).getTopicProbabilityForThisDoc();
			}
			nmeanValue = (double)totalProbablityOfCurrentTopic / (2);
			for (int j=i-1;j<i+1;j++) {
				Double temp = list.get(j).getTopicProbabilityForThisDoc();
				sumOfMeanDistance = sumOfMeanDistance + Math.pow(temp-nmeanValue, 2);
			}
			nstandardDeviation = Math.sqrt(sumOfMeanDistance/(1));
			nthreshold = nmeanValue + gapKeyValue * nstandardDeviation;
			
			gapBetweenAdjacentDoc = list.get(i).getTopicProbabilityForThisDoc() - list.get(i+1).getTopicProbabilityForThisDoc();
			String num = df.format(gapBetweenAdjacentDoc);
			System.out.println(i+"\t"+list.get(i).getTopicProbabilityForThisDoc() +" \t"+ num+"\t"+nthreshold+"\t"+nmeanValue+"\t"+nstandardDeviation);
		}
		
		/*  test function: loadAPhiLine. Here for analyzing the keyword probability performance. Given a topic, check the keywords */
		DecimalFormat df1 = new DecimalFormat("0.00000000000"); 
		int atopicnumber = 23;
		PhiLine pl = c.loadAPhiLine(atopicnumber, "model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20");
		List<WordInTopic> wordlist = pl.getList();
		System.out.println("== topic word probability analysis ==");
		System.out.println("MeanValue = "+df1.format(pl.getPhiLineMeanValue()));
		System.out.println("StandardDeviationValue = "+df1.format(pl.getStandDeviationValue()));		
		
		
		for (int i=0;i<wordlist.size()-1;i++) {
			String num = df1.format(wordlist.get(i).getWordInTopicProbability()); 
			String gap = df1.format(wordlist.get(i).getWordInTopicProbability()-wordlist.get(i+1).getWordInTopicProbability());
			if(wordlist.get(i).getWord().length()>7)
				System.out.println(i+"\t"+wordlist.get(i).getWord()+"\t\t\t"+num+"\t"+gap);
			else 
				System.out.println(i+"\t"+wordlist.get(i).getWord()+"\t\t\t\t"+num+"\t"+gap);
		}
	}
	
	public void testQuantitativeTopicTheta() {
		String ldaRunDocName = "model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20";
		
		List<ThetaGrid> list = c.quantitativeTopicTheta(ldaRunDocName);
		System.out.println("come here1");
		HashMap<Integer, Integer> amap = new HashMap<Integer, Integer>();
		for (int i=0;i<list.size();i++) {
			amap.put(list.get(i).getDocNum(), list.get(i).getDocNum());
		}
		System.out.println("ThetaGrid number is "+list.size());
		System.out.println("Unique document number is "+amap.size());
		
		ThetaGridComparator comp = new ThetaGridComparator();
		
		System.out.println(list.size());
		
//		for (int i=0;i<120000;i++) {
//			if(i % 1000 == 0) {
//				System.out.println();
//			}
//			
//			for (int j=0;j<list.size();j++) {
//				if(list.get(j).getDocNum().intValue() == i) {
//					System.out.println(list.get(j).getDocNum()+" "+list.get(j).getTopicNum()+" "+list.get(j).getTopicProbabilityForThisDoc());
//				}
//			}
//		}
		
		for (int i=0;i<120;i++) {	
			List<ThetaGrid> tempList = new ArrayList<ThetaGrid>();
			File cacheFile = new File("C:/Users/Administrator/Desktop/temp/topic"+i+".txt");
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(cacheFile));
				for (int j=0;j<list.size();j++) {
					if(list.get(j).getTopicNum() == i) {
						tempList.add(list.get(j));
					}
				}
				Collections.sort(tempList,comp);
				
//				double max = tempList.get(0).getTopicProbabilityForThisDoc();
//				double min = tempList.get(tempList.size()-1).getTopicProbabilityForThisDoc();
//				double mean = 0;
//				for (int m=0;m<tempList.size();m++) {
//					mean = mean + tempList.get(m).getTopicProbabilityForThisDoc();
//				}
//				mean = mean / tempList.size();
//				double standardDev = 0;
//				for (int m=0;m<tempList.size();m++) {
//					standardDev = standardDev + Math.pow(tempList.get(m).getTopicProbabilityForThisDoc()-mean, 2);
//				}
//				standardDev = Math.sqrt(standardDev/(tempList.size()-1));
//				System.out.println("topic"+i+": \tmax "+max+" \tmin"+min+" \tmean"+mean+" \tstandardDev"+standardDev);
//				
				writer.write("========================================\n");
				writer.write("Topic Number is "+tempList.get(0).getTopicNum()+"\n");
				writer.write("Document amount is "+tempList.size()+"\n");
				writer.write("========================================\n\n");
				writer.write("'topicNum'	'DocName'      'Probability'   'Rank'\n");
				for (int j=0;j<tempList.size();j++) {
					writer.write(tempList.get(j).getTopicNum()+"\t\t "+tempList.get(j).getDocName()+"\t\t "+tempList.get(j).getTopicProbabilityForThisDoc()+"\t\t "+tempList.get(j).getRank()+"\n");
				}
				
				System.out.println("topic "+i +"\t"+tempList.size());
				
			} catch(Exception e){
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

}
