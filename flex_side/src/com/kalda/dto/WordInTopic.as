package com.kalda.dto
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.WordInTopic")]
	public class WordInTopic
	{
		public var  wordNum:int; // this is the number in wordmap.txt
		public var  word:String;
		public var  wordInTopicProbability:Number;
	}
}