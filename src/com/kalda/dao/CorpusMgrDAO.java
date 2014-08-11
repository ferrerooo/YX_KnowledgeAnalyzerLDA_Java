package com.kalda.dao;

import java.util.List;

import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.domain.TblReplyJavaRanch;
import com.kalda.domain.TblThreadJavaRanch;
import com.kalda.domain.TblThreadStackoverflow;
import com.kalda.domain.TblUser;

public interface CorpusMgrDAO {

	// used for backend, prepare java ranch forum data
	public boolean saveThreadJavaRanch(TblThreadJavaRanch tjr);	
	public TblThreadJavaRanch getThreadJavaRanch(int id);	
	public boolean saveReplyJavaRanch(TblReplyJavaRanch rjr);
	public boolean saveUserJavaRanch(List<TblUser> userList);
	public boolean updateUserJavaRanchForPostAmountAndReplyAmount(List<TblUser> userList);
	public List<TblReplyJavaRanch> getReplyJavaRanch(TblUser u);
	public List<TblReplyJavaRanch> getReplyJavaRanch();
	public List<TblReplyJavaRanch> getReplyJavaRanch(int id);
	
	// used for backend, prepare stackoverflow forum data
	public boolean saveThreadStackoverflow(TblThreadStackoverflow tsf);	
	public TblThreadStackoverflow getThreadStackoverflow(int id);
	
	// used for backend, database analysis
	public List<TblReplyJavaRanch> getReplyContentWithSourceCode();
	
	// used for the frontend
	public List<String> loadAllUsers();
	public List<TblUser> loadAllUsersWithPostAndReply();
	
	// used for the frontend -- new corpus training
	public List<TblKeyWordsForTopic> loadKeyWordsForTopic(String whichCorpus, String whichLDARun);
	
	// used for the frontend, get Thread URL of a corpus
	public TblReplyJavaRanch loadReply(int replyId);
	public TblThreadJavaRanch loadThread(int threadId);
}
