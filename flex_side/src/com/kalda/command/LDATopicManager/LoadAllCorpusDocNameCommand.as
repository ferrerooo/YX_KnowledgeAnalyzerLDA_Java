package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.dto.ldaanalysis.CorpusnameAndTopicamountcontained;
	import com.kalda.model.Models;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoadAllCorpusDocNameCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute LoadAllCorpusDocNameCommand");
			
			delegate.loadAllCorpusDocName();
		}
		
		override public function serialResult(data:Object):void{
			//Models.ldaAnalysisModel.allCorpusDocNamesAndTopicAmount = data.result as ArrayCollection;
			
			// calculate 'Models.ldaAnalysisModel.allCorpusDocNamesAndTopicAmount'. 
			// use this to replace 'Models.ldaAnalysisModel.allCorpusDocNames' for data display
//			for (var i:int=0;i<Models.ldaAnalysisModel.allCorpusDocNames.length;i++) {
//				var amount:int = 0;
//				for (var j:int=0;j<Models.ldaAnalysisModel.thetaThreshold.list.length;j++) {
//					if (Models.ldaAnalysisModel.allCorpusDocNames.getItemAt(i) == Models.ldaAnalysisModel.thetaThreshold.list.getItemAt(j).corpusDocName) {
//						amount++;
//					}
//				}
//				var cnp:CorpusnameAndTopicamountcontained = new CorpusnameAndTopicamountcontained();
//				cnp.name = Models.ldaAnalysisModel.allCorpusDocNames.getItemAt(i) as String;
//				cnp.topicAmount = amount;
//				Models.ldaAnalysisModel.allCorpusDocNamesAndTopicAmount.addItem(cnp);
//			}
			
		}
	}
}