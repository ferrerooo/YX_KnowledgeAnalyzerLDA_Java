package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class TopicCorrespondDocListWithThetaThresholdCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_TopicCorrespondDocListWithThetaThresholdCommand:String = "EVENTTYPE_TopicCorrespondDocListWithThetaThresholdCommand";   
		
		public function TopicCorrespondDocListWithThetaThresholdCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_TopicCorrespondDocListWithThetaThresholdCommand, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new TopicCorrespondDocListWithThetaThresholdCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}