package com.kalda.utils;

import java.util.Comparator;
import com.kalda.dto.TopicInDocument;

public class TopicInDocumentComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		TopicInDocument c1 = (TopicInDocument) o1;
		TopicInDocument c2 = (TopicInDocument) o2;
		if (c1.getTopicInDocumentProbability() < c2.getTopicInDocumentProbability()) {
			return 1;
		} else {
			if (c1.getTopicInDocumentProbability() == c2.getTopicInDocumentProbability()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
