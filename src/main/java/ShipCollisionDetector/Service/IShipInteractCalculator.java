package ShipCollisionDetector.Service;

import ShipCollisionDetector.Models.Position;
import ShipCollisionDetector.Models.Ship;
import ShipCollisionDetector.Models.Time;

public interface IShipInteractCalculator {
	Position getRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection);
	Time getTimeOfReachPosition(Ship ship, Position shipPosition, double shipDirection, Position reachablePosition);	
	Boolean doShipsCollide(Ship firstShip, Time firstShipTimeToReachPosition, Ship secondShip, Time secondShiptimeToReachPosition);
	String getWarningMessage(Ship otherShip, double otherShipDirection);
}
