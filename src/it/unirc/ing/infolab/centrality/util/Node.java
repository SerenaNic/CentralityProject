package it.unirc.ing.infolab.centrality.util;

public class Node {

	private Integer id;
	private Double score;
	
	public Node(Integer id, Double score) {
		super();
		this.id = id;
		this.score = score;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	
}
