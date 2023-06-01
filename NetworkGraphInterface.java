// --== CS400 File Header Information ==--
// Name: Samuel Pekofsky
// Email: pekofsky@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Thanks for grading :^)

public interface NetworkGraphInterface {

	//instance variable	
	//public NetworkNodeInterface currentNode;

	//constructor (for NetworkGraph class)
	//public NetworkGraph();

	//moves node
	public void moveCurrentNode(NetworkInterface net);

	//gets most efficient Node
	public NetworkInterface shortestNode(NetworkInterface start);

	//randomizes all edge weights
	public void refreshNetworks();

	//returns a random, non-null node
	public Network connectAvailableNetwork();
	
}

