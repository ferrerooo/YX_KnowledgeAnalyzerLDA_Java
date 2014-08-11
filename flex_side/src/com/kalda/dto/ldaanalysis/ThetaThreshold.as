package com.kalda.dto.ldaanalysis
{
	import mx.collections.ArrayCollection;

	[Bindable] 
	[RemoteClass(alias="com.kalda.dto.ldaanalysis.ThetaThreshold")]
	public class ThetaThreshold
	{
		public var ldaRunDocName:String;   // send to java
		
		public var totalGridAmount:int=0;
		public var totalCorpusDocAmount:int=0;
		
		public var note:String;
		public var selectedGridAmount:int=0;
		public var selectedCorpusDocAmount:int=0;
		
		public var total:Boolean;  // send to java
		
		public var percentile:Boolean;  // send to java
		public var percentNum:Number = 0;  // send to java
		public var percentThreshold:Number;
		
		public var deviation:Boolean;   // send to java
		public var kValue:int = 0;    // send to java
		public var meanValue:Number = 0;
		public var standDeviationValue:Number = 0;
		public var deviationValue:Number = 0; // this value is what we want: meanValue + key X standDeviationValue. 	
		
	}
}