
public class Link<T> {
	private T value;
	private Link<T> next;

	public Link(T p) {
		this(p, null);
	}

	public Link(T p, Link<T> next) {
		value = p;
		this.next = next;
	}
	
	public T getValue() {
		return value;
	}

	public Link<T> getNext() {
		return next;
	}

	public void setNext(Link<T> next) {
		this.next = next;
	}
}
