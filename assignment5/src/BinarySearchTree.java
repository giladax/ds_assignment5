public class BinarySearchTree {
	private Node root;

	public BinarySearchTree(Point[] points) {
		root = new Node(points[points.length / 2], null);
		buildTree(points, root, points.length / 2, 0, points.length);
	}

	private void buildTree(Point[] points, Node node, int curr, int left, int right) {
		// If has children
		if (left != right) {
			int leftChild = left + (curr - left) / 2;
			int rightChild = curr + (int) Math.ceil((right - curr + 1) / 2);

			// Has left child
			if (curr != leftChild && leftChild >= 0) {
				node.setLeft(new Node(points[leftChild], node));
				buildTree(points, node.getLeft(), leftChild, left, curr - 1);
			}

			// Has right child
			if (curr != rightChild && rightChild < points.length) {
				node.setRight(new Node(points[rightChild], node));
				buildTree(points, node.getRight(), rightChild, curr + 1, right);
			}
		}

		// Update other fields when coming back from the recursion
		node.updateNode();
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

	public boolean remove(Point toRemove) {
		int value = toRemove.getX();

		if (root == null)
			return false;
		else {
			// Handle root removal. Update node when done
			if (root.getValue().getX() == value) {
				// Create a temporal father for the root to remove
				Node auxRoot = new Node(new Point(0, 0), null);
				auxRoot.setLeft(root);
				boolean result = root.remove(value, auxRoot);
				root = auxRoot.getLeft();
				root.updateNode();
				return result;
			} else {
				return root.remove(value, null);
			}
		}
	}

	public Point[] getPointsInRange(int XLeft, int XRight) {
		LinkedList points = new LinkedList();
		getPointsInRange(XLeft, XRight, root, points);

		return points.toArray();
	}

	private void getPointsInRange(int XLeft, int XRight, Node node, LinkedList list) {
		// Add node in range
		if (node != null) {

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

	private AverageSizePair averageHeightInRange(int XLeft, int XRight, Node node) {
		// Full overlap
		if (node.getMinVal().getX() >= XLeft && node.getMaxVal().getX() <= XRight) {
			return new AverageSizePair(
					(node.getLeftAverage() * node.getLeftSize() + node.getRightAverage() * node.getRightSize()
							+ node.getValue().getY()) / (node.getLeftSize() + node.getRightSize() + 1),
					node.getRightSize() + node.getLeftSize() + 1);
		}

		double sum = 0;
		int size = 0;

		// Partial overlap
		if ((XLeft >= node.getMinVal().getX() && XLeft <= node.getMaxVal().getX())
				|| (XRight <= node.getMaxVal().getX() && XRight >= node.getMinVal().getX())) {
			if (node.getLeft() != null) {
				AverageSizePair pair = averageHeightInRange(XLeft, XRight, node.getLeft());
				sum += pair.average * pair.size;
				size += pair.size;
			}
			if (node.getRight() != null) {
				AverageSizePair pair = averageHeightInRange(XLeft, XRight, node.getRight());
				sum += pair.average * pair.size;
				size += pair.size;

			}
			if (node.getValue().getX() >= XLeft && node.getValue().getX() <= XRight) {
				sum += node.getValue().getY();
				size++;
			}
		}
		if (size != 0) {
			return new AverageSizePair(sum / size, size);
		} else {
			return new AverageSizePair(0, 0);
		}
	}

	public int numOfPointsInRange(int XLeft, int XRight) {
		return numOfPointsInRange(XLeft, XRight, root);
	}

	private int numOfPointsInRange(int XLeft, int XRight, Node node) {
		// Fully contains range
		if (node != null) {
			// Total overlap
			if (node.getMinVal().getX() >= XLeft && node.getMaxVal().getX() <= XRight) {
				return node.getLeftSize() + node.getRightSize() + 1;
			}

			int size = 0;

			// Partial overlap
			if ((XLeft >= node.getMinVal().getX() && XLeft <= node.getMaxVal().getX())
					|| (XRight <= node.getMaxVal().getX() && XRight >= node.getMinVal().getX())) {
				if (node.getLeft() != null) {
					size += numOfPointsInRange(XLeft, XRight, node.getLeft());
				}
				if (node.getRight() != null) {
					size += numOfPointsInRange(XLeft, XRight, node.getRight());
				}
				if (node.getValue().getX() >= XLeft && node.getValue().getX() <= XRight) {
					size++;
				}

				return size;
			}
		}

		return 0;
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
