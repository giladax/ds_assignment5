
public class PointDataStructure implements PDT {

	private Point median;
	private MaxHeap smallerPoints;
	private MinHeap largerPoints;
	private BinarySearchTree xvalues;

	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
		median = new Point(initialYMedianPoint);
		Point[] sortedXvalues = new Point[points.length];

		// First, sort X values in O(n)
		// If value is less than or equal to n-1
		// Bucket Sort (Because it's either the first company with this index in
		// order or the second company with max value = n-1)
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

		// Generate a BST for X values - Will take O(n) because the array is
		// ordered
		xvalues = new BinarySearchTree(sortedXvalues);

		// Handle Y values in O(n)
		LinkedList lessThanMed = new LinkedList();
		LinkedList moreThanMed = new LinkedList();

		for (int i = 0; i < points.length; i++) {
			// Skip if current is the median
			if (points[i].equals(median)) {
				continue;
			}
			
			// If Y is smaller than median (or Y is equal but X is smaller)
			if ((points[i].getY() < median.getY())
					|| (points[i].getY() == median.getY() && points[i].getX() < median.getX())) {
				lessThanMed.add(points[i]);
			} else {
				moreThanMed.add(points[i]);
			}
		}

		// Build heaps, O(n) each
		smallerPoints = new MaxHeap(lessThanMed);
		largerPoints = new MinHeap(moreThanMed);
	}

	@Override
	public void addPoint(Point point) {
		// Handle Y adding
		if (median.getY() > point.getY()) {
			largerPoints.add(point);
		} else {
			smallerPoints.add(point);
		}

		// Balance heaps - Set new median if required
		if (smallerPoints.getSize() < largerPoints.getSize()) {
			smallerPoints.add(median);
			median = largerPoints.extract();
		}
		if (largerPoints.getSize() + 1 < smallerPoints.getSize()) {
			largerPoints.add(median);
			median = smallerPoints.extract();
		}

		// Handle X Adding
		xvalues.add(point);
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		return xvalues.getPointsInRange(XLeft, XRight);
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		return xvalues.numOfPointsInRange(XLeft, XRight);
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		return xvalues.averageHeightInRange(XLeft, XRight);
	}

	@Override
	public void removeMedianPoint() {
		// Handle X: Remove from tree
		xvalues.remove(median);

		// Handle Y: Re balance heaps, override median
		// Always smaller heap size >= larger heap size (by 1)
		if (smallerPoints.getSize() > largerPoints.getSize()) {
			median = smallerPoints.extract();
		} else {
			median = largerPoints.extract();
		}
	}

	@Override
	public Point[] getMedianPoints(int k) {
		LinkedList medianPoints = new LinkedList();
		
		if (k > 0) {
			medianPoints.add(new Point(median));
			
			// Get floor(k / 2) points from smaller than median
			medianPoints.merge(smallerPoints.getMinMaxValues(k / 2));
			
			// Get floor(k - 1/2) points from larger than median
			medianPoints.merge(largerPoints.getMinMaxValues((k - 1) / 2));
			//medianPoints.merge(largerPoints.getMinMaxValues((k / 2) -1));
		}
		
		return medianPoints.toArray();
	}

	@Override
	public Point[] getAllPoints() {
		return xvalues.toArray();
	}

}
