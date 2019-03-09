package ShipCollisionDetector.Service.Impl;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Models.Enums.LengthUnit;
import ShipCollisionDetector.Service.IShipInteractCalculator;

public class ShipInteractCalculator implements IShipInteractCalculator {
	
	public Position getRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection) {
		double speedX = interactShip.getSpeed().getSpeedValue() * Math.cos(90-interactShipDirection); 
		double speedY = interactShip.getSpeed().getSpeedValue() * Math.sin(90-interactShipDirection); 
		double time = - (interactShipPosition.getX().getLengthValue()/speedX);
		double calculatedY = interactShipPosition.getY().getLengthValue() + speedY*time;
		
		Position calculatedPosition = new Position(new Length(LengthUnit.KM, 0), new Length(LengthUnit.KM, calculatedY));
		return calculatedPosition;
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
