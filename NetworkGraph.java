// --== CS400 File Header Information ==--
// Name: Samuel Pekofsky
// Email: pekofsky@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Thanks for grading :^)

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;


public class NetworkGraph extends DijkstraGraph<Network, Double> implements NetworkGraphInterface {

	// instance variables	
	// where you are
	public Node currentNode;

	// constructor (for NetworkGraph class)
	public NetworkGraph() {
//		currentNode = nodes.get(new Network(curr)).data;
	}

	@Override
	public void moveCurrentNode(NetworkInterface net) {
		if(nodes.containsKey(net)) {
			currentNode = nodes.get(net);
		}
	}

	@Override
	public Network shortestNode(NetworkInterface start) {
		PriorityQueue<SearchNode> pq = new PriorityQueue<>();
		Node first = nodes.get(start);
		SearchNode pred = new SearchNode(first, 0, null);
		for(Edge edge: nodes.get(start).edgesLeaving) {
			if(edge.successor != edge.predecessor) pq.add(new SearchNode(edge.successor, edge.data, pred));
		}
		// should return nearest
		return pq.remove().node.data;
	}

	// randomize all weights
	@Override
	public void refreshNetworks() {
		Random rand = new Random();
		for(Network net: nodes.keySet()) {
			double[] vals = new double[nodes.get(net).edgesLeaving.size()];
			for(double val: vals) {
				val = rand.nextDouble(1, 1000);
			}
			int idx = 0;
			for(Edge edge: nodes.get(net).edgesLeaving) {
				edge.data = vals[idx];
				idx++;
			}
			idx = 0;
			for(Edge edge: nodes.get(net).edgesEntering) {
				edge.data = vals[idx];
				idx++;
			}
		}
	}

	// connect to nearest
	@Override
	public Network connectAvailableNetwork() {
		if(nodes.size() == 0) return null;
		return nodes.get(shortestNode(currentNode.data)).data;
	}

	public double getAverageSpeeds() {
		double count = 0;
		double total = 0;
		
		List<Edge> visited = new LinkedList<>();
		
		for(Network net: nodes.keySet()) {
			for(Edge edge: nodes.get(net).edgesLeaving) {
				if(!visited.contains(edge)) {
					count++;
					total += edge.data;
					visited.add(edge);
				}
			}
		}
		
		if(count > 0) return total/count;
		return -1;
	}

}
