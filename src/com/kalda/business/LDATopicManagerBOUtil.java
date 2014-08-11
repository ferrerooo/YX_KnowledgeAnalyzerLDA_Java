package com.kalda.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kalda.dto.Phi;
import com.kalda.dto.PhiLine;
import com.kalda.dto.Tassign;
import com.kalda.dto.Theta;
import com.kalda.dto.ThetaColumn;
import com.kalda.dto.ThetaLine;
import com.kalda.dto.TopWordInTopic;
import com.kalda.dto.TopicInDocument;
import com.kalda.dto.Twords;
import com.kalda.dto.WordInTopic;
import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.model.LDAAnalysisModel;
import com.kalda.model.Models;
import com.kalda.utils.StringUtil;

import com.kalda.domain.TblCorpusDocument;

public class LDATopicManagerBOUtil {
	
	/** internal methods **/
	
	public static Twords loadTwords(String ldaRunDocName, String LDAModelPath) {
		Twords twords = new Twords();
		twords.setLdaDocName(ldaRunDocName+".twords");
		twords.setAlpah(StringUtil.StringToDouble(ldaRunDocName.substring(18, 20)));
		twords.setBeta(StringUtil.StringToDouble(ldaRunDocName.substring(27, 29)));
		twords.setNtopics(StringUtil.StringToInteger(ldaRunDocName.substring(38, 40)));
		twords.setNiters(StringUtil.StringToInteger(ldaRunDocName.substring(49, 53)));
		twords.setTwords(StringUtil.StringToInteger(ldaRunDocName.substring(61, 63)));
		
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
	
	public static Tassign loadTassign(String ldaRunDocName) {
		return null;
	}
	
	public static Phi loadPhi(String ldaRunDocName, String lDAModelPath, String wordMapPath) {
		
		// data preparation
		List<String> wordMap = LDATopicManagerBOUtil.getWordsInWordMap(wordMapPath);
		
		// create Phi object. (Phi -> PhiLine / PhiColumn -> WordInTopic )
		Phi phi = new Phi();
		
		phi.setLdaDocName(ldaRunDocName+".phi");
		phi.setAlpah(StringUtil.StringToDouble(ldaRunDocName.substring(18, 20)));
		phi.setBeta(StringUtil.StringToDouble(ldaRunDocName.substring(27, 29)));
		phi.setNtopics(StringUtil.StringToInteger(ldaRunDocName.substring(38, 40)));
		phi.setNiters(StringUtil.StringToInteger(ldaRunDocName.substring(49, 53)));
		phi.setTwords(StringUtil.StringToInteger(ldaRunDocName.substring(61, 63)));
		
		File file = new File(lDAModelPath + "/" + ldaRunDocName+".phi");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		int count = -1;
		try {
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				count++;
				// one line is one phiLine object. (Phi -> PhiLine / PhiColumn -> WordInTopic )
				PhiLine phiLine = new PhiLine();
				
				phiLine.setTopicNumber(new Integer(count));
				
				
				str = str.trim();
				s = str.split(" ");
				for (int i=0;i<s.length;i++) {
					
					// every word is an object WordInTopic (Phi -> PhiLine / PhiColumn -> WordInTopic )
					WordInTopic wt = new WordInTopic();
					
					wt.setWord(wordMap.get(i));
					//wt.setTopicNumber(new Integer(count));
					wt.setWordInTopicProbability(StringUtil.She4ru5ReturnDouble(8, s[i]));
					
					
					phiLine.getList().add(wt);
					
				}
				phi.getLineList().add(phiLine);
				
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
		System.out.println("Load phi matrix DONE!");
		return phi;
	}
	
	public static Theta loadTheta(String ldaRunDocName, String LDAModelPath, String corpusPath) {
		// create Theta object (Theta -> ThetaLine / ThetaColumn -> TopicInDocument)
		Theta theta = new Theta();
		theta.setLdaDocName(ldaRunDocName+".theta");
		theta.setAlpah(StringUtil.StringToDouble(ldaRunDocName.substring(18, 20)));
		theta.setBeta(StringUtil.StringToDouble(ldaRunDocName.substring(27, 29)));
		theta.setNtopics(StringUtil.StringToInteger(ldaRunDocName.substring(38, 40)));
		theta.setNiters(StringUtil.StringToInteger(ldaRunDocName.substring(49, 53)));
		theta.setTwords(StringUtil.StringToInteger(ldaRunDocName.substring(61, 63)));
		
		
		File dir = new File(corpusPath);
		Models.ldaAnalysisModel.fileNames = dir.list();
		
		File file = new File(LDAModelPath + "/" + ldaRunDocName+".theta");
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		//Integer topicNumber = null;
		int count = -1;
		try {
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				
				count++;
				// one line is one ThetaLine object.  (Theta -> (ThetaLine / ThetaColumn) -> TopicInDocument)
				ThetaLine thetaLine = new ThetaLine();
				
				System.out.println("file number-"+ count+ " file name-"+LDATopicManagerBOUtil.getCorpusDocumentName(count));
				thetaLine.setCorpusDocName(LDATopicManagerBOUtil.getCorpusDocumentName(count));
				Models.ldaAnalysisModel.fileNames[count] = null; 
				
				str = str.trim();
				s = str.split(" ");
				for (int i=0;i<s.length;i++) {
					
					// every word is an object TopicInDocument (Theta -> ThetaLine / ThetaColumn -> TopicInDocument)
					TopicInDocument td = new TopicInDocument();
					
					td.setTopicNumber(new Integer(i));
					td.setCorpusDocName(thetaLine.getCorpusDocName());
					td.setCorpusDocId(new Integer(count));
					td.setTopicInDocumentProbability(StringUtil.She4ru5ReturnDouble(4, s[i]));
					
					thetaLine.getList().add(td);
					
				}
				theta.getLineList().add(thetaLine);
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
		System.out.println("Load theta matrix DONE!");
		return theta;
	}
	
	private static String getCorpusDocumentName(int count){
		
		return Models.ldaAnalysisModel.fileNames[count];
		//return null;	
	}
	
	public static List<String> getWordsInWordMap(String wordMapPath){
		List<String> wordMap = new ArrayList<String>();
		File wordmap = new File(wordMapPath);
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		int count = -1;
		try {
			reader = new BufferedReader(new FileReader(wordmap));	
			while ((str = reader.readLine()) != null) {
				count++;
				if (count != 0) {
					s = str.split(" ");
					wordMap.add(s[0]);
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
		return wordMap;
	}

	
	
//	public static List<CorpusnameAndTopicamountcontained> loadAllCorpusDocName(String corpusPath) {
//		
//		List<CorpusnameAndTopicamountcontained> returnlist = new ArrayList<CorpusnameAndTopicamountcontained>();
//		
//		//List<String> docNames = Models.ldaAnalysisModel.fileNames;
//		String[] docNames = Models.ldaAnalysisModel.fileNames;
//		// get every doc's corresponding topic amount
//		ThetaThreshold tt = Models.ldaAnalysisModel.tt;
//		for (int i=0;i<docNames.length;i++) {
//			
//			int amount = 0;
////			for (int j=0;j<tt.getList().size();j++) {
////				if (docNames[i].equals(tt.getList().get(j).getCorpusDocName()) ) {
////					amount++;
////				}
////			}
//			CorpusnameAndTopicamountcontained cat = new CorpusnameAndTopicamountcontained();
//			cat.setDocSeqId(i);
//			cat.setName(docNames[i]);
//			cat.setTopicAmount(amount);
//			returnlist.add(cat);
//			
//		}
//		
//		// calculate the topic amount deviation 
//		Double totalAmountOfTopicAppearance = new Double(0);
//		for (int i=0;i<returnlist.size();i++) {			
//			totalAmountOfTopicAppearance = totalAmountOfTopicAppearance + returnlist.get(i).getTopicAmount();
//		}
//		Double meanTopicAmount = totalAmountOfTopicAppearance / returnlist.size();
//		
//		Double sumOfMeanDistance = new Double(0);
//		for (int i=0;i<returnlist.size();i++) {
//			sumOfMeanDistance = sumOfMeanDistance + Math.pow(returnlist.get(i).getTopicAmount()-meanTopicAmount, 2);
//		}
//		Double standardDeviationValue = Math.sqrt(sumOfMeanDistance/(returnlist.size()-1));
//		// put 'standardDeviationValue' into all the element of returnlist; I have to do this because in frontend, users may sort the list
//		for (int i=0;i<returnlist.size();i++) {
//			returnlist.get(i).setMeanOfTopicAmount(StringUtil.She4ru5ReturnDouble(2, meanTopicAmount));
//			returnlist.get(i).setDeviationOfTopicAmount(StringUtil.She4ru5ReturnDouble(2, standardDeviationValue));
//		}
//		
//		return returnlist;
//	}
	
//	public static List<ThetaColumn> topicInDocumentToColumnList(List<TopicInDocument> list){
//		List<ThetaColumn> columnList = new ArrayList<ThetaColumn>();
//		boolean added = false;
//		int columnNum = 0;
//		for (int i=0;i<list.size();i++) {
//			added = false;
//			columnNum = 0;
//			for (int j=0;j<columnList.size();j++) {
//				if (list.get(i).getTopicNumber().intValue() == columnList.get(j).getTopicNumber().intValue()) {
//					added = true;
//					columnNum = j;
//					break;
//				}
//			}
//			if (added == false) {
//				ThetaColumn tc = new ThetaColumn();
//				tc.setTopicNumber(list.get(i).getTopicNumber());
//				tc.getList().add(list.get(i));
//				columnList.add(tc);
//			} else if(added == true) {
//				columnList.get(columnNum).getList().add(list.get(i));
//			}
//		}
//		return columnList;
//	}
	
//	public static List<ThetaLine> topicInDocumentToLineList(List<TopicInDocument> list){
//		List<ThetaLine> lineList = new ArrayList<ThetaLine>();
//		boolean added = false;
//		int lineNum = 0;
//		for (int i=0;i<list.size();i++) {
//			added = false;
//			lineNum = 0;
//			for (int j=0;j<lineList.size();j++) {
//				if (list.get(i).getCorpusDocName().equals(lineList.get(j).getCorpusDocName())) {
//					added = true;
//					lineNum = j;
//					break;
//				}
//			}
//			if (added == false) {
//				ThetaLine tl = new ThetaLine();
//				tl.setCorpusDocName(list.get(i).getCorpusDocName());
//				tl.getList().add(list.get(i));
//				lineList.add(tl);
//			} else if(added == true) {
//				lineList.get(lineNum).getList().add(list.get(i));
//			}
//		}
//		return lineList;
//	}

}
