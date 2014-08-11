package com.kalda.dto.ldaanalysis
{
	
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold")]
	public class TopicDocListWithThetaThreshold
	{
		public var docNum:int;
		public var docName:String;
		public var topicProbabilityForThisDoc:Number;
	}
}