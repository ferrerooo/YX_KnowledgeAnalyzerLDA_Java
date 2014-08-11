package com.kalda.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kalda.domain.TblCorpusDocument;
import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.domain.TblKeyWordsForTopicPK;

public class LDATopicManagerDAOImpl extends HibernateDaoSupport implements LDATopicManagerDAO {

	@Override
	public boolean saveCorpusDocument(TblCorpusDocument doc) throws Exception {		
//		doc.setId(new Integer(0));
//		doc.setDocumentName("222.txt");
//		doc.setDocumentContent("555555555555555");
//		doc.setDocumentComment("commmmmmmmm");
		this.getHibernateTemplate().saveOrUpdate(doc);
		System.out.println(doc.getId());
		return true;
	}
	
	@Override
	public TblCorpusDocument getDocIDandComment(String docName) {
		TblCorpusDocument doc = new TblCorpusDocument();
		String hql = "FROM TblCorpusDocument WHERE documentName = ?";
		List<TblCorpusDocument> docList = this.getHibernateTemplate().find(hql, docName);
		if(docList.size() == 0) {
			doc = null;
		} else {
			doc = docList.get(0);
		}
		return doc;
	}

	
	public static void main(String[] args) {
		LDATopicManagerDAOImpl dao = new LDATopicManagerDAOImpl();
		try {
			dao.saveCorpusDocument(new TblCorpusDocument());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean saveKeyWordsForTopic(String topicLable, String keyWords, Integer topicNum, String ldaRunDocName, String whichCorpus) throws Exception {
		TblKeyWordsForTopic kwt = new TblKeyWordsForTopic();
		
		TblKeyWordsForTopicPK kwtPK = new TblKeyWordsForTopicPK();
		kwtPK.setWhichCorpus(whichCorpus);
		kwtPK.setWhichLDARun(ldaRunDocName);
		kwtPK.setTopicNum(topicNum);
		
		kwt.setKwtpk(kwtPK);
		kwt.setTopicLable(topicLable);
		kwt.setKeyWords(keyWords);
		this.getHibernateTemplate().saveOrUpdate(kwt);
		return true;
	}

	@Override
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String ldaRunDocName, String whichCorpus) {

		List<TblKeyWordsForTopic> returnList = new ArrayList<TblKeyWordsForTopic>();
		
		String hql = "FROM TblKeyWordsForTopic WHERE whichCorpus = ? AND whichLDARun = ?";
		returnList = this.getHibernateTemplate().find(hql, new Object[] {whichCorpus, ldaRunDocName});
		
		return returnList;
	}

	@Override
	public TblKeyWordsForTopic loadKeyWordsForOneTopic(String ldaRunDocName,
			int topicNum, String whichCorpus) {
		List<TblKeyWordsForTopic> returnList = new ArrayList<TblKeyWordsForTopic>();
		String hql = "FROM TblKeyWordsForTopic WHERE whichCorpus = ? AND whichLDARun = ? AND topicNum = ?";
		returnList = this.getHibernateTemplate().find(hql, new Object[] {whichCorpus, ldaRunDocName, topicNum});
		if (returnList.size() == 1) {
			return returnList.get(0);
		} else {
			return null;
		}		
	}

}
