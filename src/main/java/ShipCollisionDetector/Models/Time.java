package ShipCollisionDetector.Models;

import ShipCollisionDetector.Models.Enums.TimeUnit;

public class Time {
	TimeUnit timeUnit;
	double timeValue;

	public Time(TimeUnit timeUnit, double timeValue) {
		this.timeUnit = timeUnit;
		this.timeValue = timeValue;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	public double getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(double timeValue) {
		this.timeValue = timeValue;
	}
	
	public Boolean isOverlap(Time otherTime) {
		return null;
	}
}
