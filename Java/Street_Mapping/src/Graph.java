
/*Uzair Tahamid Siam
email: usiam@u.rochester.edu
URID: 31434546 / NETID: usiam
Lab session - M/W 6:15 - 7:30
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Graph {

	// Since a road/edge is just two intersections/nodes we can just store the graph
	// info in a HashMap of intersections
	public static HashMap<String, Intersection> nodes = new HashMap<String, Intersection>();

	// Worst case time complexity: O(nlogn)
	// Algorithm taken from Zybook and Sedgwick's book
	static void dijkstraShortestPath(Intersection start, Intersection end) {
		PriorityQueue<Intersection> nextIntersect = new PriorityQueue<Intersection>();
		start.distance = 0;
		nextIntersect.add(start);
		while (!nextIntersect.isEmpty()) { // while queue is not empty
			Intersection current = nextIntersect.poll(); // get the minimum intersection
			current.visited = true; // mark it as visited
			LinkedList<Road> neighbors = current.neighborRds; // list of neighbors of the current intersection
			for (Road r : neighbors) {
				Intersection neighbor = r.i1 == current ? r.i2 : r.i1;
				if (neighbor.visited == false) {
					if (neighbor.distance > // this condition relaxes the vertices
					current.distance + r.length) {
						neighbor.distance = current.distance + r.length;
						neighbor.parent = current;
						nextIntersect.add(neighbor);
						neighbor.visited = true;
					}
				}
			}
			if (end.visited == true) { // if end has been visited, break from the loop
				MapProj.shortestPathFound = true;
				System.out.println("Shortest path found from " + start.IntersectionID + " to " + end.IntersectionID + "!");
				break;
			}
		}
	}

	// Algorithm taken from Sedgwick's book
	public static LinkedList<Road> PrimMWST() {
		PriorityQueue<Road> pq = new PriorityQueue<Road>();
		LinkedList<Road> mwstPath = new LinkedList<Road>();

		Intersection start = nodes.entrySet().iterator().next().getValue();
		visitMWST(start, pq);

		while (!pq.isEmpty()) {
			Road road = pq.poll();
			Intersection itersect1 = road.i1;
			Intersection itersect2 = road.i2;
			if (itersect1.visited && itersect2.visited) {
				continue;
			}
			mwstPath.addLast(road);
			if (!itersect1.visited) {
				visitMWST(itersect1, pq);
			}
			if (!itersect2.visited) {
				visitMWST(itersect2, pq);
			}
		}

		MapProj.meridianPathFound = true;
		System.out.println("MWST computed");
		for (Road path : mwstPath) {
			System.out.println(path);
		}
		System.out.println("End reached");
		return mwstPath;
	}

	private static void visitMWST(Intersection node, PriorityQueue<Road> pq) {// checks if an intersection has been
																				// visited or not
		node.visited = true;
		for (Road r : node.neighborRds) {
			Intersection neighborRd;
			if (node == r.i1) {
				neighborRd = r.i2;
			} else {
				neighborRd = r.i1;
			}
			if (!neighborRd.visited) {
				pq.add(r);
			}

		}
	}
}