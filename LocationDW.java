// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

public class LocationDW implements LocationInterfaceDW {
    public NetworkGraphDW graph;
    public String location;
    public LocationDW(NetworkGraphDW graph, String location) {
        this.graph = graph;
        this.location = location;
    }

    @Override
    public boolean equals(LocationDW loc) {
        return false;
    }

    @Override
    public NetworkGraphDW getGraph() {
        return graph;
    }

    @Override
    public void setGraph(NetworkGraphDW graph) {

    }

    @Override
    public String getLocation() {
        return location;
    }
}
