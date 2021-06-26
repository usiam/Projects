/*Uzair Tahamid Siam
email: usiam@u.rochester.edu
URID: 31434546 / NETID: usiam
Lab session - M/W 6:15 - 7:30
*/

import java.awt.*;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MapGraphics extends JComponent {

	public LinkedList<Road> mwstPath = null;
	public boolean meridianPathFound = false;

	public void setMeridianPath(LinkedList<Road> mwstPath, boolean meridianPathFound) {
		this.mwstPath = mwstPath;
		this.meridianPathFound = meridianPathFound;
		this.repaint();
	}

	public void paintComponent(Graphics g) {

		for (String key : Graph.nodes.keySet()) { // draws the map
			LinkedList<Road> adjList = Graph.nodes.get(key).neighborRds;
			for (Road r : adjList) {
				g.drawLine(Math.abs(getWidth() - scaleLongitude(Graph.nodes.get(key).longitude)),
						Math.abs(getHeight() - scaleLatitude(Graph.nodes.get(key).latitude)),
						Math.abs(getWidth() - scaleLongitude(Graph.nodes.get(r.endIntersectID).longitude)),
						Math.abs(getHeight() - scaleLatitude(Graph.nodes.get(r.endIntersectID).latitude)));

			}
		}

		if (MapProj.shortestPathFound == true) { // if a path has been isFound, the path is drawn
			DecimalFormat df = new DecimalFormat("#.###");
			g.setColor(Color.RED);
			// makes a red circle at destination
			g.fillOval(Math.abs(getWidth() - scaleLongitude(MapProj.pathList.get(0).longitude)) - 5,
					Math.abs(getHeight() - scaleLatitude(MapProj.pathList.get(0).latitude)) - 5, 10, 10);

			g.setColor(Color.BLUE);
			g.setFont(new Font("Times New Roman", Font.BOLD, 12));
			g.drawString("Distance: " + df.format(MapProj.pathList.get(0).distance) + " mi", getWidth() / 2 - 60, 20);
			g.setColor(Color.BLACK);
			g.fillOval(
					Math.abs(getWidth() - scaleLongitude(MapProj.pathList.get(MapProj.pathList.size() - 1).longitude))
							- 5,
					Math.abs(getHeight() - scaleLatitude(MapProj.pathList.get(MapProj.pathList.size() - 1).latitude))
							- 5,
					10, 10);

			Graphics2D g2D = (Graphics2D) g;
			for (int i = 0; i < MapProj.pathList.size() - 1; i++) {
				Intersection start = MapProj.pathList.get(i);
				Intersection end = MapProj.pathList.get(i + 1);
				g2D.setColor(Color.BLUE);
				g2D.setStroke(new BasicStroke(3));
				int startX = Math.abs(getWidth() - scaleLongitude(start.longitude));
				int startY = Math.abs(getHeight() - scaleLatitude(start.latitude));
				int endX = Math.abs(getWidth() - scaleLongitude(end.longitude));
				int endY = Math.abs(getHeight() - scaleLatitude(end.latitude));
				g2D.drawLine(startX, startY, endX, endY);
			}
		}

		if (MapProj.meridianPathFound == true) {

			Graphics2D g2D = (Graphics2D) g;
			Color purple = new Color(102, 0, 153);
//			if(mwstPath == null)
//				return;
			for (Road r : MapProj.mwstPath) {
				g2D.setColor(purple);
				g2D.setStroke(new BasicStroke(3));
				int startX = Math.abs(getWidth() - scaleLongitude(r.i1.longitude));
				int startY = Math.abs(getHeight() - scaleLatitude(r.i1.latitude));
				int endX = Math.abs(getWidth() - scaleLongitude(r.i2.longitude));
				int endY = Math.abs(getHeight() - scaleLatitude(r.i2.latitude));
				g2D.drawLine(startX, startY, endX, endY);
			}
		}
	}

	// scaling the longitude to the canvas
	public int scaleLongitude(double longToBeScaled) {
		return (int) ((longToBeScaled - MapProj.MinLongitude) / (MapProj.MaxLongitude - MapProj.MinLongitude) * 7
				* getWidth() / 10) + 3 * getWidth() / 20;
	}

	//// scaling the latitude to the canvas
	public int scaleLatitude(double latToBeScaled) {
		return (int) ((latToBeScaled - MapProj.MinLatitude) / (MapProj.MaxLatitude - MapProj.MinLatitude) * 9
				* getHeight() / 10) + 1 * getHeight() / 20;
	}

}
