package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadThetaWithThresholdCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADTHETA:String = "EVENTTYPE_LOADTHETA";   
		
		public function LoadThetaWithThresholdCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADTHETA, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadThetaWithThresholdCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}