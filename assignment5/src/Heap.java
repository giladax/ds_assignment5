public abstract class Heap {
	protected Point[] arr;
	protected int size;

	public Heap(Point[] arr) {
		size = arr.length;
		this.arr = new Point[size + (int) (15 * Math.log(size))];
		
		// Deep copy array
		for (int i = 0; i < arr.length; i++) {
			this.arr[i] = new Point(arr[i]);
		}

		buildHeap();
	}

	public abstract void heapify(int i);

	private void buildHeap() {
		for (int i = arr.length / 2; i > 0; i--) {
			heapify(i);
		}
	}

}
