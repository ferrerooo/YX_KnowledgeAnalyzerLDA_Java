package com.kalda.utils;

import java.util.Comparator;

import com.kalda.dto.WordInTopic;

// sort based on word Number in wordmap.txt
public class WordInTopicComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		WordInTopic c1 = (WordInTopic) o1;
		WordInTopic c2 = (WordInTopic) o2;
		if (c1.getWordNum() < c2.getWordNum()) {
			return -1;
		} else {
			if (c1.getWordNum() == c2.getWordNum()) {
				return 0;
			} else {
				return 1;
			}
		}
	}

}
