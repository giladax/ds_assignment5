
public class BinarySearchTree {
	Node root;

	public BinarySearchTree(Point[] points) {
		root = new Node(points[points.length / 2]);
		buildTree(points, root, points.length / 2, 0, points.length);
	}

	private void buildTree(Point[] points, Node node, int curr, int left, int right) {
		// If has children
		if (left != right) {
			int leftChild = curr - left + 1;
			int rightChild = right - curr + 1;
			node.setLeft(new Node(points[leftChild / 2]));
			node.setRight(new Node(points[rightChild / 2]));
			buildTree(points, node.getLeft(), leftChild, left, curr - 1);
			buildTree(points, node.getRight(), rightChild, curr + 1, right);
		}
	}
}
