package com.kalda.util
{
	public class DateUtils
	{
		
		/* 
		* Date Object can use " fullYear  month date hours minutes seconds milliseconds" to visit the details
		*/
		public static function getYear(date:Date):String {
			return date["fullYear"];
		}
		
		public static function getMonth(date:Date):String {
			return date["month"]+1;
		}
		
		public static function getDay(date:Date):String {
			return date["date"];
		}
		
		public static function getHour(date:Date):String {
			return date["hours"];	
		}
		
		public static function getMinute(date:Date):String {
			return date["minutes"];
		}
		
		public static function getSecond(date:Date):String {
			return date["seconds"];
		}
		
		public static function getYearMonthDay(date:Date):String {
			return DateUtils.getYear(date)+","+DateUtils.getMonth(date)+","+DateUtils.getDay(date);
		}
		
	}
}