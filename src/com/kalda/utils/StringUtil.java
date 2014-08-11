package com.kalda.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	
	public static Integer StringToInteger(String str) {
		
		return Integer.valueOf(str);
	}
	
	public static String She4ru5ReturnString(int i, Double d) {
		//double d = 3.1465926;
		String result = String.format("%."+i+"f", d.doubleValue());
		return result;
	}
	
	public static Double She4ru5ReturnDouble(int i, Double d) {
		String result = String.format("%."+i+"f", d.doubleValue());
		return StringToDouble(result);
	}
	
	public static Double She4ru5ReturnDouble(int i, String d) {
		Double ad = StringToDouble(d);
		Double returndouble = She4ru5ReturnDouble(i, ad);
		return returndouble;
	}
	
	
	public static double StringTodouble(String str){
		
		double d = Double.parseDouble(str);
		return d;
	}
	
	public static Double StringToDouble(String str) {
		return Double.valueOf(str);
	}
	
	public static String DateToString(Date d) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH-mm");
		return sdf.format(d);
	}
	
	public static String TimestampToString(Timestamp t) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH-mm");
		return sdf.format(t);
	}
	
	
	public static void main(String[] args) {
		
		
	}
}
