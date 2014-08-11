package com.kalda.business;

import java.util.List;

import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.dto.PhiLine;
import com.kalda.dto.WordInTopic;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;
import com.kalda.dto.ldaanalysis.ThetaGrid;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold;
import com.kalda.dto.ldaanalysis.TopicDocumentAmountWithThetaThresholdObject;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;

public interface LDATopicMgrBO {
	
	
	
	// return only file name, no postfix. e.g. "model ---- alpha-0.5 beta-0.1 ntopics-66 niters-02000 twords-20"
	public List<String> loadAllLDARunDocName();
	
	// return info in twords matrix. string format of topic. [topic 1 - aaa, bbb, ccc...]
	public List<TwordsDisplayFormat1Object> loadTwordsDisplayFormat1(String ldaRunDocName);
	
	// to find important topics in document
	public ThetaThreshold loadThetaWithThreshold(ThetaThreshold tt);
	
	public List<TopicDocumentAmountWithThetaThresholdObject> topicCorrespondHowManyDocumentWithThetaThreshold(ThetaThreshold tt);
	
	// use .txt file as cache. return value is the path of the txt file
	public List<TopicDocListWithThetaThreshold> topicCorrespondDocListWithThetaThreshold(ThetaThreshold tt, int topicNum);

	// get all corpus document names
	public String[] loadAllCorpusDocName();
	
	// return the amount of the corpus document
	public int getAllCorpusDocAmount();
	
	// to get the corpusDocName, corpusDocContent, corpusDocComment
	public CorpusTransferObject loadCorpusDocument(CorpusTransferObject ct);
	
	// save
	public boolean saveDocumentComment(CorpusTransferObject ct);
	
	
	// phi related
	public PhiLine loadAPhiLine(Integer topicNum, String ldaDocName);
	public boolean saveKeyWordsForTopic(String topicLable, String keyWords, Integer topicNum, String ldaRunDocName);
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String ldaRunDocName); // load all
	public TblKeyWordsForTopic loadKeyWordsForOneTopic(String ldaRunDocName, int topicNum); // load one

	// for quantitative analysis
	public List<TopicDocListWithThetaThreshold> quantitativeTopicCorrespondDoc(ThetaThreshold tt, int topicNum);

	// To-Do
	public List<ThetaGrid> quantitativeTopicTheta(String ldaRunDocName);


}
