package com.kalda.utils;

import java.util.Comparator;

import com.kalda.dto.WordInTopic;

// sort based on probability
public class WordInTopicComparator_V2 implements Comparator {

	public int compare(Object o1, Object o2) {
		WordInTopic c1 = (WordInTopic) o1;
		WordInTopic c2 = (WordInTopic) o2;
		if (c1.getWordInTopicProbability() < c2.getWordInTopicProbability()) {
			return 1;
		} else {
			if (c1.getWordInTopicProbability() == c2.getWordInTopicProbability()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
