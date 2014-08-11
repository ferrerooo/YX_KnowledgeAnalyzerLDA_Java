package com.kalda.command.CorpusMgr
{
	import com.adobe.cairngorm.control.CairngormEventDispatcher;
	import com.kalda.command.CorpusMgr.LoadAllUsersWithPostAndReplyCommandEvent;
	import com.kalda.command.CorpusMgr.TrainNewCorpusCommandEvent;
	import com.kalda.command.CorpusMgr.LoadThreadurlCommandEvent;
	
	public class CorpusMgrControl
	{
		
		private static const WAITMSG_LOADUSERS:String = "Loading ALL Users...";
		private static const WAITMSG_TRAINNEW:String = "Training New Corpus...";
		private static const WAITMSG_LOADTHREADURL:String = "Load the thread...";
		
		public static function loadAllUsersWithPostAndReply() :void {
			
			var cmdEvt:LoadAllUsersWithPostAndReplyCommandEvent = new LoadAllUsersWithPostAndReplyCommandEvent( 
				true, WAITMSG_LOADUSERS);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function trainNewCorpus() :void {
			
			var cmdEvt:TrainNewCorpusCommandEvent = new TrainNewCorpusCommandEvent( 
				true, WAITMSG_TRAINNEW);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
		
		public static function loadThreadurl() :void {
			
			var cmdEvt:LoadThreadurlCommandEvent = new LoadThreadurlCommandEvent( 
				true, WAITMSG_LOADTHREADURL);
			CairngormEventDispatcher.getInstance().dispatchEvent(cmdEvt);
		}
	}
}