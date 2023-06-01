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
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;


public class NetworkGraphFinal extends DijkstraGraph<NetworkFinal, Double> implements NetworkGraphInterfaceFinal {

	// instance variables	
	// where you are
	public Node currentNode = new Node(new NetworkFinal("starting"));

	// constructor (for NetworkGraph class)
	public NetworkGraphFinal() {
	}

	@Override
	public void moveCurrentNode(NetworkInterfaceFinal net) {
		if(nodes.containsKey(net)) {
			currentNode = nodes.get(net);
		}
	}

	@Override
	public NetworkFinal shortestNode(NetworkInterfaceFinal start) {
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
        for (NetworkFinal network : nodes.keySet()){
            Node node = nodes.get(network);
            for (int i = 0; i < node.edgesEntering.size(); i++){
                Edge temp = node.edgesEntering.get(i);
                temp.data = temp.data * (Math.random() * 2) + 1;
                node.edgesEntering.set(i, temp);
            }
            for (int i = 0; i < node.edgesLeaving.size(); i++){
                Edge temp = node.edgesLeaving.get(i);
                temp.data = temp.data * (Math.random() * 2) + 1;
                node.edgesLeaving.set(i, temp);
            }
        }
    }

	// connect to nearest
	@Override
	public NetworkFinal connectAvailableNetwork() {
		if(nodes.size() == 0) return null;
		return nodes.get(shortestNode(currentNode.data)).data;
	}

	public double getAverageSpeeds() {
		double count = 0;
		double total = 0;
		
		List<Edge> visited = new LinkedList<>();
		
		for(NetworkFinal net: nodes.keySet()) {
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
	
	public NetworkFinal slowestNetwork(NetworkInterfaceFinal start) {
		PriorityQueue<SearchNode> pq = new PriorityQueue<>();
		Node first = nodes.get(start);
		SearchNode pred = new SearchNode(first, 0, null);
		for(Edge edge: nodes.get(start).edgesLeaving) {
			if(edge.successor != edge.predecessor) pq.add(new SearchNode(edge.successor, edge.data, pred));
		}
		NetworkFinal n = null;
		// should return farthest(slowest)
		while(!pq.isEmpty())
		{
			n = pq.remove().node.data;
		}
		return n;
	}

}
