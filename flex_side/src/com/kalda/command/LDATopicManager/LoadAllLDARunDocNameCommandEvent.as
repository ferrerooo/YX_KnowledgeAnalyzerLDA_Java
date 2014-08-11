package com.kalda.command.LDATopicManager
{
	
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class LoadAllLDARunDocNameCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADALLLDARUNDOCNAME:String = "EVENTTYPE_LOADALLLDARUNDOCNAME";   
		
		public function LoadAllLDARunDocNameCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADALLLDARUNDOCNAME, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadAllLDARunDocNameCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}