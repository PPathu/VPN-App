import java.util.*;
import java.io.FileNotFoundException;

public class VPNFinalBD implements VPNInterfaceFinalBD {
  // Attributes:
  LocationFinal currLocation;
  ArrayList<LocationFinal> locations;
  ArrayList<LocationFinal> favorites = new ArrayList<>();
  NetworkFinal currNetwork;
  boolean connected = false;

  // filling locations array with corresponding locations
  @Override
  public void loadData(String file) throws FileNotFoundException {
    if (locations != null)
      throw new IllegalArgumentException();
    NetworkReaderFinal reader = new NetworkReaderFinal();
    locations = reader.loadNetworkData(file);
    if (locations == null)
      throw new FileNotFoundException();
  }

  @Override
  public void changeLocation(int index) {
    if (currLocation == null)
      throw new NullPointerException();
    if (index >= locations.size() || index < 0)
      throw new IllegalArgumentException();
    currLocation = locations.get(index);
    currNetwork = currLocation.graph.shortestNode(currLocation.graph.currentNode.data);
  }

  // adds new network to the current location
  @Override
  public boolean addNetwork(String n) {
    if (currLocation == null)
      throw new NullPointerException("currLocation is null");
    Set<NetworkFinal> keys = currLocation.graph.nodes.keySet();
    for (NetworkFinal net : keys) {
      if (net.name.equals(n))
        throw new IllegalArgumentException("Network already exists in location");
    }

    NetworkFinal network = new NetworkFinal(n);
    NetworkFinal net = currLocation.graph.connectAvailableNetwork();

    try {
      double weight = Math.random() * 100;
      currLocation.graph.insertNode(network);
      currLocation.graph.insertEdge(net, network, weight);
      currNetwork = currLocation.graph.shortestNode(currLocation.graph.currentNode.data);
    } catch (Exception e) {
      System.out.println("Error occurred");
      return false;
    }

    return true;
  }

  // removes a network in the current location
  @Override
  public boolean forgetNetwork(String n) {
    if (currLocation == null)
      throw new NullPointerException();
    Set<NetworkFinal> keys = currLocation.graph.nodes.keySet();
    List<NetworkFinal> arr = new ArrayList<>(keys);
    NetworkFinal toUse = null;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i).name.equals(n)) {
        toUse = arr.get(i);
        break;
      }
    }
    if (toUse == null)
      throw new NoSuchElementException("Network does not exist");

    try {
      if (currNetwork.getName().equals(toUse.getName())) {
        currNetwork = null;
      }
      currLocation.graph.removeNode(toUse);
      currNetwork = currLocation.graph.shortestNode(currLocation.graph.currentNode.data);
    } catch (Exception e) {
      System.out.println("Error occurred");
      return false;
    }

    return true;
  }

  // Checks if a location connection is available through the vpn
  @Override
  public boolean containsLocation(String l) {
    if (locations == null)
      throw new NullPointerException();
    for (LocationFinal loc : locations) {
      if (loc.location.equals(l))
        return true;
    }

    return false;
  }

  // Checks if a location connection is available through the vpn
  @Override
  public boolean favoritesContainsLocation(String l) {
    if (favorites == null)
      throw new NullPointerException();
    for (LocationFinal loc : favorites) {
      if (loc.location.equals(l))
        return true;
    }

    return false;
  }

  // checks if current location contains network
  @Override
  public boolean containsNetwork(String name) {
    if (currLocation == null)
      throw new NullPointerException("current location is null");
    Set<NetworkFinal> keys = currLocation.graph.nodes.keySet();
    for (NetworkFinal network : keys) {
      if (network.name.equals(name))
        return true;
    }
    return false;
  }

  // Gets number of networks in current location
  @Override
  public int getNetworkCount(LocationInterfaceFinal location) {
    if (location == null)
      throw new NullPointerException("Location is null");
    return location.getGraph().nodes.size();
  }

  // Displays favorite locations in String format
  @Override
  public String displayFavoriteLocations() {
    if (locations == null)
      throw new NullPointerException();
    if (favorites.size() == 0)
      return "{EMPTY}";
    String str = "{ ";
    for (LocationFinal loc : favorites) {
      str += loc.location + ", ";
    }
    str = str.trim().substring(0, str.length() - 2);
    str += " }";

    return str;
  }

  // adds a designated location to user's favorite locations
  @Override
  public void addToFavorite(int index) {
    if (locations == null || index >= locations.size() || index < 0)
      throw new NullPointerException();
    favorites.add(locations.get(index));
  }

  // removes a designated location from user's favorite locations
  @Override
  public void removeFromFavorite(int index) {
    if (index >= locations.size() || index < 0)
      throw new NullPointerException();
    if (!favorites.contains(locations.get(index)))
      throw new IllegalArgumentException();
    favorites.remove(locations.get(index));
  }

  // returns fastest network in the current network (the network the user is automatically connected
  // to)
  @Override
  public NetworkInterfaceFinal getFastestNetwork() {
    if (currNetwork == null)
      throw new NullPointerException();
    return currNetwork;
  }

  // returns slowest network in the current location
  // calling AE method
  public NetworkInterfaceFinal getSlowestNetwork() {
    if (currLocation == null)
      throw new NullPointerException();
    return currLocation.graph.slowestNetwork(currLocation.graph.currentNode.data);
  }

  // gets the speed from current network to other network
  public double getSpeedBetweenNetworks(String name) {
    if (currLocation == null)
      throw new NullPointerException("current location is null");
    if (currNetwork == null)
      throw new NullPointerException("current network is null");
    if (!containsNetwork(name))
      throw new NoSuchElementException("given network not in graph");
    Set<NetworkFinal> keys = currLocation.graph.nodes.keySet();
    NetworkFinal network = null;
    for (NetworkFinal net : keys) {
      if (net.name.equals(name))
        network = net;
    }
    return currLocation.graph.shortestPathCost(currNetwork, network);
  }

  // app returns a random location for the user to connect to
  @Override
  public LocationInterfaceFinal getRandomLocation() {
    if (locations == null)
      throw new NullPointerException();
    int x = (int) (Math.random() * (locations.size() - 1));
    return locations.get(x);
  }


  // connects or disconnects user from vpn
  @Override
  public void toggleConnection(int index) {
    if (index != -1) {
      if (index >= locations.size())
        throw new NullPointerException();
      if (currLocation != null && !locations.get(index).location.equals(currLocation.location))
        throw new IllegalArgumentException();
      if (currLocation == null) {
        currLocation = locations.get(index);
        currNetwork = currLocation.graph.shortestNode(currLocation.graph.currentNode.data);
      }
    } else if (index == -1) {
      currLocation.graph.refreshNetworks();
      currLocation = null;
      currNetwork = null;
    }

  }

  public String displayAllNetworks() {
    // TODO Auto-generated method stub
    return null;
  }


}
