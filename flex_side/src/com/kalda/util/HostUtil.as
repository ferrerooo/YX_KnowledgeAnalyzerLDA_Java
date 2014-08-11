package com.kalda.util
{
	/**
	 * HostUtil is designed to get the hostURL of CATS is being deployed.
	 * If it's in developing model, #getHostURL() method will return a hard coding hostURL.
	 * You can set SERVER_NAME, SERVER_PORT and CONTEXT_ROOT by yourself in developing model.
	 * 
	 * 
	 */
	public class HostUtil
	{
		//import mx.core.Application;
		import mx.core.FlexGlobals;
		
		/*
		private static const REQUEST_SCHEME:String = "https";
		private static const SERVER_NAME:String = "gpfdev.nam.nsroot.net";
		private static const SERVER_PORT:String = "443";
		private static const CONTEXT_ROOT:String = "/PBAH";
		*/
		/*
		private static const REQUEST_SCHEME:String = "http";
		private static const SERVER_NAME:String = "serl.clarkson.edu";
		private static const SERVER_PORT:String = "8080";
		private static const CONTEXT_ROOT:String = "/ldaanalyzer";
		*/
		
		private static const REQUEST_SCHEME:String = "http";
		private static const SERVER_NAME:String = "127.0.0.1";
		private static const SERVER_PORT:String = "8080";
		private static const CONTEXT_ROOT:String = "/YX_KnowledgeAnalyzerLDA_Java";
		
		//private static const SERVER_PORT:String = "8888";
		//private static const CONTEXT_ROOT:String = "/fanta";
		
		
		public static function getHostURL():String{
			//if(Application.application.parameters.hostURL != null){
			if(FlexGlobals.topLevelApplication.parameters.hostURL != null){
				return FlexGlobals.topLevelApplication.parameters.hostURL+FlexGlobals.topLevelApplication.parameters.hostContext;
			}else{
				return REQUEST_SCHEME+"://"+SERVER_NAME+":"+SERVER_PORT+CONTEXT_ROOT;
			}
		}
		public static function getHostRootURL():String{
			if(FlexGlobals.topLevelApplication.parameters.hostURL != null){
				return FlexGlobals.topLevelApplication.parameters.hostURL;
			}else{
				return REQUEST_SCHEME+"://"+SERVER_NAME+":"+SERVER_PORT;
			}
		}
	}
}