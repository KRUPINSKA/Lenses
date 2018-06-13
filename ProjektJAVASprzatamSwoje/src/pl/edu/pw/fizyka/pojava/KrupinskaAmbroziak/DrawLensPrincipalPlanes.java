//Natalia Krupińska
package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawLensPrincipalPlanes {
	void paint(Graphics g, LensDrawingPanel lensDrawingPanel){
			
			Graphics2D g2 = (Graphics2D) g;
	
			double y1=lensDrawingPanel.getHeight()/2;
			for(int i=0;i<OpticalSystem.lensList.size();i++) {
				if(OpticalSystem.lensList.get(i).isPositionSet()== true) {
					
					g2.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float [] {1,2},0));
					g2.setColor(Color.RED);
					g2.drawLine( OpticalSystem.lensList.get(i).getFrontFocalPointPosition(),(int) y1-200, OpticalSystem.lensList.get(i).getFrontFocalPointPosition(),(int) y1+200);
					g2.drawLine( OpticalSystem.lensList.get(i).getBackFocalPointPosition(),(int) y1-200, OpticalSystem.lensList.get(i).getBackFocalPointPosition(),(int) y1+200);
					g2.setColor(Color.GREEN);
					g2.drawLine( OpticalSystem.lensList.get(i).getFrontPrincipalPointPosition(),(int) y1-200, OpticalSystem.lensList.get(i).getFrontPrincipalPointPosition(),(int) y1+200);
					g2.drawLine( OpticalSystem.lensList.get(i).getBackPrincipalPointPosition(),(int) y1-200, OpticalSystem.lensList.get(i).getBackPrincipalPointPosition(),(int) y1+200);
					//System.out.println("FOP: " + OpticalSystem.lensList.get(i).getFrontPrincipalPointPosition() +"\nBOP: " + OpticalSystem.lensList.get(i).getBackPrincipalPointPosition()+"\nFFP: "+OpticalSystem.lensList.get(i).getFrontFocalPointPosition()+"\nBFP: "+OpticalSystem.lensList.get(i).getBackFocalPointPosition());
					}
				}
	}
}
