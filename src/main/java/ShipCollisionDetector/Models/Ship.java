package ShipCollisionDetector.Models;

public class Ship {
	
	private Mass mass;
	private Length length;
	private Speed speed;
	
	public Mass getMass() {
		return mass;
	}

	public Length getLength() {
		return length;
	}

	public Speed getSpeed() {
		return speed;
	}
	
	public Ship(Mass mass, Length length, Speed speed) {
		this.mass = mass;
		this.length = length;
		this.speed = speed;
	}
	
	public Boolean isOtherShipIntersectMyRoute(Position otherShipRelativePosition, double otherShipDirection ) {
		return null;
	}
}
