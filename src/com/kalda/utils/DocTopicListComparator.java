package com.kalda.utils;

import java.util.Comparator;
import com.kalda.dto.corpus.NewCorpusTrainResult;

public class DocTopicListComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		NewCorpusTrainResult c1 = (NewCorpusTrainResult) o1;
		NewCorpusTrainResult c2 = (NewCorpusTrainResult) o2;
		if (c1.getProbability() < c2.getProbability()) {
			return 1;
		} else {
			if (c1.getProbability() == c2.getProbability()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
