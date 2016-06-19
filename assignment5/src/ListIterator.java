import java.util.Iterator;

// Iterator for list
public class ListIterator implements Iterator<Point> {
	private Link curr;

	public ListIterator(Link head) {
		curr = head;
	}

	@Override
	public boolean hasNext() {
		return curr != null;
	}

	@Override
	public Point next() {
		if (hasNext()) {
			Link result = curr;
			curr = curr.getNext();
			return result.getValue();
		}

		return null;
	}

	@Override
	public void remove() {
		// Do nothing
	}
}
