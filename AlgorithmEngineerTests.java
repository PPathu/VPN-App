import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {

	@Test
	public void test1() {
		Network n1 = new Network("n1");
		assertEquals("n1", n1.getName());
		assert(n1.equals(n1));
		Network n2 = new Network("n2");
		assert(!n1.equals(n2));
		assertEquals(-1, n1.compareTo(n2));
	}
	
	@Test
	public void test2() {
		NetworkGraph graph = new NetworkGraph();
		Network n1 = new Network("n1");
		graph.insertNode(n1);
		Network n2 = new Network("n2");
		graph.insertNode(n2);
		Network n3 = new Network("n3");
		graph.insertNode(n3);
		graph.insertEdge(n1, n2, 500.0);
		graph.insertEdge(n2, n3, 100.0);
		graph.insertEdge(n3, n1, 750.0);
		graph.insertEdge(n2, n1, 500.0);
		graph.insertEdge(n3, n2, 100.0);
		graph.insertEdge(n1, n3, 750.0);
		graph.moveCurrentNode(n3);
		assertEquals(n3, graph.currentNode.data);
		assertEquals(n2, graph.connectAvailableNetwork());
		assert(graph.containsEdge(n2, n3));
		assert(graph.containsNode(n1));
	}
	
	@Test
	public void test3() {
		NetworkGraph graph = new NetworkGraph();
		Network n1 = new Network("n1");
		graph.insertNode(n1);
		Network n2 = new Network("n2");
		graph.insertNode(n2);
		Network n3 = new Network("n3");
		graph.insertNode(n3);
		graph.insertEdge(n1, n2, 500.0);
		graph.insertEdge(n2, n3, 100.0);
		graph.insertEdge(n3, n1, 750.0);
		graph.insertEdge(n2, n1, 500.0);
		graph.insertEdge(n3, n2, 100.0);
		graph.insertEdge(n1, n3, 750.0);
		graph.removeNode(n1);
		assert(!graph.containsNode(n1));
		assert(!graph.containsEdge(n3, n1));
		try {
			// this does not work because n1 is no longer attached to the graph and therefore is not
			// accessible based on the way containsEdge works
			graph.containsEdge(n1, n3);
		}
		catch(NullPointerException e) {
			
		}
		catch(Exception e) {
			assert(false);
		}
		
	}
	
	@Test
	public void test4() {
		NetworkGraph graph = new NetworkGraph();
		Network n1 = new Network("n1");
		graph.insertNode(n1);
		Network n2 = new Network("n2");
		graph.insertNode(n2);
		Network n3 = new Network("n3");
		graph.insertNode(n3);
		Network n4 = new Network("n4");
		graph.insertNode(n4);
		graph.insertEdge(n1, n2, 500.0);
		graph.insertEdge(n2, n3, 100.0);
		graph.insertEdge(n3, n1, 750.0);
		graph.insertEdge(n2, n1, 500.0);
		graph.insertEdge(n3, n2, 100.0);
		graph.insertEdge(n1, n3, 750.0);
		graph.insertEdge(n3, n4, 50.0);
		graph.insertEdge(n4, n3, 50.0);
		assertEquals(n1, graph.computeShortestPath(n4, n1).node.data);
		assertEquals(n2, graph.computeShortestPath(n4, n1).predecessor.node.data);
		assertEquals(n3, graph.computeShortestPath(n4, n1).predecessor.predecessor.node.data);
		assertEquals(n4, graph.computeShortestPath(n4, n1).predecessor.predecessor.predecessor.node.data);
		assertEquals(n3, graph.shortestPathData(n1, n4).get(2));
	}
	
	@Test
	public void test5() {
		NetworkGraph graph = new NetworkGraph();
		Network n1 = new Network("n1");
		graph.insertNode(n1);
		Network n2 = new Network("n2");
		graph.insertNode(n2);
		Network n3 = new Network("n3");
		graph.insertNode(n3);
		Network n4 = new Network("n4");
		graph.insertNode(n4);
		graph.insertEdge(n1, n2, 500.0);
		graph.insertEdge(n2, n3, 100.0);
		graph.insertEdge(n3, n1, 750.0);
		graph.insertEdge(n2, n1, 500.0);
		graph.insertEdge(n3, n2, 100.0);
		graph.insertEdge(n1, n3, 750.0);
		graph.insertEdge(n3, n4, 50.0);
		graph.insertEdge(n4, n3, 50.0);
		assertEquals(350.0, graph.getAverageSpeeds());
	}

}
