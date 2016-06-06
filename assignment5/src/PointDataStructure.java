
public class PointDataStructure implements PDT {

	private Point median;
	private MaxHeap smallerPoints;
	private MinHeap largerPoints;
	private Point[] sortedXvalues;
	private Point[] xExtension;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint)
	{
		median = new Point(initialYMedianPoint);
		sortedXvalues = new Point[points.length];
		xExtension = new Point[(int) (Constants.EXTRA_SIZE * Math.log(points.length))];
		
		
		// First, sort X values in O(n)
		// If value is less than or equal to n-1
		// Bucket Sort (Because it's either the first company with this index in order or the second company with max value = n-1)
		if (points[points.length - 1].getX() <= points.length - 1) {
			for (int i = 0; i < points.length; i++) {
				sortedXvalues[points[i].getX()] = new Point(points[i]);
			}
		} else {
			// It's the second company and X values are already ordered
			for (int i = 0; i < points.length; i++) {
				sortedXvalues[i] = new Point(points[i]);
			}
		}
	}

	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeMedianPoint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getMedianPoints(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getAllPoints() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

