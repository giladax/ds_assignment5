public class MaxHeap extends Heap {

	public MaxHeap(LinkedList list) {
		super(list);
	}
	
	public MaxHeap(int capacity) {
		super(capacity);
	}

	@Override
	public boolean isRelationInvalid(PointIndexPair child, PointIndexPair parent) {
		int cY = child.getpPoint().getY();
		int pY = parent.getpPoint().getY();
		int cX = child.getpPoint().getX();
		int pX = parent.getpPoint().getX();
		return (cY > pY) || (cY == pY && cX > pX);
	}



}
