package com.kalda.util
{
	public class StringUtils
	{
		public static function str2Boolean(str:String):Boolean {  
			if (str != null && str.toUpperCase()=="Y") {  
				return true;  
			} else {  
				return false;  
			}  
		} 
		
		public static function boolean2Str(b:Boolean):String {  
			return b ? "Y" : "N";  
		} 
		
		public static function LTrim(s : String):String{  
			var i : Number = 0;  
			while(s.charCodeAt(i) == 32 || s.charCodeAt(i) == 13 || s.charCodeAt(i) == 10 || s.charCodeAt(i) == 9)  
			{  
				i++;  
			}  
			return s.substring(i,s.length);  
		}  
		
		public static function RTrim(s : String):String{  
			var i : Number = s.length - 1;  
			while(s.charCodeAt(i) == 32 || s.charCodeAt(i) == 13 || s.charCodeAt(i) == 10 ||s.charCodeAt(i) == 9)  
			{  
				i--;  
			}   
			return s.substring(0,i+1);  
		}  
		
		public static function Trim(s : String):String  
		{  
			if (s == null) {  
				return null;  
			}  
			return LTrim(RTrim(s));  
		} 
		
		public static function StringToNumber(s : String):Number  
		{  
			s = Trim(s);
			return new Number(s);
		} 
		
		public static function StringToint(s : String):int  
		{  
			s = Trim(s);
			return int(s);
		}
		
		public static function intToString(i:int):String {
			return new String(i);
		}
		
		public static function NumberToString4she5ru(i:int, d:Number):String{
			return d.toFixed(i);
		}
		
	}
}