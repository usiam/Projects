#Street Mapping

###Synopsis:\
This project involved using graphs to create maps and use Dijkstra's algorithm to find shortest path between two nodes (Intersections) and also creating a minimum weight spanning tree for a meridian map of the ur.txt data file.

###Classes:\
`MapProj`:\
Class that contains the main method and extends JFrame to display graphics components in MapGraphics class. Main method is used to read the data in, construct the graphs with the data and generate graphics (or not depending on argument), and either generate a shortest distance output or a meridian map output listing the roads that one should take.  

`Intersection`:\
Represents nodes in the graphs. Every intersection has an id, longitude, and latitude associated to it. It also has other class variables (detailed comment in the code itself) that are used when implementing Dijkstra's shortest path algorithm. The compareTo method is also used for the algorithm.

`Road`:\
Represents edges in the graphs. Every road has its id but also has two intersections (aka nodes) associated to it and length which is how we will weight these roads for the algorithms spoken above. Once again, the compareTo method is used for the implementation of an algorithm which in this case is the Lazy Prim's algorithm. It also has the calculateLength() method used to actually calculate the length of each road using the Haversine formulation.

`Graph`:\
Has a HashMap of Intersections as the class variable. Has the Dijkstra algorithm implemented using the algorithm found on Zybook and Sedgwick's book. Also contains two other methods - primMWST() and visitMWST() methods used (as the name suggests) to create the MWST. The algorithm was taken from Sedgwick's book once again.	

`MapGraphics`:\
Class where all the graphics work is done. It paints not only the map itself but also paints the shortest path or the mwst in a different color depending on the arguments passed by the user. Some utility methods used in this class are the scaling methods to make the JFrame scale and also have a better appearance.

### Runtime Analysis:\

Displaying the Graphics: Because all the roads are added to an Linked List of Roads, which is then iterated through to graph 
every road, I believe the runtime for displaying the regular graph is O(E) where is E is the number of roads in the 
graph. All other calculations (i.e. finding the minimum and maximum longitude and latitude, which are updated when a new Road 
is inserted) have constant time and can therefor be ignored.

For Calculating the Shortest Path between Two Intersections: Finding the smallest unknown vertex is a constant time operation, 
due to the Priority Queue of Intersections. The dijkstraShortestPath() method requires traversal of the list of adjacent roads until it finds the desired 
Road. So, the worst case run time for the method would be if the Road is the very last in the list, so the runtime of 
the method is O(E) where E is the number of Edges in the linked list. All other calculations in the method are constant time 
operations. Since the loop also runs over every key in the HashMap, the total runtime for Dijkstra's 
algorithm is O(V*|E|), where V is the number of intersections in the Graph and |E| is the average number of Edges in each 
Linked List.

For Calculating the Minimum Weight Spanning Tree: Finding the shortest road is a constant time operation, thanks to the 
Priority Queue of Roads. Since checking if the HashSet corresponding to the end of the Road is a constant time operation, 
the runtime for Lazy Prim's algorithm is O(E) where E is the number of Roads in the graph, because every Road is checked in my  
implementation of the algorithm.

Displaying The Shortest Path: The runtime for displaying the directions between two intersections is O(V) where V is the number 
of intersections that make up the path. 

Displaying the Minimum Weight Spanning Tree: The runtime for displaying the Meridian map is O(E) where E is all the roads in the 
tree. All the edges have already been added to a separate ArrayList, so every Road in the List is displayed.

In conclusion the runtime for the entire program should grow linearly if my proposals for the runtimes are accurate since a larger dataset would correspond to a larger number of edges and vertices and none of my calculations suggest any non-linear growth order.

#Run Instructions:\
*Compiles the java files*:\
`javac -d bin -classpath src -sourcepath src src/*.java`

*To show the map only*:\
`java -classpath src MapProj mapName.txt -show`

*To show map and direction graphically and print out the path and distance*:\ 
`java -classpath src MapProj mapName.txt -show -directions startIntersectionID endIntersectionID`

*To print out the path and distance only*:\
`java -classpath src MapProj mapName.txt -directions startIntersectionID endIntersectionID` 

*To show map and path graphically and print out the path*:\
`java -classpath src MapProj ur.txt -show -meridianmap `

*To print out the path only*:\
`java -classpath src MapProj mapName.txt -meridianmap`