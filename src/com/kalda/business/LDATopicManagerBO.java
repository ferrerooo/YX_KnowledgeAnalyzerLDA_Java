package com.kalda.business;

import java.util.List;

import com.kalda.domain.TblCorpusDocument;
import com.kalda.dto.LDARunObject;
import com.kalda.dto.ldaanalysis.CorpusTransferObject;
import com.kalda.dto.ldaanalysis.ThetaThreshold;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;
import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;

public interface LDATopicManagerBO {
	
	// return only file name, no postfix. e.g. "model ---- alpha-0.5 beta-0.1 ntopics-66 niters-02000 twords-20"
	public List<String> loadAllLDARunDocName();
	 
	// return info in twords matrix. string format of topic. [topic 1 - aaa, bbb, ccc...]
	public List<TwordsDisplayFormat1Object> loadTwordsDisplayFormat1(String ldaRunDocName);
	
	// to find important topics in document OR high related documents of one topic
	public ThetaThreshold loadThetaWithThreshold(ThetaThreshold tt);
	
	// get all corpus document names
	public List<String> loadAllCorpusDocName();
	
	// to get the corpusDocName, corpusDocContent, corpusDocComment
	public CorpusTransferObject loadCorpusDocument(CorpusTransferObject ct);
	
	public boolean saveDocumentComment(CorpusTransferObject ct);

}
