package it.unirc.ing.infolab.centrality.metrics;


import edu.uci.ics.jung.algorithms.importance.BetweennessCentrality;
import edu.uci.ics.jung.graph.Graph;

public class Betweenness {

	public static void compute(Graph<Integer,String> g)
	{
		BetweennessCentrality<Integer, String> bc = new BetweennessCentrality<Integer, String>(g);
		bc.evaluate();
		System.out.println("Betweenness Centrality");
		bc.printRankings(true,true);
	}
}
