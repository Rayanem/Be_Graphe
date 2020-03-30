package org.insa.graphs.algorithm.shortestpath;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.*;
import org.insa.graphs.algorithm.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        List<Node> listNode = data.getGraph().getNodes();
        Graph graph = data.getGraph();
        Label[] labs = new Label[graph.size()];
        
        Arc ark;
        Label lb;
        Node nod; 
        Path path;
        
        boolean done = false;
        BinaryHeap<Label> bh = new BinaryHeap<Label>();
        
        for(int i=0;i<listNode.size();i++) {
        	lb = new Label(graph.get(i).getId(),false,Float.MAX_VALUE,null);
        	labs[graph.get(i).getId()]=lb;
        }
        
        labs[data.getOrigin().getId()].setCout(0);
        bh.insert(labs[data.getOrigin().getId()]);
        
        while(!bh.isEmpty() && !done) {
        	lb = bh.deleteMin();
        	lb.setMarque(true);
        	
        	if(data.getDestination().getId() == lb.getSommet())
        		done = true;
        	
        	for (Arc arc : graph.get(lb.getSommet()).getSuccessors()) {
				
				Label lb2 = labs[arc.getDestination().getId()];
				if (!lb2.isMarque()) {
					if (lb2.getCost() > lb.getCost() + arc.getLength()) {
						try {
							bh.remove(lb2);
							
						} catch (ElementNotFoundException e) {
							
						}
						
						lb2.setCout(lb.getCost() + arc.getLength());
						lb2.setPere(arc);					
						bh.insert(lb2);
						
					}
				}
			}
        }
        
        LinkedList<Arc> arks = new LinkedList<Arc>();
        nod = data.getDestination();
        if (labs[nod.getId()].getCost() != Float.MAX_VALUE ) {
			while (!(nod.equals(data.getOrigin()))) {
				arks.add(labs[nod.getId()].getPere());
				nod = labs[nod.getId()].getPere().getOrigin();
			}
			
			Collections.reverse(arks);
			path = new Path(graph, arks);
			solution = new ShortestPathSolution(data, AbstractSolution.Status.OPTIMAL, path);
		} 
        else
        	solution = new ShortestPathSolution(data, AbstractSolution.Status.INFEASIBLE);
        
        
        
        return solution;
    }	

}
