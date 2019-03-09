package ShipCollisionDetector.Services;

import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ShipCollisionDetector.Models.Length;
import ShipCollisionDetector.Models.Mass;
import ShipCollisionDetector.Models.Position;
import ShipCollisionDetector.Models.Ship;
import ShipCollisionDetector.Models.Speed;
import ShipCollisionDetector.Models.Time;
import ShipCollisionDetector.Models.Enums.LengthUnit;
import ShipCollisionDetector.Models.Enums.MassUnit;
import ShipCollisionDetector.Models.Enums.SpeedUnit;
import ShipCollisionDetector.Models.Enums.TimeUnit;
import ShipCollisionDetector.Service.Impl.ShipInteractCalculator;

public class ShipInteractCalculatorTests {

	private static double EPSYLON = 0.0001;
	private ShipInteractCalculator shipInteractCalculator;

	public static Collection<Object[]> validDataSourceForGetRoutesInteractPosition() {
		return Arrays.asList(new Object[][] {{ 
					new Ship(
							new Mass(MassUnit.KG, 2000), 
							new Length(LengthUnit.KM, 200), 
							new Speed(SpeedUnit.KMH, 30)
							),
					new Position(
							new Length(LengthUnit.KM, 500), 
							new Length(LengthUnit.KM, 500)
							),
					270,
					new Position(
							new Length(LengthUnit.KM, 0),
							new Length(LengthUnit.KM, 1169.345105)
							)
					}});
	}

	public static Collection<Object[]> validDataSourceForGetTimeOfReachPosition() {
		return Arrays.asList(new Object[][] {
				{ 
					new Ship(
							new Mass(MassUnit.KG, 2000), 
							new Length(LengthUnit.KM, 200), 
							new Speed(SpeedUnit.KMH, 30)
							),
					new Position(
							new Length(LengthUnit.KM, 500), 
							new Length(LengthUnit.KM, 500)
							), 
					new Time(TimeUnit.H, 27.84925433)
				},
				{ 
					new Ship(
							new Mass(MassUnit.KG, 2000), 
							new Length(LengthUnit.KM, 200), 
							new Speed(SpeedUnit.KMH, 30)
							),
					new Position(
							new Length(LengthUnit.KM, 0), 
							new Length(LengthUnit.KM, 0)
							), 
					new Time(TimeUnit.H, 38.97817017)
				}
			});
	}

	@BeforeEach
	void before() {
		this.shipInteractCalculator = new ShipInteractCalculator();
	}

	@ParameterizedTest
	@MethodSource("validDataSourceForGetRoutesInteractPosition")
	void testGetRoutesInteractPosition(Ship interactShip, Position interactShipPosition, double interactShipDirection, Position expectedPosition) {
		// Arrange

		// Act
		Position calculatedPosition = shipInteractCalculator.getRoutesInteractPosition(interactShip, interactShipPosition, interactShipDirection);

		// Assert
		assertEquals(calculatedPosition.getX().getLengthValue(), expectedPosition.getX().getLengthValue(), EPSYLON);
		assertThat(calculatedPosition.getX().getLengthUnit(), is(expectedPosition.getX().getLengthUnit()));
		assertEquals(calculatedPosition.getY().getLengthValue(), expectedPosition.getY().getLengthValue(), EPSYLON);
		assertThat(calculatedPosition.getY().getLengthUnit(), is(expectedPosition.getY().getLengthUnit()));
	}

	@ParameterizedTest
	@MethodSource("validDataSourceForGetTimeOfReachPosition")
	void testGetTimeOfReachPosition(Ship interactShip, Position interactShipPosition, Time expectedTime) {
		// Arrange

		// Act
		Time calculatedTime = shipInteractCalculator.getTimeOfReachPosition(interactShip, interactShipPosition);

		// Assert
		assertEquals(calculatedTime.getTimeValue(), expectedTime.getTimeValue(), EPSYLON);
		assertThat(calculatedTime.getTimeUnit(), is(expectedTime.getTimeUnit()));
	}
}
