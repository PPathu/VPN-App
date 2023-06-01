
public class NetworkGraphBD extends DijkstraGraph<NetworkInterface,Double> implements NetworkGraphInterfaceBD {
	//instance variables	
	public NetworkBD startingNode;

	//moves node
	public void moveCurrentNode() {}
		
	//gets most efficient Node
	public NetworkInterface shortestNode(NetworkInterface start)
	{
		return null;
	}
	
	//longest path
	public NetworkInterface slowestNetwork(NetworkBD start)
	{
		return new NetworkBD("slowNetwork");
	}
	
	public NetworkBD connectAvailableNetwork() 
	{
		return new NetworkBD("randomNetwork"); 
	}
	
	public void refreshNetworks()
	{System.out.println("Networks have been refreshed");}
	
	

}
