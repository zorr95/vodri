package ShipCollisionDetector;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Service.ShipInteractCalculator;

public class ProgramController {

	private static ShipInteractCalculator shipInteractCalculator;
	private Ship myShip;
	private Position myPosition;
	private double myDirection;
	
	public ProgramController(Ship ship) {
		this.shipInteractCalculator = new ShipInteractCalculator();
		this.myShip = ship;
	}
	
	public String getWarningMessageForCollapsion(Ship otherShip, Position otherPosition, double otherDirection) {
		myShip.isOtherShipIntersectMyRoute(otherPosition, otherDirection);
		Position collapsePosition = shipInteractCalculator.getRoutesInteractPosition(otherShip);
		Time myTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(myShip, myPosition);
		Time otherShipsTimeToReachCollapsePosition = shipInteractCalculator.getTimeOfReachPosition(otherShip, otherPosition);
		shipInteractCalculator.doShipsCollide(myTimeToReachCollapsePosition, otherShipsTimeToReachCollapsePosition);
		return shipInteractCalculator.getWarningMessage(otherShip, otherDirection);
	}
}
