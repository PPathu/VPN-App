// --== CS400 File Header Information ==--
// Name: Shivam Ratnani
// Email: ratnani@wisc.edu
// Group and Team: DR Blue
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: How are you doing today?

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkReader implements NetworkReaderInterface {

    @Override
    public ArrayList<LocationDW> loadNetworkData(String fileName) {
        ArrayList<LocationDW> locations = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            StringBuilder fileContentBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                fileContentBuilder.append(scanner.nextLine());
                fileContentBuilder.append("\n");
            }
            scanner.close();
            String fileContent = fileContentBuilder.toString();

            // pattern for matching edges
            Pattern edgePattern = Pattern.compile("(\\w+) -- (\\w+) \\[distance = (\\d+)\\];");
            String[] subgraphs = fileContent.split("(?<=\\})\\s*");

            // Loop over each subgraph and add it to the appropriate location
            for (String subgraph : subgraphs) {
                String locationName = subgraph.substring(subgraph.indexOf("graph ") + 6, subgraph.indexOf("{")).trim();
                NetworkGraphDW locationGraph = new NetworkGraphDW();
                Matcher edgeMatcher = edgePattern.matcher(subgraph);
                HashMap<String, NetworkDW> insertedNodes = new HashMap<>();

                while (edgeMatcher.find()) {
                    String node1 = edgeMatcher.group(1);
                    String node2 = edgeMatcher.group(2);
                    int distance = Integer.parseInt(edgeMatcher.group(3));

                    // Insert the nodes into the graph if they don't exist
                    NetworkDW network1 = insertedNodes.get(node1);
                    if (network1 == null) {
                        network1 = new NetworkDW(node1);
                        locationGraph.insertNode(network1);
                        insertedNodes.put(node1, network1);
                    }
                    NetworkDW network2 = insertedNodes.get(node2);
                    if (network2 == null) {
                        network2 = new NetworkDW(node2);
                        locationGraph.insertNode(network2);
                        insertedNodes.put(node2, network2);
                    }

                    // Insert the edge into the graph
                    locationGraph.insertEdge(network1, network2, distance);
                }
                // Add the location to the list of locations
                locations.add(new LocationDW(locationGraph, locationName));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return locations;
    }
}



