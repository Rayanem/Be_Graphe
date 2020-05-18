package org.insa.graphs.algotithm.shortestpath;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;


public class AstarPathTest extends ShortestPathTest{
	
	@Override
	public ShortestPathSolution makeChemin(ShortestPathData data) {
		return new AStarAlgorithm(data).doRun();
	}
	

}