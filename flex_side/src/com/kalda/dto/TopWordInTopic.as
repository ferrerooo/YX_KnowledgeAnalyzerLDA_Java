package com.kalda.dto
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.TopWordInTopic")]
	public class TopWordInTopic
	{
		public var topicNumber:int;
		public var word:String;
		public var wordInTopicProbability:Number;
		
		public var  ldaDocName:String;
		public var  alpah:Number;
		public var  beta:Number;
		public var  ntopics:int;
		public var  niters:int;
		public var  twords:int;
	}
}