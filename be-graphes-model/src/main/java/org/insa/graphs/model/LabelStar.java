package org.insa.graphs.model;

public class LabelStar extends Label {
    
    private double costEstime;
    
    public LabelStar(int sommet, boolean marque, float cost, Arc pere, double costEstime) {
        super(sommet, marque, cost, pere);
        this.costEstime= costEstime;
    }
    
    public double getCostEstime() {
        return costEstime;
    }
    
    @Override
    public double getTotalCost() {
        return cost+costEstime;
    }
    
    public void setCosts(double cost, double costEstime) {
        this.cost = cost;
        this.costEstime = costEstime;
    }

    
}