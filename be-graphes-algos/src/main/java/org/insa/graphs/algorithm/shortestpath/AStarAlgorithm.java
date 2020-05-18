package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import org.insa.graphs.model.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    protected Label createLabel(int sommet, float cout) {
        final ShortestPathData data = getInputData();
        double cost;
        if(getInputData().getMode() == AbstractInputData.Mode.LENGTH) {
        	cost = Point.distance(data.getGraph().get(sommet).getPoint(),data.getDestination().getPoint());
        }
        else {
        	cost = Point.distance(data.getGraph().get(sommet).getPoint(),data.getDestination().getPoint())/((float)data.getMaximumSpeed());
        }
        return new LabelStar(sommet,false,cout,null,cost);
    }

}