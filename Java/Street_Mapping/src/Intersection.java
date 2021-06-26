/*Uzair Tahamid Siam
email: usiam@u.rochester.edu
URID: 31434546 / NETID: usiam
Lab session - M/W 6:15 - 7:30
*/

import java.util.LinkedList;

public class Intersection implements Comparable<Intersection> {

	String IntersectionID;
	double longitude, latitude;
	boolean visited = false; // used for Dijkstra algorithm
	double distance = 1000000000.0; // used for Dijkstra algorithm
	LinkedList<Road> neighborRds = new LinkedList<>(); // stores the multiple roads connected to each intersection
	Intersection parent = null; // used for Dijkstra algorithm

	// constructor
	public Intersection(String IntersectionID, String longitude, String latitude) {// every intersection has an id and
																					// longitude and latitude
		this.IntersectionID = IntersectionID;
		this.longitude = Double.parseDouble(longitude);
		this.latitude = Double.parseDouble(latitude);
	}

	public String toString() { //just to print things out for testing
		return "Point{longitude=" + longitude + ", latitude=" + latitude + ", id=" + IntersectionID
				+ ", Adjacent roads =" + neighborRds + ", distance=" + distance + ", visited = " + visited + ", parent="
				+ parent + "}";
	}

	public int compareTo(Intersection node) {
		if (node.distance > distance)
			return -1;
		if (node.distance < distance)
			return 1;
		return 0;
	}

}
