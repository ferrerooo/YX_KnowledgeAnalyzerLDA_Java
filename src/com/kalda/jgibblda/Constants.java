/*
 * Copyright (C) 2007 by
 * 
 * 	Xuan-Hieu Phan
 *	hieuxuan@ecei.tohoku.ac.jp or pxhieu@gmail.com
 * 	Graduate School of Information Sciences
 * 	Tohoku University
 * 
 *  Cam-Tu Nguyen
 *  ncamtu@gmail.com
 *  College of Technology
 *  Vietnam National University, Hanoi
 *
 * JGibbsLDA is a free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * JGibbsLDA is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JGibbsLDA; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */

package com.kalda.jgibblda;

public class Constants {
	public static final long BUFFER_SIZE_LONG = 10000;
	public static final short BUFFER_SIZE_SHORT = 512;
	
	public static final int MODEL_STATUS_UNKNOWN = 0;
	public static final int MODEL_STATUS_EST = 1;
	public static final int MODEL_STATUS_ESTC = 2;
	public static final int MODEL_STATUS_INF = 3;
	
	//-----------------------------------------------------------------
	
	/***** used in Stemmer.java ******/
		// Input
		public static final String PATH_ORIGINAL_CORPUS = "c:/Corpus - LDA-servlets";  // 原始的documents
		// Output
		public static final String PATH_STEMMED_CORPUS = "c:/CorpusStemmed";   // stemmer 过后的documents
	
	/***** used in LDADataPreparation。java *****/
		// Input
		//public static final String PATH_DATAFILES = "C:/Users/Administrator/Desktop/Corpus_forToolPaper";  // 这个类的输入document的路径
		//public static final String PATH_DATAFILES = "C:/Users/Administrator/Desktop/corpusfinal_afile_is_apost_withsourcecode";
		public static final String PATH_DATAFILES = "C:/Users/Administrator/Desktop/CorpusPostBasedNoCoCde_v2_expertpost3000";
		//public static final String PATH_DATAFILES =  "C:\\Users\\Administrator\\Desktop\\ldatry\\corpus_t3";
		
		public static final String PATH_STOPWORDS = "c:/stopwords.txt";  // 输入的document要用这个filter来过滤无效词
		//public static final String PATH_STOPWORDS = "C:/stopwords_v2.txt";
		
		// Output
		public static final String PATH_DATAOUTPUTFILE = "C:/Processed-Data.dat";   // 这个类的输出 。 LDA-C需要的输入文件
		public static final String PATH_VOCABOUTPUTFILE = "C:/vocab.txt";  // 这个类的输出。  LDA-C的字典
		public static final String PATH_DATAOUTPUTFILEFORGIBBSUSE = 
			"C:/Users/Administrator/VD J2EE/workspaceForEclipseJUNO/YX_KnowledgeAnalyzerLDA_Java/src/models/casestudy/newdocs.dat";  // 这个类的输出 。 JGibbLDA需要的输入文件
	
	
	
	
	
}
