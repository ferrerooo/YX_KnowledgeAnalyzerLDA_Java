package com.kalda.command.CorpusMgr
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class LoadAllUsersWithPostAndReplyCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADUSER:String = "EVENTTYPE_LOADUSER"; 
		
		public function LoadAllUsersWithPostAndReplyCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADUSER, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadAllUsersWithPostAndReplyCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}