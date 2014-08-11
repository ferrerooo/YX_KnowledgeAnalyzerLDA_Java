package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class LoadCorpusDocumentCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_LOADCORPUS:String = "EVENTTYPE_LOADCORPUS";   
		
		public function LoadCorpusDocumentCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_LOADCORPUS, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new LoadCorpusDocumentCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}