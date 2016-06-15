import java.lang.reflect.InvocationTargetException;

public abstract class Heap {
	protected PointIndexPair[] arr;
	protected int size;

	public Heap(int capacity) {
		size = 0;
		arr = new PointIndexPair[capacity];
	}

	public Heap(LinkedList list) {
		size = list.size();
		this.arr = new PointIndexPair[size
				+ (int) (Constants.EXTRA_SIZE * Math.log(size))];
		int idx = 0;

		// Deep copy array
		for (Point p : list) {
			arr[idx] = new PointIndexPair(p, idx);
			idx++;
		}
		// changed
		size = idx;
		this.buildHeap();
	}

	public boolean add(Point p) {
		// Add a new point, disregard index value
		return add(p, -1);
	}
	
	private boolean add(Point p, int i) {
		// Add new point, set its index
		if (size < arr.length) {
			arr[size] = new PointIndexPair(p, i);
			sift(size);
			size++;

			return true;
		}

		return false;		
	}

	private void add(PointIndexPair p) {
		// Add point, regard its given index
		add(p.getPoint(), p.getIndex());
	}

	// Maintain heap properties
	public void heapify(int i) {
		int left = Left(i);
		int right = Right(i);
		int largest = i;

		if (left < size && isRelationInvalid(arr[left], arr[largest])) {
			largest = left;
		}
		if (right < size && isRelationInvalid(arr[right], arr[largest])) {
			largest = right;
		}
		if (largest != i) {
			swap(arr, i, largest);
			heapify(largest);
		}
	}

	public LinkedList getMinMaxValues(int num) {
		int i = 0;
		LinkedList result = new LinkedList();

		try {
			Heap temp = (Heap) Class.forName(this.getClass().getName())
					.getConstructor(int.class)
					.newInstance((num + Constants.EXTRA_SIZE));
			temp.add(new PointIndexPair(arr[0].getPoint(), 0));
			while (i < num) {
				if (!(temp.isEmpty())) {
					if (arr.length > Left(temp.arr[0].getIndex())
							&& arr[Left(temp.arr[0].getIndex())] != null) {
						temp.add(new PointIndexPair(arr[Left(temp.arr[0].getIndex())].getPoint(),Left(temp.arr[0].getIndex())));
					}
					if (arr.length > Right(temp.arr[0].getIndex())
							&& arr[Right(temp.arr[0].getIndex())] != null) {
						temp.add(new PointIndexPair(arr[Right(temp.arr[0].getIndex())].getPoint(),Right(temp.arr[0].getIndex())));
					}
					result.add(temp.extract());
				}
				i++;
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
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

	public abstract boolean isRelationInvalid(PointIndexPair child,
			PointIndexPair parent);

	public Point extract() {
		PointIndexPair max = arr[0];
		swap(arr, 0, size - 1);
		arr[size - 1] = null;
		size--;
		heapify(0);
		return max.getPoint();
	}

	public int getSize() {
		return this.size;
	}

	private void buildHeap() {
		for (int i = size / 2; i >= 0; i--) {
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

	public void swap(PointIndexPair[] arr, int i, int j) {
		PointIndexPair tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public boolean isEmpty() {
		return arr[0] == null;
	}
}
