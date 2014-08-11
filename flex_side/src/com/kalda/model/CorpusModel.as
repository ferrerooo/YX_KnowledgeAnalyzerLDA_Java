package com.kalda.model
{
	import mx.collections.ArrayCollection;

	[Bindable]
	public class CorpusModel
	{
		private static var model:CorpusModel;
		
		public static function getInstance() : CorpusModel {
			if ( model == null )
			{
				model = new CorpusModel();
			}        
			return model;
		}
		
		/***** Flex -> Java *****/
		public var newCorpus:String = new String();
		
		/***** Java -> Flex *****/
		public var userList:ArrayCollection = new ArrayCollection();  // <TblUser>
		
		public var newCorpusTrainResultList:ArrayCollection = new ArrayCollection(); // <NewCorpusTrainResult.as>
	}
}