package ShipCollisionDetector;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Service.Impl.ShipInteractCalculator;

public class ProgramController {

	private static ShipInteractCalculator shipInteractCalculator;
	private Ship myShip;
	private Position myPosition;
	private double myDirection;
	
	public ProgramController(Ship ship, Position position, double direction) {
		this.shipInteractCalculator = new ShipInteractCalculator(new Intervall());
		this.myShip = ship;
		this.myPosition = position; 
		this.myDirection = direction;
	}
	
	public Ship getMyShip() {
		return myShip;
	}

	public void setMyShip(Ship myShip) {
		this.myShip = myShip;
	}

	public Position getMyPosition() {
		return myPosition;
	}

	public void setMyPosition(Position myPosition) {
		this.myPosition = myPosition;
	}

	public double getMyDirection() {
		return myDirection;
	}

	public void setMyDirection(double myDirection) {
		this.myDirection = myDirection;
	}

	public String getWarningMessageForCollapsion(Ship otherShip, Position otherPosition, double otherDirection) {
		myShip.isOtherShipIntersectMyRoute(otherPosition, otherDirection);
		Position collapsePosition = shipInteractCalculator.getRoutesInteractPosition(otherShip, otherPosition, otherDirection);
		Time myTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(myShip, myPosition, myDirection, collapsePosition);
		Time otherShipsTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(otherShip, otherPosition, otherDirection, collapsePosition);
		shipInteractCalculator.doShipsCollide(myShip, myTimeToReachCollapsePosition, otherShip, otherShipsTimeToReachCollapsePosition);
		return shipInteractCalculator.getWarningMessage(otherShip, otherPosition, otherDirection, myShip, myPosition, myDirection);
	}
}
