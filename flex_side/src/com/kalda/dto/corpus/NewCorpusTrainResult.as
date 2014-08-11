package com.kalda.dto.corpus
{
	public class NewCorpusTrainResult
	{
		public var topicNum:int;
		public var probability:Number;
		public var topWords:String;  // key words in .twords file
		public var selectedWords:String;  // key words manually selected
		public var topicSummary:String;
	}
}