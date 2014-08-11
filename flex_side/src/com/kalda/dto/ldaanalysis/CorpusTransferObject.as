package com.kalda.dto.ldaanalysis
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ldaanalysis.CorpusTransferObject")]
	public class CorpusTransferObject
	{
		public var id:int;
		
		public var corpusDocName:String;
		public var corpusDocContent:String;
		public var corpusDocComment:String;
		
		public var replyTableKey:int;
		public var threadTableKey:int;
		public var threadurl:String;
		public var replySequence:int;
	}
}