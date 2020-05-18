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
    
    protected Label createLabel(int sommet, float cout) {
    	return new Label(sommet,false,cout,null);
    }

    @Override
    public ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        List<Node> listNode = data.getGraph().getNodes();
        Graph graph = data.getGraph();
        Label[] labs = new Label[graph.size()];
        
        Label lb = null;
        Node nod; 
        Path path;
        
        boolean done = false;
        BinaryHeap<Label> bh = new BinaryHeap<Label>();
        
		lb = createLabel(graph.get(0).getId(),(float)0.0);
		labs[graph.get(0).getId()]=lb;
        for(int i=1;i<listNode.size();i++) {
        	lb = createLabel(graph.get(i).getId(),Float.MAX_VALUE);
        	labs[graph.get(i).getId()]=lb;
        }
        notifyOriginProcessed(data.getOrigin());
        
        labs[data.getOrigin().getId()].setCout(0);
        bh.insert(labs[data.getOrigin().getId()]);
        System.out.println((lb.getCost()));
        
        while(!bh.isEmpty() && !done) {
        	lb = bh.deleteMin();
        	lb.setMarque(true);
        	if(!bh.isValid())
        		System.out.println("Popipo petite erreur :poncerip:");
        	
        	if(data.getDestination().getId() == lb.getSommet())
        		done = true;
        	//System.out.println("nb successeurs : " + graph.get(lb.getSommet()).getNumberOfSuccessors()+"\n");
        	for (Arc arc : graph.get(lb.getSommet()).getSuccessors()) {
        		if (!data.isAllowed(arc)) {
                    continue;
                }
        		//System.out.println("Successeurs visités\n");
				notifyNodeReached(arc.getDestination());
				Label lb2 = labs[arc.getDestination().getId()];
				if (!lb2.isMarque()) {
					if (lb2.getCost() > lb.getCost() + getInputData().getCost(arc)) {
						try {
							bh.remove(lb2);
							
						} catch (ElementNotFoundException e) {
							
						}
						lb2.setCout(lb.getCost() + getInputData().getCost(arc));
						lb2.setPere(arc);					
						bh.insert(lb2);
						
					}
				}
			}
        }
        
        LinkedList<Arc> arks = new LinkedList<Arc>();
        nod = data.getDestination();
        if (labs[nod.getId()].getCost() != Float.MAX_VALUE && labs[nod.getId()].getPere() != null) {
        	notifyDestinationReached(data.getDestination());
			while (!(nod.equals(data.getOrigin()))) {
				arks.add(labs[nod.getId()].getPere());
				nod = labs[nod.getId()].getPere().getOrigin();
			}
			
			Collections.reverse(arks);
			path = new Path(graph, arks);
			System.out.println(path.isValid());
			if (Math.round(labs[data.getDestination().getId()].getCost()) == Math.round(path.getLength())) {
				  System.out.println("Route --> OK");
				}
			solution = new ShortestPathSolution(data, AbstractSolution.Status.OPTIMAL, path);
		} 
        else
        	solution = new ShortestPathSolution(data, AbstractSolution.Status.INFEASIBLE);
        
        
        
        return solution;
    }	

}
