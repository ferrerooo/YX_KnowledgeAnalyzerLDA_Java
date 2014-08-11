package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.model.Models;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadCorpusDocumentCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var ct:CorpusTransferObject = Models.ldaModel.corpusTransferObject;
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show(ct.corpusDocName);
			
			delegate.loadCorpusDocument(ct);
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.corpusTransferObject = data.result as CorpusTransferObject;
		}
	}
}