package com.kalda.business.impl
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.kalda.business.ICorpusMgrBODelegate;
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	
	public class CorpusMgrBODelegateImpl implements ICorpusMgrBODelegate
	{
		private var _responder:IResponder;
		private var _service:RemoteObject;
		
		public function CorpusMgrBODelegateImpl(responder:IResponder)
		{
			_service = ServiceLocator.getInstance().getRemoteObject("CorpusMgrServices"); 
			_responder = responder;
		}
		
		public function loadAllUsersWithPostAndReply():void
		{
			var call:AsyncToken = _service.loadAllUsersWithPostAndReply();
			call.addResponder(_responder);
		}
		
		public function trainNewCorpus(ldaRunDocName:String, newCorpus:String):void {
			var call:AsyncToken = _service.trainNewCorpus(ldaRunDocName, newCorpus);
			call.addResponder(_responder);
		}
		
		public function loadThreadurl(ct:CorpusTransferObject):void {
			var call:AsyncToken = _service.loadThreadurl(ct);
			call.addResponder(_responder);
		}
	}
}