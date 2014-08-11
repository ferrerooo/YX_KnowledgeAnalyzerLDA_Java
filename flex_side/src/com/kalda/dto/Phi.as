package com.kalda.dto
{
	import mx.collections.ArrayCollection;

	// line -> topic, column -> word
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.Phi")]
	public class Phi
	{
		public var  ldaDocName:String;
		public var  alpah:Number;
		public var  beta:Number;
		public var  ntopics:int;
		public var  niters:int;
		public var  twords:int;
		
		public var  lineList:ArrayCollection = new ArrayCollection();  // <PhiLine.as>
	}
}