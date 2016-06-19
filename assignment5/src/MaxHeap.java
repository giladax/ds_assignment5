public class MaxHeap extends Heap {

	public MaxHeap(LinkedList list) {
		super(list);
	}
	
	public MaxHeap(int capacity) {
		super(capacity);
	}

	// Lexicographic verification of relation validity
	@Override
	public boolean isRelationInvalid(PointIndexPair child, PointIndexPair parent) {
		int cY = child.getPoint().getY();
		int pY = parent.getPoint().getY();
		int cX = child.getPoint().getX();
		int pX = parent.getPoint().getX();
		return (cY > pY) || (cY == pY && cX > pX);
	}



}
