package com.kalda.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

public class LuceneUtil {
	
	public static List<String> tokenizeStringReturnList(Analyzer analyzer, String string) {
	    List<String> result = new ArrayList<String>();
	    try {
	      TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
	      stream.reset();
	      while (stream.incrementToken()) {
	        result.add(stream.getAttribute(CharTermAttribute.class).toString());
	      }
	    } catch (IOException e) {
	      // not thrown b/c we're using a string reader...
	      throw new RuntimeException(e);
	    }
	    return result;
	  }
	
	public static String tokenizeStringReturnString(Analyzer analyzer, String string) {
	    String str = "";
	    try {
	      TokenStream stream  = analyzer.tokenStream(null, new StringReader(string));
	      stream.reset();
	      while (stream.incrementToken()) {
	        str = str + " " +stream.getAttribute(CharTermAttribute.class).toString();
	      }
	    } catch (IOException e) {
	      // not thrown b/c we're using a string reader...
	      throw new RuntimeException(e);
	    }
	    return str;
	  }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = LuceneUtil.tokenizeStringReturnList(new EnglishAnalyzer(Version.LUCENE_40), 
				"20 21 202 22 gas car cars bus buses replies buy buies bought");
		for (int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}

	}

}
