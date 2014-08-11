package com.kalda.domain
{
	[Bindable] 
	[RemoteClass(alias="com.kalda.domain.TblCorpusDocument")]
	public class TblCorpusDocument
	{
		public var id:int;
		public var documentName:String;
		public var documentContent:String;
		public var documentComment:String;
	}
}