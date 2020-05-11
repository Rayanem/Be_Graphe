package org.insa.graphes.model;

import static org.junit.Assert.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.junit.Test;

public class AlgoTest {
	
    // Small graph use for tests

	@Test
	public void test() {
	    Graph graph;
		String cheminMap = new String("C:/Users/Ali/Desktop/Git/Be_Graphe/maps/carre.mapgr");
	    File file = new File(cheminMap);
	    FileInputStream input1 = new FileInputStream(file);
	    DataInputStream dataInput1 = new DataInputStream(input1);
	    BinaryGraphReader binary1 = new BinaryGraphReader(dataInput1);
	}

}
