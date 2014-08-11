package com.kalda.analysistools;

import java.util.ArrayList;
import java.util.List;

import com.kalda.business.LDATopicMgrBOUtils;
import com.kalda.dto.TopWordInTopic;
import com.kalda.dto.Twords;
import com.kalda.dto.WordInTopic;

public class KeyWordCompare {
	
	public static String lDAModelPath1 = "C:/Users/Administrator/Desktop/ldaoutput_afile_is_apost_withsourcecode";	
	public static String ldaRunDocName1 = "model ---- alpha-0.5 beta-0.1 ntopics-120 niters-01000 twords-20";
	
	public static String lDAModelPath2 = "C:/Users/Administrator/Desktop/ldaoutput_afile_is_apost_withsourcecode";	
	public static String ldaRunDocName2 = "model ---- alpha-0.5 beta-0.1 ntopics-100 niters-01000 twords-20";

	public static int sameWordThreshold = 12;
	
	public static void main(String[] args) {


		Twords twords1 = new Twords();
		twords1 = LDATopicMgrBOUtils.loadTwords(ldaRunDocName1, KeyWordCompare.lDAModelPath1);
		List<TopWordInTopic> list1 = twords1.getList();
		int maxTopicNum1 = list1.get(list1.size()-1).getTopicNumber();
		
		Twords twords2 = new Twords();
		twords2 = LDATopicMgrBOUtils.loadTwords(ldaRunDocName2, KeyWordCompare.lDAModelPath2);
		List<TopWordInTopic> list2 = twords2.getList();
		int maxTopicNum2 = list2.get(list2.size()-1).getTopicNumber();
		
		for (int i=0; i<=maxTopicNum1; i++) {
			
			List<String> keywords1 = KeyWordCompare.getKeyWords(list1, i);
			
			for (int j=0;j<maxTopicNum2; j++) {
				
				List<String> keywords2 = KeyWordCompare.getKeyWords(list2, j);
				
				
			}
			
			
		}

	}
	
	public static List<String> getKeyWords(List<TopWordInTopic> list, int topicNum) {
		
		List<String> returnList = new ArrayList<String>();
		for (int i=0;i<list.size();i++) {
			
			returnList.add(list.get(i).getWord());
			
		}
		return returnList;
	}
	
	public static List<Integer> CalculateSameWordTopic(List<String> list1, List<String> list2, int sameWordThreshold) {
		
		List<Integer> returnList = new ArrayList<Integer>();
		int sameWordAmount = 0;
		for (int i=0;i<list1.size();i++) {
			sameWordAmount = 0;
			String word = list1.get(i);
			for (int j=0;j<list2.size();j++) {
				String comparedword = list2.get(j);
				
				if (word.equals(comparedword)) {
					sameWordAmount = sameWordAmount+1;
				}
			}
			if (sameWordAmount >= sameWordThreshold) {
				
			}
			
		}
		
		return returnList;
	}

}
