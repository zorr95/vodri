package ShipCollisionDetector.Models;

public interface IIntervall {
	Boolean isCollapsed(Intervall otherIntervall) ;
	void setLeftSide(double d);
	void setRightSide(double d);
}
