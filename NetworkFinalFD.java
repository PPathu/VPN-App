

import java.util.LinkedList;
import java.util.List;


public class NetworkFinalFD implements NetworkInterfaceFinalFD, Comparable<NetworkFinalFD> {

    // instance variable    
    public String name;
    

    // constructor  
    public NetworkFinalFD(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object otherNetwork) {
        if(otherNetwork instanceof NetworkFinalFD && this.name.equals(((NetworkFinalFD) otherNetwork).getName())) return true;
        return false;
    }
    
    @Override
    public int compareTo(NetworkFinalFD n) {
        return this.name.compareTo(n.getName());
    }

    @Override
    public String toString() {
        return this.name;
    }

    
    
}
