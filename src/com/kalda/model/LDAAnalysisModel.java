package com.kalda.model;

import java.io.File;
import java.util.ArrayList;

import com.kalda.dto.ldaanalysis.ThetaThreshold;

public class LDAAnalysisModel {

	private static LDAAnalysisModel model;
	
	public static LDAAnalysisModel getInstance()  {
		if ( model == null )
		{
			model = new LDAAnalysisModel();
		}        
		return model;
	}
	
	//--------------------------------------------------
	
	public ThetaThreshold tt = new ThetaThreshold();
	
	public String[] fileNames = null;

}
