package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadAllLDARunDocNameCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute SaveDocumentCommentCommand");
			
			delegate.loadAllLDARunDocName();
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.allLDARunDocName = data.result as ArrayCollection;
			//trace("aa");
		}
	}
}