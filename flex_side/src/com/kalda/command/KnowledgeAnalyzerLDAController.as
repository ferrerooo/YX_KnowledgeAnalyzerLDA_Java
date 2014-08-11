package com.kalda.command
{
	
	import com.adobe.cairngorm.control.FrontController;
	import com.kalda.command.LDATopicManager.LoadAllLDARunDocNameCommand;
	import com.kalda.command.LDATopicManager.LoadAllLDARunDocNameCommandEvent;
	import com.kalda.command.LDATopicManager.LoadTwordsDisplayFormat1Command;
	import com.kalda.command.LDATopicManager.LoadTwordsDisplayFormat1CommandEvent;
	import com.kalda.command.LDATopicManager.LoadThetaWithThresholdCommand;
	import com.kalda.command.LDATopicManager.LoadThetaWithThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.LoadCorpusDocumentCommand;
	import com.kalda.command.LDATopicManager.LoadCorpusDocumentCommandEvent;
	import com.kalda.command.LDATopicManager.LoadAllCorpusDocNameCommand;
	import com.kalda.command.LDATopicManager.LoadAllCorpusDocNameCommandEvent;
	import com.kalda.command.LDATopicManager.SaveDocumentCommentCommand;
	import com.kalda.command.LDATopicManager.SaveDocumentCommentCommandEvent;
	import com.kalda.command.LDATopicManager.TopicCorrespondHowManyDocumentWithThetaThresholdCommand;
	import com.kalda.command.LDATopicManager.TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.TopicCorrespondDocListWithThetaThresholdCommand;
	import com.kalda.command.LDATopicManager.TopicCorrespondDocListWithThetaThresholdCommandEvent;
	import com.kalda.command.LDATopicManager.LoadAPhiLineCommand;
	import com.kalda.command.LDATopicManager.LoadAPhiLineCommandEvent;
	import com.kalda.command.LDATopicManager.SaveKeyWordsForTopicCommand;
	import com.kalda.command.LDATopicManager.SaveKeyWordsForTopicCommandEvent;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForTopicCommand;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForTopicCommandEvent;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForOneTopicCommand;
	import com.kalda.command.LDATopicManager.LoadKeyWordsForOneTopicCommandEvent;
	import com.kalda.command.LDATopicManager.QuantitativeTopicCorrespondDocCommand;
	import com.kalda.command.LDATopicManager.QuantitativeTopicCorrespondDocCommandEvent;
	
	import com.kalda.command.CorpusMgr.LoadAllUsersWithPostAndReplyCommand;
	import com.kalda.command.CorpusMgr.LoadAllUsersWithPostAndReplyCommandEvent;
	import com.kalda.command.CorpusMgr.TrainNewCorpusCommand;
	import com.kalda.command.CorpusMgr.TrainNewCorpusCommandEvent;
	import com.kalda.command.CorpusMgr.LoadThreadurlCommand;
	import com.kalda.command.CorpusMgr.LoadThreadurlCommandEvent;
	
	public class KnowledgeAnalyzerLDAController extends FrontController
	{
		public function KnowledgeAnalyzerLDAController()
		{
			initializeCommands();
		}
		
		public function initializeCommands() : void
		{
			this.addCommand(LoadAllLDARunDocNameCommandEvent.EVENTTYPE_LOADALLLDARUNDOCNAME, LoadAllLDARunDocNameCommand);
			this.addCommand(LoadTwordsDisplayFormat1CommandEvent.EVENTTYPE_LOADTWORDS, LoadTwordsDisplayFormat1Command);
			this.addCommand(LoadThetaWithThresholdCommandEvent.EVENTTYPE_LOADTHETA, LoadThetaWithThresholdCommand);
			this.addCommand(LoadCorpusDocumentCommandEvent.EVENTTYPE_LOADCORPUS, LoadCorpusDocumentCommand);
			this.addCommand(LoadAllCorpusDocNameCommandEvent.EVENTTYPE_LOADALLLCORPUSNAME, LoadAllCorpusDocNameCommand);
			this.addCommand(SaveDocumentCommentCommandEvent.EVENTTYPE_SAVECOMMENT, SaveDocumentCommentCommand);
			this.addCommand(TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent.EVENTTYPE_TopicCorrespondHowManyDocumentWithThetahresholdCommand, TopicCorrespondHowManyDocumentWithThetaThresholdCommand);
			this.addCommand(TopicCorrespondDocListWithThetaThresholdCommandEvent.EVENTTYPE_TopicCorrespondDocListWithThetaThresholdCommand, TopicCorrespondDocListWithThetaThresholdCommand);
			this.addCommand(LoadAPhiLineCommandEvent.EVENTTYPE_LOADAPHILINE, LoadAPhiLineCommand);
			this.addCommand(SaveKeyWordsForTopicCommandEvent.EVENTTYPE_SAVEKEYWORDS, SaveKeyWordsForTopicCommand);
			this.addCommand(LoadKeyWordsForTopicCommandEvent.EVENTTYPE_LOADKEYWORDS, LoadKeyWordsForTopicCommand);
			this.addCommand(LoadKeyWordsForOneTopicCommandEvent.EVENTTYPE_LOADKEYWORDSFORONE, LoadKeyWordsForOneTopicCommand);
			this.addCommand(QuantitativeTopicCorrespondDocCommandEvent.EVENTTYPE_QuantitativeTopicCorrespondDocCommand, QuantitativeTopicCorrespondDocCommand);
			
			this.addCommand(LoadAllUsersWithPostAndReplyCommandEvent.EVENTTYPE_LOADUSER, LoadAllUsersWithPostAndReplyCommand);
			this.addCommand(TrainNewCorpusCommandEvent.EVENTTYPE_TRAINNEW, TrainNewCorpusCommand);
			this.addCommand(LoadThreadurlCommandEvent.EVENTTYPE_LOADTHREADURL, LoadThreadurlCommand);
		}
	}
}