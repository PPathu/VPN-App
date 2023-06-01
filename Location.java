
public class Location implements LocationInterface {
	
	public NetworkGraphBD graph;
	public String location;
	
	public Location(NetworkGraphBD graph, String location)
	{
		this.graph = graph;
		this.location = location;
	}
	
	public boolean equals(Location loc)
	{
		if(location.equals(loc.location)) return true;
		return false;
	}
	
	public NetworkGraphBD getGraph()
	{
		return graph;
	}
	
	public void setGraph(NetworkGraphBD g)
	{
		graph = g;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	
	
}
