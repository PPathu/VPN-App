// --== CS400 File Header Information ==--
// Name: Aditya Pillai
// Email: appillai@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Prof. Florian


import java.util.PriorityQueue;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.Test;

/**
 * This class extends the BaseGraph data structure with additional methods for
 * computing the total cost and list of node data along the shortest path
 * connecting a provided starting to ending nodes.  This class makes use of
 * Dijkstra's shortest path algorithm.
 */
public class DijkstraGraph<NodeType, EdgeType extends Number>
    extends BaseGraph<NodeType,EdgeType>
    implements GraphADT<NodeType, EdgeType> {

    /**
     * While searching for the shortest path between two nodes, a SearchNode
     * contains data about one specific path between the start node and another
     * node in the graph.  The final node in this path is stored in it's node
     * field.  The total cost of this path is stored in its cost field.  And the
     * predecessor SearchNode within this path is referened by the predecessor
     * field (this field is null within the SearchNode containing the starting
     * node in it's node field).
     *
     * SearchNodes are Comparable and are sorted by cost so that the lowest cost
     * SearchNode has the highest priority within a java.util.PriorityQueue.
     */
    protected class SearchNode implements Comparable<SearchNode> {
        public Node node;
        public double cost;
        public SearchNode predecessor;
        public SearchNode(Node node, double cost, SearchNode predecessor) {
            this.node = node;
            this.cost = cost;
            this.predecessor = predecessor;
        }
        public int compareTo(SearchNode other) {
            if( cost > other.cost ) return +1;
            if( cost < other.cost ) return -1;
            return 0;
        }
    }

    /**
     * This helper method creates a network of SearchNodes while computing the
     * shortest path between the provided start and end locations.  The
     * SearchNode that is returned by this method is represents the end of the
     * shortest path that is found: it's cost is the cost of that shortest path,
     * and the nodes linked together through predecessor references represent
     * all of the nodes along that shortest path (ordered from end to start).
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return SearchNode for the final end node within the shortest path
     * @throws NoSuchElementException when no path from start to end is found
     *         or when either start or end data do not correspond to a graph node
     */
    protected SearchNode computeShortestPath(NodeType start, NodeType end) {
        if(!containsNode(start) || !containsNode(end)) throw new NoSuchElementException();
      
        Hashtable<NodeType,SearchNode>visited = new Hashtable<>(); 
        PriorityQueue<SearchNode>paths = new PriorityQueue<>();
        SearchNode starting = new SearchNode(nodes.get(start),0,null);
        SearchNode current;
        paths.add(starting);
        
        while(!paths.isEmpty()) {
        	current = paths.remove();
        	if(!visited.containsKey(current.node.data)) {
        		visited.put(current.node.data, current); 
        		for(Edge e : current.node.edgesLeaving) {
        			if(!visited.containsKey(e.successor.data)) {
        				paths.add(new SearchNode(e.successor, current.cost + e.data.doubleValue(),current));
        			}
        		}
        	}
        }
        
        if(!visited.containsKey(end)) throw new NoSuchElementException();
        return visited.get(end);
    }

    /**
     * Returns the list of data values from nodes along the shortest path
     * from the node with the provided start value through the node with the
     * provided end value.  This list of data values starts with the start
     * value, ends with the end value, and contains intermediary values in the
     * order they are encountered while traversing this shorteset path.  This
     * method uses Dijkstra's shortest path algorithm to find this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return list of data item from node along this shortest path
     */
    public List<NodeType> shortestPathData(NodeType start, NodeType end) {    	
    	List<NodeType> list = new LinkedList<>();
    	SearchNode path = computeShortestPath(start,end);
    	
    	while(path != null) {
    		list.add(0,path.node.data); 
    		path = path.predecessor;
    	}
    	
    	return list;
    }

    /**
     * Returns the cost of the path (sum over edge weights) of the shortest
     * path freom the node containing the start data to the node containing the
     * end data.  This method uses Dijkstra's shortest path algorithm to find
     * this solution.
     *
     * @param start the data item in the starting node for the path
     * @param end the data item in the destination node for the path
     * @return the cost of the shortest path between these nodes
     */
    public double shortestPathCost(NodeType start, NodeType end) {
    	return computeShortestPath(start,end).cost;
    }
    
    
//    //Method tests the lecture example results
//    @Test
//    public void testLectureExample()
//    {
//    	//Creating graph
//    	DijkstraGraph<String,Double>graph = new DijkstraGraph<>();
//    
//    	//nodes to add
//    	graph.insertNode("A");
//    	graph.insertNode("B");
//    	graph.insertNode("C");
//    	graph.insertNode("D");
//    	graph.insertNode("E");
//    	graph.insertNode("F");
//    	graph.insertNode("G");
//    	graph.insertNode("H");
//    	
//    	//connecting nodes
//    	graph.insertEdge("A","B",4.0);
//    	graph.insertEdge("A","E",15.0);
//    	graph.insertEdge("A","C",2.0);
//    	graph.insertEdge("C","D",5.0);
//    	graph.insertEdge("B","D",1.0);
//    	graph.insertEdge("B","E",10.0);
//    	graph.insertEdge("D","E",3.0);
//    	graph.insertEdge("D","F",0.0);
//    	graph.insertEdge("F","D",2.0);
//    	graph.insertEdge("F","H",4.0);
//    	graph.insertEdge("G","H",4.0);
//    	
//    	//testing lecture results are same as what is computed here
//    	
//    	//testing shortest path cost
//    	if(graph.shortestPathCost("A","E") != 8.0) Assertions.fail();
//    	
//    	//Testing node path
//    	String [] array = {"A","B","D","E"};
//    	List<String> list = graph.shortestPathData("A", "E");
//    	for(int i = 0; i < list.size(); i++)
//    	{
//    		if(!array[i].equals(list.get(i))) Assertions.fail();
//    	}
//    	
//    	//testing shortest path cost
//    	if(graph.shortestPathCost("A","F") != 5.0) Assertions.fail();
//    	
//    	//Testing node path
//    	String [] arr = {"A","B","D","F"};
//    	list = graph.shortestPathData("A", "F");
//    	for(int i = 0; i < list.size(); i++)
//    	{
//    		if(!arr[i].equals(list.get(i))) Assertions.fail();
//    	}
//    }
//    
//    //Method tests a different path between two nodes that was not tested in the above tester
//    @Test
//    public void secondTest()
//    {
//    	//Creating graph
//    	DijkstraGraph<String,Double>graph = new DijkstraGraph<>();
//    
//    	//nodes to add
//    	graph.insertNode("A");
//    	graph.insertNode("B");
//    	graph.insertNode("C");
//    	graph.insertNode("D");
//    	graph.insertNode("E");
//    	graph.insertNode("F");
//    	graph.insertNode("G");
//    	graph.insertNode("H");
//    	
//    	//connecting nodes
//    	graph.insertEdge("A","B",4.0);
//    	graph.insertEdge("A","E",15.0);
//    	graph.insertEdge("A","C",2.0);
//    	graph.insertEdge("C","D",5.0);
//    	graph.insertEdge("B","D",1.0);
//    	graph.insertEdge("B","E",10.0);
//    	graph.insertEdge("D","E",3.0);
//    	graph.insertEdge("D","F",0.0);
//    	graph.insertEdge("F","D",2.0);
//    	graph.insertEdge("F","H",4.0);
//    	graph.insertEdge("G","H",4.0);
//    	
//    	//testing shortest path cost 
//    	if(graph.shortestPathCost("A","H") != 9.0) Assertions.fail();
//    	
//    	//Testing node path
//    	String [] arr = {"A","B","D","F","H"};
//    	List<String>list = graph.shortestPathData("A", "H");
//    	for(int i = 0; i < list.size(); i++)
//    	{
//    		if(!arr[i].equals(list.get(i))) Assertions.fail();
//    	}
//    }
//    
//    //Tests when a path does not exist between two nodes
//    @Test
//    public void thirdTest()
//    {
//    	//Creating graph
//    	DijkstraGraph<String,Double>graph = new DijkstraGraph<>();
//    
//    	//nodes to add
//    	graph.insertNode("A");
//    	graph.insertNode("B");
//    	graph.insertNode("C");
//    	graph.insertNode("D");
//    	graph.insertNode("E");
//    	graph.insertNode("F");
//    	graph.insertNode("G");
//    	graph.insertNode("H");
//    	
//    	//connecting nodes
//    	graph.insertEdge("A","B",4.0);
//    	graph.insertEdge("A","E",15.0);
//    	graph.insertEdge("A","C",2.0);
//    	graph.insertEdge("C","D",5.0);
//    	graph.insertEdge("B","D",1.0);
//    	graph.insertEdge("B","E",10.0);
//    	graph.insertEdge("D","E",3.0);
//    	graph.insertEdge("D","F",0.0);
//    	graph.insertEdge("F","D",2.0);
//    	graph.insertEdge("F","H",4.0);
//    	graph.insertEdge("G","H",4.0);
//    	
//    	//testing result when no such path exists between two nodes
//    	try {graph.computeShortestPath("A", "G"); Assertions.fail();}
//    	catch(NoSuchElementException e) {}
//    	catch(Exception e) {Assertions.fail();}
//    }
    
}
