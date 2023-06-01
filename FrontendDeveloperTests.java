// --== CS400 P3W3 ==--
// Name: Pratham Patel
// CSL Username: PPatel
// Email: ppatel@wisc.edu
// Lecture #: (3:30 - 4:20) 4
// Notes to Grader: N/A

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FrontendDeveloperTests {


  private VpnFDBD frontend;

  @BeforeEach
  public void setUp() {
    this.frontend = new VpnFDBD();
  }


  // Tests the frontend to see if it can pull the network count from the backend placeholder class
  @Test
  public void frontendTest1() {
    frontend = new VpnFDBD();
    new IllegalVpnFD(frontend, new Scanner(System.in));
    int count = frontend.getNetworkCount(null);
    Assertions.assertEquals(2, frontend.getNetworkCount(null),
        "Network count not retrieved successfully");
  }

  // Tests the frontend to see if it load from the backend placeholder class
  @Test
  public void frontendTest2() {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

      String testInput = "1\nTest.dot\n\n0\n";
      Scanner scanner = new Scanner(testInput);
      VpnFDBD backendTest = new VpnFDBD();
      IllegalVpnFD frontend = new IllegalVpnFD(backendTest, scanner);

      // temporarily redirect standard output to a null stream
      PrintStream nullStream = new PrintStream(new OutputStream() {
        public void write(int b) {
          // do nothing
        }
      });
      PrintStream originalOut = System.out;
      System.setOut(nullStream);

      // run the operation loop
      frontend.runOperationLoop();

      // restore the original standard output
      System.setOut(originalOut);

      // compare the output to the expected value as before
      Assertions.assertEquals(
          "-------------------------------------------------------------------------------\n"
              + "Welcome to the Illegal VPN App.\n"
              + "-------------------------------------------------------------------------------\n"
              + "Please choose one of the following commands:\n" + "[1] Load data from file\n"
              + "[2] Add network\n" + "[3] Get network count\n" + "[4] Forget network\n"
              + "[5] Change network location\n" + "[6] Show contained locations\n"
              + "[7] Add network to favorites\n" + "[8] Remove network from favorites\n"
              + "[9] Display favorite locations\n" + "[10] Toggle network connection\n"
              + "[0] Quit\n"
              + "Enter your command number: Enter the name of the file to load: Error: Could not find or load file: Test.dot\n"
              + "Enter the name of the file to load: Error: Could not find or load file: \n"
              + "-------------------------------------------------------------------------------\n"
              + "Welcome to the Illegal VPN App.\n"
              + "-------------------------------------------------------------------------------\n"
              + "Please choose one of the following commands:\n" + "[1] Load data from file\n"
              + "[2] Add network\n" + "[3] Get network count\n" + "[4] Forget network\n"
              + "[5] Change network location\n" + "[6] Show contained locations\n"
              + "[7] Add network to favorites\n" + "[8] Remove network from favorites\n"
              + "[9] Display favorite locations\n" + "[10] Toggle network connection\n"
              + "[0] Quit\n"
              + "Enter your command number: Enter the name of the file to load: Scanner ran out of input",
          byteStream.toString());
    } catch (NoSuchElementException e) {
      System.out.println("Scanner ran out of input");
    }
  }

  // Tests the frontend to see if it can forget the network from the backend placeholder class
  @Test
  public void frontendTest3() {

    assertEquals(false, frontend.forgetNetwork("10.0.0.1"));
    assertEquals(false, frontend.forgetNetwork("192.168.1.1"));
    assertEquals(false, frontend.forgetNetwork("172.16.0.1"));
    assertEquals(false, frontend.forgetNetwork("192.168.0.10"));
    assertEquals(false, frontend.forgetNetwork("10.10.0.1"));
    assertEquals(false, frontend.forgetNetwork("192.168.1.100"));
    assertEquals(false, frontend.forgetNetwork("172.20.0.1"));
  }

  // Tests the frontend to see if it can call the containsLocation from the backend placeholder
  // class
  @Test
  public void frontendTest4() {
    assertEquals(true, frontend.containsLocation("New York"));
    assertEquals(true, frontend.containsLocation("San Francisco"));
    assertEquals(true, frontend.containsLocation("Los Angeles"));
    assertEquals(true, frontend.containsLocation("Chicago"));
    assertEquals(true, frontend.containsLocation("Miami"));
    assertEquals(true, frontend.containsLocation("Seattle"));
    assertEquals(true, frontend.containsLocation("Boston"));
    assertEquals(true, frontend.containsLocation("Houston"));
    assertEquals(true, frontend.containsLocation("Toronto"));
    assertEquals(true, frontend.containsLocation("London"));
    assertEquals(true, frontend.containsLocation("Paris"));
    assertEquals(true, frontend.containsLocation("Sydney"));
    assertEquals(true, frontend.containsLocation("Tokyo"));
    assertEquals(true, frontend.containsLocation("Beijing"));
    assertEquals(true, frontend.containsLocation("Mumbai"));

  }

  // Tests the frontend to see if it displays the favorite networks from the backend placeholder
  // class
  @Test
  public void frontendTest5() {
    assertEquals("New York\n Los Angeles\n Chicago\n Houston\n Miami\n Seattle\n",
        frontend.displayFavoriteLocations());
  }

  @Test
  /**
   * tests each role's basic functionality through a basic example.
   */
  public void integrationTest1() {
    try {
      // Test the correctness of data loaded from the file
      VPNFinal backend = new VPNFinal();
      backend.loadData("NetworkGraph.dot");

      Assert.assertEquals(backend.locations.get(0).location, "USANetworkGraph");
      Assert.assertEquals(backend.locations.get(2).location, "RussiaNetworkGraph");


      // Test that the graph is correctly loaded, specifically that no other locations were added
      try {
        Assert.assertEquals(backend.locations.get(9).location, "USANetworkGraph");
        Assert.fail();
      } catch (Exception e) {
        // Expected behavior
      }

      Assert.assertEquals(0, backend.favorites.size());
      Assert.assertEquals(true, backend.containsLocation("USANetworkGraph"));

    } catch (FileNotFoundException e) {
      Assert.fail();
    }
  }



  /**
   * tests getting the fastest networks at a certain location which utilizes Dijkstra's algorithm
   */
  @Test
  public void integrationTest2() {
    try {

      VPNFinal backend = new VPNFinal();
      backend.loadData("NetworkGraph.dot");

      backend.toggleConnection(1);

      // connected to fastest network
      Assert.assertEquals(backend.currNetwork.name, "ChinaNetwork2");

      // Test toggleConnection disconnects user
      backend.toggleConnection(-1);
      Assert.assertEquals(null, backend.currNetwork);

      Assert.assertEquals(false, backend.connected);

      Assert.assertEquals(true, backend.containsLocation("USANetworkGraph"));

      // test that the getSlowestNetwork returns the correct value for a different Location

      backend.toggleConnection(3);
      Assert.assertEquals("BrazilNetwork2", backend.getSlowestNetwork().getName());
    } catch (Exception e) {
      Assert.fail();
    }
  }



  /**
   * Test method that tests the functionality of the backend developer's code in detail
   */
  @Test
  public void codeReviewOfBackendDeveloper1() {
    try {
      // Setup the backend with data from NetworkGraph.dot
      VPNFinal backend = new VPNFinal();
      backend.loadData("NetworkGraph.dot");

      // Test that each individual subgraph was correctly loaded from the file, and that the backend
      // can
      // correctly process each Location object
      Assert.assertEquals(false, backend.connected);
      Assert.assertEquals("USANetworkGraph", backend.locations.get(0).getLocation());
      Assert.assertEquals("ChinaNetworkGraph", backend.locations.get(1).getLocation());
      Assert.assertEquals("RussiaNetworkGraph", backend.locations.get(2).getLocation());
      Assert.assertEquals("BrazilNetworkGraph", backend.locations.get(3).getLocation());
      Assert.assertEquals("IndiaNetworkGraph", backend.locations.get(4).getLocation());

      // Connect to India's NetworkGraph, test that it correctly retrieve the fastest
      // connection
      backend.toggleConnection(4);
      Assert.assertEquals("IndiaNetwork4", backend.getFastestNetwork().getName());

      // Test that containsNetwork works
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork1"));
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork2"));
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork3"));
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork4"));
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork5"));
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetwork6"));



      // check for file
    } catch (FileNotFoundException e) {
      Assert.fail("File not found exception incorrectly thrown.");
    }
  }

  /**
   * Test method that tests the functionality of the backend developer's code in detail
   */
  @Test
  public void codeReviewOfBackendDeveloper2() {
    try {
      // Setup the backend with data from NetworkGraph.dot
      VPNFinal backend = new VPNFinal();
      backend.loadData("NetworkGraph.dot");


      // Connect to India's NetworkGraph, test that it correctly retrieve the fastest
      // connection
      backend.toggleConnection(4);
      Assert.assertEquals("IndiaNetwork4", backend.getFastestNetwork().getName());

      // test that adding a network works correctly
      backend.addNetwork("IndiaNetworkX");
      Assert.assertEquals(true, backend.containsNetwork("IndiaNetworkX"));

      // test that the network count was updated
      Assert.assertEquals(7, backend.getNetworkCount(backend.locations.get(4)));



    } catch (FileNotFoundException e) {

    }
  }

}


