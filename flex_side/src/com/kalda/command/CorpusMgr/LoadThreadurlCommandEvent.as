package com.kalda.command.CorpusMgr
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadThreadurlCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADTHREADURL:String = "EVENTTYPE_LOADTHREADURL"; 
		
		public function LoadThreadurlCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADTHREADURL, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadThreadurlCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}