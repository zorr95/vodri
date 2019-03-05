package ShipCollisionDetector.Models;

import ShipCollisionDetector.Models.Enums.LengthUnit;

public class Length implements Comparable<Length> {

	private LengthUnit lengthUnit;
	private double lengthValue;
	
	public LengthUnit getLengthUnit() {
		return lengthUnit;
	}

	public double getLengthValue() {
		return lengthValue;
	}

	public int compareTo(Length arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
