package com.kalda.business;

import java.util.List;

import com.kalda.domain.TblReplyJavaRanch;
import com.kalda.domain.TblThreadJavaRanch;
import com.kalda.domain.TblThreadStackoverflow;
import com.kalda.domain.TblUser;
import com.kalda.dto.ThetaLine;
import com.kalda.dto.corpus.NewCorpusTrainResult;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;

public interface CorpusMgrBO {
	
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
	
	// used for the frontend -- forum mining
	public List<String> loadAllUsers();
	public List<TblUser> loadAllUsersWithPostAndReply();

	
	// used for the frontend -- new corpus training
	public List<NewCorpusTrainResult> trainNewCorpus(String ldaRunDocName, String newCorpus) ;
	
	// used for the frontend, get Thread URL of a corpus
	public CorpusTransferObject loadThreadurl(CorpusTransferObject ct);


}
