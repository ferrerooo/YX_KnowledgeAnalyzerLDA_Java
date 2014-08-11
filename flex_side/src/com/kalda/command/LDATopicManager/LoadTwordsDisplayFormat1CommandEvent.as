package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class LoadTwordsDisplayFormat1CommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADTWORDS:String = "EVENTTYPE_LOADTWORDS";   
		
		public function LoadTwordsDisplayFormat1CommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADTWORDS, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadTwordsDisplayFormat1CommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}