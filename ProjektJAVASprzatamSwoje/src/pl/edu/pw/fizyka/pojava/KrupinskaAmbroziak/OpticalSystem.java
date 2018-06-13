 // KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.swing.JButton;

public class OpticalSystem {
	
	private static double REFRACTIVE_INDEX_SURROUNDINGS = 1.0003;
	public static ArrayList<Lens> lensList = new ArrayList<Lens>();
	
	private double focalLength, frontFocalDistance, backFocalDistance; //ogniskowa i zbiegowe
	private double opticalPower, frontOpticalPower, backOpticalPower; //odwrotnosci tego na gorze
	

	//// TO JEST JU� W PIKSELACH
	private boolean positionSet;
	private double frontPrincipalPointPosition, backPrincipalPointPosition; /// DO POZYCJONOWANIA NA OSI Z
	private double frontFocalPointPosition, backFocalPointPosition;/// TRZEBA BEDZIE DOPASOWAC ZAWARTOSC
	private double frontVertexPosition, backVertexPosition;/// KLASY LensSystem BO GRUBOSC UKLADU
	/// NIE BEDZIE ODPOWIADALA PRZESUNIECIU NA OSI Z MIEDZY WIERZCHOLKAMI TYLKO PLASZCZYZNAMI GLOWNYMI
	/// DLATEGO TRZEBA BEDZIE TO JAKOS DOPASOWAC ...
	
	// lensList methods
	public static void addLens(Lens lens) {
		lensList.add(lens);
	}
	public static void removeLens(int lensIndex) {
		lensList.remove(lensIndex);
	}
	
	// IS LENS ON AXIS
	public boolean isPositionSet() {
		return positionSet;
	}
	public void setPositionState(boolean bool) {
		positionSet = bool;
	}
	
	// COMPUTE CARDINAL POINTS
	public void computeCardinalPoints(double thickness) {
		backVertexPosition = frontVertexPosition + thickness;
		frontFocalPointPosition = frontVertexPosition - frontFocalDistance;
		backFocalPointPosition = backVertexPosition + backFocalDistance;
		frontPrincipalPointPosition = frontFocalPointPosition + focalLength;
		backPrincipalPointPosition = backFocalPointPosition - focalLength;
	}
	
	// SET methods
	public static void setRefractiveIndexSurroundings(double refIndexSurroundings) {
		REFRACTIVE_INDEX_SURROUNDINGS = refIndexSurroundings;
	}
	
	protected void setFrontVertexPosition(double zAxisPosition, Lens lens) {
		frontVertexPosition = zAxisPosition;
		computeCardinalPoints(lens.getLensThickness());
		setPositionState(true);
		
		LensSystem.resetSystem();
	}
	
	protected void setOpticalPower(double systemRefractiveIndex, double systemThickness, double systemFrontPower, double systemBackPower) {
		opticalPower = (systemFrontPower + systemBackPower) - ((systemThickness / systemRefractiveIndex) * systemFrontPower * systemBackPower);
		focalLength = (1 / opticalPower);
	}
	protected void setFrontOpticalPower(double systemRefractiveIndex, double systemThickness, double systemFrontPower, double systemBackPower) {
		frontOpticalPower = (systemFrontPower / (1 + (systemThickness / systemRefractiveIndex) * systemFrontPower)) + systemBackPower;
		frontFocalDistance = (1 / frontOpticalPower);
	}
	protected void setBackOpticalPower(double systemRefractiveIndex, double systemThickness, double systemFrontPower, double systemBackPower) {
		backOpticalPower = (systemBackPower / (1 + (systemThickness / systemRefractiveIndex) * systemBackPower)) + systemFrontPower;
		backFocalDistance = (1 / backOpticalPower);
	}
	
	// GET methods
	public static double getRefractiveIndexSurroundings() {
		return REFRACTIVE_INDEX_SURROUNDINGS;
		
	}
	
	public double getFocalLength() {
		return focalLength;
	}
	public double getFrontFocalDistance() {
		return frontFocalDistance;
	}
	public double getBackFocalDistance() {
		return backFocalDistance;
	}
	
	public double getOpticalPower() {
		return opticalPower;
	}
	public double getFrontOpticalPower() {
		return frontOpticalPower;
	}
	public double getBackOpticalPower() {
		return backOpticalPower;
	}
	
	// PUNKTY KARDYNALNE
	public double getDoubleFrontVertexPosition() {
		frontVertexPosition = Math.round((frontVertexPosition * Math.pow(10.0, 3)))/Math.pow(10.0, 3);
		return frontVertexPosition;
	}
	public double getDoubleBackVertexPosition() {
		backVertexPosition = Math.round((backVertexPosition * Math.pow(10.0, 3)))/Math.pow(10.0, 3);
		return backVertexPosition;
	}
	public int getFrontVertexPosition() {
		return distanceToPixels(frontVertexPosition);
	}
	public int getBackVertexPosition() {
		return distanceToPixels(backVertexPosition);
	}
	public int getFrontFocalPointPosition() {
		return distanceToPixels(frontFocalPointPosition);
	}
	public int getBackFocalPointPosition() {
		return distanceToPixels(backFocalPointPosition);
	}
	public int getFrontPrincipalPointPosition() {
		return distanceToPixels(frontPrincipalPointPosition);
	}
	public int getBackPrincipalPointPosition() {
		return distanceToPixels(backPrincipalPointPosition);
	}
	
	// KONWERSJA ODLEG�O�CI NA PIKSELE
	public int distanceToPixels(double distance) {
		return ((int) (Math.round(distance * Math.pow(10.0, 3))));
	}

}

