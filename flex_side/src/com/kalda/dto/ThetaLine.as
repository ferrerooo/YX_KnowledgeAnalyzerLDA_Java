package com.kalda.dto
{
	import mx.collections.ArrayCollection;
	
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ThetaLine")]
	public class ThetaLine
	{
		public var corpusDocName:String;
		
		
		public var  list:ArrayCollection = new ArrayCollection(); // <TopicInDocument.as>
	}
}