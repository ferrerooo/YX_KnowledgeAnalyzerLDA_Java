package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.PhiLine;
	import com.kalda.dto.ldaanalysis.CorpusTransferObject;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadAPhiLineCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			//var ct:CorpusTransferObject = Models.ldaModel.corpusTransferObject;
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show(Models.ldaModel.topicNumForPhi + Models.ldaModel.ldaRunDocName);
			
			delegate.loadAPhiLine(Models.ldaModel.topicNumForPhi, Models.ldaModel.ldaRunDocName);
		}
		
		override public function serialResult(data:Object):void{
			Models.ldaModel.aPhiLine = data.result as PhiLine;
			
			var list:ArrayCollection = Models.ldaModel.aPhiLine.list;
			Models.ldaModel.WordInTopicList = new ArrayCollection();
			for (var i:int=0;i<list.length;i++) {
				Models.ldaModel.WordInTopicList.addItemAt(list.getItemAt(i), 0);
			}
			
		}
	}
}