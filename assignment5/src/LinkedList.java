import java.util.Iterator;

public class LinkedList implements Iterable<Point> {
	private Link head;
	private Link tail;
	private int size;

	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	// O(1) addition to the tail of the list
	public void add(Point p) {
		if (head == null) {
			head = new Link(p);
			tail = head;
		} else {
			tail.setNext(new Link(p));
			tail = tail.getNext();
		}
		
		size++;
	}

	// Get item at index
	public Point get(int index) {
		if (index < size) {
			Link curr = head;
			for (int i = 0; i < index; i++) {
				curr = curr.getNext();
			}

			return curr.getValue();
		}

		return null;
	}

	public void merge(LinkedList other) {
		// Merge if list contains values
		if (other.size > 0) {
			tail.setNext(other.head);
			tail = other.tail;
			size += other.size;
		}
	}

	public int size() {
		return size;
	}

	public Point[] toArray() {
		Point[] result = new Point[size];
		int index = 0;
		
		for (Point p : this) {
			result[index] = new Point(p);
			index++;
		}

		return result;
	}

	@Override
	public Iterator<Point> iterator() {
		return new ListIterator(head);
	}
}