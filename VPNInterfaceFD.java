import java.io.FileNotFoundException;

public interface VPNInterfaceFD {

  void loadData(String file) throws FileNotFoundException;

  void changeLocation(int index);

  boolean addNetwork(String network);

  boolean forgetNetwork(String network);

  boolean containsLocation(String location);

  int getNetworkCount(String location);

  String displayFavoriteLocations();

  void addToFavorite(int index);

  void removeFromFavorite(int index);

  LocationInterface getRecommendedLocation();

  void toggleConnection(int index);

  



}
