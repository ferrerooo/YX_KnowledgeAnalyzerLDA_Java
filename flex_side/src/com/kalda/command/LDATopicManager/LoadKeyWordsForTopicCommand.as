package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.ldaanalysis.ThetaThreshold;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadKeyWordsForTopicCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute LoadThetaWithThresholdCommand");
			
			delegate.loadKeyWordsForTopic(Models.ldaModel.ldaRunDocName);
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.keyWordsForTopicList = data.result as ArrayCollection;
			
			for (var i:int=0;i<Models.ldaModel.keyWordsForTopicList.length;i++) {
				Models.ldaModel.keyWordsForTopicList.getItemAt(i).tn = 
					Models.ldaModel.keyWordsForTopicList.getItemAt(i).kwtpk.topicNum;
			}
			
		}
	}
}