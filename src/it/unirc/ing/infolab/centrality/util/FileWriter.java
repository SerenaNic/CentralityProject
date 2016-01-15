package it.unirc.ing.infolab.centrality.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.uci.ics.jung.graph.Graph;

public class FileWriter {
	
	public static void ScriptGnuplot(String delEdge) 
	{
	    BufferedWriter writer = null;
	    try 
	    {
	    	writer = new BufferedWriter(new java.io.FileWriter("gnuplotScript.gpi"));
	             
	        writer.write("set grid");writer.newLine();
	        writer.write("set key outside");writer.newLine();
	        writer.write("set terminal svg size 1500,1000");writer.newLine();
	        writer.write("set output \"out"+delEdge+".png\"");writer.newLine();
	        writer.write("set title \"Propagation Edge Centrality (PEC)\"");writer.newLine();
	        writer.write("set xlabel \"Level\"");writer.newLine();
	        writer.write("set ylabel \"PEC\"");writer.newLine();
	        writer.write("plot   \""+delEdge+"\" u 1:2 t  \""+delEdge+"\" w lp");writer.newLine();

	        writer.flush();

	    } catch(IOException ex) {
	        ex.printStackTrace();
	    } finally{
	    	if(writer!=null)
	    	{
	            try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }  
	    }
		
	}
	
	public static void ScriptGnuplotMe(String type) 
	{
	    BufferedWriter writer = null;
	    try 
	    {
	    	writer = new BufferedWriter(new java.io.FileWriter("gnuplotScriptMe.gpi"));
	    	    
	    	writer.write("set grid");writer.newLine();
		        writer.write("set key outside");writer.newLine();
		        writer.write("set terminal svg size 1500,1000");writer.newLine();
		        writer.write("set output \"outMeNonMe.png\"");writer.newLine();
		        writer.write("set title \"Propagation Edge Centrality Me-NonME(PEC)"+type+"\"");writer.newLine();
		        writer.write("set xlabel \"Level\"");writer.newLine();
		        writer.write("set ylabel \"PEC\"");writer.newLine();
		       
		      
		        writer.write("plotsGraph =  \"edge edgeMe \"");writer.newLine();
        		
        		writer.write("plot for [pl in plotsGraph]  pl u 1:2 t pl w lp");writer.newLine();
     
		       
		        writer.flush();
		        
	    } catch(IOException ex) {
	        ex.printStackTrace();
	    } finally{
	    	if(writer!=null)
	    	{
	            try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }  
	    }
		
	}
	
	public static void GraphCode(Graph<Integer,String> g, ArrayList<Integer> colorArray,ArrayList<Integer> nodeArray) throws FileNotFoundException 
	{
	    BufferedWriter writer = null;
	    try 
	    {
	    	writer = new BufferedWriter(new java.io.FileWriter("graphCode.txt"));
	        
	        writer.newLine();
	        writer.write("Graph<Integer,String> g = new SparseMultigraph<Integer,String>();");
	        writer.newLine();
	        writer.newLine();
	        writer.write("for(int i=0;i<"+g.getVertexCount()+";i++) g.addVertex(i);");
	        writer.newLine();
	        writer.newLine();
	        
	        for(String edge : g.getEdges())
	        {
	        	writer.write("g.addEdge(\""+edge+"\","+ g.getIncidentVertices(edge).toArray()[0] +", "
	        			+g.getIncidentVertices(edge).toArray()[1]+", EdgeType.UNDIRECTED );");
	        	writer.newLine();    	
	        }
	        
	        writer.newLine();
	        writer.newLine();
	        writer.write("ArrayList<Integer> colorArray = new ArrayList<Integer>();");
	        writer.newLine();
	        
	        for(int i=0;i<colorArray.size();i++)
	        {
	        	writer.write("colorArray.add("+colorArray.get(i)+");");
	        	writer.newLine();
	        }
	        
	        writer.newLine();
	        writer.write("ArrayList<Integer> nodeArray = new ArrayList<Integer>();");
	        writer.newLine();
	        
	        for(int i=0;i<nodeArray.size();i++)
	        {
	        	writer.write("nodeArray.add("+nodeArray.get(i)+");");
	        	writer.newLine();
	        }
	        
	        writer.flush();

	    } catch(IOException ex) {
	        ex.printStackTrace();
	    } finally{
	    	if(writer!=null){
	    		try {
					writer.close();
				} catch (IOException e) {
			          System.out.println("Write failed");
				}
	        }  
	    }
	}

	public static void DataPlot(Double[] propValues, String delEdge) {
		 BufferedWriter writer = null;
		    try 
		    {
		    	writer = new BufferedWriter(new java.io.FileWriter(delEdge));
		        
		        for (int i=0; i<propValues.length; i++)
		        { 
		        	writer.write(i+" "+propValues[i]);
		        	writer.newLine();
		        	writer.flush();
		        
		        }

		    } catch(IOException ex) {
		        ex.printStackTrace();
		    } finally{
		    	if(writer!=null){
		    		try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }  
		    }
	}

	public static void ScriptGnuplotTot(Graph<Integer, String> graph) {
		
		 BufferedWriter writer = null;
		    try 
		    {
		    	writer = new BufferedWriter(new java.io.FileWriter("gnuplotScriptTot.gpi"));
		             
		        writer.write("set grid");writer.newLine();
		        writer.write("set key outside");writer.newLine();
		        writer.write("set terminal png size 1500,1000");writer.newLine();
		        writer.write("set output \"outTOT.png\"");writer.newLine();
		        writer.write("set title \"Propagation Edge Centrality TOT(PEC)\"");writer.newLine();
		        writer.write("set xlabel \"Level\"");writer.newLine();
		        writer.write("set ylabel \"PEC\"");writer.newLine();
		       
		        writer.write("plotsGraph =  \"" );
		        		for(String edge :graph.getEdges())
		        			writer.write(edge+" "); 
		        		writer.write("\"");writer.newLine();
		        writer.write("plot for [pl in plotsGraph]  pl u 1:2 t pl w lp");writer.newLine();
		        writer.flush();

		    } catch(IOException ex) {
		        ex.printStackTrace();
		    } finally{
		    	if(writer!=null)
		    	{
		            try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
		        }  
		    }
			
		
	}

}
