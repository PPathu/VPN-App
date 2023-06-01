import java.util.*;
import java.io.FileNotFoundException;

public class VPNFinal implements VPNInterfaceFinal {
 
  LocationFinalFD currLocation;
  ArrayList<LocationFinalFD> locations;
  ArrayList<LocationFinalFD> favorites = new ArrayList<>();
  NetworkFinalFD currNetwork;
  boolean connected = false;

  
  public void loadData(String file) throws FileNotFoundException {
    
  }

  
  public void changeLocation(int index) {
    
  }

  public boolean addNetwork(String n) {
    
   
    return true;
  }

  
  public boolean forgetNetwork(String n) {
   
    return true;
  }

 
  public boolean containsLocation(String l) {
   
    return false;
  }

  
  public boolean favoritesContainsLocation(String l) {
    
    return false;
  }

  
  public boolean containsNetwork(String name) {
    
    return false;
  }

  
  public int getNetworkCount(LocationInterfaceFinalFD location) {
    return 0;
    
  }

 
  public String displayFavoriteLocations() {
    return null;
   
    
  }

  
  public void addToFavorite(int index) {
   
  }

  
  public void removeFromFavorite(int index) {
   
  }

 
  public NetworkInterfaceFinalFD getFastestNetwork() {
    return null ;
    
    
  }

 
  public NetworkInterfaceFinalFD getSlowestNetwork() {
    return currNetwork;
  
  }

 
  public double getSpeedBetweenNetworks(String name) {
    return 0;
  
  }

  
  public LocationInterfaceFinalFD getRandomLocation() {
    return null;
    
    
   
  }


  
  public void toggleConnection(int index) {
  
  }


  
  public String displayAllNetworks() {
    return null;
   
    
  }


  


 
 



  





}

