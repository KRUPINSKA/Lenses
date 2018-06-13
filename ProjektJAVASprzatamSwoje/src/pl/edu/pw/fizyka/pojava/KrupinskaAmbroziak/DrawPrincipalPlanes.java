//Natalia Krupińska

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawPrincipalPlanes {

void paint(Graphics g, LensDrawingPanel lensDrawingPanel){
		
		Graphics2D g2 = (Graphics2D) g;

		double y1=lensDrawingPanel.getHeight()/2;
		g2.setStroke(new BasicStroke(1.5f));
	
		g2.setColor(Color.DARK_GRAY);
		g2.drawLine( LensSystem.getFrontPrincipalPoint(),(int) y1-200, LensSystem.getFrontPrincipalPoint(),(int) y1+200);
		g2.drawLine( LensSystem.getBackPrincipalPoint(),(int) y1-200, LensSystem.getBackPrincipalPoint(),(int) y1+200);

		g2.setColor(Color.LIGHT_GRAY);
		g2.drawLine( LensSystem.getFrontFocalPoint(),(int) y1-200, LensSystem.getFrontFocalPoint(),(int) y1+200);
		g2.drawLine( LensSystem.getBackFocalPoint(),(int) y1-200, LensSystem.getBackFocalPoint(),(int) y1+200);
	}
}
