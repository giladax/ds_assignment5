
public class Node {
	private Point value;
	private Node left;
	private Node right;
	
	public Node(Point p) {
		value = p;
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
