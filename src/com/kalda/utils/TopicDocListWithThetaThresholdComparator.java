package com.kalda.utils;

import java.util.Comparator;
import com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold;

// 给定一个topic，他所对应的所有doc的list
public class TopicDocListWithThetaThresholdComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		TopicDocListWithThetaThreshold c1 = (TopicDocListWithThetaThreshold) o1;
		TopicDocListWithThetaThreshold c2 = (TopicDocListWithThetaThreshold) o2;
		if (c1.getTopicProbabilityForThisDoc() < c2.getTopicProbabilityForThisDoc()) {
			return 1;
		} else {
			if (c1.getTopicProbabilityForThisDoc() == c2.getTopicProbabilityForThisDoc()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

}
