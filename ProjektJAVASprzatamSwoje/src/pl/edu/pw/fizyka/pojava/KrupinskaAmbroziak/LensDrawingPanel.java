//Natalia Krupińska

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LensDrawingPanel extends JPanel {
	
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);

	DrawRay drawRay;
	DrawPrincipalPlanes drawPrincipalPlanes;
	DrawLensPrincipalPlanes drawLensPrincipalPlanes;
	
	public boolean selected = false;
	public boolean selected2 = false;
	public boolean selected3 = false;

	public LensDrawingPanel(){
		setBackground(Color.WHITE);
		drawRay = new DrawRay();
		drawPrincipalPlanes = new DrawPrincipalPlanes();
		drawLensPrincipalPlanes = new DrawLensPrincipalPlanes();
	}
	
	public void drawCircle(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		
		for(int i=0;i<OpticalSystem.lensList.size();i++) {
			
			g2.setColor(Color.blue);
			g2.setStroke(new BasicStroke(2f));
			
			if(OpticalSystem.lensList.get(i).isPositionSet()== true) {
				double V1x;
				double V1y;
				double R1 = distanceToPixels(OpticalSystem.lensList.get(i).getFrontRefractiveSurfaceRadius());
			    System.out.println("R1: " + R1);
			    double R2 = distanceToPixels(OpticalSystem.lensList.get(i).getBackRefractiveSurfaceRadius());
			    System.out.println("R2: " + R2);
			    double thick = distanceToPixels(OpticalSystem.lensList.get(i).getLensThickness());
			    double y1 = getHeight()/2;
			    double y2 = getHeight()/2;

			    if(R1>0 && R2<0) {
			    	System.out.println("Soczewka dwuwypukła");
			    	R2 = -R2;
			    	double x1 = OpticalSystem.lensList.get(i).getFrontVertexPosition()+R1;
				    System.out.println("x1: "+x1);
				    double x2 = OpticalSystem.lensList.get(i).getBackVertexPosition()-R2;
				    System.out.println("x2: "+x2);
				 
				    double dx = Math.abs(x2-x1);
				       
		            // Find a and h.
		            double a = (R1 * R1 - R2 * R2 + dx * dx) / (2 * dx);
		            double h = Math.sqrt(R1 * R1 - a * a);

		            // Find P2.
		            double cx2 = x1 + a * (x2 - x1) / dx;
		            double cy2 = y1 + a * (y2-y1) / dx;

		            // Get the points P3.
		            double vX1= (cx2 + h * (y1 - y2) / dx);
		            double vY1= (cy2 - h * (x2 - x1) / dx);
				            
			    	if( thick < R1 + R2) {
			    		if(dx <= Math.abs(R1-R2) && R1>R2) {
			    			g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 180-Math.abs(Math.toDegrees(Math.asin(R2/R1))), 2*Math.abs(Math.toDegrees(Math.asin(R2/R1))), Arc2D.OPEN));
					    	g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2, 2*R2,2*R2, 270,180, Arc2D.OPEN));
					    	g2.drawLine((int)x2,(int)(y1-R2) , (int)(x2+(R1+R2-thick)-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1-R2));
							g2.drawLine((int)x2,(int)(y1+R2) , (int)(x2+(R1+R2-thick)-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1+R2));
			    		}else if (dx <= Math.abs(R1-R2) && R1<R2){
			    			g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 90,180, Arc2D.OPEN));
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2,2*R2,2*R2, -Math.abs(Math.toDegrees(Math.asin(R1/R2))),2*Math.abs(Math.toDegrees(Math.asin(R1/R2))), Arc2D.OPEN));
						    g2.drawLine((int)x1,(int)(y1-R1) , (int)(x1+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))-(R1+R2-thick)), (int)(y1-R1));
						    g2.drawLine((int)x1,(int)(y1+R1) , (int)(x1+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))-(R1+R2-thick)), (int)(y1+R1));
			    		}else {
			    			g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 180-Math.abs(Math.toDegrees(Math.asin(h/R1))), 2*Math.abs(Math.toDegrees(Math.asin(h/R1))), Arc2D.OPEN));
					    	g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2, 2*R2,2*R2, -Math.abs(Math.toDegrees(Math.asin(h/R2))), 2*Math.abs(Math.toDegrees(Math.asin(h/R2))), Arc2D.OPEN));
			    		}
						}else if( thick == R1 + R2 && R2==R1) {
			            g2.drawOval((int)OpticalSystem.lensList.get(i).getFrontVertexPosition(), (int)(y1-R1),(int) (2*R1),(int) (2*R1));
			    	}else if( thick == R1 + R2 && R2!=R1) {
			    		if(R2>R1) {
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 90,180, Arc2D.OPEN));
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2,2*R2,2*R2, -Math.abs(Math.toDegrees(Math.asin(R1/R2))),2*Math.abs(Math.toDegrees(Math.asin(R1/R2))), Arc2D.OPEN));
						    g2.drawLine((int)x1,(int)(y1-R1) , (int)(x1+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))), (int)(y1-R1));
						    g2.drawLine((int)x1,(int)(y1+R1) , (int)(x1+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))), (int)(y1+R1));
			    		}else {
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 180-Math.abs(Math.toDegrees(Math.asin(R2/R1))),2*Math.abs(Math.toDegrees(Math.asin(R2/R1))), Arc2D.OPEN));
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2,2*R2,2*R2, 270,180, Arc2D.OPEN));
						    g2.drawLine((int)x2,(int)(y1-R2) , (int)(x2-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1-R2));
						    g2.drawLine((int)x2,(int)(y1+R2) , (int)(x2-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1+R2));
			    		}
			    	}else if (thick > R1 + R2 && R1!= R2) {
			    		if(R2>R1) {
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 90,180, Arc2D.OPEN));
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2,2*R2,2*R2, -Math.abs(Math.toDegrees(Math.asin(R1/R2))),2*Math.abs(Math.toDegrees(Math.asin(R1/R2))), Arc2D.OPEN));
						    g2.drawLine((int)x1,(int)(y1-R1) , (int)(x2+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))), (int)(y1-R1));
						    g2.drawLine((int)x1,(int)(y1+R1) , (int)(x2+(Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))), (int)(y1+R1));
			    		}else {
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 180-Math.abs(Math.toDegrees(Math.asin(R2/R1))),2*Math.abs(Math.toDegrees(Math.asin(R2/R1))), Arc2D.OPEN));
						    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2,2*R2,2*R2, 270,180, Arc2D.OPEN));
						    g2.drawLine((int)x2,(int)(y1-R2) , (int)(x1-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1-R2));
						    g2.drawLine((int)x2,(int)(y1+R2) , (int)(x1-(Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int)(y1+R2));
			    		}						
			    	}else if (thick > R1 + R2 && R1==R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), y1-R1,2*R1,2*R1, 90, 180, Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, y1-R2, 2*R2,2*R2, 270, 180, Arc2D.OPEN));
						g2.drawLine((int)x1,(int)(y1-R1), (int)x2,(int)(y1-R1) );
						g2.drawLine((int)x1,(int)(y1+R1), (int)x2,(int)(y1+R1) );
			    	}
				    
				} else if(R1<0 && R2>0 ) {
			    	System.out.println("Soczewka dwuwklęsła");
			    	R1 = -R1; 
			    	
				    if(R1<R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition()-2*R1, getHeight()/2-R1, 2*R1,2*R1, 270,180, Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 180-Math.toDegrees(Math.asin(R1/R2)), 2*Math.toDegrees(Math.asin(R1/R2)), Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2-R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2-R1));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2+R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2+R1));
				    }else if(R1>R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition()-2*R1, getHeight()/2-R1, 2*R1,2*R1, 360-Math.toDegrees(Math.asin(R2/R1)),2*Math.toDegrees(Math.asin(R2/R1)), Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 90,180, Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2-R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2-R2));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2+R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2+R2));
				    }else if(R1==R2) {
				    	g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition()-2*R1, getHeight()/2-R1, 2*R1,2*R1, 270,180, Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 90,180, Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2-R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2-R2));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2+R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2+R2));
				    }
				}else if (R1 >0 && 2 >0) {
					System.out.println("Soczewka wypukło-wklęsła");
			    	
				    if(R1<=R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), getHeight()/2-R1, 2*R1,2*R1, 90,180, Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 180-Math.toDegrees(Math.asin(R1/R2)), 2*Math.toDegrees(Math.asin(R1/R2)), Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()+R1), (int) ( getHeight()/2-R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2-R1));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()+R1), (int) ( getHeight()/2+R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2+R1));
				    }else if(R1>R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition(), getHeight()/2-R1, 2*R1,2*R1, 180-Math.toDegrees(Math.asin(R2/R1)),2*Math.toDegrees(Math.asin(R2/R1)), Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 90,180, Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()+(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2-R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2-R2));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()+(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2+R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2+R2));
				    }
				} else if(R1<0 && R2<0 ) {
			    	System.out.println("Soczewka wklęsło-wypukła");
			    	R1 = -R1; 
			    	R2=-R2;
			    	
				    if(R1<=R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition()-2*R1, getHeight()/2-R1, 2*R1,2*R1, 270,180, Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition()-2*R2, getHeight()/2-R2, 2*R2,2*R2, 360-Math.toDegrees(Math.asin(R1/R2)), 2*Math.toDegrees(Math.asin(R1/R2)), Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2-R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()-(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2-R1));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-R1), (int) ( getHeight()/2+R1), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()-(R2-Math.sqrt(Math.pow(R2, 2)-Math.pow(R1, 2)))),(int) ( getHeight()/2+R1));
				    }else if(R1>R2) {
					    g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getFrontVertexPosition()-2*R1, getHeight()/2-R1, 2*R1,2*R1, 360-Math.toDegrees(Math.asin(R2/R1)),2*Math.toDegrees(Math.asin(R2/R1)), Arc2D.OPEN));
						g2.draw(new Arc2D.Double(OpticalSystem.lensList.get(i).getBackVertexPosition(), getHeight()/2-R2, 2*R2,2*R2, 90,180, Arc2D.OPEN));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2-R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2-R2));
						g2.drawLine((int) (OpticalSystem.lensList.get(i).getFrontVertexPosition()-(R1-Math.sqrt(Math.pow(R1, 2)-Math.pow(R2, 2)))), (int) ( getHeight()/2+R2), (int) (OpticalSystem.lensList.get(i).getBackVertexPosition()+R2),(int) ( getHeight()/2+R2));
				    }
				}
			g2.setStroke(new BasicStroke(2f));
			g2.setColor(Color.black);
			g2.drawLine(0, (int)y1, getWidth(), (int)y1);
			}
		}
	}
	
	public  boolean isSelected() {
		return selected;
	}
	public  boolean isSelected2() {
		return selected2;
	}
	public  boolean isSelected3() {
		return selected3;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawCircle(g);
		if(isSelected()== true)
			this.drawRay.paint(g,this);
		if(isSelected2()== true)
			this.drawPrincipalPlanes.paint(g,this);
		if(isSelected3()== true)
			this.drawLensPrincipalPlanes.paint(g,this);
	}
	
	public int distanceToPixels(double distance) {
		return ((int) (Math.round(distance * Math.pow(10.0, 3))));
	}
	
}