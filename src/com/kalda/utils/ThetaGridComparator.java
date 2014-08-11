package com.kalda.utils;

import java.util.Comparator;

import com.kalda.dto.ldaanalysis.TopicDocListWithThetaThreshold;
import com.kalda.dto.ldaanalysis.ThetaGrid;

public class ThetaGridComparator implements Comparator {

	public int compare(Object o1, Object o2) {
		ThetaGrid c1 = (ThetaGrid) o1;
		ThetaGrid c2 = (ThetaGrid) o2;
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