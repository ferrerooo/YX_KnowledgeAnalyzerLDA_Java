package com.kalda.dto
{
	import mx.collections.ArrayCollection;

	// line -> document, column -> topic
	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.Theta")]
	public class Theta
	{
		public var  ldaDocName:String;
		public var  alpah:Number;
		public var  beta:Number;
		public var  ntopics:int;
		public var  niters:int;
		public var  twords:int;
		
		public var lineList:ArrayCollection = new ArrayCollection();  //  <ThetaLine.as>
		public var columnList:ArrayCollection = new ArrayCollection();  // <ThetaColumn.as>
	}
}