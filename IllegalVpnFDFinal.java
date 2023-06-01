// --== CS400 P3W3 ==--
// Name: Pratham Patel
// CSL Username: PPatel
// Email: ppatel@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IllegalVpnFDFinal implements IllegalVpnFDInterfaceFinal {
  Scanner scanner;
  boolean dataLoaded;
  ArrayList<String> inputs;
  private VPNFinal backend;

  public IllegalVpnFDFinal(VPNFinal backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
    dataLoaded = false;
    inputs = new ArrayList<>();
    for (int i = 0; i <= 15; i++) {
      inputs.add(Integer.toString(i));
    }
  }

  /**
   * This method displays a wide line of dashes to the console.
   */
  private void line() {
    System.out
        .println("-------------------------------------------------------------------------------");
  }

  @Override
  public void runOperationLoop() {
    String command = "";

    line(); // display welcome message
    System.out.println("Welcome to the Illegal VPN App.");
    line();

    while (!command.equals("0")) {
      command = this.mainMenuPrompt();

      switch (command) {
        case "1":
          loadDataOperation();
          break;
        case "2":
          addNetWork();
          break;
        case "3":
          getNetworkCount();
          break;
        case "4":
          forgetNetwork();
          break;
        case "5":
          changeLocation();
          break;
        case "6":
          containsLocation();
          break;
        case "7":
          addToFavorite();
          break;
        case "8":
          removeFromFavorite();
          break;
        case "9":
          displayFavoriteLocations();
          break;
        case "10":
          toggleConnection();
          break;
        case "11":
          displayAllNetworks();
          break;
        case "12":
          containsNetwork();
          break;
        case "13":
          getSpeedBetweenNetworks();
          break;
        case "14":
          favoritesContainsLocation();
          break;
        case "0":
          break;
        case "loadData":
          System.out.println("You need to load data first!");
          break;
        default:
          System.out.println(
              "Didn't recognize that command. Please type one of the numbers presented to identify the command you would like to choose.");
      }
      line();// called after every command
    }
    line();
    System.out.println("Thank you for using the Illegal VPN App.");
    line();
  }



  @Override
  public String mainMenuPrompt() {
    // Print menu of choices

    System.out.println("Please choose one of the following commands:");

    System.out.println("[1] Load data from file");
    System.out.println("[2] Add network to current location");
    System.out.println("[3] Get network count in current location");
    System.out.println("[4] Forget network in current location");
    System.out.println("[5] Change network location");
    System.out.println("[6] Show contained locations");
    System.out.println("[7] Add Location to favorites");
    System.out.println("[8] Remove Location from favorites");
    System.out.println("[9] Display favorite Locations");
    System.out.println("[10] Toggle network connection");
    System.out.println("[11] Display All Networks");
    System.out.println("[12] Contains network");
    System.out.println("[13] Get speed between networks");
    System.out.println("[14] Check favorites list");
    System.out.println("[0] Quit");
    System.out.print("Enter your command number: ");
    String input = scanner.nextLine();
    if (!inputs.contains(input)) {
      return " ";
    }
    if (!dataLoaded && !input.equals(("1")) && !input.equals("0")) {
      return "loadData";
    }
    return input;
  }


  @Override
  public void loadDataOperation() {
    System.out.print("Enter the name of the file to load: ");
    String filename = scanner.nextLine().trim();
    try {
      backend.loadData(filename);
      System.out.println("Data loaded from file.");
      dataLoaded = true;
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
    } catch (IllegalArgumentException e) {
      System.out.println("Data has already been loaded.\nPlease exit the app to load new data.");
    }
  }

  @Override
  public void addNetWork() {
    System.out.println("Enter the following information about the network to add to location:");
    System.out.print("Network name: ");
    String name = scanner.nextLine().trim();
    try {
      backend.addNetwork(name);
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN first using option 10!");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("Error: network has already been added");
      return;
    } catch (Exception e) {
      System.out.println("Error, try again");
      return;
    }
    System.out.println("Network added to VPN.");

  }

  @Override
  public void getNetworkCount() {
    try {
      System.out.println(backend.getNetworkCount(backend.currLocation));
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN first using option 10!");
    } catch (Exception e) {
      System.out.println("Error, try again");
    }

  }

  @Override
  public void forgetNetwork() {
    System.out.println("Enter the following information about the network to forget:");
    System.out.print("Network name: ");
    String name = scanner.nextLine();
    try {
      backend.forgetNetwork(name);
      System.out.println("Network removed!");
    } catch (NoSuchElementException e) {
      System.out.println("Error: network does not exist. Please enter a valid network.");
      return;
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN first using option 10!");
    } catch (Exception e) {
      System.out.println("error, try again. " + e);
    }
  }

  @Override
  public void changeLocation() {
    // TODO Auto-generated method stub
    // wait for locations menu
    System.out.println("Enter which location you would like to change to:");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid location");
      return;
    }
    int location = Integer.parseInt(name);

    try {
      backend.changeLocation(location);
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN before changing location");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter a index between 0 and 4");
      return;
    }
    System.out.println("changed location");
  }

  @Override
  public void containsLocation() {
    // TODO Auto-generated method stub
    // prompt user to search for location through list
    System.out.println(
        "Enter a location you would like to search for (type NetworkGraph after the name):");
    String location = scanner.nextLine().trim();
    if (!backend.containsLocation(location)) {
      System.out.println("Location not found");
    } else {
      System.out.println("Location found!");
    }
  }


  @Override
  public void addToFavorite() {
    // TODO Auto-generated method stub
    System.out.println("Enter which location you would like favorite(enter index from 0-"
        + backend.locations.size() + ", duplicate locations allowed):");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid location");
      return;
    }

    try {
      int location = Integer.parseInt(name);
      backend.addToFavorite(location);
      System.out.println("Favorited location");
    } catch (NumberFormatException e) {
      System.out.println("Please enter a valid index");
    } catch (NullPointerException e) {
      System.out.println("Error, try again by either connecting to VPN or entering a valid index");
    }
  }

  @Override
  public void removeFromFavorite() {
    // TODO Auto-generated method stub
    System.out.println(
        "Enter which location you would like to remove from favorites (enter index from 0-4):");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid location");
      return;
    }
    int location = Integer.parseInt(name);

    try {
      backend.removeFromFavorite(location);
      System.out.println("Un-favorited location");
    } catch (NullPointerException e) {
      System.out
          .println("Please enter valid index between 0 and " + (backend.locations.size() - 1));
    } catch (IllegalArgumentException e) {
      System.out.println("This location does not exist or does not exist in your favorites");
    }
  }


  @Override
  public void displayFavoriteLocations() {
    // TODO Auto-generated method stub
    System.out.println(backend.displayFavoriteLocations());
  }

  @Override
  public void toggleConnection() {
    // TODO Auto-generated method stub
    if (!backend.connected) {
      System.out.println("Enter which location you toggle the connection to:");
      System.out.print("Location index: ");
      String name = scanner.nextLine().trim();
      int location = Integer.parseInt(name);
      try {
        backend.toggleConnection(location);
        System.out.println("Toggled Connection to fastest network: " + backend.currNetwork);
        backend.connected = true;
      } catch (NullPointerException e) {
        System.out.println("Please enter a index between 0 and " + backend.locations.size());
        return;
      }
    }

    else if (backend.connected) {
      System.out.println("You are now disconnected.");
      backend.connected = false;
      backend.toggleConnection(-1);
    }
  }


  public void displayAllNetworks() {
    if (backend.currLocation != null) {
      System.out.println(backend.displayAllNetworks());
    } else {
      System.out.println("Please connect to VPN by using option 10");
    }
  }

  public void containsNetwork() {

    System.out.println("Enter which network you would like to search for:");
    System.out.print("network name: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid network");
      return;
    }

    try {

      System.out.println(backend.containsNetwork(name));
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN before searching");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter a valid network name");
      return;
    }
  }



  public void getSpeedBetweenNetworks() {

    System.out.println("Enter which network you would like to check the speed for:");
    System.out.print("network name: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid network");
      return;
    }
    try {
      System.out.println(backend.getSpeedBetweenNetworks(name));
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN before checking speed");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter a valid network name");
      return;
    }
  }


  public void favoritesContainsLocation() {

    System.out.println(
        "Enter a Location you would like to search in the favorites list for (type NetworkGraph after the name):");
    System.out.print("location name: ");
    String name = scanner.nextLine().trim();
    if (name.length() == 0) {
      System.out.println("Please enter a valid location");
      return;
    }
    try {
      System.out.println(backend.favoritesContainsLocation(name));
    } catch (NullPointerException e) {
      System.out.println("Please connect to the VPN before checking favorites list");
      return;
    } catch (IllegalArgumentException e) {
      System.out.println("Please enter a valid network name");
      return;
    }
  }

}


