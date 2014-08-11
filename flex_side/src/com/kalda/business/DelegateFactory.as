package com.kalda.business
{
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.business.impl.LDATopicManagerBODelegateImpl;
	import com.kalda.business.ICorpusMgrBODelegate;
	import com.kalda.business.impl.CorpusMgrBODelegateImpl;
	
	import mx.rpc.IResponder;
  /**
   * The DelegateFactory is responsible for providing the 
   * implementation for the business delegates. Stub implementations
   * are provided when hostURL parameter (supplied by the flex object wrapper jsp)
   * is not available
   */
	public class DelegateFactory
	{
		public static function getLDATopicManagerBODelegate(responder:IResponder):ILDATopicManagerBODelegate{
			return new LDATopicManagerBODelegateImpl(responder);
		}
		
		public static function getCorpusMgrBODelegate(responder:IResponder):ICorpusMgrBODelegate{
			return new CorpusMgrBODelegateImpl(responder);
		}
	}
}