package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadKeyWordsForTopicCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADKEYWORDS:String = "EVENTTYPE_LOADKEYWORDS";   
		
		public function LoadKeyWordsForTopicCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADKEYWORDS, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadKeyWordsForTopicCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}