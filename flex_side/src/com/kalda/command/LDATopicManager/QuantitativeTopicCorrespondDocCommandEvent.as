package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class QuantitativeTopicCorrespondDocCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_QuantitativeTopicCorrespondDocCommand:String = "EVENTTYPE_QuantitativeTopicCorrespondDocCommand";   
		
		public function QuantitativeTopicCorrespondDocCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_QuantitativeTopicCorrespondDocCommand, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new QuantitativeTopicCorrespondDocCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}