package com.kalda.domain
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.domain.TblUser")]
	public class TblUser
	{
		public var id:int;
		public var whichCorpus:String;
		public var userName:String;
		public var postAmount:int = 0;
		public var replyAmount:int = 0;
	}
}