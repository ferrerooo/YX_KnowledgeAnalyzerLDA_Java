package com.kalda.model
{
	import com.kalda.dto.PhiLine;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	import com.kalda.dto.ldaanalysis.ThetaThreshold;
	import com.kalda.dto.ldaanalysis.TopicDocumentAmountWithThetaThresholdObject;
	import com.kalda.domain.TblKeyWordsForTopic;
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class LDAModel
	{
		private static var model:LDAModel;
		
		public static function getInstance() : LDAModel {
			if ( model == null )
			{
				model = new LDAModel();
			}        
			return model;
		}
		
		
		/***** Java -> Flex *****/
		public var allLDARunDocName:ArrayCollection = new ArrayCollection(); // <String> 
		public var twordsDisplayFormat1:ArrayCollection = new ArrayCollection(); // <TwordsDisplayFormat1Object>
		public var topicCorrespondDocAmount:ArrayCollection = new ArrayCollection(); // <TopicDocumentAmountWithThetaThresholdObject>
		public var topicCorrespondDocList:ArrayCollection = new ArrayCollection();  // <TopicDocListWithThetaThreshold>
		// 
		public var aPhiLine:PhiLine = new PhiLine();
		// 这个arraycollection完全是为了bar chart显示用。仅仅把aPhiLine中的list做一个反排序
		public var WordInTopicList:ArrayCollection = new ArrayCollection();
		// .phi界面的子界面。显示出所有用户亲自选择的topic words
		public var keyWordsForTopicList:ArrayCollection = new ArrayCollection(); // <TblKeyWordsForTopic>
		
		/***** Flex -> Java *****/
		// BO方法loadTwordsDisplayFormat1用到了 / 存key words时也用到了
		public var ldaRunDocName:String = new String();
		// 在 threshold条件下，计算某个topic对应哪些document，这里记录那个topic号，传给java端。
		public var topicNum:int = 0;
		// load phi matrix的时候，需要从前台得到一个topic num / 存key words时也用到了
		public var topicNumForPhi:int = 0;
		
		/***** Flex <-> Java *****/
		public var thetaThreshold:ThetaThreshold = new ThetaThreshold();
		// thetaThreshold的界面  以及 corpus train的地方都用到了
		public var corpusTransferObject:CorpusTransferObject = new CorpusTransferObject();
		// .phi界面的key words和topic lable
		public var keyWordsForOneTopic:TblKeyWordsForTopic = new TblKeyWordsForTopic();
		
	}
}