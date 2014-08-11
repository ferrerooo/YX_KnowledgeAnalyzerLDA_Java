package com.kalda.dto
{
	import mx.collections.ArrayCollection;

	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ThetaColumn")]
	public class ThetaColumn
	{
		public var  topicNumber:int;

		
		public var  list:ArrayCollection<TopicInDocument> = new ArrayCollection<TopicInDocument>(); // <TopicInDocument.as>
	}
}