import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public abstract class Heap {
	protected Point[] arr;
	protected int size;

	public Heap(int capacity) {
		size = 0;
		arr = new Point[capacity];

	}

	public Heap(LinkedList list) {
		size = list.size();
		this.arr = new Point[size
				+ (int) (Constants.EXTRA_SIZE * Math.log(size))];
		int idx = 0;

		// Deep copy array
		for (Point p : list) {
			arr[idx] = new Point(p);
			idx++;
		}

		buildHeap();
	}

	public void add(Point p) {
		size++;
		arr[size - 1] = new Point(p);
		sift(size - 1);
	}

	// Maintain heap properties
	public void heapify(int i) {
		int left = Left(i);
		int right = Right(i);
		int largest = i;
		arr[Right(i)].getY();
		if (left <= size && isRelationInvalid(arr[left], arr[i])) {
			largest = left;
		}
		if (right <= size && isRelationInvalid(arr[right], arr[i])) {
			largest = right;
		}
		if (largest != i) {
			swap(arr, i, largest);
			heapify(largest);
		}
	}

	public Point[] getMinMaxValues(int num) {
		int i = 0;
		Point[] result = new Point[num];
		try {
			Heap temp = (Heap) Class.forName(this.getClass().getName())
					.getConstructor(Integer.class).newInstance((num + 1));
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		while (i < num) {

		}
		return null;
	}

	public void insert(Point p) {
		size++;
		arr[size - 1] = new Point(p);
		sift(size - 1);
	}

	public void sift(int i) {
		int parentIndex;
		if (i != 0) {
			parentIndex = Parent(i);
			if (isRelationInvalid(arr[i], arr[parentIndex])) {
				swap(arr, i, parentIndex);
				sift(parentIndex);
			}
		}
	}

	public abstract boolean isRelationInvalid(Point child, Point parent);

	public Point extract() {
		Point max = arr[0];
		swap(arr, 0, size - 1);
		arr[size - 1] = null;
		size--;
		heapify(0);
		return new Point(max);
	}

	public int getSize() {
		return this.size;
	}

	private void buildHeap() {
		for (int i = arr.length / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	public int Right(int i) {
		return (2 * i) + 2;
	}

	public int Left(int i) {
		return (2 * i) + 1;
	}

	public int Parent(int i) {
		return (i - 1) / 2;
	}

	public void swap(Point[] arr, int i, int j) {
		Point tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
