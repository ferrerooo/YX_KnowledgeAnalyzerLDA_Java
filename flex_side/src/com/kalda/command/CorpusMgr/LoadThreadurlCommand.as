package com.kalda.command.CorpusMgr
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.business.ICorpusMgrBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;
	import com.kalda.model.Models;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadThreadurlCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ICorpusMgrBODelegate = DelegateFactory.getCorpusMgrBODelegate(this);
			//Alert.show("come here serialExecute TrainNewCorpusCommand");
			
			delegate.loadThreadurl(Models.ldaModel.corpusTransferObject);
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.corpusTransferObject = data.result as CorpusTransferObject;
			Alert.show(Models.ldaModel.corpusTransferObject.threadurl);
		}
	}
}