public class MaxHeap extends Heap {

	public MaxHeap(LinkedList list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isRelationInvalid(Point child, Point parent) {
		int cY = child.getY();
		int pY = parent.getY();
		int cX = child.getX();
		int pX = parent.getX();
		return (cY > pY) || (cY == pY && cX > pX);
	}

	@Override
	public void insert(Point p) {
		// TODO Auto-generated method stub

	}

}
