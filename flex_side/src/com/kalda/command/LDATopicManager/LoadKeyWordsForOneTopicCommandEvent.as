package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadKeyWordsForOneTopicCommandEvent extends CommandEvent 
	{
		public static const EVENTTYPE_LOADKEYWORDSFORONE:String = "EVENTTYPE_LOADKEYWORDSFORONE";   
		
		public function LoadKeyWordsForOneTopicCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADKEYWORDSFORONE, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadKeyWordsForOneTopicCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}