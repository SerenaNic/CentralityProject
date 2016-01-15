package it.unirc.ing.infolab.centrality.util;

import it.unirc.ing.infolab.centrality.util.FileWriter;


import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.screencap.EPSDump;

public class Save {

	public static void saveJpeg(VisualizationViewer<Integer,String> vv)
	{

	    int width = vv.getWidth();
        int height = vv.getHeight();

        BufferedImage bi = new BufferedImage(width, height,
               BufferedImage.TYPE_INT_RGB);
       Graphics2D graphics = bi.createGraphics();
       vv.paint(graphics);
        graphics.dispose();

        File file = new File("graph.jpeg");
            
        try {
			ImageIO.write(bi, "jpeg", file);
			 System.out.println("Dumping!");
        } catch (Exception e1) {
            System.out.println("Dump failed");}
	}
	
	public static void saveEps(VisualizationViewer<Integer,String> vv)
	{
    	
        EPSDump dumper = new EPSDump();
        dumper.textVector = false;
        try 
        {
        	System.out.println("Dumping!");
        	Component component =  vv;
        	dumper.dumpComponent(new File("graph.eps"), component);
          
        } catch (IOException e1) {
          System.out.println("Dump failed");
        }
	}

	public static void saveCode(Graph<Integer, String> g, ArrayList<Integer> colorArray,
			ArrayList<Integer> nodeArray) 
	{
		
		try 
		{
			FileWriter.GraphCode(g,colorArray, nodeArray);
	        System.out.println("Code written!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
