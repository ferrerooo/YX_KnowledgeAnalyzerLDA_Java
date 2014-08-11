package com.kalda.dto
{
	import mx.collections.ArrayCollection;

	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.PhiLine")]
	public class PhiLine
	{
		public var  topicNumber:int;
		public var  list:ArrayCollection = new ArrayCollection(); // <WordInTopic.as> 
		
		public var phiLineMeanValue:Number;
		public var standDeviationValue:Number;
	}
}