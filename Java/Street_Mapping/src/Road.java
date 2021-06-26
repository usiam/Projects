/*Uzair Tahamid Siam
email: usiam@u.rochester.edu
URID: 31434546 / NETID: usiam
Lab session - M/W 6:15 - 7:30
*/

public class Road implements Comparable<Road> {
	public String roadID;
	public Intersection i1;
	public Intersection i2;
	public String endIntersectID;
	double length; // used for weighting

	// constructor
	public Road(String roadID, Intersection i1, Intersection i2, double length) {
		this.roadID = roadID;
		this.i1 = i1;
		this.i2 = i2;
		this.length = length;
		endIntersectID = i2.IntersectionID; 
	}

	public String toString() {
		return String.format("RoadID: %s, length = %.3f mi", roadID, length); 
	}

	// Haversine formula to calculate length
	static double calculateLength(double lati1, double lati2, double longi1, double longi2) {
		final double R = 3958.8; // radius of earth (miles)
		double delLat = Math.toRadians(lati2 - lati1);
		double delLong = Math.toRadians(longi2 - longi1);
		double a = Math.sin(delLat / 2) * Math.sin(delLat / 2) + Math.cos(Math.toRadians(lati1))
				* Math.cos(Math.toRadians(lati2)) * Math.sin(delLong / 2) * Math.sin(delLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	@Override
	public int compareTo(Road road) {
		if (length < road.length)
			return -1;
		if (length > road.length)
			return 1;
		return 0;
	}
}
