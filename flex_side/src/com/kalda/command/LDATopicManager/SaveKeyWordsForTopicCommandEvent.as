package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class SaveKeyWordsForTopicCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_SAVEKEYWORDS:String = "EVENTTYPE_SAVEKEYWORDS";   
		
		public function SaveKeyWordsForTopicCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_SAVEKEYWORDS, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new SaveKeyWordsForTopicCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}