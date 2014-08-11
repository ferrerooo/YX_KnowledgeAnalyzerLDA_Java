package com.kalda.command
{
  import com.kalda.command.SerialCommand;
  import com.kalda.util.LogUtils
  import com.kalda.util.WaitPopUp;
  import flash.utils.describeType;
  
  import mx.logging.ILogger;
  import com.kalda.command.CommandEvent;
  
  /**
   * The Serial Command Manager is a singelton responsible for executing
   * commands in a serial fashion. 
   * 
   * @see com.citi.gpb.ca.common.command.SerialCommand
   */
  public class SerialCommandManager
  {
    private var logger:ILogger = LogUtils.getLogger(this);	  
		private static var manager : SerialCommandManager;
		
		private var _commandQueue:Array = [];
		
		
		/**
		 * Retrieves the singleton instance
		 * 
		 * @return Singleton instance  
		 * 
		 */
		public static function getInstance() : SerialCommandManager 
		{
			if ( manager == null )
				manager = new SerialCommandManager();
				
			return manager;
	  }
    
    
    /**
     * Pushes the given command to the end of the queue. If this is the only
     * command, it is immediately executed.
     * 
     * @param command SerialCommand to be executed
     * 
     * @see com.citi.gpf.ah.core.common.command.SerialCommand
     */
    public function executeCommmand(command:SerialCommand):void
    {
      logger.debug("Push command: " + describeType(command).@name);
      _commandQueue.push(command);
      logger.debug("Queue length: " + _commandQueue.length);
      if(command.event is CommandEvent){
      	var cmdEvent:CommandEvent = command.event as CommandEvent;
      	if(cmdEvent.displayWaitMsg && cmdEvent.waitMsg != null && cmdEvent.waitMsg != ""){
      		WaitPopUp.show(cmdEvent.waitMsg);
      	}
      }
      if (_commandQueue.length == 1) {
        logger.debug("Executing command: " + describeType(command).@name);
        command.serialExecute(command.event);
      }
    }
    
    
    /**
     * Removes the first command from the queue and executes the next
     * command, if necessary.
     * 
     */
    public function commandComplete():void
    {
      var completedCommand:SerialCommand = SerialCommand(_commandQueue.shift());
      logger.debug("Command completed: " + describeType(completedCommand).@name);
      
      logger.debug("New queue length: " + _commandQueue.length);
      if(completedCommand.event is CommandEvent){
      	var cmdEvent:CommandEvent = completedCommand.event as CommandEvent;
      	if(cmdEvent.displayWaitMsg && cmdEvent.waitMsg != null && cmdEvent.waitMsg != ""){
      		WaitPopUp.close();
      	}
      }
      if(!completedCommand.executeNextCommand){
      	 logger.debug("Executing SerialCommand is terminated.Because the previous command is failed.");
      	 _commandQueue = [];
      	 if(WaitPopUp.isWaiting()){
	      	 //Call waitPopUp.forceClose to remove all the waiting messages.
	      	 WaitPopUp.forceClose();
      	 }
      }
      if (_commandQueue.length > 0) 
      {
        var command:SerialCommand = SerialCommand(_commandQueue[0]);
        logger.debug("Executing command: " + describeType(command).@name);
        command.serialExecute(command.event); 
      }
    } 
  }
}