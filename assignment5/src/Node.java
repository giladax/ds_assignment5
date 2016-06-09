
public class Node {
	private Point value;
	private Node left;
	private Node right;
	private Node parent;
	private double leftAverage;
	private double rightAverage;
	private int leftSize;
	private int rightSize;
	private int minVal;
	private int maxVal;

	public Node(Point p, Node parent) {
		value = p;
		this.parent = parent;
		leftAverage = 0;
		rightAverage = 0;
		leftSize = 0;
		rightSize = 0;
	}

	public void updateNode() {
		// Update all fields depending on subtrees
		if (right != null) {
			rightAverage = (right.rightAverage * right.rightSize + right.value.getY()
					+ right.leftAverage * right.leftSize) / (double) (right.rightSize + 1 + right.leftSize);
			rightSize = right.rightSize + 1 + right.leftSize;
			maxVal = right.maxVal;
		} else {
			maxVal = value.getY();
		}

		if (left != null) {
			leftAverage = (left.rightAverage * left.rightSize + left.value.getY() + left.leftAverage * left.leftSize)
					/ (double) (left.rightSize + 1 + left.leftSize);
			leftSize = left.rightSize + 1 + left.leftSize;
			minVal = left.minVal;
		} else {
			minVal = value.getY();
		}
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		this.minVal = minVal;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		this.maxVal = maxVal;
	}

	public double getLeftAverage() {
		return leftAverage;
	}

	public void setLeftAverage(double leftAverage) {
		this.leftAverage = leftAverage;
	}

	public double getRightAverage() {
		return rightAverage;
	}

	public void setRightAverage(double rightAverage) {
		this.rightAverage = rightAverage;
	}

	public int getLeftSize() {
		return leftSize;
	}

	public void setLeftSize(int leftSize) {
		this.leftSize = leftSize;
	}

	public int getRightSize() {
		return rightSize;
	}

	public void setRightSize(int rightSize) {
		this.rightSize = rightSize;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Point getValue() {
		return value;
	}

	public void setValue(Point value) {
		this.value = value;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
