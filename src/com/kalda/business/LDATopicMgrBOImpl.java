package com.kalda.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kalda.dao.LDATopicManagerDAO;
import com.kalda.domain.TblCorpusDocument;
import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.dto.PhiLine;
import com.kalda.dto.TopWordInTopic;
import com.kalda.dto.Twords;
import com.kalda.dto.WordInTopic;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;
import com.kalda.dto.ldaanalysis.ThetaGrid;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold;
import com.kalda.dto.ldaanalysis.TopicDocumentAmountWithThetaThresholdObject;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;
import com.kalda.utils.StringComparator;
import com.kalda.utils.StringUtil;
import com.kalda.utils.ThetaGridComparator;
import com.kalda.utils.TopicDocListWithThetaThresholdComparator;
import com.kalda.utils.TopicInDocumentComparator;
import com.kalda.utils.WordInTopicComparator;
import com.kalda.utils.WordInTopicComparator_V2;

public class LDATopicMgrBOImpl implements LDATopicMgrBO {
	
	private String lDAModelPath;
	private String corpusPath;
	private String wordMapPath;
	private LDATopicManagerDAO lDATopicManagerDAO;
	
	public String getlDAModelPath() {
		return lDAModelPath;
	}

	public void setlDAModelPath(String lDAModelPath) {
		this.lDAModelPath = lDAModelPath;
	}

	public String getCorpusPath() {
		return corpusPath;
	}

	public void setCorpusPath(String corpusPath) {
		this.corpusPath = corpusPath;
	}

	public String getWordMapPath() {
		return wordMapPath;
	}

	public void setWordMapPath(String wordMapPath) {
		this.wordMapPath = wordMapPath;
	}

	public LDATopicManagerDAO getlDATopicManagerDAO() {
		return lDATopicManagerDAO;
	}

	public void setlDATopicManagerDAO(LDATopicManagerDAO lDATopicManagerDAO) {
		this.lDATopicManagerDAO = lDATopicManagerDAO;
	}

	@Override
	public List<String> loadAllLDARunDocName() {
		System.out.println(this.lDAModelPath);
		List<String> returnList = new ArrayList<String>();
		File dir = new File(this.lDAModelPath);
		File[] files = dir.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			boolean added = false;
			String name = files[i].getName();
			if (name.startsWith("model") && name.endsWith("others")) {
				name = name.substring(0,name.length()-7);
				returnList.add(name);
			}
			
		}
		
		return returnList;
	}

	@Override
	public List<TwordsDisplayFormat1Object> loadTwordsDisplayFormat1(String ldaRunDocName) {
		
		List<TwordsDisplayFormat1Object> returnList = new ArrayList<TwordsDisplayFormat1Object>();
		
		Twords twords = new Twords();
		twords = LDATopicMgrBOUtils.loadTwords(ldaRunDocName, this.lDAModelPath);
		List<TopWordInTopic> list = twords.getList();
		
		for (int i=0; i<twords.getNtopics(); i++) {
			String topicnumAndWord = new String();
			String topicnumAndWordAndProbability = new String();
			if (i<10) {
				topicnumAndWord = new String("000"+ i +" - ");
				topicnumAndWordAndProbability = new String("000" + i +" - ");
			} else if (i>=10 && i<=99) {
				topicnumAndWord = new String("00"+i +" - ");
				topicnumAndWordAndProbability = new String("00"+i +" - ");
			} else if (i>=100 && i<999) {
				topicnumAndWord = new String("0"+i +" - ");
				topicnumAndWordAndProbability = new String("0"+i +" - ");
			}		
			Double totalProbability = new Double(0);
			for (int j=0;j<list.size();j++) {
				if (list.get(j).getTopicNumber().intValue() == i) {
					topicnumAndWord = topicnumAndWord + list.get(j).getWord() + ", ";
					topicnumAndWordAndProbability = topicnumAndWordAndProbability + list.get(j).getWord() +
													"("+list.get(j).getWordInTopicProbability()+"), ";
					totalProbability = totalProbability + list.get(j).getWordInTopicProbability();
				}
			}
			topicnumAndWord = topicnumAndWord.substring(0, topicnumAndWord.length()-2);
			topicnumAndWord = topicnumAndWord + "\n";
			topicnumAndWordAndProbability = topicnumAndWordAndProbability.substring(0, topicnumAndWordAndProbability.length()-2);
			topicnumAndWordAndProbability = topicnumAndWordAndProbability + "\n";
			TwordsDisplayFormat1Object tdf1 = new TwordsDisplayFormat1Object();
			
			tdf1.setTopicNumber(new Integer(i));
			tdf1.setTopicnumAndWords(topicnumAndWord);
			tdf1.setTopicnumAndWordsAndProbability(topicnumAndWordAndProbability);
			tdf1.setTopicTotalProbability(StringUtil.She4ru5ReturnDouble(4, totalProbability));
			
			returnList.add(tdf1);
		}
		for (int i=0;i<returnList.size();i++) {
			System.out.print(returnList.get(i).getTopicnumAndWords());
		}
		return returnList;
	}

	@Override
	public ThetaThreshold loadThetaWithThreshold(ThetaThreshold tt) {
		
		int totalCorpusAmount = this.getAllCorpusDocAmount();
		// get topicAmount start
		//int topicAmount = StringUtil.StringToInteger(tt.getLdaRunDocName().substring(61, 63));  
		int topicAmount = 0;
		File file1 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".others");
		BufferedReader reader1 = null;
		String str1 = "";
		try {
			reader1 = new BufferedReader(new FileReader(file1));	
			while ((str1 = reader1.readLine()) != null) {
				str1 = str1.trim();
				if (str1.startsWith("ntopics")) {
					topicAmount = StringUtil.StringToInteger(str1.substring(8));
				}
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
		// get topicAmount end
		tt.setTotalGridAmount(totalCorpusAmount * topicAmount);
		tt.setTotalCorpusDocAmount(totalCorpusAmount);
		
		// threshold method1
		if (tt.isTotal()) {
			
			
			tt.setNote("Successful!   Totally "+tt.getTotalGridAmount()+" Theta Grids loaded. \n"+
								"Corpus file amount " +totalCorpusAmount + "\n"+
								"No Threshold.");
			
		// threshold method2
		} else if (tt.isPercentile()) {
			
			Double percentile = tt.getPercentNum()/100;
			Double d = (tt.getTotalGridAmount() * percentile);
			int num = d.intValue();
			tt.setSelectedGridAmount(num);
			
			tt.setPercentThreshold(LDATopicMgrBOUtils.getPercentileThresholdValue(tt, this.lDAModelPath));
			
			tt.setNote("Successful!   Totally "+tt.getTotalGridAmount()+ " Theta Grids are Calculated.\n"+
									"Threshold = "+ tt.getPercentThreshold()+"\n"+
									"Top " + tt.getPercentNum() + " percent, which is "+ tt.getSelectedGridAmount() +" Theta Grids loaded."+"Corpus file amount ");
		// threshold method3
		} else if (tt.isDeviation()) {
			
			LDATopicMgrBOUtils.setDeviationValues(tt, this.lDAModelPath);
			
			tt.setNote("Successful!   Totally "+ tt.getTotalGridAmount() +" Theta Grids are Calculated.\n"+
									"Threshold ="  + tt.getMeanValue()+ " + " +tt.getkValue() +  " X " + tt.getStandDeviationValue() + " = " +tt.getDeviationValue()+ "\n" +
									tt.getSelectedGridAmount()+" Theta Grids loaded ("+StringUtil.She4ru5ReturnDouble(4, new Double(tt.getSelectedGridAmount()*100)/new Double(tt.getTotalGridAmount()))+" %)\n"+
									tt.getSelectedCorpusDocAmount()+" Corpus file loaded (" + StringUtil.She4ru5ReturnDouble(4, new Double(tt.getSelectedCorpusDocAmount()*100)/new Double(tt.getTotalCorpusDocAmount())) +" %)");
		}
		
		return tt;
	}

	@Override
	public String[] loadAllCorpusDocName() {
		File dir = new File(this.corpusPath);
		String[] docNames = dir.list();
		
		return docNames;
	}

	@Override
	public CorpusTransferObject loadCorpusDocument(CorpusTransferObject ct) {
		/*--- get document content ----*/
		File file = new File(this.corpusPath + "/" + ct.getCorpusDocName());
		BufferedReader reader = null;
		String documentContent = new String();
		String str = new String();
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((str = reader.readLine()) != null) {
				documentContent = documentContent + str + "\n";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		ct.setCorpusDocContent(documentContent);
		/*--- get document id and comment ----*/
		TblCorpusDocument tcd = new TblCorpusDocument();
		tcd = lDATopicManagerDAO.getDocIDandComment(ct.getCorpusDocName());
		if (tcd == null ) {
			ct.setCorpusDocComment("");
			ct.setId(0);
		} else {
			ct.setCorpusDocComment(tcd.getDocumentComment());
			ct.setId(tcd.getId());
		}
		
		return ct;
	}

	@Override
	public boolean saveDocumentComment(CorpusTransferObject ct) {
		TblCorpusDocument tcd = new TblCorpusDocument();
		try {
			if(ct.getId().intValue() == 0) {
				tcd.setId(null);
			} else {
				tcd.setId(ct.getId());
			}
			tcd.setDocumentName(ct.getCorpusDocName());
			tcd.setDocumentContent(ct.getCorpusDocContent());
			tcd.setDocumentComment(ct.getCorpusDocComment());
			return(lDATopicManagerDAO.saveCorpusDocument(tcd));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override
	public int getAllCorpusDocAmount() {
		File dir = new File(this.corpusPath);
		String[] docNames = dir.list();
		return docNames.length;
	}

	@Override
	public List<TopicDocumentAmountWithThetaThresholdObject> topicCorrespondHowManyDocumentWithThetaThreshold(ThetaThreshold tt) {
		System.out.println("##### come to topicCorrespondHowManyDocumentWithThetaThreshold");
		Double threshold = new Double(0);
		Integer ntopics = 0;
		List<TopicDocumentAmountWithThetaThresholdObject> returnList = new ArrayList<TopicDocumentAmountWithThetaThresholdObject>();
		if (tt.isTotal()) {
			threshold = new Double(0);
		} else if (tt.isPercentile()) {
			threshold = tt.getPercentThreshold();
		} else if (tt.isDeviation()) {
			threshold = tt.getDeviationValue();
		}
		// get the topic number:ntopics
		List<TwordsDisplayFormat1Object> topiclist = this.loadTwordsDisplayFormat1(tt.getLdaRunDocName());
		ntopics = topiclist.size();
		
		// get the corpus doc amount
		Integer[] topicCorrespondCorpusDocAmount = new Integer[ntopics];
		for (int i=0;i<ntopics;i++) {
			//  init the array
			topicCorrespondCorpusDocAmount[i] = new Integer(0);
		}
		File file1 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		BufferedReader reader1 = null;
		String str1 = null;
		String[] s = null;
		try {
			reader1 = new BufferedReader(new FileReader(file1));	
			while ((str1 = reader1.readLine()) != null) {
				str1 = str1.trim();
				s = str1.split(" ");
				for (int i=0;i<s.length;i++) {
					if (new Double(s[i]) >= threshold) {
						topicCorrespondCorpusDocAmount[i] ++;
					}
				}
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
		
		// build the returnlist
		for (int i=0;i<topiclist.size();i++) {
			TopicDocumentAmountWithThetaThresholdObject t = new TopicDocumentAmountWithThetaThresholdObject();
			t.setTopicNum(i);
			t.setTopicString(topiclist.get(i).getTopicnumAndWords());
			t.setCorpusDocAmount(topicCorrespondCorpusDocAmount[i]);
			returnList.add(t);
		}
		
		return returnList;
	}

	@Override
	public List<TopicDocListWithThetaThreshold> topicCorrespondDocListWithThetaThreshold(ThetaThreshold tt, int topicNum) {
		
		//************************************************************************************************************
		/*
		 * 2014 01 14. expert post logic 
		 * */
		
		
		
		
		//************************************************************************************************************
		
		System.out.println("##### come to topicCorrespondDocListWithThetaThreshold");
		String returnPath = null;
		String[] nameArray = this.loadAllCorpusDocName();
		
		// get threshold value
		Double threshold = new Double(0);
		if (tt.isTotal()) {
			threshold = new Double(0);
		} else if (tt.isPercentile()) {
			threshold = tt.getPercentThreshold();
		} else if (tt.isDeviation()) {
			threshold = tt.getDeviationValue();
		}
		
		// write the cache file
		File file1 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		File cacheFile = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt");
		BufferedReader reader1 = null;
		BufferedWriter writer = null;
		String str1 = null;
		String[] s = null;
		int count = -1;
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			writer = new BufferedWriter(new FileWriter(cacheFile));
			writer.write("");
			while ((str1 = reader1.readLine()) != null) {
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				
				//if (new Double(s[topicNum]) >= threshold ) {
				if (new Double(s[topicNum]) >= 0.02 ) {
					writer.write(count+" "+nameArray[count]+" "+StringUtil.She4ru5ReturnDouble(4, new Double(s[topicNum]))+"\n");
				}
//				
//				for (int m=0;m<120;m++) {
//					
//					if (m == 115 || m==82 || m==75 || m==64 || m==51 || m==59 || m==17
//							|| m==28 || m==31 || m==38 || m==77 || m==78 || m==99) {
//						continue;
//					}
//					
//					
//					if (new Double(s[m]) >= threshold ) {
//						writer.write(nameArray[count]+"\n");
//					}
//				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader1.close();
				writer.close();
				nameArray = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("##### Cache file CacheForDocumentListWithThetaThreshold.txt generated...");
		
		// sort the cache file based on probability
		List<TopicDocListWithThetaThreshold> list = new ArrayList<TopicDocListWithThetaThreshold>();
		file1 = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt"); 
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			while ((str1 = reader1.readLine()) != null) {
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				TopicDocListWithThetaThreshold tltt = new TopicDocListWithThetaThreshold();
				tltt.setDocNum(StringUtil.StringToInteger(s[0]));
				tltt.setDocName(s[1]);
				tltt.setTopicProbabilityForThisDoc(StringUtil.StringToDouble(s[2]));
				list.add(tltt);   //  ===> THIS IS THE BOTTLE NECK !!! load all the file content into memory
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
		
		return list;		
	}
	
	
	/*
	@Override
	public List<TopicDocListWithThetaThreshold> topicCorrespondDocListWithThetaThreshold(ThetaThreshold tt, int topicNum) {
		
		
		
		System.out.println("##### come to topicCorrespondDocListWithThetaThreshold");
		String returnPath = null;
		String[] nameArray = this.loadAllCorpusDocName();
		
		// get threshold value
		Double threshold = new Double(0);
		if (tt.isTotal()) {
			threshold = new Double(0);
		} else if (tt.isPercentile()) {
			threshold = tt.getPercentThreshold();
		} else if (tt.isDeviation()) {
			threshold = tt.getDeviationValue();
		}
		
		// write the cache file
		File file1 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		File cacheFile = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt");
		BufferedReader reader1 = null;
		BufferedWriter writer = null;
		String str1 = null;
		String[] s = null;
		int count = -1;
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			writer = new BufferedWriter(new FileWriter(cacheFile));
			writer.write("");
			while ((str1 = reader1.readLine()) != null) {
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				if (new Double(s[topicNum]) >= threshold ) {
					writer.write(count+" "+nameArray[count]+" "+StringUtil.She4ru5ReturnDouble(4, new Double(s[topicNum]))+"\n");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader1.close();
				writer.close();
				nameArray = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("##### Cache file CacheForDocumentListWithThetaThreshold.txt generated...");
		
		// sort the cache file based on probability
		List<TopicDocListWithThetaThreshold> list = new ArrayList<TopicDocListWithThetaThreshold>();
		file1 = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt"); 
		try {
			reader1 = new BufferedReader(new FileReader(file1));
			while ((str1 = reader1.readLine()) != null) {
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				TopicDocListWithThetaThreshold tltt = new TopicDocListWithThetaThreshold();
				tltt.setDocNum(StringUtil.StringToInteger(s[0]));
				tltt.setDocName(s[1]);
				tltt.setTopicProbabilityForThisDoc(StringUtil.StringToDouble(s[2]));
				list.add(tltt);   //  ===> THIS IS THE BOTTLE NECK !!! load all the file content into memory
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
		//TopicDocListWithThetaThresholdComparator comp = new TopicDocListWithThetaThresholdComparator();
		//Collections.sort(list,comp);    //  ==> THIS IS a narrower BOTTLE NECK !!!
		//System.out.println("##### Cache file CacheForDocumentListWithThetaThreshold.txt sorted...");
		
		return list;
		
	} */
	

	@Override
	public PhiLine loadAPhiLine(Integer topicNum, String ldaDocName) {
		
		//  load wordmap.txt into List
		List<WordInTopic> list = new ArrayList<WordInTopic>();
		File wordmapFile = new File(this.wordMapPath);
		BufferedReader reader = null;
		String str = null;
		String[] s = null;
		try {
			reader = new BufferedReader(new FileReader(wordmapFile));
			while ((str = reader.readLine()) != null) {
				s = str.split(" ");
				if (s.length == 2) {
					WordInTopic wit = new WordInTopic();
					wit.setWord(s[0]);
					wit.setWordNum(new Integer(s[1]));
					list.add(wit);
				}
			}
			WordInTopicComparator comp = new WordInTopicComparator();
			// here, the word sequence should be the SAME as it in Phi table.
			Collections.sort(list,comp);
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		File phiFile = new File(this.lDAModelPath+"/"+ldaDocName+".phi");
		try {
			reader = new BufferedReader(new FileReader(phiFile));
			int count = -1;
			while ((str = reader.readLine()) != null) {
				count++;
				if (topicNum == count) {
					s = str.split(" ");
					if (s.length == list.size()) {
						for (int i=0;i<s.length;i++) {
							list.get(i).setWordInTopicProbability(StringUtil.She4ru5ReturnDouble(10, s[i]));
						}
					} else {
						throw new Exception();
					}
				}
			}
			WordInTopicComparator_V2 comp = new WordInTopicComparator_V2();
			// here, sorted based on probability
			Collections.sort(list,comp);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		List<WordInTopic> smallerList = new ArrayList<WordInTopic>();
		for (int m=0;m<1000;m++) {
			smallerList.add(list.get(m));
		}
		
		PhiLine pl = new PhiLine();
		pl.setList(smallerList);
		pl.setTopicNumber(topicNum);
		
		// added on 20130806, calculate deviation values for philine, preparation for auto choose key words use on frontend
		Double meanValue = new Double(0);
		Double standardDeviationValue = new Double(0);
		for (int i=0;i<list.size();i++) {
			meanValue = meanValue + list.get(i).getWordInTopicProbability();
		}
		meanValue = meanValue / list.size(); // finally get the mean value;
		for (int i=0;i<list.size();i++) {
			standardDeviationValue = standardDeviationValue + Math.pow(list.get(i).getWordInTopicProbability()-meanValue, 2);			
		}
		standardDeviationValue = Math.sqrt(standardDeviationValue/(list.size()-1));
		pl.setPhiLineMeanValue(StringUtil.She4ru5ReturnDouble(8, meanValue));
		pl.setStandDeviationValue(StringUtil.She4ru5ReturnDouble(8, standardDeviationValue));
		// calculation deviation end
		
		return pl;
	}

	@Override
	public boolean saveKeyWordsForTopic(String topicLable, String keyWords, Integer topicNum, String ldaRunDocName) {
		try {
			return(lDATopicManagerDAO.saveKeyWordsForTopic(topicLable, keyWords, topicNum, ldaRunDocName, this.corpusPath));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String ldaRunDocName) {
		
		List<TblKeyWordsForTopic> returnList = new ArrayList<TblKeyWordsForTopic>();
		
		returnList = lDATopicManagerDAO.loadKeyWordsForTopic(ldaRunDocName, this.corpusPath);
		
		return returnList;
	}

	@Override
	public TblKeyWordsForTopic loadKeyWordsForOneTopic(String ldaRunDocName,
			int topicNum) {

		TblKeyWordsForTopic kwt = new TblKeyWordsForTopic();
		kwt = lDATopicManagerDAO.loadKeyWordsForOneTopic(ldaRunDocName, topicNum, this.corpusPath);
		return kwt;
	}

	@Override
	public List<TopicDocListWithThetaThreshold> quantitativeTopicCorrespondDoc(ThetaThreshold tt, int topicNum) {
		// get total corpus amount
		int totalCorpusAmount = this.getAllCorpusDocAmount();
		
		// get total topic amount
		int topicAmount = 0;
		File file1 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".others");
		BufferedReader reader1 = null;
		String str1 = "";
		try {
			reader1 = new BufferedReader(new FileReader(file1));	
			while ((str1 = reader1.readLine()) != null) {
				str1 = str1.trim();
				if (str1.startsWith("ntopics")) {
					topicAmount = StringUtil.StringToInteger(str1.substring(8));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// deviation calculation related
		Double meanValue = new Double(0);
		Double standardDeviationValue = new Double(0);
		Double deviationValue = new Double(0);		
		Double totalProbablityOfCurrentTopic = new Double(0);  // temp value for calculation
		Double sumOfMeanDistance = new Double(0); // temp value for calculation
		String str = null;
		String[] s = null;
		BufferedReader reader = null;
		File file = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		try {
			// calculate meanValue
			reader = new BufferedReader(new FileReader(file));
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				s = str.split(" ");
				totalProbablityOfCurrentTopic = totalProbablityOfCurrentTopic +new Double(s[topicNum]);
			}
			meanValue = totalProbablityOfCurrentTopic / totalCorpusAmount;
			//System.out.println("meanValue = totalProbablityOfCurrentTopic / totalCorpusAmount --- "+meanValue + "=" +totalProbablityOfCurrentTopic+"/"+totalCorpusAmount);
			reader.close();
			
			// calculate standardDeviationValue and deviationValue
			reader = new BufferedReader(new FileReader(file));	
			while ((str = reader.readLine()) != null) {
				str = str.trim();
				s = str.split(" ");
				sumOfMeanDistance = sumOfMeanDistance + Math.pow(new Double(s[topicNum])-meanValue, 2);
			}
			standardDeviationValue = Math.sqrt(sumOfMeanDistance/(totalCorpusAmount-1));
			//System.out.println("standardDeviationValue --- "+ standardDeviationValue);
			deviationValue = meanValue + tt.getkValue() * standardDeviationValue;
			//System.out.println("threshold = meanValue + keyValue * standardDeviationValue --- "+ deviationValue+"="+tt.getkValue()+"*"+standardDeviationValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		// set ThetaThreshold object
		tt.setStandDeviationValue(standardDeviationValue);
		tt.setDeviationValue(deviationValue);
		tt.setMeanValue(meanValue);
		tt.setNote("meanValue-"+meanValue + " standardDeviationValue-"+standardDeviationValue+" threshold-"+deviationValue);
		//System.out.println(tt.getNote());
		
		Double threshold = new Double(0);
		
		// get document name list
		String[] nameArray = this.loadAllCorpusDocName();
		// write the cache file
		File file2 = new File(this.lDAModelPath + "/" + tt.getLdaRunDocName()+".theta");
		File cacheFile = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt");
		BufferedReader reader2 = null;
		BufferedWriter writer = null;
		str1 = null;
		s = null;
		int count = -1;
		try {
			reader2 = new BufferedReader(new FileReader(file2));
			writer = new BufferedWriter(new FileWriter(cacheFile));
			writer.write("");
			while ((str1 = reader2.readLine()) != null) {
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				if (new Double(s[topicNum]) >= threshold ) {
					writer.write(count+" "+nameArray[count]+" "+StringUtil.She4ru5ReturnDouble(4, new Double(s[topicNum]))+"\n");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader2.close();
				writer.close();
				nameArray = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("##### Cache file CacheForDocumentListWithThetaThreshold.txt generated...");
		
		// get return data from cache file
		List<TopicDocListWithThetaThreshold> list = new ArrayList<TopicDocListWithThetaThreshold>();
		file2 = new File(this.lDAModelPath + "/CacheForDocumentListWithThetaThreshold.txt"); 
		try {
			reader2 = new BufferedReader(new FileReader(file2));
			while ((str1 = reader2.readLine()) != null) {
				str1 = str1.trim();
				s = str1.split(" ");
				TopicDocListWithThetaThreshold tltt = new TopicDocListWithThetaThreshold();
				tltt.setDocNum(StringUtil.StringToInteger(s[0]));
				tltt.setDocName(s[1]);
				tltt.setTopicProbabilityForThisDoc(StringUtil.StringToDouble(s[2]));
				list.add(tltt);   //  ===> THIS IS THE BOTTLE NECK !!! load all the file content into memory
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader2.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		TopicDocListWithThetaThresholdComparator comp = new TopicDocListWithThetaThresholdComparator();
		Collections.sort(list,comp);    //  ==> THIS IS a narrower BOTTLE NECK !!!		
		
		
		return list;
	}

	@Override
	public List<ThetaGrid> quantitativeTopicTheta(String ldaRunDocName) {
		
		String[] nameArray = this.loadAllCorpusDocName();
		
		List<ThetaGrid> returnList = new ArrayList<ThetaGrid>();
		
		ThetaGridComparator comp = new ThetaGridComparator();
		StringComparator scomp = new StringComparator();
		
		File readFile = new File(this.lDAModelPath + "/" + ldaRunDocName+".theta");
		BufferedReader reader = null;
		String str1 = null;
		String[] s = null;
		int count = -1;
		
		//---------------------------------------------------------------------------------
		// hardcoded here
		int howManyTopics = 120;
		int howManyDocuments = 111132;
		double OverKaverage = 0.00833333;
		
		double[][] percentileConstraint = new double[120][5];
		for (int i=0;i<120;i++) {
			for (int j=0;j<5;j++) {
				percentileConstraint[i][j] = 0;
			}
		}
		
		try {
//			File readPercentile = new File("C:/Users/Administrator/Desktop/temp/percentile.txt");
//			reader = new BufferedReader(new FileReader(readPercentile));
//			int tinteger = -1;
//			while ((str1 = reader.readLine()) != null) {
//				str1 = str1.trim();
//				s = str1.split(" ");
//				tinteger ++;
//					percentileConstraint[tinteger][0] = StringUtil.She4ru5ReturnDouble(4, new Double(s[0]));
//					percentileConstraint[tinteger][1] = StringUtil.She4ru5ReturnDouble(4, new Double(s[1]));
//					percentileConstraint[tinteger][2] = StringUtil.She4ru5ReturnDouble(4, new Double(s[2]));
//					percentileConstraint[tinteger][3] = StringUtil.She4ru5ReturnDouble(4, new Double(s[3]));
//					percentileConstraint[tinteger][4] = StringUtil.She4ru5ReturnDouble(4, new Double(s[4]));
//				
//			}
			//-------------------------------------------------------------------------------
			
			
			reader = new BufferedReader(new FileReader(readFile));
			while ((str1 = reader.readLine()) != null) {
				List<ThetaGrid> thetaLine = new ArrayList<ThetaGrid>();
				count++;
				str1 = str1.trim();
				s = str1.split(" ");
				
				for(int i=0;i<s.length;i++) {
					ThetaGrid tg = new ThetaGrid();
					tg.setDocNum(count);
					tg.setDocName(nameArray[count]);
					tg.setTopicNum(i);
					//tg.setTopicProbabilityForThisDoc(StringUtil.She4ru5ReturnDouble(4, new Double(s[i])));
					tg.setTopicProbabilityForThisDoc(new Double(s[i]));
					tg.setRank(-1);
					
					thetaLine.add(tg);
				}
				Collections.sort(thetaLine,comp);
				
				thetaLine.get(0).setRank(1);
				for (int i=1;i<thetaLine.size();i++) {
					if(thetaLine.get(i-1).getRank() == 3){
						break;
					}
					double d1 = thetaLine.get(i).getTopicProbabilityForThisDoc();
					double d2 = thetaLine.get(i-1).getTopicProbabilityForThisDoc();
					if ( d1 != d2) {
						thetaLine.get(i).setRank(thetaLine.get(i-1).getRank()+1);
					} else {
						thetaLine.get(i).setRank(thetaLine.get(i-1).getRank());
					}
				}
				
				for (int i=0;i<thetaLine.size();i++) {
					if(thetaLine.get(i).getRank()==1) {
					//if(thetaLine.get(i).getRank()==2 || thetaLine.get(i).getRank()==1) {
						if (thetaLine.get(i).getTopicProbabilityForThisDoc() > percentileConstraint[i][4])
							returnList.add(thetaLine.get(i));
					}
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				nameArray = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnList;
	}



}
