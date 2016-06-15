public class MinHeap extends Heap {

	public MinHeap(LinkedList list) {
		super(list);
	}
	
	public MinHeap(int capacity) {
		super(capacity);
	}

	public boolean isRelationInvalid(PointIndexPair child, PointIndexPair parent) {
		int cY = child.getPoint().getY();
		int pY = parent.getPoint().getY();
		int cX = child.getPoint().getX();
		int pX = parent.getPoint().getX();
		return (cY < pY) || (cY == pY && cX < pX);
	}

}
