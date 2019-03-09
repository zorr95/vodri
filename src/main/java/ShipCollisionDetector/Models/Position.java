package ShipCollisionDetector.Models;

public class Position {
	Length x;
	Length y;

	public Position(Length x, Length y) {
		this.x = x;
		this.y = y;
	}
	
	public Length getX() {
		return x;
	}
	public void setX(Length x) {
		this.x = x;
	}
	public Length getY() {
		return y;
	}
	public void setY(Length y) {
		this.y = y;
	}
}
