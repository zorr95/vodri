package ShipCollisionDetector;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Service.IShipInteractCalculator;

public class ProgramController {

	private static IShipInteractCalculator shipInteractCalculator;
	private Ship myShip;
	private Position myPosition;
	private double myDirection;
	
	public ProgramController(Ship ship, Position position, double direction, IShipInteractCalculator calculator) {
		this.shipInteractCalculator = calculator;
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
		if(myShip.isOtherShipIntersectMyRoute(otherPosition, otherDirection)) {
			Position collapsePosition = shipInteractCalculator.getRoutesInteractPosition(otherShip, otherPosition, otherDirection);
			Time myTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(myShip, myPosition, myDirection, collapsePosition);
			Time otherShipsTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(otherShip, otherPosition, otherDirection, collapsePosition);
			if(shipInteractCalculator.doShipsCollide(myShip, myTimeToReachCollapsePosition, otherShip, otherShipsTimeToReachCollapsePosition)) {
				return shipInteractCalculator.getWarningMessage(otherShip, otherPosition, otherDirection, myShip, myPosition, myDirection);
			}
		}
		return null;
	}
}
