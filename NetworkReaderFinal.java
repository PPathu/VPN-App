// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkReaderFinal implements NetworkReaderInterfaceFinal {

    @Override
    public ArrayList<LocationFinal> loadNetworkData(String fileName) {
        ArrayList<LocationFinal> locations = new ArrayList<>();
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

            // Define the pattern for matching edges
            Pattern edgePattern = Pattern.compile("(\\w+) -- (\\w+) \\[distance = (\\d+)\\];");

            // Split the file content into subgraphs
            String[] subgraphs = fileContent.split("(?<=\\})\\s*");

            // Loop over each subgraph and add it to the appropriate location
            for (String subgraph : subgraphs) {
                // Get the location name from the subgraph name
                String locationName = subgraph.substring(subgraph.indexOf("graph ") + 6, subgraph.indexOf("{")).trim();

                // Create a new NetworkGraphDW object for the location
                NetworkGraphFinal locationGraph = new NetworkGraphFinal();

                // Use the pattern to match the edges in the subgraph
                Matcher edgeMatcher = edgePattern.matcher(subgraph);

                // Keep track of nodes that have already been inserted
                HashMap<String, NetworkFinal> insertedNodes = new HashMap<>();

                while (edgeMatcher.find()) {
                    String node1 = edgeMatcher.group(1);
                    String node2 = edgeMatcher.group(2);
                    double distance = Double.parseDouble(edgeMatcher.group(3));

                    // Insert the nodes into the graph if they don't exist
                    NetworkFinal network1 = insertedNodes.get(node1);
                    if (network1 == null) {
                        network1 = new NetworkFinal(node1);
                        locationGraph.insertNode(network1);
                        if(locationGraph.currentNode.data.name.equals("starting")) locationGraph.currentNode.data = network1;
                        insertedNodes.put(node1, network1);
                    }
                    NetworkFinal network2 = insertedNodes.get(node2);
                    if (network2 == null) {
                        network2 = new NetworkFinal(node2);
                        locationGraph.insertNode(network2);
                        insertedNodes.put(node2, network2);
                    }

                    // Insert the edge into the graph
                    locationGraph.insertEdge(network1, network2, distance);
                }

                // Add the location to the list of locations
                locations.add(new LocationFinal(locationGraph, locationName));
            }

        } catch (FileNotFoundException e) {
            return null;
        }

        return locations;
    }

    public static void main(String[] args){
        NetworkReaderFinal reader = new NetworkReaderFinal();
        ArrayList<LocationFinal> locations = reader.loadNetworkData("NetworkGraph.dot");
        for(LocationFinal location : locations){
            System.out.println(location.location + " " + location.getGraph().getNodeCount() + " " + location.getGraph().getEdgeCount());
        }
    }
}