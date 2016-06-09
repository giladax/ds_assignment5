public class MinHeap extends Heap {

	public MinHeap(LinkedList list) {
		super(list);
	}
	
	public MinHeap(int capacity) {
		super(capacity);
	}

	public boolean isRelationInvalid(PointIndexPair child, PointIndexPair parent) {
		int cY = child.getpPoint().getY();
		int pY = parent.getpPoint().getY();
		int cX = child.getpPoint().getX();
		int pX = parent.getpPoint().getX();
		return (cY < pY) || (cY == pY && cX < pX);
	}

}
