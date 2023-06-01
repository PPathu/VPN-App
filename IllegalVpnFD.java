// --== CS400 P3W3 ==--
// Name: Pratham Patel
// CSL Username: PPatel
// Email: ppatel@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class IllegalVpnFD implements IllegalVpnFDInterface {

  Scanner scanner = new Scanner(System.in);

  private VpnFDBD backend;

  public IllegalVpnFD(VpnFDBD backend, Scanner scanner) {
    this.backend = backend;
    this.scanner = scanner;
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
        case "0":

          break;
        default:
          System.out.println(
              "Didn't recognize that command. Please type one of the numbers presented to identify the command you would like to choose.");
          break;

      }
    }
    line();
    System.out.println("Thank you for using the Illegal VPN App.");
    line();
  }



  @Override
  public String mainMenuPrompt() {
    // Print menu of choices

    line(); // display welcome message
    System.out.println("Welcome to the Illegal VPN App.");
    line();

    System.out.println("Please choose one of the following commands:");

    System.out.println("[1] Load data from file");
    System.out.println("[2] Add network");
    System.out.println("[3] Get network count");
    System.out.println("[4] Forget network");
    System.out.println("[5] Change network location");
    System.out.println("[6] Show contained locations");
    System.out.println("[7] Add network to favorites");
    System.out.println("[8] Remove network from favorites");
    System.out.println("[9] Display favorite locations");
    System.out.println("[10] Toggle network connection");
    System.out.println("[0] Quit");
    System.out.print("Enter your command number: ");
    String input = scanner.nextLine().trim();
    if (input.length() == 0) // if user's choice is blank then return null
      return null;
    if ((input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")
        || input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9")
        || input.equals("10"))) {
      System.out.println("You need to load data first!");
      runOperationLoop();

    } else {
      loadDataOperation();
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
    } catch (FileNotFoundException e) {
      System.out.println("Error: Could not find or load file: " + filename);
    }
  }

  @Override
  public void addNetWork() {
    System.out.println("Enter the following information about the network to add to location:");
    System.out.print("Network name: ");
    String name = scanner.nextLine().trim();

    if (name.contains(" ")) {
      String[] nameSplit = name.split(" ");
      nameSplit[0] = nameSplit[0].toUpperCase();
      name = nameSplit[0] + " " + nameSplit[1];
    }
    String[] nameSplit = name.split("");
    nameSplit[0] = nameSplit[0].toUpperCase();
    name = nameSplit[0];
    if (name.length() == 0) {
      System.out.println("Error: Network name cannot be blank.");
      return;
    }
    backend.addNetwork(name);
    System.out.println("");
    line();

  }

  @Override
  public void getNetworkCount() {
    System.out.println(backend.getNetworkCount(null));

  }

  @Override
  public void forgetNetwork() {
    System.out.println("Enter the following information about the network to forget:");
    System.out.print("Network name: ");
    String name = scanner.nextLine().trim();
    String[] nameSplit = name.split(" ");
    nameSplit[0] = nameSplit[0].toUpperCase();
    name = nameSplit[0] + " " + nameSplit[1];
    if (name.length() == 0) {
      System.out.println("Error: Network name cannot be blank.");
      return;
    }
    backend.forgetNetwork(name);
    System.out.println("");
    line();
  }

  @Override
  public void changeLocation() {
    // TODO Auto-generated method stub
    // wait for locations menu
    System.out.println("Enter which location you would like to change to:");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    int location = Integer.parseInt(name);

    backend.changeLocation(location);
    System.out.println("changed location");
    line();
  }

  @Override
  public void containsLocation() {
    // TODO Auto-generated method stub
    // prompt user to search for location through list
    System.out.println("Enter a location you would like to search for:");
    String location = scanner.nextLine().trim();
    String[] nameSplit = location.split(" ");
    nameSplit[0] = nameSplit[0].toUpperCase();
    location = nameSplit[0] + " " + nameSplit[1];
    if (location.length() == 0) {
      System.out.println("Error: Network name cannot be blank.");
      return;
    }
    backend.containsLocation(location);
    System.out.println("");
    line();
  }


  @Override
  public void addToFavorite() {
    // TODO Auto-generated method stub
    System.out.println("Enter which location you would like favorite:");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    int location = Integer.parseInt(name);

    backend.addToFavorite(location);
    System.out.println("Favorited location");
    line();
  }

  @Override
  public void removeFromFavorite() {
    // TODO Auto-generated method stub
    System.out.println("Enter which location you would like favorite:");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    int location = Integer.parseInt(name);

    backend.removeFromFavorite(location);
    System.out.println("Favorited location");
    line();
  }


  @Override
  public void displayFavoriteLocations() {
    // TODO Auto-generated method stub
    System.out.println(backend.displayFavoriteLocations());
  }

  @Override
  public void toggleConnection() {
    // TODO Auto-generated method stub
    System.out.println("Enter which location you toggle the connection to:");
    System.out.print("Location index: ");
    String name = scanner.nextLine().trim();
    int location = Integer.parseInt(name);

    backend.toggleConnection(location);
    System.out.println("Toggled Connection");
    line();
  }



}
