package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Label;
import org.insa.graphs.model.LabelStar;
import org.insa.graphs.model.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    protected Label createLabel(int sommet) {
        final ShortestPathData data = getInputData();
        double dist = Point.distance(data.getGraph().get(sommet).getPoint(),data.getDestination().getPoint());
        return new LabelStar(sommet,false,Float.MAX_VALUE,null,dist);
    }

}