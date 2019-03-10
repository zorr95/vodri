package ShipCollisionDetector.Models;

import ShipCollisionDetector.Models.Enums.SpeedUnit;

public class Speed {
	SpeedUnit speedUnit;
	double speedValue;
	
	public Speed(SpeedUnit speedUnit, double speedValue) {
		this.speedUnit = speedUnit;
		this.speedValue = speedValue;
	}
	
	public SpeedUnit getSpeedUnit() {
		return speedUnit;
	}
	public void setSpeedUnit(SpeedUnit speedUnit) {
		this.speedUnit = speedUnit;
	}
	public double getSpeedValue() {
		return speedValue;
	}
	public void setSpeedValue(double speedValue) {
		speedValue = speedValue;
	}
}
