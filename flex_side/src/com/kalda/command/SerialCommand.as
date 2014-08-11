package com.kalda.command
{
  import com.adobe.cairngorm.commands.ICommand;
  import com.kalda.command.SerialCommandManager;
  import com.kalda.util.LogUtils;
  import com.kalda.util.ErrorPopUp;
  
  import mx.logging.ILogger;
  import mx.rpc.IResponder;
  import com.adobe.cairngorm.control.CairngormEvent;

  /**
   * Abstract cairngorm command responsible for serializing
   * asyncronous command calls. Since the ServiceLocator is a 
   * singleton, multiple calls using the same remote object 
   * was resulting in blocking. This command in conjunction
   * with the SerialManager ensures that multiple event calls 
   * result in command execution in order. 
   * 
   * Subclasses need to implement serialExecute, serialResult,
   * and serialFault   
   * 
   * @see com.citi.gpf.ah.core.common.control.SerialCommandManager
   */
  public class SerialCommand implements ICommand, IResponder
  {
    protected var logger:ILogger = LogUtils.getLogger(this);
    private var manager:SerialCommandManager = SerialCommandManager.getInstance();
    
    public var event:CairngormEvent;
    /**
    * The indicator field indicates whether execute the next command in the Serial command queue.
    * <ul>  
    * <li>The default value of it is true. It means it will default to execute the next serial command if it has.
    * <li>It will be set to false automatically when when #fault method is invoked.
    *     (If you really want to execute the next command even if the current command is failed. 
    *      You can change it back to true in #serialFault method.) 
    * <li>It can be set explicitly by the developer too.
    *     (For example, in #serialResult method, you can set its value upon the returned result)
    * <ul>
    */
    public var executeNextCommand:Boolean = true;
    
    /**
     * Implementation of the <code>ICommand</code> execute function.
     * It is marked final since all subclasses should call the serialExecute
     * function. This function registers the command with the SerialCommandMaanger
     * 
     * @param event CairngormEvent
     * 
     * @see com.citi.gpf.ah.core.common.control.SerialCommandManager
     * 
     */
    final public function execute(event:CairngormEvent):void
    {
      this.event = event;
      manager.executeCommmand(this);        
    }
    
    
    /**
     * Abstract method called when the command is at the top of
     * command queue
     * 
     * @param event CommandEvent
     * 
     * @see com.citi.gpf.ah.core.common.control.SerialCommandManager
     */
    public function serialExecute(event:CairngormEvent):void 
    {
      throw new Error("Abstract Method");
    }
    
    /**
     * Implementation of the <code>IResponder</code> result function.
     * It is marked final since all subclasses should call the serialResult
     * function. After running serialResult, it alerts the SerialCommandManager
     * that the command has completed.
     * 
     * @param data ResultEvent
     * 
     * @see com.citi.gpf.ah.core.common.control.SerialCommandManager
     */
    final public function result(data:Object):void
    {
      serialResult(data);
      commandComplete();
    }
    
    /**
     * Abstract method called by the <code>result</code> function.
     * 
     * @param data ResultEvent
     * 
     */
    public function serialResult(data:Object):void 
    {
      throw new Error("Abstract Method");
    }

    /**
     * Implementation of the <code>IResponder</code> fault function.
     * It is marked final since all subclasses should call the serialFault
     * function. After running serialFault, it alerts the SerialCommandManager
     * that the command has completed.
     * 
     * @param info FaultEvent
     * 
     * @see com.citi.gpf.ah.core.common.control.SerialCommandManager
     */    
    public function fault(info:Object):void
    {
      executeNextCommand = false;
      serialFault(info);
      commandComplete();
    }
    
    /**
     * Abstract method called by the <code>fault</code> function.
     * 
     * @param info FaultEvent
     * 
     */    
    public function serialFault(info:Object):void 
    {
       ErrorPopUp.showError(info);
    }
    

    /**
     * Convenience method used to alert the SerialCommandManager
     * that the command has completed
     */
    public function commandComplete():void
    {
      manager.commandComplete();
    }
  }
}