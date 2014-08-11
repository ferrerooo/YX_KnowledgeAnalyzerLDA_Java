package com.kalda.dto
{
	import mx.collections.ArrayCollection;

	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.Twords")]
	public class Twords
	{
		public var  ldaDocName:String;
		public var  alpah:Number;
		public var  beta:Number;
		public var  ntopics:int;
		public var  niters:int;
		public var  twords:int;
		
		public var list:ArrayCollection = new ArrayCollection(); // <TopWordInTopic.as>
	}
}