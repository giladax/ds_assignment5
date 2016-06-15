
public class PointIndexPair {
	private Point p;
	private int i;
	public PointIndexPair(Point p, int i) {
		this.p = p;
		this.i = i;
	}
	public PointIndexPair(PointIndexPair other) {
		this.p = new Point(other.p);
		this.i = other.i;
	}
	public Point getPoint(){
		return this.p;
	}
	public int getIndex(){
		return this.i;
	}
	
	public void setIndex(int i) {
		this.i = i;
	}
}
