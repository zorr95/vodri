package ShipCollisionDetector.Service.Impl;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Service.IShipInteractCalculator;

public class ShipInteractCalculator implements IShipInteractCalculator {
	
	public Position getRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection) {
		return null;
	}
	
	public Time getTimeOfReachPosition(Ship ship, Position position) {
		return null;
	}
	
	public Boolean doShipsCollide(Time firstShip, Time secondShip) {
		return null;
	}
	
	public String getWarningMessage(Ship otherShip, double otherShipDirection) {
		return null;
	}
}
