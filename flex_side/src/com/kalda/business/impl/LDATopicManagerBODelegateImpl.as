package com.kalda.business.impl
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.domain.TblCorpusDocument;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	import com.kalda.dto.ldaanalysis.ThetaThreshold;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class LDATopicManagerBODelegateImpl implements ILDATopicManagerBODelegate
	{
		
		private var _responder:IResponder;
		private var _service:RemoteObject;
		
		public function LDATopicManagerBODelegateImpl(responder:IResponder){
			_service = ServiceLocator.getInstance().getRemoteObject("LDATopicManagerServices"); 
			_responder = responder;
		}
		
		public function loadAllLDARunDocName():void {
			var call:AsyncToken = _service.loadAllLDARunDocName();
			call.addResponder(_responder);
		}
		
		public function loadTwordsDisplayFormat1(ldaRunDocName:String):void{
			var call:AsyncToken = _service.loadTwordsDisplayFormat1(ldaRunDocName);
			call.addResponder(_responder);
		}
		
		public function loadThetaWithThreshold(thetaThreshold:ThetaThreshold):void{
			var call:AsyncToken = _service.loadThetaWithThreshold(thetaThreshold);
			call.addResponder(_responder);
		}
		
		public function loadCorpusDocument(corpusTransferObject:CorpusTransferObject):void{
			var call:AsyncToken = _service.loadCorpusDocument(corpusTransferObject);
			call.addResponder(_responder);
		}
		
		public function loadAllCorpusDocName():void{
			var call:AsyncToken = _service.loadAllCorpusDocName();
			call.addResponder(_responder);
		}
		
		public function saveDocumentComment(ct:CorpusTransferObject):void{
			var call:AsyncToken = _service.saveDocumentComment(ct);
			call.addResponder(_responder);
		}
		
		public function topicCorrespondHowManyDocumentWithThetaThreshold(tt:ThetaThreshold):void{
			var call:AsyncToken = _service.topicCorrespondHowManyDocumentWithThetaThreshold(tt);
			call.addResponder(_responder);
		}
		
		public function topicCorrespondDocListWithThetaThreshold(tt:ThetaThreshold, topicNum:int):void{
			var call:AsyncToken = _service.topicCorrespondDocListWithThetaThreshold(tt, topicNum);
			call.addResponder(_responder);
		}
		
		public function loadAPhiLine(topicNum:int, ldaDocName:String):void{
			var call:AsyncToken = _service.loadAPhiLine(topicNum, ldaDocName);
			call.addResponder(_responder);
		}
		
		public function saveKeyWordsForTopic(topicLable:String, keyWords:String, topicNum:int, ldaRunDocName:String):void{
			var call:AsyncToken = _service.saveKeyWordsForTopic(topicLable, keyWords, topicNum, ldaRunDocName);
			call.addResponder(_responder);
		}

		public function loadKeyWordsForTopic(ldaRunDocName:String):void{
			var call:AsyncToken = _service.loadKeyWordsForTopic(ldaRunDocName);
			call.addResponder(_responder);
		}
		
		public function loadKeyWordsForOneTopic(ldaRunDocName:String, topicNum:int):void{
			var call:AsyncToken = _service.loadKeyWordsForOneTopic(ldaRunDocName, topicNum);
			call.addResponder(_responder);
		}
		
		public function quantitativeTopicCorrespondDoc(tt:ThetaThreshold, topicNum:int):void{
			var call:AsyncToken = _service.quantitativeTopicCorrespondDoc(tt, topicNum);
			call.addResponder(_responder);
		}
	}
}