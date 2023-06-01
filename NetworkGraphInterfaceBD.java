
public interface NetworkGraphInterfaceBD{
		//instance variables	
		//public NetworkNodeInterface currentNode;

		//constructor (for NetworkGraph class)
		//public NetworkGraph();

		//moves node
		public void moveCurrentNode();
		
		//gets most efficient Node
		public NetworkInterface shortestNode(NetworkInterface start);
		
		public NetworkInterface connectAvailableNetwork();
		
}
