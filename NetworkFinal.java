// --== CS400 File Header Information ==--
// Name: Samuel Pekofsky
// Email: pekofsky@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Thanks for grading :^)

import java.util.LinkedList;
import java.util.List;


public class NetworkFinal implements NetworkInterfaceFinal, Comparable<NetworkFinal> {

	// instance variable	
	public String name;
	

	// constructor	
	public NetworkFinal(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object otherNetwork) {
		if(otherNetwork instanceof NetworkFinal && this.name.equals(((NetworkFinal) otherNetwork).getName())) return true;
		return false;
	}
	
	@Override
	public int compareTo(NetworkFinal n) {
		return this.name.compareTo(n.getName());
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
