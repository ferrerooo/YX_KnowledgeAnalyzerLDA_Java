package com.kalda.tools;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
//import org.hibernate.cfg.AnnotationConfiguration;

public class ExportDB {

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
		
		//new SchemaExport(new AnnotationConfiguration().configure()).create(true,true);


	}
}
