package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.kalda.command.LDATopicManager.LoadAllLDARunDocNameCommandEvent;
	import com.kalda.command.LDATopicManager.LoadTwordsDisplayFormat1CommandEvent;
	import com.kalda.command.LDATopicManager.LoadThetaWithThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.LoadCorpusDocumentCommandEvent;
	import com.kalda.command.LDATopicManager.LoadAllCorpusDocNameCommandEvent;
	import com.kalda.command.LDATopicManager.SaveDocumentCommentCommandEvent;
	import com.kalda.command.LDATopicManager.TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.TopicCorrespondDocListWithThetaThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.LoadAPhiLineCommandEvent;
	import com.kalda.command.LDATopicManager.SaveKeyWordsForTopicCommandEvent;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForTopicCommandEvent;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForOneTopicCommandEvent;
	import com.kalda.command.LDATopicManager.QuantitativeTopicCorrespondDocCommandEvent;
	
	public class LDATopicManagerControl
	{
		
		private static const WAITMSG_LOADALLLDARUNDOCNAME:String = "Loading LDA Runs...";
		private static const WAITMSG_LOADTOPWORDSINTOPIC:String = "Loading Top Words in Topics...";
		private static const WAITMSG_LOADTHETA:String = "Loading Theta Matrix...";
		private static const WAITMSG_LOADCORPUS:String = "Loading Corpus Content...";
		private static const WAITMSG_LOADALLCORPUSNAME:String = "Loading Corpus List...";
		private static const WAITMSG_SAVECOMMENT:String = "Saving Comments...";
		private static const WAITMSG_TopicCorrespondHowManyDocumentWithThetaThreshold:String = "Loading Corpus Document Amount for each Topic...";
		private static const WAITMSG_TopicCorrespondDocListWithThetaThreshold:String = "Loading Corpus Document List for selected Topic...";
		private static const WAITMSG_LOADAPHILINE:String = "Loading Words In Phi Matrix...";
		private static const WAITMSG_SAVEKEYWORDS:String = "Saving Key Words...";
		private static const WAITMSG_LOADKEYWORDS:String = "Loading Key Words...";
		private static const WAITMSG_LOADKEYWORDSFORONETOPIC:String = "Loading Key Words For Current Topic...";
		private static const WAITMSG_QuantitativeTopicCorrespondDoc:String = "Loading Corpus Document List for selected Topic...";
		
		public static function loadAllLDARunDocName() :void {
			
			var cmdEvt:LoadAllLDARunDocNameCommandEvent = new LoadAllLDARunDocNameCommandEvent( 
				true, WAITMSG_LOADALLLDARUNDOCNAME);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		} 
		
		public static function loadTwordsDisplayFormat1() :void {
			
			var cmdEvt:LoadTwordsDisplayFormat1CommandEvent = new LoadTwordsDisplayFormat1CommandEvent( 
				true, WAITMSG_LOADTOPWORDSINTOPIC);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		} 
		
		public static function loadThetaWithThreshold() :void {
			
			var cmdEvt:LoadThetaWithThresholdCommandEvent = new LoadThetaWithThresholdCommandEvent( 
				true, WAITMSG_LOADTHETA);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadCorpusDocument() :void {
			
			var cmdEvt:LoadCorpusDocumentCommandEvent = new LoadCorpusDocumentCommandEvent( 
				true, WAITMSG_LOADCORPUS);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadAllCorpusDocName() :void {
			
			var cmdEvt:LoadAllCorpusDocNameCommandEvent = new LoadAllCorpusDocNameCommandEvent( 
				true, WAITMSG_LOADALLCORPUSNAME);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function saveDocumentComment() :void {
			
			var cmdEvt:SaveDocumentCommentCommandEvent = new SaveDocumentCommentCommandEvent( 
				true, WAITMSG_SAVECOMMENT);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function topicCorrespondHowManyDocumentWithThetaThresholdCommand() :void {
			
			var cmdEvt:TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent = new TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent( 
				true, WAITMSG_TopicCorrespondHowManyDocumentWithThetaThreshold);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function topicCorrespondDocListWithThetaThresholdCommand() :void {
			
			var cmdEvt:TopicCorrespondDocListWithThetaThresholdCommandEvent = new TopicCorrespondDocListWithThetaThresholdCommandEvent( 
				true, WAITMSG_TopicCorrespondDocListWithThetaThreshold);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadAPhiLine() :void {
			
			var cmdEvt:LoadAPhiLineCommandEvent = new LoadAPhiLineCommandEvent( 
				true, WAITMSG_LOADAPHILINE);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function saveKeyWordsForTopic() :void {
			
			var cmdEvt:SaveKeyWordsForTopicCommandEvent = new SaveKeyWordsForTopicCommandEvent( 
				true, WAITMSG_SAVEKEYWORDS);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadKeyWordsForTopic() :void {
			
			var cmdEvt:LoadKeyWordsForTopicCommandEvent = new LoadKeyWordsForTopicCommandEvent( 
				true, WAITMSG_LOADKEYWORDS);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadKeyWordsForOneTopic() :void {
			
			var cmdEvt:LoadKeyWordsForOneTopicCommandEvent = new LoadKeyWordsForOneTopicCommandEvent( 
				true, WAITMSG_LOADKEYWORDSFORONETOPIC);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function QuantitativeTopicCorrespondDoc() :void {
			
			var cmdEvt:QuantitativeTopicCorrespondDocCommandEvent = new QuantitativeTopicCorrespondDocCommandEvent( 
				true, WAITMSG_QuantitativeTopicCorrespondDoc);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
	}
}