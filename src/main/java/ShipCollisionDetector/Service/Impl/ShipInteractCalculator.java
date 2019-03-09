package ShipCollisionDetector.Service.Impl;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Models.Enums.LengthUnit;
import ShipCollisionDetector.Models.Enums.TimeUnit;
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
	
	public Time getTimeOfReachPosition(Ship ship, Position shipPosition, double shipDirection, Position reachablePosition) {
		if(shipPosition.getX().getLengthValue() == 0.0 && shipPosition.getY().getLengthValue() == 0.0) {
			double time = reachablePosition.getY().getLengthValue() / ship.getSpeed().getSpeedValue();
			return new Time(TimeUnit.H, time);
		} else {
			double speedX = ship.getSpeed().getSpeedValue() * Math.cos(90-shipDirection); 
			double time = - (shipPosition.getX().getLengthValue()/speedX);
			return new Time(TimeUnit.H, time);
		}
	}
	
	public Boolean doShipsCollide(Ship firstShip, Time firstShipTimeToReachPosition, Ship secondShip, Time secondShiptimeToReachPosition) {
		return null;
	}
	
	public String getWarningMessage(Ship otherShip, double otherShipDirection) {
		return null;
	}
}
