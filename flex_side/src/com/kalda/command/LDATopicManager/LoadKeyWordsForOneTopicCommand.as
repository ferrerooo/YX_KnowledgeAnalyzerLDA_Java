package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.ldaanalysis.ThetaThreshold;
	import com.kalda.model.Models;
	import com.kalda.domain.TblKeyWordsForTopic;
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadKeyWordsForOneTopicCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute LoadThetaWithThresholdCommand");
			
			delegate.loadKeyWordsForOneTopic(Models.ldaModel.ldaRunDocName, Models.ldaModel.topicNumForPhi);
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.keyWordsForOneTopic = data.result as TblKeyWordsForTopic;
			
			if (Models.ldaModel.keyWordsForOneTopic == null) {
				Alert.show("No summary and key words saved for this topic before!");
			}
			
		}
	}
}