// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Lens extends OpticalSystem {
	
	private double lensRefractiveIndex, lensThickness;
	private double frontRefractiveSurfaceRadius, backRefractiveSurfaceRadius;
	private double frontSurfacePower, backSurfacePower;
	private String lensName;
	

	
	public Lens(double refIndex, double thickness, double radiusFront, double radiusBack, String name) {
		setLensParameters(refIndex, thickness, radiusFront, radiusBack);
		lensName = name;
		this.setPositionState(false);
		
		super.addLens(this);
	}
	public Lens(double refIndex, double thickness, double radiusFront, double radiusBack, String name, double frontVerticePosition) {
		setLensParameters(refIndex, thickness, radiusFront, radiusBack);
		lensName= name;
		setFrontVertexPosition(frontVerticePosition, this);
		
		super.addLens(this);
	}
	
	
	// COMPUTE methods
	private void computeFrontSurfacePower() {
		frontSurfacePower = (lensRefractiveIndex - super.getRefractiveIndexSurroundings()) / frontRefractiveSurfaceRadius;
	}
	private void computeBackSurfacePower() {
		backSurfacePower = (super.getRefractiveIndexSurroundings() - lensRefractiveIndex) / backRefractiveSurfaceRadius;
	}
	public void computeLensResultParameters() {
		super.setOpticalPower(lensRefractiveIndex, lensThickness, frontSurfacePower, backSurfacePower);
		super.setFrontOpticalPower(lensRefractiveIndex, lensThickness, frontSurfacePower, backSurfacePower);
		super.setBackOpticalPower(lensRefractiveIndex, lensThickness, frontSurfacePower, backSurfacePower);
	}
	
	// SET methods
	public void setLensParameters(double refIndex, double thickness, double radiusFront, double radiusBack) {
		lensRefractiveIndex = refIndex;
		lensThickness = thickness;
		frontRefractiveSurfaceRadius = radiusFront;
		backRefractiveSurfaceRadius = radiusBack;
		
		computeFrontSurfacePower();
		computeBackSurfacePower();
		
		computeLensResultParameters();	
	}
	
	// GET methods
	public int getLensIndex() {
		for(int ii=0; ii<super.lensList.size(); ii++) {
			//lens = lensList.get(ii);
			if(getLensName().equals(super.lensList.get(ii).getLensName())) {
				return ii;
			}
		}
		return -1;
	}
	
	public double getLensRefractiveIndex() {
		return lensRefractiveIndex;
	}
	public double getLensThickness() {
		return lensThickness;
	}
	public double getFrontRefractiveSurfaceRadius() {
		return frontRefractiveSurfaceRadius;
	}
	public double getBackRefractiveSurfaceRadius() {
		return backRefractiveSurfaceRadius;
	}
	public double getFrontSurfacePower() {
		return frontSurfacePower;
	}
	public double getBackSurfacePower() {
		return backSurfacePower;
	}
	public String getLensName() {
		return lensName;
	}
	
}
