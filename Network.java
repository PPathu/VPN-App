// --== CS400 File Header Information ==--
// Name: Samuel Pekofsky
// Email: pekofsky@wisc.edu
// Group and Team: DR Red
// Group TA: Yuye Jiang
// Lecturer: Florian Heimerl
// Notes to Grader: Thanks for grading :^)

import java.util.LinkedList;
import java.util.List;


public class Network implements NetworkInterface, Comparable<Network> {

	// instance variable	
	public String name;
	

	// constructor	
	public Network(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object otherNetwork) {
		if(otherNetwork instanceof Network && this.name.equals(((Network) otherNetwork).getName())) return true;
		return false;
	}
	
	@Override
	public int compareTo(Network n) {
		return this.name.compareTo(n.getName());
	}

	@Override
	public String toString() {
		return this.name + "network";
	}
	
}
