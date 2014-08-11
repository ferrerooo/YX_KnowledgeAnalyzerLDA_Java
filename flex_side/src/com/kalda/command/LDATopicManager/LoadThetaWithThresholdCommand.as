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
	
	public class LoadThetaWithThresholdCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute LoadThetaWithThresholdCommand");
			var thetaThreshold:ThetaThreshold = Models.ldaModel.thetaThreshold;
			delegate.loadThetaWithThreshold(Models.ldaModel.thetaThreshold);
			
		}
		
		override public function serialResult(data:Object):void{
			var tt:ThetaThreshold = new ThetaThreshold();
			tt = data.result as ThetaThreshold;
			Models.ldaModel.thetaThreshold = tt;
			
			
			
			
			// add 'thetaCorpusAmount' info to TwordsDisplayFormat1Object.as
			//Models.ldaAnalysisModel.topicTableOfTopicToCorpus = null;
			
			//for (var i:int=0;i<Models.ldaAnalysisModel.twordsDisplayFormat1.length;i++) {
			//	var amount:int = 0;
			//	Models.ldaAnalysisModel.twordsDisplayFormat1.getItemAt(i).thetaCorpusAmount = amount;				
			//}
			//Models.ldaAnalysisModel.topicTableOfTopicToCorpus = Models.ldaAnalysisModel.twordsDisplayFormat1;
			//var model:LDAAnalysisModel = Models.ldaAnalysisModel;
			trace("aa");
			
			
		}
	}
}