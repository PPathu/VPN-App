// --== CS400 File Header Information ==--
// Name: Shivam Ratnani
// Email: ratnani@wisc.edu
// Group and Team: DR Blue
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: How are you doing today?

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.util.*;

public class DataWranglerTests {
    NetworkReader reader = new NetworkReader();

    @Test
    public void testUSA() throws FileNotFoundException {
        ArrayList<LocationDW> locations = reader.loadNetworkData("NetworkGraph.dot");
        Assertions.assertEquals(5, locations.size());
        Assertions.assertEquals("USANetworkGraph", locations.get(0).getLocation());
        Assertions.assertEquals(6 , locations.get(0).getGraph().getNodeCount());
        Assertions.assertEquals(9 , locations.get(0).getGraph().getEdgeCount());
    }

    @Test
    public void testChina() throws FileNotFoundException {
        ArrayList<LocationDW> locations = reader.loadNetworkData("NetworkGraph.dot");
        Assertions.assertEquals(5, locations.size());
        Assertions.assertEquals("ChinaNetworkGraph", locations.get(1).getLocation());
        Assertions.assertEquals(6 , locations.get(1).getGraph().getNodeCount());
        Assertions.assertEquals(9 , locations.get(1).getGraph().getEdgeCount());
    }

    @Test
    public void testRussia() throws FileNotFoundException {
        ArrayList<LocationDW> locations = reader.loadNetworkData("NetworkGraph.dot");
        Assertions.assertEquals(5, locations.size());
        Assertions.assertEquals("RussiaNetworkGraph", locations.get(2).getLocation());
        Assertions.assertEquals(6 , locations.get(2).getGraph().getNodeCount());
        Assertions.assertEquals(9 , locations.get(2).getGraph().getEdgeCount());
    }

    @Test
    public void testBrazil() throws FileNotFoundException {
        ArrayList<LocationDW> locations = reader.loadNetworkData("NetworkGraph.dot");
        Assertions.assertEquals(5, locations.size());
        Assertions.assertEquals("BrazilNetworkGraph", locations.get(3).getLocation());
        Assertions.assertEquals(6 , locations.get(3).getGraph().getNodeCount());
        Assertions.assertEquals(9 , locations.get(3).getGraph().getEdgeCount());
    }

    @Test
    public void testIndia() throws FileNotFoundException {
        ArrayList<LocationDW> locations = reader.loadNetworkData("NetworkGraph.dot");
        Assertions.assertEquals(5, locations.size());
        Assertions.assertEquals("IndiaNetworkGraph", locations.get(4).getLocation());
        Assertions.assertEquals(6 , locations.get(4).getGraph().getNodeCount());
        Assertions.assertEquals(9 , locations.get(4).getGraph().getEdgeCount());
    }
}
