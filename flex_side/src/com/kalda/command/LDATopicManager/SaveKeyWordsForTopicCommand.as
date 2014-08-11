package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class SaveKeyWordsForTopicCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			
			delegate.saveKeyWordsForTopic(	//Models.ldaModel.topicLable, 
											//Models.ldaModel.chooseWords, 
											Models.ldaModel.keyWordsForOneTopic.topicLable,
											Models.ldaModel.keyWordsForOneTopic.keyWords,
											Models.ldaModel.topicNumForPhi, 
											Models.ldaModel.ldaRunDocName);
		}
		
		override public function serialResult(data:Object):void{
			var b:Boolean = data.result as Boolean;
			if (b==true) {
				Alert.show("Save Successful");
			} else {
				Alert.show("Save NOT Successful");
			}
		}
	}
}