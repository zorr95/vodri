package ShipCollisionDetector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import ShipCollisionDetector.Models.*;
import ShipCollisionDetector.Models.Enums.*;
import ShipCollisionDetector.Service.IShipInteractCalculator;

public class ProgramControllerTest {

	private static String SLOWDOWNMESSAGE = "Mi lassítsunk, a másik hajó mehet!";
	private static String BEAWAREMESSAGE = "Mi mehetünk, de figyeljünk a másik hajóra!";
	
	private ProgramController programController;
	private IShipInteractCalculator mockCalculator;
	private Ship mockShip;
	private Position mockShipPosition;

	public static Collection<Object[]> validDataSourceForGetWarningMessageForCollapsion() {
		return Arrays.asList(new Object[][] {
			{ 
				false, 
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
						),
				new Time(TimeUnit.H, 27.84925433),
				new Time(TimeUnit.H, 38.97817017),
				true,
				null
			},
			{ 
				false, 
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
						),
				new Time(TimeUnit.H, 27.84925433),
				new Time(TimeUnit.H, 38.97817017),
				true,
				SLOWDOWNMESSAGE
			},
			{ 
				false, 
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
						),
				new Time(TimeUnit.H, 27.84925433),
				new Time(TimeUnit.H, 38.97817017),
				true,
				BEAWAREMESSAGE
			},
			{ 
				false, 
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
						),
				new Time(TimeUnit.H, 27.84925433),
				new Time(TimeUnit.H, 38.97817017),
				false,
				null
			}
		});
	}
	
	@BeforeEach
	void before() {
		this.mockCalculator = Mockito.mock(IShipInteractCalculator.class);
		this.mockShip = Mockito.mock(Ship.class);
		this.mockShipPosition = new Position(new Length(LengthUnit.KM,0),new Length(LengthUnit.KM,0));
		
		this.programController = new ProgramController(mockShip, mockShipPosition, 0, mockCalculator);
	}
	
	@ParameterizedTest
	@MethodSource("validDataSourceForGetWarningMessageForCollapsion")
	public void testGetWarningMessageForCollapsion(Boolean isIntersect, Ship otherShip, Position otherPosition, double otherDirection, Position mockPosition, Time firstMockTime, Time secondMockTime, Boolean isCollide, String expectedMessage) {
		//Arrange
		Mockito.when(this.mockShip.isOtherShipIntersectMyRoute(org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble()))
			.thenReturn(isIntersect);
		Mockito.when(mockCalculator.getRoutesInteractPosition(otherShip, otherPosition, otherDirection))
			.thenReturn(mockPosition);
		Mockito.when(mockCalculator.getTimeOfReachPosition(mockShip, mockShipPosition, 0, mockPosition))
			.thenReturn(firstMockTime);
		Mockito.when(mockCalculator.getTimeOfReachPosition(otherShip, otherPosition, otherDirection, mockPosition))
		.thenReturn(secondMockTime);
		Mockito.when(mockCalculator.doShipsCollide(mockShip, firstMockTime, otherShip, secondMockTime))
			.thenReturn(isCollide);
		Mockito.when(mockCalculator.getWarningMessage(mockShip, mockShipPosition, 0, otherShip, otherPosition, otherDirection))
			.thenReturn(expectedMessage);
		
		//Act
		String calculatedMessage = programController.getWarningMessageForCollapsion(otherShip, otherPosition, otherDirection);
		
		//Assert
		assertEquals(calculatedMessage, expectedMessage);
		verify(mockShip, times(1)).isOtherShipIntersectMyRoute(org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
		if(isIntersect) {
			verify(mockCalculator, times(1)).getRoutesInteractPosition(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
			verify(mockCalculator, times(2)).getTimeOfReachPosition(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble(), org.mockito.Matchers.any(Position.class));
			verify(mockCalculator, times(1)).doShipsCollide(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Time.class), org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Time.class));
			if(isCollide) {
				verify(mockCalculator, times(1)).getWarningMessage(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble(), org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
			} else {
				verify(mockCalculator, times(0)).getWarningMessage(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble(), org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
			}
		} else {
			verify(mockCalculator, times(0)).getRoutesInteractPosition(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
			verify(mockCalculator, times(0)).getTimeOfReachPosition(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble(), org.mockito.Matchers.any(Position.class));
			verify(mockCalculator, times(0)).doShipsCollide(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Time.class), org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Time.class));
			verify(mockCalculator, times(0)).getWarningMessage(org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble(), org.mockito.Matchers.any(Ship.class), org.mockito.Matchers.any(Position.class), org.mockito.Matchers.anyDouble());
		}
	}
}
