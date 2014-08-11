package com.kalda.command.LDATopicManager
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;	
	
	public class SaveDocumentCommentCommandEvent  extends CommandEvent
	{
		public static const EVENTTYPE_SAVECOMMENT:String = "EVENTTYPE_SAVECOMMENT";   
		
		public function SaveDocumentCommentCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_SAVECOMMENT, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new SaveDocumentCommentCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}