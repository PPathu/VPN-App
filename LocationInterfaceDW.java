// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

public interface LocationInterfaceDW {

    //attributes
    //GraphADT graph;
    // String location;

    //constructor:
    //public Location(GraphADT graph)

    public boolean equals(LocationDW loc);

    public NetworkGraphDW getGraph();

    public void setGraph(NetworkGraphDW graph);

    public String getLocation();

    public String toString();
}
