// --== CS400 P3W3 ==--
// Name: Pratham Patel
// CSL Username: PPatel
// Email: ppatel@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.tools.DocumentationTool.Location;

public class VpnFDBD implements VPNInterfaceFD {


  LocationFinalFD currLocation;
  ArrayList<LocationFinalFD> locations;
  ArrayList<LocationFinalFD> favorites = new ArrayList<>();
  NetworkFinalFD currNetwork;
  boolean connected = false;

  // filling locations array with corresponding locations
  @Override
  public void loadData(String file) throws FileNotFoundException {
    throw new FileNotFoundException();
  }

  @Override
  public void changeLocation(int index) {

  }

  // adds new network to the current location
  @Override
  public boolean addNetwork(String network) {
    return false;

  }
 
  // removes a network in the current location
  @Override
  public boolean forgetNetwork(String network) {
    return false;


  }

  // Checks if a location connection is available through the vpn
  @Override
  public boolean containsLocation(String location) {
    return true;

  }

  // Gets number of networks in current location
  @Override
  public int getNetworkCount(String location) {
    return 2;

  }

  // Displays favorite locations in String format
  @Override
  public String displayFavoriteLocations() {
    return "New York\n Los Angeles\n Chicago\n Houston\n Miami\n Seattle\n";

  }

  // adds a designated location to user's favorite locations
  @Override
  public void addToFavorite(int index) {

  }

  // removes a designated location from user's favorite locations
  @Override
  public void removeFromFavorite(int index) {

  }

  // app returns a reccomended location for the user to connect to
  @Override
  public LocationInterface getRecommendedLocation() {
    return null;

  }

  // connects and disconnects user from vpn
  @Override
  public void toggleConnection(int index) {

  }

  public char[] containsNetwork(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  public char[] getSpeedBetweenNetworks(String name) {
    // TODO Auto-generated method stub
    return null;
  }



}
