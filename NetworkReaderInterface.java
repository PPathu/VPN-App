// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Interface that defines the method for DW to implement, which reads Network data from a .dot file
 */
public interface NetworkReaderInterface {
    public ArrayList<LocationDW> loadNetworkData(String fileName);
}
