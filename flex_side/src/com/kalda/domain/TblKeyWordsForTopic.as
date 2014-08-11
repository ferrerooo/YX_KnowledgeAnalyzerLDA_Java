package com.kalda.domain
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.domain.TblKeyWordsForTopic")]
	public class TblKeyWordsForTopic
	{
		public var  kwtpk:TblKeyWordsForTopicPK;
		public var  topicLable:String;
		public var  keyWords:String;
		
		public var  tn:int; // topicNumber
	}
}