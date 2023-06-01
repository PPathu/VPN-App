// --== CS400 File Header Information ==--
// Name: Ivan Pavlovic
// Email: ipavlovic@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

/**
 * Placeholder class for Network object that will later be implemented by AE
 */
public class NetworkDW implements NetworkInterfaceDW, Comparable<NetworkDW> {
    public String name;
    public NetworkDW(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object otherNetwork) {
        if (otherNetwork instanceof NetworkDW) {
            if (this.name.equals(((NetworkDW) otherNetwork).getName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(NetworkDW o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public String toString(){
        return "Network: " + this.getName();
    }
}
