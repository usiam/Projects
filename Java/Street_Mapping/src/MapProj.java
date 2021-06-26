
/*Uzair Tahamid Siam
email: usiam@u.rochester.edu
URID: 31434546 / NETID: usiam
Lab session - M/W 6:15 - 7:30
*/

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MapProj extends JFrame {

	static boolean shortestPathFound = false;
	static boolean meridianPathFound = false;

	// variables needed for scaling window
	static double MaxLatitude = 0;
	static double MaxLongitude = 0;
	static double MinLatitude = 400;
	static double MinLongitude = 400;

	// List to store the visited intersections for the Dijkstra method
	static ArrayList<Intersection> pathList = new ArrayList<Intersection>();
	static LinkedList<Road> mwstPath = new LinkedList<Road>();
	static MapGraphics graphics;

	public MapProj() {
		setSize(1000, 750);
		setTitle("MAP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		graphics = new MapGraphics();
		add(graphics);
	}

	public static void main(String[] args) {

		String start = "";
		String end = "";

		String fileName = args[0];
		File file = new File(fileName);
		Scanner scanner;

		try {
			scanner = new Scanner(file);

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String[] tokens = line.split("\b|\t");

				if (tokens[0].equals("i")) {
					Graph.nodes.put(tokens[1], new Intersection(tokens[1], tokens[3], tokens[2]));

					// updates max and min longitude and latitude
					if (Math.abs(Double.parseDouble(tokens[2])) > Math.abs(MaxLatitude)) {
						MaxLatitude = Double.parseDouble(tokens[2]);
					}
					if (Math.abs(Double.parseDouble(tokens[3])) > Math.abs(MaxLongitude)) {
						MaxLongitude = Double.parseDouble(tokens[3]);
					}
					if (Math.abs(Double.parseDouble(tokens[2])) < Math.abs(MinLatitude)) {
						MinLatitude = Double.parseDouble(tokens[2]);
					}
					if (Math.abs(Double.parseDouble(tokens[3])) < Math.abs(MinLongitude)) {
						MinLongitude = Double.parseDouble(tokens[3]);
					}
				} else if (tokens[0].equals("r")) {
					double weight = Road.calculateLength(Graph.nodes.get(tokens[2]).latitude,
							Graph.nodes.get(tokens[3]).latitude, Graph.nodes.get(tokens[2]).longitude,
							Graph.nodes.get(tokens[3]).longitude);
					Graph.nodes.get(tokens[2]).neighborRds
							.add(new Road(tokens[1], Graph.nodes.get(tokens[2]), Graph.nodes.get(tokens[3]), weight));
					Graph.nodes.get(tokens[3]).neighborRds
							.add(new Road(tokens[1], Graph.nodes.get(tokens[2]), Graph.nodes.get(tokens[3]), weight));
				} else {
					System.out.println("Invalid input: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("File not found.");
		}

		if (args[1].equals("-show")) {
			new MapProj().setVisible(true);

			if (args.length > 2) {// if the number of arguments is just 2 then you dont go into this if statement
				if (args[2].equals("-directions")) {
					start = args[3];
					end = args[4];
					Graph.dijkstraShortestPath(Graph.nodes.get(start), Graph.nodes.get(end));
					if (shortestPathFound == true) {
						getPath(Graph.nodes.get(start), Graph.nodes.get(end));
					} else {
						System.out.println("Nodes not connected");
					}
				} else if (args[2].equals("-meridianmap")) {
					mwstPath = Graph.PrimMWST();
					graphics.setMeridianPath(mwstPath, meridianPathFound);

				}
			}

		} else if (args[1].equals("-meridianmap")) {
			try {
				mwstPath = Graph.PrimMWST();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		} else if (args[1].equals("-directions")) {
			start = args[2];
			end = args[3];
			Graph.dijkstraShortestPath(Graph.nodes.get(start), Graph.nodes.get(end));
			if (shortestPathFound == true) {
				getPath(Graph.nodes.get(start), Graph.nodes.get(end));
			} else {
				System.out.println("Nodes not connected");
			}
		} else {
			System.out.println("Check your arguments");
		}

		if (shortestPathFound == true) { // if a path is found, then the path is printed
			System.out.println("PATH: ");
			for (int i = pathList.size() - 1; i >= 0; i--) {
				if (i != 0) {
					System.out.println(pathList.get(i).IntersectionID);
				} else if (i == 0) {
					System.out.print(pathList.get(i).IntersectionID);
				}

			}
			System.out.println("");
			System.out.println(String.format("Path length %.3f mi", pathList.get(0).distance ));
		}
	}

	public static void getPath(Intersection start, Intersection end) {
		pathList.add(end);
		while (!pathList.contains(start)) {
			pathList.add(pathList.get(pathList.size() - 1).parent);
		}
	}

}
