package com.kalda.dto.ldaanalysis
{
	
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained")]
	public class CorpusnameAndTopicamountcontained
	{
		public var docSeqId:int;
		public var name:String;
		public var topicAmount:int;
		
		public var deviationOfTopicAmount:Number=0;
		public var meanOfTopicAmount:Number=0;
	}
}