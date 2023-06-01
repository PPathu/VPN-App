
public interface LocationInterfaceFinal {	
	//attributes
	//GraphADT graph;
	// String location;

	//constructor:
	//public Location(GraphADT graph,String location)
	
	public boolean equals(LocationFinal loc);
	public NetworkGraphFinal getGraph();
	public void setGraph(NetworkGraphFinal graph);
	public String getLocation();
	public String toString();
}
