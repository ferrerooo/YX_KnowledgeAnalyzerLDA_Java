package com.kalda.business
{
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;

	public interface ICorpusMgrBODelegate
	{
		function loadAllUsersWithPostAndReply():void;
		
		function trainNewCorpus(ldaRunDocName:String, newCorpus:String):void ;
		
		function loadThreadurl(ct:CorpusTransferObject):void;
	}
}