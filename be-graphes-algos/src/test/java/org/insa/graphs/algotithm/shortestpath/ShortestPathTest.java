package org.insa.graphs.algotithm.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.AbstractSolution;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public abstract class ShortestPathTest {
	

	private static ShortestPathData spd1;
	private static ShortestPathData spd2;
	private static ShortestPathData spd3;
	private static ShortestPathData spd4;
	private static ShortestPathData spd5;
	private static ShortestPathData spd6;
	private static ShortestPathData spd7;
	private static ShortestPathData spd8;
	private static ShortestPathData spd9;
	private static ShortestPathData spd10;
	
	public abstract ShortestPathSolution makeChemin(ShortestPathData data) ;
	
	@BeforeClass
	public static void test() throws IOException {
		
		String map1 = "C:/Users/Ali/Desktop/Git/Be_Graphe/maps/carre.mapgr";
		String map2 = "C:/Users/Ali/Desktop/Git/Be_Graphe/maps/madagascar.mapgr";
				
		BinaryGraphReader bgr1 = new BinaryGraphReader(new DataInputStream(new FileInputStream(map1)));	
		Graph carre = bgr1.read();
		
		BinaryGraphReader bgr2 = new BinaryGraphReader(new DataInputStream(new FileInputStream(map2)));	
		Graph mada = bgr2.read();
			
		//Carre
		Node nodCarreNul1 = carre.get(18);
		Node nodCarreNul2 = carre.get(18);
		
		Node nodCarreNormal1 = carre.get(13);
		Node nodCarreNormal2 = carre.get(2);
		
		//Madagascar
		Node nodMadaNul1 = mada.get(18);
		Node nodMadaNul2 = mada.get(18);
		
		Node nodMadaNormal1 = mada.get(35260);
		Node nodMadaNormal2 = mada.get(85496);
		
		Node nodMadaImpossible1 = mada.get(29495);
		Node nodMadaImpossible2 = mada.get(74616);
		
		
		
		//Chemins nuls
        
        spd1= new ShortestPathData(carre, nodCarreNul1, nodCarreNul2, ArcInspectorFactory.getAllFilters().get(0)); //shortest
        spd2= new ShortestPathData(carre, nodCarreNul1, nodCarreNul2, ArcInspectorFactory.getAllFilters().get(2)); //fastest
        
        spd3= new ShortestPathData(mada, nodMadaNul1, nodMadaNul2, ArcInspectorFactory.getAllFilters().get(0)); //shortest
        spd4= new ShortestPathData(mada, nodMadaNul1, nodMadaNul2	, ArcInspectorFactory.getAllFilters().get(2)); //fastest
        
        //Chemins normaux
        spd5= new ShortestPathData(carre, nodCarreNormal1, nodCarreNormal2, ArcInspectorFactory.getAllFilters().get(0)); //shortest
        spd6= new ShortestPathData(carre, nodCarreNormal1, nodCarreNormal2, ArcInspectorFactory.getAllFilters().get(2)); //fastest
        
        spd7= new ShortestPathData(mada, nodMadaNormal1, nodMadaNormal2, ArcInspectorFactory.getAllFilters().get(0)); //shortest
        spd8= new ShortestPathData(mada, nodMadaNormal1, nodMadaNormal2, ArcInspectorFactory.getAllFilters().get(2));//fastest
		
        
      //Chemins impossibles
        spd9= new ShortestPathData(mada, nodMadaImpossible1, nodMadaImpossible2, ArcInspectorFactory.getAllFilters().get(0)); //shortest
        spd10= new ShortestPathData(mada, nodMadaImpossible1, nodMadaImpossible2, ArcInspectorFactory.getAllFilters().get(2)); //fastest

        
        bgr1.close();
        bgr2.close();
	}
	
	//Nuls
	@Test
    public void testCheminValid1() {		
		assertTrue((makeChemin(spd1)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }
	
	@Test
    public void testCheminValid2() {		
		assertTrue((makeChemin(spd2)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }
	
	@Test
    public void testCheminValid3() {		
		assertTrue((makeChemin(spd3)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }
	
	@Test
    public void testCheminValid4() {		
		assertTrue((makeChemin(spd4)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }

	//Normaux
	
	@Test
    public void testCheminValid5() {		
		double resultBellman = (new BellmanFordAlgorithm(spd5).run().getPath().getLength());
		assertEquals((long)((makeChemin(spd5)).getPath().getLength()),(long)(resultBellman));
    }
	
	@Test
    public void testCheminValid6() {		
		double resultBellman = (new BellmanFordAlgorithm(spd6).run().getPath().getLength());
		assertEquals((long)((makeChemin(spd6)).getPath().getLength()),(long)(resultBellman));
    }
	
	@Test
    public void testCheminValid7() {		
		double resultBellman = (new BellmanFordAlgorithm(spd7).run().getPath().getLength());
		assertEquals((long)((makeChemin(spd7)).getPath().getLength()),(long)(resultBellman));
    }
	
	@Test
    public void testCheminValid8() {		
		double resultBellman = (new BellmanFordAlgorithm(spd8).run().getPath().getLength());
		assertEquals((long)((makeChemin(spd8)).getPath().getLength()),(long)(resultBellman));
    }
	
	//Infaisables
	@Test
    public void testCheminValid9() {		
		assertTrue((makeChemin(spd9)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }
	
	@Test
    public void testCheminValid10() {		
		assertTrue((makeChemin(spd10)).getStatus().equals(AbstractSolution.Status.INFEASIBLE));
    }
	
	
}