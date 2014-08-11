package com.kalda.jgibblda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.kalda.business.LDATopicMgrBOImpl;
import com.kalda.domain.TblKeyWordsForTopic;
import com.kalda.dto.corpus.NewCorpusTrainResult;
import com.kalda.dto.ldaanalysis.TwordsDisplayFormat1Object;

public class LDAClientForNewCorpus {
	
	public static double [] trainNewCorpus(String lDAModelPath, String ldaRunDocName, String newCorpus) {
		
		LDACmdOption ldaOption = new LDACmdOption();
		ldaOption.inf = true;
		ldaOption.dir = lDAModelPath;
		ldaOption.modelName = ldaRunDocName;

		Inferencer inferencer = new Inferencer();
		inferencer.init(ldaOption);

		String[] test = { newCorpus };
		Model newModel = inferencer.inference(test);
		return newModel.theta[0];
	}

	public static void main(String[] args) throws Exception {

//		String lDAModelPath = "C:/Users/Administrator/Desktop/ldaoutput_afile_is_apost_withoutsourcecode";
//		String ldaRunDocName = "model ---- alpha-0.4 beta-0.1 ntopics-120 niters-01000 twords-20";
		
		String lDAModelPath = "E:/temp/output/casestudy";
		String ldaRunDocName = "model ---- alpha-0.5 beta-0.1 ntopics-76 niters-02000 twords-20";
		
		String newCorpus = "";
		
		File file = new File("C:/Users/Administrator/Desktop/1.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = "";
		while ( (str = br.readLine()) !=null ) {
			newCorpus = newCorpus + str;
		}
		br.close();
		
		double [] d = LDAClientForNewCorpus.trainNewCorpus(lDAModelPath, ldaRunDocName, newCorpus);
		Pairr[] arr = new Pairr[d.length];
		for (int i=0;i<d.length;i++) {
			//System.out.println(i+" 		"+d[i]);
			Pairr p = new Pairr();
			p.index = i;
			p.probability = d[i];
			arr[i]=p;
		}
		
		for (int i=0;i<d.length-1;i++) {
			
			for (int j=0;j<d.length-i-1;j++) {
				
				if (arr[j].probability<arr[j+1].probability) {
					Pairr temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		for (int i=0;i<100;i++){
			System.out.println(arr[i].index+" "+arr[i].probability);
		}
		
		
	}
	
	
	

}

class Pairr{
	int index;
	double probability;
	public Pairr(){}
}
