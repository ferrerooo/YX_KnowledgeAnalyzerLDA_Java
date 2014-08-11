package com.kalda.command.CorpusMgr
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.business.ICorpusMgrBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
		
	public class LoadAllUsersWithPostAndReplyCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ICorpusMgrBODelegate = DelegateFactory.getCorpusMgrBODelegate(this);
			//Alert.show("come here serialExecute LoadAllUsersWithPostAndReplyCommand");
			
			delegate.loadAllUsersWithPostAndReply();
		}
		
		override public function serialResult(data:Object):void{
			Models.corpusModel.userList = data.result as ArrayCollection;
		}
	}
}