
public class LocationFinal implements LocationInterfaceFinal {
    
    public NetworkGraphFinal graph;
    public String location;
    
    public LocationFinal(NetworkGraphFinal graph, String location)
    {
        this.graph = graph;
        this.location = location;
    }
    
    public boolean equals(LocationFinal loc)
    {
        if(location.equals(loc.location)) return true;
        return false;
    }
    
    public NetworkGraphFinal getGraph()
    {
        return graph;
    }
    
    public void setGraph(NetworkGraphFinal g)
    {
        graph = g;
    }
    
    public String getLocation()
    {
        return location;
    }
    
    
    
}
