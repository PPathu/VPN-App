
public class LocationFinalFD implements LocationInterfaceFinalFD {
    
    public NetworkGraphFinalFD graph;
    public String location;
    
    public LocationFinalFD(NetworkGraphFinalFD graph, String location)
    {
        this.graph = graph;
        this.location = location;
    }
    
    public boolean equals(LocationFinalFD loc)
    {
        if(location.equals(loc.location)) return true;
        return false;
    }
    
    public NetworkGraphFinalFD getGraph()
    {
        return graph;
    }
    
    public void setGraph(NetworkGraphFinalFD g)
    {
        graph = g;
    }
    
    public String getLocation()
    {
        return location;
    }

 

    
    
    
}
