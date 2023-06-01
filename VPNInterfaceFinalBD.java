import java.util.*;
import java.io.FileNotFoundException;

public interface VPNInterfaceFinalBD {
  // Attributes:
  // Location currLocation;
  // ArrayList<Location> locations;
  // ArrayList<Location> favorites;
  // Network currNetwork;

  // Constructor: public VPN()

  public void loadData(String file) throws FileNotFoundException;

  public void changeLocation(int index);

  public boolean addNetwork(String network);

  public boolean forgetNetwork(String network);

  public boolean containsLocation(String location);

  public int getNetworkCount(LocationInterfaceFinal location);

  public String displayFavoriteLocations();

  public NetworkInterfaceFinal getFastestNetwork();

  public NetworkInterfaceFinal getSlowestNetwork();

  public void toggleConnection(int index);

  public void addToFavorite(int index);

  public void removeFromFavorite(int index);

  public LocationInterfaceFinal getRandomLocation();

  public boolean containsNetwork(String name);

  public boolean favoritesContainsLocation(String l);

  public String displayAllNetworks();
}
