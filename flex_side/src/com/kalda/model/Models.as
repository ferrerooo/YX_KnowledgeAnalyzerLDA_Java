package com.kalda.model
{
	
	import com.adobe.cairngorm.model.ModelLocator;
	
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class Models extends EventDispatcher implements ModelLocator 
	{
			
		public static var ldaModel:LDAModel = LDAModel.getInstance();		
		public static var corpusModel:CorpusModel = CorpusModel.getInstance();
		
	}
}