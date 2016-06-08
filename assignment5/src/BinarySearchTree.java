
public class BinarySearchTree {
	private Node root;

	public BinarySearchTree(Point[] points) {
		root = new Node(points[points.length / 2], null);
		buildTree(points, root, points.length / 2, 0, points.length);
	}

	private void buildTree(Point[] points, Node node, int curr, int left, int right) {
		// If has children
		if (left != right) {
			int leftChild = curr - left + 1;
			int rightChild = right - curr + 1;
			node.setLeft(new Node(points[leftChild / 2], node));
			node.setRight(new Node(points[rightChild / 2], node));
			buildTree(points, node.getLeft(), leftChild, left, curr - 1);
			buildTree(points, node.getRight(), rightChild, curr + 1, right);
		}
	}

	public void add(Point p) {
		add(p, root);
	}

	private void add(Point p, Node node) {
		if (node.getValue().getX() > p.getX()) {
			if (node.getLeft() == null) {
				node.setLeft(new Node(p, node));
			} else {
				add(p, node.getLeft());
			}
		} else {
			if (node.getRight() == null) {
				node.setRight(new Node(p, node));
			} else {
				add(p, node.getRight());
			}
		}
	}

	public void remove(Point toRemove) {
		if (root.getValue().getX() == toRemove.getX()) {
			if (root.getRight() != null) {
				root = root.getRight();
			} else if (root.getLeft() != null) {
				root = root.getLeft();
			} else {
				root = null;
			}
		} else {
			remove(toRemove, root);
		}
	}

	private void remove(Point toRemove, Node node) {
		if (node.getValue().getX() == toRemove.getX()) {
			// First try replacing node with right child, then left, then remove
			// self
			if (node.getRight() != null) {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(node.getRight());
				} else {
					node.getParent().setRight(node.getRight());
				}
			} else if (node.getLeft() != null) {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(node.getLeft());
				} else {
					node.getParent().setRight(node.getLeft());
				}
			} else {
				if (node.getParent().getLeft() == node) {
					node.getParent().setLeft(null);
				} else {
					node.getParent().setRight(null);
				}
			}
		} else if (node.getValue().getX() > toRemove.getX()) {
			remove(toRemove, node.getLeft());
		} else if (node.getValue().getX() < toRemove.getX()) {
			remove(toRemove, node.getRight());
		}
	}

	public Point[] getPointsInRange(int XLeft, int XRight) {
		LinkedList points = new LinkedList();
		getPointsInRange(XLeft, XRight, root, points);

		return points.toArray();
	}

	private void getPointsInRange(int XLeft, int XRight, Node node, LinkedList list) {
		// Add node in range
		if (XLeft <= node.getValue().getX() && XRight >= node.getValue().getX()) {
			list.add(node.getValue());
		}

		// Check left child if XLeft is smaller than node
		if (XLeft < node.getValue().getX()) {
			getPointsInRange(XLeft, XRight, node.getLeft(), list);
		}

		// Check right child if XRight is larger than node
		if (XRight > node.getValue().getX()) {
			getPointsInRange(XLeft, XRight, node.getRight(), list);
		}
	}

	public Point[] toArray() {
		LinkedList list = new LinkedList();
		toArray(list, root);

		return list.toArray();
	}

	private void toArray(LinkedList list, Node node) {
		if (node.getLeft() != null) {
			toArray(list, node.getLeft());
		}

		list.add(node.getValue());

		if (node.getRight() != null) {
			toArray(list, node.getRight());
		}
	}
}
