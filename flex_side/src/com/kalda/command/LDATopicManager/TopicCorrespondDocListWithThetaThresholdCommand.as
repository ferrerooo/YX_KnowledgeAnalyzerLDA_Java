package com.kalda.command.LDATopicManager
{
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.kalda.business.DelegateFactory;
	import com.kalda.business.ILDATopicManagerBODelegate;
	import com.kalda.command.SerialCommand;
	import com.kalda.model.Models;
	
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class TopicCorrespondDocListWithThetaThresholdCommand extends SerialCommand
	{
		override public function serialExecute(event:CairngormEvent):void{
			var delegate:ILDATopicManagerBODelegate = DelegateFactory.getLDATopicManagerBODelegate(this);
			//Alert.show("come here serialExecute TopicCorrespondDocListWithThetaThresholdCommand");
			
			delegate.topicCorrespondDocListWithThetaThreshold(Models.ldaModel.thetaThreshold, Models.ldaModel.topicNum);
		}
		
		override public function serialResult(data:Object):void{
			
			var ac:ArrayCollection = data.result as ArrayCollection;
			
			Models.ldaModel.topicCorrespondDocList = ac;
			
			//Models.ldaModel.topicCorrespondDocAmount = ac;
			
		}
	}
}