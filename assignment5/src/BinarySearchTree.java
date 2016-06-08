public class BinarySearchTree {
	private Node root;

	public BinarySearchTree(Point[] points) {
		root = new Node(points[points.length / 2], null);
		buildTree(points, root, points.length / 2, 0, points.length);
	}

	private void buildTree(Point[] points, Node node, int curr, int left,
			int right) {
		// If has children
		if (left != right) {
			int leftChild = (curr - left + 1) / 2;
			int rightChild = (right - curr + 1) / 2;
			node.setLeft(new Node(points[leftChild], node));
			node.setRight(new Node(points[rightChild], node));
			buildTree(points, node.getLeft(), leftChild, left, curr - 1);
			buildTree(points, node.getRight(), rightChild, curr + 1, right);

			// Update other fields when coming back from the recursion
			node.updateNode();
		}
	}

	public void add(Point p) {
		add(p, root);
	}

	private void add(Point p, Node node) {
		// Add new point
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

		// Update nodes in path from new leaf to root
		node.updateNode();
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
		// Attempt removal of given point
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

		// Update nodes in path from new leaf to root
		node.updateNode();
	}

	public Point[] getPointsInRange(int XLeft, int XRight) {
		LinkedList points = new LinkedList();
		getPointsInRange(XLeft, XRight, root, points);

		return points.toArray();
	}

	private void getPointsInRange(int XLeft, int XRight, Node node,
			LinkedList list) {
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

	public double averageHeightInRange(int XLeft, int XRight) {
		return averageHeightInRange(XLeft, XRight, root).average;
	}

	private AverageSizePair averageHeightInRange(int XLeft, int XRight,
			Node node) {
		// Fully contains range
		if (node.getLeft() != null && node.getLeft().getMinVal() >= XLeft
				&& node.getRight() != null
				&& node.getRight().getMaxVal() <= XRight) {
			return new AverageSizePair((node.getLeftAverage()
					* node.getLeftSize() + node.getRightAverage()
					* node.getRightSize() + node.getValue().getY())
					/ (node.getLeftSize() + node.getRightSize() + 1),
					node.getRightSize() + node.getLeftSize() + 1);
		}

		double sum = 0;
		int size = 0;

		// Right bound is smaller than right subtree min value
		if (node.getRight() != null && XRight >= node.getRight().getMinVal()) {
			AverageSizePair pair = averageHeightInRange(XLeft, XRight,
					node.getRight());
			sum += pair.average * pair.size;
			size += pair.size;

		}
		if (node.getLeft() != null && XLeft <= node.getLeft().getMaxVal()) {
			AverageSizePair pair = averageHeightInRange(XLeft, XRight,
					node.getRight());
			sum += pair.average * pair.size;
			size += pair.size;

		}
		if (node.getValue().getY() >= XLeft && node.getValue().getY() <= XRight) {
			sum += node.getValue().getY();
			size++;
		}

		return new AverageSizePair(sum / size, size);
	}

	public int numOfPointsInRange(int XLeft, int XRight) {

		return numOfPointsInRange(XLeft, XRight, root);
	}

	private int numOfPointsInRange(int XLeft, int XRight, Node node) {
		// Fully contains range
		if (node.getLeft() != null && node.getLeft().getMinVal() >= XLeft
				&& node.getRight() != null
				&& node.getRight().getMaxVal() <= XRight) {
			return node.getLeftSize() + node.getRightSize() + 1;
		}

		int size = 0;

		// Right bound is smaller than right subtree min value
		if (node.getRight() != null && XRight >= node.getRight().getMinVal()) {
			size += numOfPointsInRange(XLeft, XRight, node.getRight());
		}
		if (node.getLeft() != null && XLeft <= node.getLeft().getMaxVal()) {
			size = numOfPointsInRange(XLeft, XRight, node.getRight());
		}
		if (node.getValue().getY() >= XLeft && node.getValue().getY() <= XRight) {
			size++;
		}

		return size;
	}

	private static class AverageSizePair {
		public double average = 0;
		public int size = 0;

		public AverageSizePair(double average, int size) {
			this.size = size;
			this.average = average;
		}
	}
}
