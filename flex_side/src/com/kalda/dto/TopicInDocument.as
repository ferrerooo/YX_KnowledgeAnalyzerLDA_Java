package com.kalda.dto
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.TopicInDocument")]
	public class TopicInDocument
	{
		public var  topicNumber:int;
		public var  corpusDocName:String;
		public var  corpusDocId:int;
		public var  topicInDocumentProbability:Number;
		
	}
}