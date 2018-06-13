// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.util.ArrayList;
import java.util.Comparator;

public class LensSystem {

	private static MainPanel mainPanel;
	
	protected LensSystem(MainPanel panel) {
		mainPanel = panel;
	}
	
	//// ******** LENS SYSTEM ******** ////
	
	private static double systemThickness, systemFrontPower, systemBackPower;//, systemCurrentFrontPower;// dotyczy soczewek
	private static double refIndex;
	
	private static double focalLength, frontFocalDistance, backFocalDistance;
	private static double opticalPower, frontOpticalPower, backOpticalPower;
	
	private static double frontVertex, backVertex;
	private static double frontPrincipalPoint, backPrincipalPoint;
	private static double frontFocalPoint, backFocalPoint;
	
	public static ArrayList<Lens> systemList = new ArrayList<Lens>();

	
	public static void resetSystem() {
		setList();
		
		if(!systemList.isEmpty()) {
			computeSystemVertices();
			computeSystemCardinalPoints();
			mainPanel.systemResultPanel.resetSystemResultParameters();
		} else {
			mainPanel.systemResultPanel.clearSystemResultParameters();
		}
	}

	// LIST METHODS
	private static void setList() {
		systemList.clear();
		for(int i=0; i<OpticalSystem.lensList.size(); i++) {
			if(OpticalSystem.lensList.get(i).isPositionSet()) systemList.add(OpticalSystem.lensList.get(i));
		}
		sortSystemList();
	}

	// COMPUTE METHODS
	private static void computeSystemThickness() {
		systemThickness = backVertex - frontVertex;
	}
	private static void computeSystemVertices() {
		setFrontSystemVertex();
		setBackSystemVertex();
		computeSystemThickness();		
	}
	private static void computeSystemPower() {		
		setFrontAndBackPower();
		
		opticalPower = (systemFrontPower + systemBackPower) - ((systemThickness / refIndex) * systemFrontPower * systemBackPower);
		focalLength = (1 / opticalPower);
		frontOpticalPower = (systemFrontPower / (1 + (systemThickness / refIndex) * systemFrontPower)) + systemBackPower;
		frontFocalDistance = (1 / frontOpticalPower);
		backOpticalPower = (systemBackPower / (1 + (systemThickness / refIndex) * systemBackPower)) + systemFrontPower;
		backFocalDistance = (1 / backOpticalPower);
	
//		systemFrontPower = opticalPower;
	}
	private static void computeSystemCardinalPoints() {
		computeSystemPower();
		
		frontFocalPoint = frontVertex - frontFocalDistance;
		backFocalPoint = backVertex + backFocalDistance;
		frontPrincipalPoint = frontFocalPoint + focalLength;
		backPrincipalPoint = backFocalPoint - focalLength;
		
	 }
	
	
	
	// SET METHODS
	private static void setFrontSystemVertex() {
		frontVertex = systemList.get(0).getDoubleFrontVertexPosition();
	//	frontVertex = Double.parseDouble(""+systemList.get(0).getFrontPrincipalPointPosition())/1000;		
	}
	private static void setBackSystemVertex() {
		backVertex = systemList.get(0).getDoubleBackVertexPosition();
	//	backVertex = Double.parseDouble(""+systemList.get(systemList.size()-1).getBackPrincipalPointPosition())/1000;
	}
	private static void setFrontAndBackPower() {
		if(systemList.size() < 2) {
			systemFrontPower = systemList.get(0).getFrontSurfacePower();
			systemBackPower = systemList.get(0).getBackSurfacePower();
			
			refIndex = systemList.get(0).getLensRefractiveIndex();		
	//		systemFrontPower = systemList.get(0).getOpticalPower();
		} else {
			for(int i=0; i<(systemList.size()-1); i++) {
				systemFrontPower = opticalPower;
				systemBackPower = systemList.get(systemList.size()-1).getOpticalPower();
				refIndex = OpticalSystem.getRefractiveIndexSurroundings();
			}
		}
	}
	
	// GET METHODS
	// values
	public static double getOpticalPower() {
		return opticalPower;
	}
	public static double getFrontOpticalPower() {
		return frontOpticalPower;
	}
	public static double getBackOpticalPower() {
		return backOpticalPower;
	}	
	public static double getFocalLength() {
		return focalLength;
	}
	public static double getFrontFocalDistance() {
		return frontFocalDistance;
	}
	public static double getBackFocalDistance() {
		return backFocalDistance;
	}
	// points
	public static int getFrontVertex() {
		return toPixels(frontVertex);
	}
	public static int getBackVertex() {
		return toPixels(backVertex);
	}
	public static int getFrontPrincipalPoint() {
		return toPixels(frontPrincipalPoint);
	}
	public static int getBackPrincipalPoint() {
		return toPixels(backPrincipalPoint);
	}
	public static int getFrontFocalPoint() {
		return toPixels(frontFocalPoint);
	}
	public static int getBackFocalPoint() {
		return toPixels(backFocalPoint);
	}

	private static int toPixels(double distance) {
		return ((int) (Math.round(distance * Math.pow(10.0, 3))));
	}
	
	//SORT
	private static void sortSystemList() {
		systemList.sort(Comparator.comparingDouble(Lens::getDoubleFrontVertexPosition));
	}
	
}
