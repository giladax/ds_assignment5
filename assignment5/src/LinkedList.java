import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
	private Link<T> head;
	private Link<T> tail;
	private int size;
	
	public LinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	// O(1) addition to the tail of the list
	public void add(T p) {
		if (head == null) {
			head = new Link<T>(p);
			tail = head;
			size = 1;
		} else {
			tail.setNext(new Link<T>(p));
			tail = tail.getNext();
			size++;
		}
	}
	
	// Get item at index
	public T get(int index) {
		if (index < size) {
			Link<T> curr = head;
			for (int i = 0; i < index; i++) {
				curr = curr.getNext();
			}
			
			return curr.getValue();
		}
		
		return null;
	}
	
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	
	
	private class ListIterator implements Iterator<T> {
		private Link<T> curr;
		
		public ListIterator() {
			curr = LinkedList.this.head;
		}
		
		
		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				Link<T> result = curr;
				curr = curr.getNext();
				return result.getValue();
			}
			
			return null;
		}
	}
}
