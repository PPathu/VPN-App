//Placeholder class for DW
import java.util.*;

public class NetworkReaderBD implements NetworkReaderInterfaceBD{
	
	public ArrayList<Location> loadNetworkData(String file)
	{
		ArrayList<Location> obj = new ArrayList<>();
		obj.add(new Location(new NetworkGraphBD(),"USA"));
		obj.add(new Location(new NetworkGraphBD(),"Brazil"));
		obj.add(new Location(new NetworkGraphBD(),"China"));
		obj.add(new Location(new NetworkGraphBD(),"South Africa"));
		obj.add(new Location(new NetworkGraphBD(),"Germany"));
		return obj;
	}
	
	
	
	
}
