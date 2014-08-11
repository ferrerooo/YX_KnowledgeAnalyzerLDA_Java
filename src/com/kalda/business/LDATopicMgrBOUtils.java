package com.kalda.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.kalda.dto.TopWordInTopic;
import com.kalda.dto.Twords;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.utils.StringUtil;

public class LDATopicMgrBOUtils {
	
	public static Twords loadTwords(String ldaRunDocName, String LDAModelPath){
		File file1 = new File(LDAModelPath + "/" + ldaRunDocName+".others");
		BufferedReader reader1 = null;
		String str1 = null;
		Twords twords = new Twords();
		twords.setLdaDocName(ldaRunDocName+".twords");
		try {
			reader1 = new BufferedReader(new FileReader(file1));	
			while ((str1 = reader1.readLine()) != null) {
				str1 = str1.trim();
				if (str1.startsWith("alpha")) {
					twords.setAlpah(StringUtil.StringToDouble(str1.substring(6)));
				} else if (str1.startsWith("beta")) {
					twords.setBeta(StringUtil.StringToDouble(str1.substring(5)));
				} else if (str1.startsWith("ntopics")) {
					twords.setNtopics(StringUtil.StringToInteger(str1.substring(8)));
				} else if (str1.startsWith("liters")) {
					twords.setNiters(StringUtil.StringToInteger(str1.substring(7)));
				}
				// here doesn't set twords number. because it is not in .others file
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File file = new File(LDAModelPath + "/" + ldaRunDocName+".twords");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		Integer topicNumber = null;
		try {
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				if (str.startsWith("T")) {
					str = str.substring(6, str.length()-3);
					topicNumber = StringUtil.StringToInteger(str);
				} else {
					TopWordInTopic twt = new TopWordInTopic();
					
					twt.setTopicNumber(topicNumber);
					s = str.split(" ");
					twt.setWord(s[0]);
					// for some very rare situation, in 'twords', there is no key word before probability...
					if (s.length>1) {
						twt.setWordInTopicProbability(StringUtil.She4ru5ReturnDouble(4, StringUtil.StringToDouble(s[1])));
					} else {
						twt.setWordInTopicProbability(new Double(0));
					}
					
					twords.getList().add(twt);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Load twords matrix DONE!");
		return twords;
	}
	
	public static Double getPercentileThresholdValue(ThetaThreshold tt, String lDAModelPath) {
		
		File file = new File(lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		
		int count = -1;
		try {
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				count++;
				str = str.trim();
				s = str.split(" ");
				for (int i=0;i<s.length;i++) {
					Double probability = StringUtil.She4ru5ReturnDouble(4, s[i]);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}

	// set 5 values here. meanValue, standDeviationValue, deviationValue, deviationSelectedGridAmount, deviationSelectedCorpusDocAmount
	public static void setDeviationValues(ThetaThreshold tt, String lDAModelPath) {
		
		System.out.println("come to setDeviationValues");
		
		File file = new File(lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		
		Double meanValue = new Double(0);
		Double standardDeviationValue = new Double(0);
		Double deviationValue = new Double(0);
		Integer deviationSelectedGridAmount = new Integer(0);
		Integer deviationSelectedCorpusDocAmount = new Integer(0);
		
		Double totalProbablityOfTheta = new Double(0); 
		Integer totalThetaGridNum = tt.getTotalGridAmount(); 
		Double sumOfMeanDistance = new Double(0);
		
		try {
			// calculate meanValue
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				s = str.split(" ");
				for (int i=0;i<s.length;i++) {
					totalProbablityOfTheta = totalProbablityOfTheta + new Double(s[i]);
				}
			}
			meanValue = totalProbablityOfTheta / totalThetaGridNum;
			System.out.println("meanValue = totalProbablityOfTheta / totalThetaGridNum --- "+meanValue + "=" +totalProbablityOfTheta+"/"+totalThetaGridNum);
			reader.close();
			
			// calculate standardDeviationValue and deviationValue
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				s = str.split(" ");
				for (int i=0;i<s.length;i++) {
					sumOfMeanDistance = sumOfMeanDistance + Math.pow(new Double(s[i])-meanValue, 2);
				}
			}
			standardDeviationValue = Math.sqrt(sumOfMeanDistance/(totalThetaGridNum-1));
			deviationValue = meanValue + tt.getkValue() * standardDeviationValue;
			System.out.println("deviationValue = meanValue + tt.getkValue() * standardDeviationValue ---"+deviationValue+"="+meanValue+"+"+tt.getkValue()+"X"+standardDeviationValue);
			reader.close();
			
			// calculate deviationSelectedGridAmount and deviationSelectedCorpusDocAmount
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				s = str.split(" ");
				boolean corpusDocAmountAdded = false;
				for (int i=0;i<s.length;i++) {
					if (new Double(s[i])>deviationValue) {
						deviationSelectedGridAmount ++;
						if (corpusDocAmountAdded == false) {
							deviationSelectedCorpusDocAmount ++;
							corpusDocAmountAdded = true;
						}
					}
				}
			}
			reader.close();
			System.out.println("deviationSelectedGridAmount ---"+deviationSelectedGridAmount);
			System.out.println("deviationSelectedCorpusDocAmount --- "+deviationSelectedCorpusDocAmount);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		tt.setMeanValue(StringUtil.She4ru5ReturnDouble(4, meanValue));
		tt.setStandDeviationValue(StringUtil.She4ru5ReturnDouble(4, standardDeviationValue));
		tt.setDeviationValue(StringUtil.She4ru5ReturnDouble(4, deviationValue));
		tt.setSelectedGridAmount(deviationSelectedGridAmount);
		tt.setSelectedCorpusDocAmount(deviationSelectedCorpusDocAmount);		
	}
	
}
