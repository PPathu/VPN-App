// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

public interface NetworkGraphInterfaceDW {
    //instance variables
    //public NetworkNodeInterface currentNode;

    //constructor (for NetworkGraph class)
    //public NetworkGraph();

    //moves node
    public void moveCurrentNode();

    //gets most efficient Node
    public NetworkInterfaceDW shortestNode(NetworkInterfaceDW start);

    //randomizes all edge weights
    public void refreshNetworks();

    //returns a random, non-null node
    public void connectAvailableNetwork();

}