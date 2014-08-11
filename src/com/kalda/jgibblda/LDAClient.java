package com.kalda.jgibblda;

import java.io.IOException;

public class LDAClient {
	
	/*
	 * -est -alpha 0.5 -beta 0.1 -ntopics 100 -niters 2000 -savestep 100 -twords 20 -dfile src/models/casestudy/newdocs.dat
	 * -estc -dir src/models/casestudy/ -model model-final -niters 800 -savestep 100 -twords 20
	 * -inf -dir src/models/casestudy/ -model model-final -niters 30 -twords 20 -dfile newdocs.dat*/
	
	private static String[] ldaargs = {
		 	"-est", 
		 	"-alpha", 		"0.5", 
		 	"-beta", 		"0.1", 
		 	"-ntopics", 	"10",    // topic number
		 	"-niters", 		"2000",    // Gibbs Sampling Iteration number
		 	"-savestep", 	"10", 
		 	"-twords",	 	"20",    // how many words, a topic contains
		 	"-dfile", 		"src/models/casestudy/newdocs.dat"
		};
	
	public static void main(String[] args) throws Exception {
		
		//Stemmer.main();
		
		// 已经在这里加入了lucene的分词器，所以上面的stemmer就 不需要了。。。
//		LDADataPreparation.main();
		
		//LDA.main(ldaargs);
		
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","7","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","8","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","9","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","10","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","11","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","12","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","13","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","14","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","15","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","16","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","17","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","18","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","19","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","20","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","21","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","22","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","23","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","24","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","25","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","26","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","27","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","28","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","29","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","30","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","31","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","32","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","33","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","34","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","35","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","36","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","37","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","38","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","39","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","40","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","41","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","42","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","43","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","44","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","45","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","46","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","47","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","48","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","49","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","50","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","51","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","52","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","53","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","54","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","55","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","56","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","57","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","58","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","59","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","60","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","61","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","62","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","63","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","64","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","65","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","66","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","67","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","68","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","69","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","70","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","71","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","72","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","73","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","74","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","75","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","76","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","77","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","78","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","79","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.6","-beta","0.1","-ntopics","80","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","81","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","82","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","83","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","84","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","85","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","86","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","87","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","88","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","89","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
//		LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","90","-niters","2000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		//LDA.main(new String[]{"-est","-alpha","0.5","-beta","0.1","-ntopics","100","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		//LDA.main(new String[]{"-est","-alpha","0.4","-beta","0.1","-ntopics","120","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		//LDA.main(new String[]{"-est","-alpha","0.4","-beta","0.1","-ntopics","140","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		//LDA.main(new String[]{"-est","-alpha","0.4","-beta","0.1","-ntopics","160","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		//LDA.main(new String[]{"-est","-alpha","0.4","-beta","0.1","-ntopics","180","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
		
		//LDA.main(new String[]{"-est","-alpha","0.4","-beta","0.1","-ntopics","200","-niters","1000","-savestep","100","-twords","20","-dfile","src/models/casestudy/newdocs.dat"});
	}

}
