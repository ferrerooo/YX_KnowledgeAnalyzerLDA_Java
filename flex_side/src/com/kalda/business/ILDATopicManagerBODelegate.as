package com.kalda.business
{
	
	import com.kalda.domain.TblCorpusDocument;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	import com.kalda.dto.ldaanalysis.ThetaThreshold;
	
	import mx.collections.ArrayCollection;
	
	public interface ILDATopicManagerBODelegate
	{
		function loadAllLDARunDocName():void;
		
		function loadTwordsDisplayFormat1(ldaRunDocName:String):void;
		
		function loadThetaWithThreshold(thetaThreshold:ThetaThreshold):void;
		
		function loadCorpusDocument(corpusTransferObject:CorpusTransferObject):void;
		
		function loadAllCorpusDocName():void;
		
		function saveDocumentComment(ct:CorpusTransferObject):void;
		
		function topicCorrespondHowManyDocumentWithThetaThreshold(thetaThreshold:ThetaThreshold):void;
		
		function topicCorrespondDocListWithThetaThreshold(thetaThreshold:ThetaThreshold, topicNum:int):void;
		
		function loadAPhiLine(topicNum:int, ldaDocName:String):void;
		
		function saveKeyWordsForTopic(topicLable:String, keyWords:String, topicNum:int, ldaRunDocName:String):void;
	
		function loadKeyWordsForTopic(ldaRunDocName:String):void;
		
		function loadKeyWordsForOneTopic(ldaRunDocName:String, topicNum:int):void;
		
		function quantitativeTopicCorrespondDoc(thetaThreshold:ThetaThreshold, topicNum:int):void;
	
	}
}