package com.kalda.dao;

import java.util.List;

import com.kalda.domain.TblCorpusDocument;
import com.kalda.domain.TblKeyWordsForTopic;

public interface LDATopicManagerDAO {
	
	public boolean saveCorpusDocument(TblCorpusDocument doc) throws Exception;
	
	public TblCorpusDocument getDocIDandComment(String docName);
	
	// for phi table use
	public boolean saveKeyWordsForTopic(String topicLable, String keyWords, Integer topicNum, String ldaRunDocName, String whichCorpus) throws Exception;
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String ldaRunDocName, String whichCorpus);
	public TblKeyWordsForTopic loadKeyWordsForOneTopic(String ldaRunDocName, int topicNum, String whichCorpus);
}
