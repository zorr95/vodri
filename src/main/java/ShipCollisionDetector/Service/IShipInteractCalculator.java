package ShipCollisionDetector.Service;

import ShipCollisionDetector.Models.Position;
import ShipCollisionDetector.Models.Ship;
import ShipCollisionDetector.Models.Time;

public interface IShipInteractCalculator {
	Position getRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection);
	Time getTimeOfReachPosition(Ship ship, Position position);	
	Boolean doShipsCollide(Time firstShip, Time secondShip);
	String getWarningMessage(Ship otherShip, double otherShipDirection);
}
