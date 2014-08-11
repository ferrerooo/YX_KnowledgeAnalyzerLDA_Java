package com.kalda.command
{
	import com.adobe.cairngorm.control.CairngormEvent;

	public class CommandEvent extends CairngormEvent{
		
		protected var _displayWaitMsg:Boolean;
		public function get displayWaitMsg():Boolean{
			return _displayWaitMsg;
		}
		protected var _waitMsg:String;
		public function get waitMsg():String{
			return _waitMsg;
		}
		public function CommandEvent(type:String,displayWaitMsgInd:Boolean = false, waitMsgText:String=""):void
		{
			super(type,false, true);
			_displayWaitMsg = displayWaitMsgInd;
			_waitMsg = waitMsgText;
		}		
	}
}