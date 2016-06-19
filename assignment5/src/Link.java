
// Linked list Link class
public class Link {
	private Point value;
	private Link next;

	public Link(Point p) {
		this(p, null);
	}

	public Link(Point p, Link next) {
		value = p;
		this.next = next;
	}
	
	public Point getValue() {
		return value;
	}

	public Link getNext() {
		return next;
	}

	public void setNext(Link next) {
		this.next = next;
	}
}
