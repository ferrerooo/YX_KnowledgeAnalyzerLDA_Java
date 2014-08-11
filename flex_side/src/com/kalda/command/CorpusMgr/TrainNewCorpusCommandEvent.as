package com.kalda.command.CorpusMgr
{
	import com.kalda.command.CommandEvent;
	import flash.events.Event;
	
	public class TrainNewCorpusCommandEvent extends CommandEvent
	{
		public static const EVENTTYPE_TRAINNEW:String = "EVENTTYPE_TRAINNEW"; 
		
		public function TrainNewCorpusCommandEvent(displayWaitMsgInd:Boolean,waitMsgText:String) {   
			super(EVENTTYPE_TRAINNEW, displayWaitMsgInd, waitMsgText);   
		}   	
		
		public override function clone():Event {
			return new TrainNewCorpusCommandEvent(_displayWaitMsg, _waitMsg); 
		}
	}
}