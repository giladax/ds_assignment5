
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
		if (getRight() != null) {
			this.setRightAverage((double) (this.getRight().getRightAverage() * this.getRight().getRightSize()
					+ this.getRight().getValue().getY()) / (this.getRight().getRightSize() + 1));
			this.setRightSize(this.getRight().getRightSize() + 1);
			this.setMaxVal(this.getRight().getMaxVal());
		} else {
			this.setMaxVal(this.getValue().getY());
		}

		if (getLeft() != null) {
			this.setLeftAverage((double) (this.getLeft().getLeftAverage() * this.getLeft().getLeftSize()
					+ this.getLeft().getValue().getY()) / (this.getLeft().getLeftSize() + 1));
			this.setLeftSize(this.getLeft().getLeftSize() + 1);
			this.setMinVal(this.getLeft().getMinVal());
		} else {
			this.setMinVal(this.getValue().getY());
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
