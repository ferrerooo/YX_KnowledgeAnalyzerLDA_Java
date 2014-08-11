package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadAPhiLineCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADAPHILINE:String = "EVENTTYPE_LOADAPHILINE";   
		
		public function LoadAPhiLineCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADAPHILINE, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadAPhiLineCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}