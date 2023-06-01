import java.util.*;
import java.io.FileNotFoundException;

public interface VPNInterfaceFinal {
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

  public int getNetworkCount(LocationInterfaceFinalFD location);

  public String displayFavoriteLocations();

  public NetworkInterfaceFinalFD getFastestNetwork();

  public NetworkInterfaceFinalFD getSlowestNetwork();

  public void toggleConnection(int index);

  public void addToFavorite(int index);

  public void removeFromFavorite(int index);

  public LocationInterfaceFinalFD getRandomLocation();

  public boolean containsNetwork(String name);

  public boolean favoritesContainsLocation(String l);

  public String displayAllNetworks();
}
