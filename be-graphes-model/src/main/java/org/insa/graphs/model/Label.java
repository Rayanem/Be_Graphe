package org.insa.graphs.model;

public class Label implements Comparable<Label>{
	
	private int sommet;
	private boolean marque;
	protected double cost;
	private Arc pere;
	
	
	public Label(int sommet, boolean marque, double cost, Arc pere) {
		this.sommet = sommet;
		this.marque = marque;
		this.cost = cost;
		this.pere = pere;
	}
	/*
	public Label(int id, Node dest, Node self) {
		// TODO Auto-generated constructor stub
	}*/

	public int getSommet() {
		return sommet;
	}
	public void setSommet(int sommet) {
		this.sommet = sommet;
	}
	public boolean isMarque() {
		return marque;
	}
	public void setMarque(boolean marque) {
		this.marque = marque;
	}
	public double getTotalCost() {
		return cost;
	}
	public double getCost() {
		return cost;
	}
	public void setCout(double d) {
		this.cost = d;
	}
	public Arc getPere() {
		return pere;
	}
	public void setPere(Arc pere) {
		this.pere = pere;
	}

	@Override
	public int compareTo(Label arg0) {
	return Double.compare(this.getTotalCost(), arg0.getTotalCost());
	}
	
	

}
