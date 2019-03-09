package ShipCollisionDetector.Services;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import ShipCollisionDetector.Models.Length;
import ShipCollisionDetector.Models.Mass;
import ShipCollisionDetector.Models.Position;
import ShipCollisionDetector.Models.Ship;
import ShipCollisionDetector.Models.Speed;
import ShipCollisionDetector.Service.IShipInteractCalculator;
import ShipCollisionDetector.Service.Impl.ShipInteractCalculator;
import junit.framework.TestCase;

public class ShipInteractCalculatorTests extends TestCase {

	private ShipInteractCalculator shipInteractCalculator;
	
	public static Collection<Object[]> validDataSource() {
		return Arrays.asList(new Object[][] { 
			{ 
				new Ship(
						new Mass(),
						new Length(),
						new Speed()),
				new Position(),
				3.4,
				new Position()
			} 
		});
	}
	
	@BeforeEach
	void before() {
		this.shipInteractCalculator = new ShipInteractCalculator();
	}

	@ParameterizedTest
	@MethodSource("validDataSource")
	void testGetRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection, Position expecctedPosition) {
		// Arrange
		IShipInteractCalculator mockCalculator = Mockito.mock(IShipInteractCalculator.class);
		Mockito.when(mockCalculator.getRoutesInteractPosition(interactShip, interactShipPosition, interactShipDirection))
			.thenReturn(expecctedPosition);
		
	}
}
