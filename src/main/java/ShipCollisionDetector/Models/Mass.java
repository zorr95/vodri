package ShipCollisionDetector.Models;

import ShipCollisionDetector.Models.Enums.MassUnit;

public class Mass implements Comparable<Mass> {
	
	private static double EPSILON = 0.00001;
	
	private MassUnit massUnit;
	private double massValue;
	
	public Mass(MassUnit massUnit, double massValue) {
		//TODO: convert given unit into KG
		this.massUnit = massUnit;
		this.massValue = massValue;
	}
	
	public MassUnit getMassUnit() {
		return massUnit;
	}

	public double getMassValue() {
		return massValue;
	}

	public double convertToRequestedUnit(MassUnit requestedUnit) {
		return 0;
	}

	public int compareTo(Mass otherMass) {
		double otherMassValue = otherMass.getMassValue();
		return (this.massValue - otherMassValue) < EPSILON ? 0 : (this.massValue < otherMassValue) ? -1 : 1;
	}

}
