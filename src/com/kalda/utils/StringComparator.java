package com.kalda.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.kalda.dto.corpus.NewCorpusTrainResult;

public class StringComparator  implements Comparator {

	public int compare(Object o1, Object o2) {
		String c1 = (String) o1;
		String c2 = (String) o2;
		Double d1 = new Double(c1);
		Double d2 = new Double(c2);
		if (d1.doubleValue() < d2.doubleValue()) {
			return 1;
		} else {
			if (d1.doubleValue() == d2.doubleValue()) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	public static void main(){
		List<String> list = new ArrayList<String>();
		list.add("0.4");
		list.add("0.4");
		list.add("0.3");
		list.add("0.8");
		list.add("0.2");
		list.add("0.3");
		list.add("0.5");
		list.add("0.42");
		list.add("0.46");
		list.add("0.1");
		
		StringComparator comp = new StringComparator();
		
		Collections.sort(list,comp);
		
		for (int i=0;i<list.size();i++) 
			System.out.println(list.get(i).toString());
	}


}
