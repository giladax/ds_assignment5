public class MinHeap extends Heap {

	public MinHeap(LinkedList list) {
		super(list);
	}

	public boolean isRelationInvalid(Point child, Point parent) {
		int cY = child.getY();
		int pY = parent.getY();
		int cX = child.getX();
		int pX = parent.getX();
		return (cY < pY) || (cY == pY && cX < pX);
	}

}
