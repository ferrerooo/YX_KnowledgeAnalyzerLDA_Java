package com.kalda.jgibblda;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.kalda.utils.LuceneUtil;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

/**
 * The purpose of this code is to preprocess data for use in LDA-C Inputs:
 * dataOutputFile - name of output file containing documents for LDA
 * vocabOutputFile - name of output file containing the vocabulary of the corpus
 * stopWords - file containing the list of stop words dataFiles - directory
 * where data to be processed is Outputs: Produces a .dat file containing the
 * documents, and the vocab file for use in LDAC
 * 
 * @author Russell J Seidel and Tiantian Zhao
 * 
 */
public class LDADataPreparation {
	private static PrintWriter dataOutputStream = null;
	private static PrintWriter dataOutputStreamForGibbsUse = null;
	private static PrintWriter vocabOutputStream = null;
	
	private static String dataOutputFile = Constants.PATH_DATAOUTPUTFILE;
	private static String dataOutputFileForGibbsUse = Constants.PATH_DATAOUTPUTFILEFORGIBBSUSE;
	private static String vocabOutputFile = Constants.PATH_VOCABOUTPUTFILE;
	private static String stopWords = Constants.PATH_STOPWORDS;
	private static String dataFiles = Constants.PATH_DATAFILES;
	
	private static EnglishAnalyzer ea = new EnglishAnalyzer(Version.LUCENE_40);
	private static StandardAnalyzer sa = new StandardAnalyzer(Version.LUCENE_40);
	

	/**
	 * @param args
	 */
	public static void main() throws IOException {
		
		System.out.println("********* LDA Input File Preparation started *********");
		
		Map<String, Integer> tmap = new HashMap<String, Integer>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Map<String, Integer> mapIndex = new HashMap<String, Integer>();
		Map<String, Integer> stopwordmap = new HashMap<String, Integer>();
		
		dataOutputStream = new PrintWriter(new FileOutputStream(new File(dataOutputFile)));
		dataOutputStreamForGibbsUse = new PrintWriter(new FileOutputStream(new File(dataOutputFileForGibbsUse)));
		vocabOutputStream = new PrintWriter(new FileOutputStream(new File(vocabOutputFile)));
		
		File dir = new File(dataFiles);
		File[] files = dir.listFiles();
		if (files == null) {
			System.out.println("No files");
			return;
		} else {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isHidden()) {
					String add = files[i].getAbsolutePath();
					readFileByChars(add, tmap);
				}
			}
			readFileByChars(stopWords, stopwordmap); // read stopword in
														// stopwordmap
			num(tmap, map, mapIndex, stopwordmap);
			// output the vocab file
			outputVocabFile(mapIndex);
			// output each file into dataOutputFile
			Map<String, Integer> filemap = new HashMap<String, Integer>();
			Map<String, Integer> newmap = new HashMap<String, Integer>();
			
			dataOutputStreamForGibbsUse.println(files.length);
			
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isHidden()) {
					String add = files[i].getAbsolutePath();
					readFileByChars(add, filemap);
					readFileByChars(filemap, mapIndex, newmap);// update filemap
																// into newmap,
																// only item in
																// mapIndex can
																// move into
																// newmap
					int n = outputNumber(newmap);
					outputDataFile(files[i].getName(), n, newmap, mapIndex); // index, frequency
					filemap.clear();
					newmap.clear();
				}
			}
		}
		System.out.print("********* LDA Input File Preparation finished **********\n");
		dataOutputStream.close();
		dataOutputStreamForGibbsUse.close();
		vocabOutputStream.close();
	}

	// generate Map
	public static void readFileByChars(String fileName, Map map)
			throws IOException {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String str = null;
			String[] s = null;
			while ((str = reader.readLine()) != null) {
				// ----Lucene stemmer added here
				//System.out.println("Before Stemming: " + str);
				str = LuceneUtil.tokenizeStringReturnString(ea, str);
				//System.out.println("After Stemming: " + str);
				s = str.split("\\W");
				//System.out.print("After splitting: ");
				for (int u=0;u<s.length;u++) {
					//System.out.print(s[u] + " ");
				}
				//System.out.println();
				// ----lucene stemmer ends
				//s = str.split("\\W");
				for (int i = 0; i < s.length; i++) {
					String key = s[i].toLowerCase();
					if (s[i].length() > 1) {
						if (map.get(key) == null) {
							map.put(key, 1);
						} else {
							int value = ((Integer) map.get(key)).intValue();
							value++;
							map.put(key, value);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	// update tmap to map, remove stopwords, generate mapindex
	// /map(term,frequency) /mapIndex(term,Index)
	public static void num(Map tmap, Map map, Map mapIndex, Map stopwordmap) {
		int num = 0;
		Set set = tmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			int u = ((Integer) mapentry.getValue()).intValue();// value
			String m = ((String) mapentry.getKey()).toString();// key
			if (stopwordmap.get(mapentry.getKey()) == null) {
				map.put(m, u);
				mapIndex.put(m, num);
				num++;
			}
		}
	}

	public static void readFileByChars(Map filemap, Map mapIndex, Map newmap)
			throws IOException {
		Set set = filemap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			if (mapIndex.get(mapentry.getKey()) == null)
				;
			else {
				int u = ((Integer) mapentry.getValue()).intValue();// value
				String m = ((String) mapentry.getKey()).toString();// key
				newmap.put(m, u);
			}
		}
	}

	// generate unique words number of one document.
	public static int outputNumber(Map map) {
		int N = 0;
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry2 = (Map.Entry) iterator.next();
			N++;
		}
		return N;
	}

	public static void outputVocabFile(Map map) {
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			vocabOutputStream.println(mapentry.getKey());
		}
	}

	public static void outputDataFile(String fileName, int N, Map map, Map mapIndex) {
		dataOutputStream.print(N + " ");
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		//dataOutputStreamForGibbsUse.print(fileName + " ");
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();
			dataOutputStream.print(mapIndex.get(mapentry.getKey()) + ":" + mapentry.getValue() + " ");
			dataOutputStreamForGibbsUse.print(mapentry.getKey() + " ");
		}
		dataOutputStream.print("\n");
		dataOutputStreamForGibbsUse.print("\n");
	}
}
