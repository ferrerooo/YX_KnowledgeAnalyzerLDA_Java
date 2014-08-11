package com.kalda.tools;

//import java.io.FileWriter;
import java.lang.reflect.Field;

import com.kalda.domain.*;

public class HibernateMapGenUtil {
	public static void main(String[] args) throws Exception {
		generateHBM(TblThreadStackoverflow.class);
	}
	
	private static void generateHBM(Class<?> domainClass) throws Exception {
		System.out.println("user.dir = " + System.getProperty("user.dir"));
		String userDirPath = System.getProperty("user.dir");
		String destinationPath = "/src/com/kalda/domain/";
		String className = domainClass.getSimpleName();
		String targetFileName = userDirPath + destinationPath + className
				+ ".hbm.xml";
		System.out.println(targetFileName);
		//FileWriter fWriter = new FileWriter(targetFileName);
		
		String fileContent = "";
		
		String header = 
			"<?xml version=\"1.0\"?>\n" + 
			"<!DOCTYPE hibernate-mapping PUBLIC \"-//Hibernate/Hibernate Mapping DTD 3.0//EN\"\n" + 
			"\"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd\">\n" + 
		
			"<hibernate-mapping>\n" + 
				"<class name=\"com.kalda.domain." + className + "\" table=\"" + className + "\">\n";
		
		String footer =     
				"</class>\n" +
			"</hibernate-mapping>";
		
		String mainBody = "";
		{
			Field[] fields = domainClass.getDeclaredFields();
			
			mainBody += 
			"<id name=\"" + fields[0].getName() + "\" type=\"java.lang.Long\">\n" +
            "<column name=\"" + fields[0].getName() + "\" />\n" +
            "<generator class=\"native\" />\n" +
            "</id>\n";
			
			for (int i = 1; i < fields.length; i++) {
				String fieldName = fields[i].getName();
				String fieldClass = fields[i].getType().getSimpleName();
				System.out.println("" + fieldClass + " " + fieldName);
				
				
				
				if ("String".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.lang.String\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
				
				if ("Integer".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.lang.Integer\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
				
				if ("Long".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.lang.Long\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
				
				if ("Double".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.lang.Double\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
				
				if ("Date".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.util.Date\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
				
				if ("Boolean".equals(fieldClass)) {
					mainBody +=
					"<property name=\""+fieldName + "\" type=\"java.lang.Boolean\">\n" + 
		            "  <column name=\"" + fieldName + "\" />\n" +
		            "</property>\n";
				}
			}
		}
		
		fileContent = header + mainBody + footer;
		
		System.out.println(fileContent);
		
		//fWriter.write(fileContent);
		
		//fWriter.close();
	}
}
