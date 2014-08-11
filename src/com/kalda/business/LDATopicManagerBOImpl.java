package com.kalda.business;

import java.io.BufferedReader;
import com.kalda.domain.TblCorpusDocument;
import com.kalda.dto.LDARunObject;
import com.kalda.dto.Theta;
import com.kalda.dto.TopWordInTopic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kalda.dao.LDATopicManagerDAO;
import com.kalda.utils.StringUtil;
import com.kalda.dto.*;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;
import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;
import com.kalda.domain.TblCorpusDocument;

import com.kalda.jgibblda.LuceneHighLighter;
import com.kalda.model.Models;
import com.kalda.utils.TopicInDocumentComparator;
import com.kalda.model.Models;
import com.kalda.model.LDAAnalysisModel;

public class LDATopicManagerBOImpl implements LDATopicManagerBO {
	
	private String lDAModelPath;
	private String corpusPath;
	private String wordMapPath;
	private LDATopicManagerDAO lDATopicManagerDAO;
	
	/* unit test */
	public static void main(String[] args) {
		

	}

	public String getWordMapPath() {
		return wordMapPath;
	}
	public void setWordMapPath(String wordMapPath) {
		this.wordMapPath = wordMapPath;
	}
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
			if (name.startsWith("model")) {
				name = name.substring(0,63);
				for (int j=0;j<returnList.size();j++) {
					if (returnList.get(j).equals(name)) {
						added = true;
						break;
					}
				}
				if (added == false) {
					returnList.add(name);
				}
			}
			
		}
		
		return returnList;
		
	}

	@Override
	public List<TwordsDisplayFormat1Object> loadTwordsDisplayFormat1(String ldaRunDocName) {
		System.out.println("come here loadTwordsDisplayFormat1");
		List<TwordsDisplayFormat1Object> returnList = new ArrayList<TwordsDisplayFormat1Object>();
		
		Twords twords = new Twords();
		twords = LDATopicManagerBOUtil.loadTwords(ldaRunDocName, this.lDAModelPath);
		List<TopWordInTopic> list = twords.getList();
		
		for (int i=0; i<twords.getNtopics(); i++) {
			String topicnumAndWord = new String();
			String topicnumAndWordAndProbability = new String();
			if (i<10) {
				topicnumAndWord = new String("0"+ i +" - ");
				topicnumAndWordAndProbability = new String("0" + i +" - ");
			} else {
				topicnumAndWord = new String(i +" - ");
				topicnumAndWordAndProbability = new String(i +" - ");
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
			System.out.println(returnList.get(i).getTopicnumAndWords());
		}
		return returnList;
	}

	@Override
	public ThetaThreshold loadThetaWithThreshold(ThetaThreshold tt) {
		
		List<TopicInDocument> returnList = new ArrayList<TopicInDocument>();
		Theta theta = LDATopicManagerBOUtil.loadTheta(tt.getLdaRunDocName(), this.lDAModelPath, this.corpusPath);
		List<TopicInDocument> tdList = new ArrayList<TopicInDocument>();
		Double totalProbablityOfTheta = new Double(0); // for deviation cal use
		Integer thetaGridNum = 0; // for deviation cal use
		for (int i=0;i<theta.getLineList().size();i++) {
			for (int j=0;j<theta.getLineList().get(i).getList().size();j++) {				
				tdList.add(theta.getLineList().get(i).getList().get(j));
				totalProbablityOfTheta = totalProbablityOfTheta + theta.getLineList().get(i).getList().get(j).getTopicInDocumentProbability();
				thetaGridNum ++;
			}
		}
		Double meanProbabilityOfTheta = totalProbablityOfTheta / thetaGridNum;
		tt.setTotalGridAmount(thetaGridNum);
		
		// threshold method1
		if (tt.isTotal()) {
			returnList = tdList;
		// threshold method2
		} else if (tt.isPercentile()) {
			
			Double percentile = tt.getPercentNum()/100;
			
			TopicInDocumentComparator comp = new TopicInDocumentComparator();
			Collections.sort(tdList,comp);
			
			Double d = (tdList.size() * percentile);
			int num = d.intValue();
			tt.setSelectedGridAmount(num);
			List<TopicInDocument> tdPercentileList = new ArrayList<TopicInDocument>();
			
			for (int m=0;m<num;m++) {
				tdPercentileList.add(tdList.get(m));
				tt.setPercentThreshold(tdList.get(m).getTopicInDocumentProbability());
			}
			
			returnList = tdPercentileList;
		// threshold method3
		} else if (tt.isDeviation()) {
			Double sumOfMeanDistance = new Double(0);
			for (int i=0;i<tdList.size();i++) {
				sumOfMeanDistance = sumOfMeanDistance + Math.pow(tdList.get(i).getTopicInDocumentProbability()-meanProbabilityOfTheta, 2);
			}
			Double standardDeviationValue = Math.sqrt(sumOfMeanDistance/(tdList.size()-1));
			Double deviationOfTheta = meanProbabilityOfTheta + tt.getkValue() * standardDeviationValue;
			List<TopicInDocument> tdDeviationList = new ArrayList<TopicInDocument>();
			for (int i=0;i<tdList.size();i++) {
				if (tdList.get(i).getTopicInDocumentProbability() >= deviationOfTheta) {
					tdDeviationList.add(tdList.get(i));
				}
			}
			returnList = tdDeviationList;
			tt.setSelectedGridAmount(tdDeviationList.size());
			tt.setMeanValue(StringUtil.She4ru5ReturnDouble(4, meanProbabilityOfTheta));
			tt.setStandDeviationValue(StringUtil.She4ru5ReturnDouble(4, standardDeviationValue));
			tt.setDeviationValue(StringUtil.She4ru5ReturnDouble(4, deviationOfTheta));
		}
		
		return tt;
	}
	
	@Override
	public List<String> loadAllCorpusDocName() {
		
		return null;
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
}
