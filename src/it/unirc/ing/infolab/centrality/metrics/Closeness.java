package it.unirc.ing.infolab.centrality.metrics;

import java.util.Comparator;
import java.util.PriorityQueue;

import edu.uci.ics.jung.algorithms.scoring.ClosenessCentrality;
import edu.uci.ics.jung.graph.Graph;
import it.unirc.ing.infolab.centrality.util.Node;

public class Closeness {
	public static void compute(Graph<Integer,String> g)
	{
		ClosenessCentrality<Integer, String> cc = new ClosenessCentrality<Integer, String>(g);

		System.out.println("\nCloseness Centrality");
	
		  PriorityQueue<Node> pq =  new PriorityQueue<Node>(g.getVertexCount(), 
				  new Comparator<Node>() {    
              public int compare(Node w1, Node w2) {                         
                  return (w1.getScore()).compareTo(w2.getScore());  
              }      
          });
				
		for(Integer vertex:g.getVertices())
			pq.add(new Node(vertex,cc.getVertexScore(vertex)));
		
		int c=1;
		while(!pq.isEmpty()){
			Node n=pq.poll();
			System.out.print("Rank "+c+": "+n.getScore());
			System.out.println("\tVertex id: "+n.getId());
			c++;
		}
	}
}
