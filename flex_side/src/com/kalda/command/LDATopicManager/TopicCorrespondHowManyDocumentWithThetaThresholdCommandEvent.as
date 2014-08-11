package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_TopicCorrespondHowManyDocumentWithThetahresholdCommand:String = "EVENTTYPE_TopicCorrespondHowManyDocumentWithThetahresholdCommand";   
		
		public function TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_TopicCorrespondHowManyDocumentWithThetahresholdCommand, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new TopicCorrespondHowManyDocumentWithThetaThresholdCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}