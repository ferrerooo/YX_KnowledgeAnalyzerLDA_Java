package com.kalda.util
{
  import flash.events.TimerEvent;
  import flash.utils.Timer;
  import flash.utils.describeType;
  
  import mx.logging.ILogger;
  import mx.logging.Log;
  import mx.logging.LogEventLevel;
  import mx.logging.targets.TraceTarget;

	/**
	 * Static utility class handling logging 
	 * 
	 */  
  public class LogUtils
  {
    private static var logger:ILogger;
    
    private static var logInitted:Boolean = false;
    private static var tempTarget:TraceTarget;

    /**
     * Retrieves the ILogger implementation
     *  
     * @param value Either a string or an object instance. If an object
     * instance, the log category will be the class name
     * @return The ILogger implementation
     * 
     */
    public static function getLogger(value:Object):ILogger {
      if(!logInitted && tempTarget == null) {
        tempTarget = new TraceTarget();
        tempTarget.level = LogEventLevel.DEBUG;
        Log.addTarget(tempTarget);
      }
      
      var comp:String;
      if (value is String)
        comp = String(value);
      else
      {
        comp = describeType(value).@name;
        if (comp.indexOf("::") > 0)
          comp = comp.substring(comp.indexOf("::") + 2, comp.length);
      }
      
      return(Log.getLogger(comp));
    }
    
    /**
     * Static function to initialize the logger at the given debug
     * level. 
     * 
     * Debug levels:
     *    <ul>
     *      <li><code>LogEventLevel.FATAL</code> designates events that are very
     *      harmful and will eventually lead to application failure</li>
     *
     *      <li><code>LogEventLevel.ERROR</code> designates error events that might
     *      still allow the application to continue running.</li>
     *
     *      <li><code>LogEventLevel.WARN</code> designates events that could be
     *      harmful to the application operation</li>
	   *
     *      <li><code>LogEventLevel.INFO</code> designates informational messages
     *      that highlight the progress of the application at
     *      coarse-grained level.</li>
	   *
     *      <li><code>LogEventLevel.DEBUG</code> designates informational
     *      level messages that are fine grained and most helpful when
     *      debugging an application.</li>
	 ` *
     *      <li><code>LogEventLevel.ALL</code> intended to force a target to
     *      process all messages.</li>
     *    </ul>
     * 
     * @param debugLevel
     * 
     */
    public static function initLogger(debugLevel:int):void {
      var logTarget:TraceTarget = new TraceTarget();
      logTarget.level = debugLevel;
      logTarget.includeDate = true;
      logTarget.includeTime = true;
      logTarget.includeLevel = true;
      logTarget.includeCategory = true;
      logTarget.fieldSeparator = ":";
      if(tempTarget != null) {
        Log.removeTarget(tempTarget);
      }
      Log.addTarget(logTarget);
      logInitted = true;
    }
    
    /**
     * Static function allowing for the delayed output of a large amount of debug
     * data. Typically, when using ObjectUtil.toString() it may take a long time
     * for the output to appear in the log. This function immediately gives you
     * an indication that logging has started.
     * 
     * @param debugOutputFunction The function responsible for the actual 
     * debug output
     * 
     */
    public static function delayedDebug(debugOutputFunction:Function):void
    {
      LogUtils.getLogger("LogUtils").debug("\nGenerating debug output...");
      
      var t:Timer = new Timer(500,1);
      t.addEventListener(TimerEvent.TIMER_COMPLETE, debugOutputFunction);
      t.start();  
    }
  }
}