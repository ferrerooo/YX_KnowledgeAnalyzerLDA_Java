package com.kalda.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import com.kalda.dao.LDATopicManagerDAO;
import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.domain.TblReplyJavaRanch;
import com.kalda.domain.TblThreadJavaRanch;
import com.kalda.domain.TblThreadStackoverflow;
import com.kalda.domain.TblUser;
import com.kalda.dto.ThetaLine;
import com.kalda.dto.corpus.NewCorpusTrainResult;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;
import com.kalda.dao.CorpusMgrDAO;
import com.kalda.utils.LuceneUtil;

import flex.messaging.io.ArrayCollection;

public class CorpusMgrBOImpl implements CorpusMgrBO {
	
	private String corpusPath;
	private String lDAModelPath;
	private CorpusMgrDAO corpusMgrDAO;
	

	public String getCorpusPath() {
		return corpusPath;
	}
	public void setCorpusPath(String corpusPath) {
		this.corpusPath = corpusPath;
	}
	public String getlDAModelPath() {
		return lDAModelPath;
	}
	public void setlDAModelPath(String lDAModelPath) {
		this.lDAModelPath = lDAModelPath;
	}
	public CorpusMgrDAO getCorpusMgrDAO() {
		return corpusMgrDAO;
	}
	public void setCorpusMgrDAO(CorpusMgrDAO corpusMgrDAO) {
		this.corpusMgrDAO = corpusMgrDAO;
	}

	public static void main(String[] args) {

	}

	@Override
	public boolean saveThreadJavaRanch(TblThreadJavaRanch tjr) {
		
		return corpusMgrDAO.saveThreadJavaRanch(tjr);
	}

	
	@Override
	public TblThreadJavaRanch getThreadJavaRanch(int id) {

		return corpusMgrDAO.getThreadJavaRanch(id);
		
	}
	@Override
	public boolean saveReplyJavaRanch(TblReplyJavaRanch rjr) {
		
		return corpusMgrDAO.saveReplyJavaRanch(rjr);
	}
	@Override
	public List<String> loadAllUsers() {
		
		List<String> userNameList = new ArrayList<String>();
		userNameList = corpusMgrDAO.loadAllUsers();
		
		return userNameList;
	}
	@Override
	public List<TblUser> loadAllUsersWithPostAndReply() {
		
		List<TblUser> userList = new ArrayList<TblUser>();
		userList = corpusMgrDAO.loadAllUsersWithPostAndReply();
		return userList;
	}
	@Override
	public boolean saveUserJavaRanch(List<TblUser> userList) {
		
		return corpusMgrDAO.saveUserJavaRanch(userList);
	}
	@Override
	public boolean updateUserJavaRanchForPostAmountAndReplyAmount(List<TblUser> userList) {
		
		return corpusMgrDAO.updateUserJavaRanchForPostAmountAndReplyAmount(userList);
	}
	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch(TblUser u) {
		
		return corpusMgrDAO.getReplyJavaRanch(u);
	}
	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch() {
		
		return corpusMgrDAO.getReplyJavaRanch();
	}
	@Override
	public boolean saveThreadStackoverflow(TblThreadStackoverflow tsf) {
		
		return corpusMgrDAO.saveThreadStackoverflow(tsf);
	}
	@Override
	public TblThreadStackoverflow getThreadStackoverflow(int id) {
		
		return corpusMgrDAO.getThreadStackoverflow(id);
	}
	@Override
	public List<NewCorpusTrainResult> trainNewCorpus(String ldaRunDocName, String newCorpus) {
		
		// pre-handle corpus
		String stemmedNewCorpus;
		StandardAnalyzer sa = new StandardAnalyzer(Version.LUCENE_40);
		stemmedNewCorpus = LuceneUtil.tokenizeStringReturnString(sa, newCorpus);
		//System.out.println(stemmedNewCorpus);
		
		// call JGibbLDA inference method
		double[] thetaline = com.kalda.jgibblda.LDAClientForNewCorpus.trainNewCorpus(this.lDAModelPath, ldaRunDocName, stemmedNewCorpus);
		
		// calculate key words in .tword file
		LDATopicMgrBOImpl tmb = new LDATopicMgrBOImpl();
		tmb.setCorpusPath(this.corpusPath);
		tmb.setlDAModelPath(this.lDAModelPath);
		List<TwordsDisplayFormat1Object> tdfoList = tmb.loadTwordsDisplayFormat1(ldaRunDocName);
		tmb = null;
		
		// get key words from table tblkeywordsfortopic
		List<TblKeyWordsForTopic> kwtList = new ArrayList<TblKeyWordsForTopic>();
		kwtList = corpusMgrDAO.loadKeyWordsForTopic(this.corpusPath, ldaRunDocName);
		
		// combine above results into returnlist
		List<NewCorpusTrainResult> returnList = new ArrayList<NewCorpusTrainResult>();
		
		for(int i=0;i<thetaline.length;i++) {
			NewCorpusTrainResult nctr = new NewCorpusTrainResult();
			nctr.setTopicNum(i);
			nctr.setProbability(thetaline[i]);
			nctr.setTopWords(tdfoList.get(i).getTopicnumAndWords());
			for (int j=0;j<kwtList.size();j++) {
					
				if (kwtList.get(j).getKwtpk().getTopicNum().intValue() == i) {
					nctr.setSelectedWords(kwtList.get(j).getKeyWords());
					nctr.setTopicSummary(kwtList.get(j).getTopicLable());
					break;
				}
				
			}
			returnList.add(nctr);
		}
		
		return returnList;
	}
	@Override
	public List<TblReplyJavaRanch> getReplyJavaRanch(int id) {
		return corpusMgrDAO.getReplyJavaRanch(id);
	}
	@Override
	public CorpusTransferObject loadThreadurl(CorpusTransferObject ct) {
		String name = ct.getCorpusDocName();
		String[] s = name.split("-");
		TblReplyJavaRanch rjr = corpusMgrDAO.loadReply(new Integer(s[0]));
		TblThreadJavaRanch tjr = corpusMgrDAO.loadThread(new Integer(s[1].substring(0, s[1].length()-4)));
		ct.setReplyTableKey(new Integer(s[0]));
		ct.setThreadTableKey(tjr.getId());
		ct.setThreadurl(tjr.getThreadURL());
		ct.setReplySequence(rjr.getReplySequenceNum());
		return ct;
	}
	@Override
	public List<TblReplyJavaRanch> getReplyContentWithSourceCode() {
		
		return corpusMgrDAO.getReplyContentWithSourceCode();
	}
	
	

}
