package ShipCollisionDetector.Models;

public class Intervall implements IIntervall {
	double leftSide;
	double rightSide;
	
	public Intervall () {}
	
	public Intervall(double leftSide, double rightSide) {
		this.leftSide = leftSide;
		this.rightSide = rightSide;
	}

	public double getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(double leftSide) {
		this.leftSide = leftSide;
	}

	public double getRightSide() {
		return rightSide;
	}

	public void setRightSide(double rightSide) {
		this.rightSide = rightSide;
	}
	
	public Boolean isCollapsed(Intervall otherIntervall) {
		return null;
	}
}
