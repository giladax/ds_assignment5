
public class Node {
	private Point value;
	private Point minVal;
	private Point maxVal;
	private Node left;
	private Node right;
	private double leftAverage;
	private double rightAverage;
	private int leftSize;
	private int rightSize;

	public Node(Point p, Node parent) {
		value = p;
		leftAverage = 0;
		rightAverage = 0;
		leftSize = 0;
		rightSize = 0;
		maxVal = p;
		minVal = p;
	}

	// Update all fields depending on subtrees
	public void updateNode() {
		if (right != null) {
			rightAverage = (right.rightAverage * right.rightSize + right.value.getY()
					+ right.leftAverage * right.leftSize) / (double) (right.rightSize + 1 + right.leftSize);
			rightSize = right.rightSize + 1 + right.leftSize;
			maxVal = right.maxVal;
		} else {
			// No right child. Default values
			maxVal = value;
			rightAverage = 0;
			rightSize = 0;
		}

		if (left != null) {
			leftAverage = (left.rightAverage * left.rightSize + left.value.getY() + left.leftAverage * left.leftSize)
					/ (double) (left.rightSize + 1 + left.leftSize);
			leftSize = left.rightSize + 1 + left.leftSize;
			minVal = left.minVal;
		} else {
			// No left child. Default values
			minVal = value;
			leftAverage = 0;
			leftSize = 0;
		}
	}

	// Node removal. Update when done
	public boolean remove(int value, Node parent) {
		if (value < this.value.getX()) {
			// Check left subtree
			if (left != null) {
				boolean res = left.remove(value, this);
				
				// Update node if child was removed
				if (res) {
					updateNode();
				}
				return res;
			}
			else
				return false;
		} else if (value > this.value.getX()) {
			// Check right subtree
			if (right != null) {
				boolean res = right.remove(value, this);
				
				// Update node if child was removed
				if (res) {
					updateNode();
				}
				return res;
			}
			else
				return false;
		} else {
			// Remove node. Replace value with successor and update the nodes
			if (left != null && right != null) {
				this.value = right.getMinVal();
				right.remove(this.value.getX(), this);
			} else if (parent.left == this) {
				// Node is left son, replace it with its single son 
				parent.left = (left != null) ? left : right;
			} else if (parent.right == this) {
				// Node is right son, replace it with its single son
				parent.right = (left != null) ? left : right;
			}
			
			// Finally update node
			updateNode();

			return true;
		}
	}

	public Point getMinVal() {
		return minVal;
	}

	public void setMinVal(Point minVal) {
		this.minVal = minVal;
	}

	public Point getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Point maxVal) {
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
