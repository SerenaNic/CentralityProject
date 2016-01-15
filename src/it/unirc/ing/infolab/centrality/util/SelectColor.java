package it.unirc.ing.infolab.centrality.util;

import java.awt.Color
;
import java.awt.Paint;
import java.util.ArrayList;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.visualization.VisualizationViewer;

public class SelectColor 
{

	private static final Color[] palette = {Color.GRAY, Color.BLACK, Color.WHITE, Color.YELLOW};
	
	public static Color[] getPalette() {
		return palette;
	}
	
	public static void choiceColor (final int col,VisualizationViewer<Integer,String> vv,  final ArrayList<Integer> nodi, final ArrayList<Integer> colorArray, final int vC)
	{
		final ArrayList<Integer> ar= new ArrayList<Integer>();
		
		for (int z=0;z<nodi.size();z++)
			for (int w=0;w<nodi.get(z);w++)
			    ar.add(colorArray.get(z));
		
		Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() 
		{  
			public Paint transform(Integer i) 
            {
				if(i>=vC) 
					return palette[col % palette.length];
				else 
					return palette[ar.get(i).intValue() % palette.length];
            	
            }           
        }; 
           
        vv.getRenderContext().setVertexFillPaintTransformer((Transformer<Integer, java.awt.Paint>) vertexPaint);

	}
}