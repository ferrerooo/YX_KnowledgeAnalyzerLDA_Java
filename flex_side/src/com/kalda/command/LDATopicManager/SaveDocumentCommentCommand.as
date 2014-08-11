package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class SaveDocumentCommentCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute SaveDocumentCommentCommand");
			
			//delegate.saveDocumentComment(Models.ldaAnalysisModel.corpusTransferObject);
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