package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class LoadAllCorpusDocNameCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADALLLCORPUSNAME:String = "EVENTTYPE_LOADALLLCORPUSNAME";   
		
		public function LoadAllCorpusDocNameCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADALLLCORPUSNAME, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadAllCorpusDocNameCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}