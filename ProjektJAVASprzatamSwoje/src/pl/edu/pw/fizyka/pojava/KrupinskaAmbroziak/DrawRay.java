//Natalia Krupińska
package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class DrawRay {
	
	void paint(Graphics g, LensDrawingPanel lensDrawingPanel){
		
		Graphics2D g2 = (Graphics2D) g;

		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(Color.red);
		
		double sinA =1;
	    double y1 = lensDrawingPanel.getHeight()/2-distanceToPixels(MainMenuBar.getRayPosition());
	    double y2 =y1;
	    double y = lensDrawingPanel.getHeight()/2;
	    double x1;
	    double x2;
	    double yDod;
	    double dodatkowa=y2-y1;
		double R1 = distanceToPixels(LensSystem.systemList.get(0).getFrontRefractiveSurfaceRadius());
		double R2 = distanceToPixels(LensSystem.systemList.get(0).getBackRefractiveSurfaceRadius());

			if(LensSystem.systemList.size()>0) {
				g2.drawLine(0,(int)y1, (int) LensSystem.systemList.get(0).getFrontPrincipalPointPosition() ,(int) y1);
			}else
				g2.drawLine(0, (int)y1, (int)lensDrawingPanel.getWidth(),(int)y1);
		
		double R;
		int i1=0;
		
		while(y2<2*y && y2>0 && i1<LensSystem.systemList.size()){
		
			R1 = distanceToPixels(LensSystem.systemList.get(i1).getFrontRefractiveSurfaceRadius());
		    R2 = distanceToPixels(LensSystem.systemList.get(i1).getBackRefractiveSurfaceRadius());
			
		    if(R1>R2) {
				R=R1;
			} else
				R=R2;
			
				x1 = LensSystem.systemList.get(i1).getFrontPrincipalPointPosition();
				x2 = LensSystem.systemList.get(i1).getBackPrincipalPointPosition();
				 
		    double n1 = LensSystem.systemList.get(i1).getLensRefractiveIndex();
		    double n0 = Lens.getRefractiveIndexSurroundings();
		    
		    
		    if(LensSystem.systemList.size()==1) {
		    	double f1=  distanceToPixels(LensSystem.systemList.get(0).getFocalLength());
		    	x1=x2;
		    	x2=lensDrawingPanel.getWidth();
		    	if(R1>0&&R2<0) {
		    		y2=y1+(y-y1)/f1*(x2-x1);
		    	}else {
			    	y2=y1+(y-y1)/f1*(x2-x1);

		    	}
		    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		    } else {
		    
		    	 if(R1>0 && y1<y) {
				    	y2=(x2-x1)*Math.tan(Math.asin(n0/n1*sinA))+y1;
				    	if(y2>lensDrawingPanel.getHeight()) {
				    		y2=lensDrawingPanel.getHeight();
					    	x2=(y2-y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
				    	} 
				    }else if(R1>0 && y1>y) {
				    	y2=y1-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
				    	if(y2<0) {
				    		y2=0;
					    	x2=(y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
				    	} 
				    }else if(R1<0 && y1<y) {
				    	y2=y1-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
				    	if(y2<0) {
				    		y2=0;
					    	x2=(y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
				    	}
				    } else if(R1<0 && y1>y) {
				    	y2=y1+(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
				    	if(y2>lensDrawingPanel.getHeight()) {
				    		y2=lensDrawingPanel.getHeight();
					    	x2=(y2-y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
				    	}
				    } else if(y1==y && sinA == 1) {
				    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y1);
				    }else if(y1==y && sinA !=1) {
				    	if(dodatkowa<0) {
					    	y2=y1-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
					    	if(y2<0) {
					    		y2=0;
						    	x2=(y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
				    	}else {
				    		y2=y1+(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
				    		if(y2>lensDrawingPanel.getHeight()) {
					    		y2=lensDrawingPanel.getHeight();
						    	x2=(y2-y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
				    	}
				    }
				    
					dodatkowa = y2 - y1;
					sinA=Math.sin(Math.atan(Math.abs(dodatkowa)/(x2-x1)));
					
					System.out.println("sinA na poczatku "+sinA);

					if(sinA==0)
						sinA=1;
					
					System.out.println("sinA"+sinA);
					
					yDod=y2;
					//y2=y2/2;
					x1=x2;
				    
					if(LensSystem.systemList.get(i1)==LensSystem.systemList.get(LensSystem.systemList.size()-1)) {
						x2=lensDrawingPanel.getWidth();
					}else if (LensSystem.systemList.get(i1+1).getFrontRefractiveSurfaceRadius()==0) {
						x2= LensSystem.systemList.get(i1+1).getFrontVertexPosition();
					}else if(LensSystem.systemList.get(i1+1).getBackRefractiveSurfaceRadius()==0) {
						x2= LensSystem.systemList.get(i1+1).getFrontVertexPosition();
					}else
						x2=LensSystem.systemList.get(i1+1).getFrontPrincipalPointPosition();				
				   
					
					if(y2<2*y && y2>0) {
				    	if(R2>0 && yDod<y) {
				    		//y2=yDod-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
				    	if(y2<0) {
				    		//y2=0;
					    	x2=(-y1)/Math.tan(Math.asin(n0/n1*sinA))+x1;
					    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
				    	} else {
					    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
				    	}
					    } else if(R2>0 && y1>y)  {
						    	//y2=yDod+(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
						    	if(y2>lensDrawingPanel.getHeight()) {
						    		//y2=lensDrawingPanel.getHeight();
							    	x2=(y2-yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	} else {
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	}
					    }else if(R2<0 && y1<y && dodatkowa <0)  {
					    	//y2=yDod-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
					    	if(y2<0) {
					    		//y2=0;
						    	x2=(yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
					    } else if(R2<0 && y1<y && dodatkowa >0) {
					    	//y2=(x2-x1)*Math.tan(Math.asin(n0/n1*sinA))+yDod;
					    	if(y2>lensDrawingPanel.getHeight()) {
					    		//y2=lensDrawingPanel.getHeight();
						    	x2=(y2-yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
					    }
					    else if(R2<0 && y1>y && dodatkowa > 0) {
					    	//y2=(x2-x1)*Math.tan(Math.asin(n0/n1*sinA))+yDod;
					    	if(y2>lensDrawingPanel.getHeight()) {
					    		//y2=lensDrawingPanel.getHeight();
						    	x2=(y2-yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
					    }else if(R2<0 && y1>y && dodatkowa < 0){
					    	//y2=yDod-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
					    	if(y2<0) {
					    	//	y2=0;
						    	x2=(yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	} else {
						    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
					    	}
					    }else if(y1==y && sinA !=1) {
					    	if(dodatkowa<0) {
						    //	y2=yDod-(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
						    	if(y2<0) {
						    	//	y2=0;
						    		x2=yDod/Math.tan(Math.asin(n0/n1*sinA))+x1;
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	} else {
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	}
					    	}else {
					    	//	y2=yDod+(x2-x1)*Math.tan(Math.asin(n0/n1*sinA));
					    		if(y2>lensDrawingPanel.getHeight()) {
						    	//	y2=lensDrawingPanel.getHeight();
							    	x2=(y2-yDod)/Math.tan(Math.asin(n0/n1*sinA))+x1;
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	} else {
							    	g2.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
						    	}
					    	}
					    } 
					}
					dodatkowa = y2 - y1;
					sinA=Math.sin(Math.atan(Math.abs(dodatkowa)/(x2-x1)));
					if(dodatkowa==0) {
						sinA=1;
					}
					y1=y2;
		    }
					i1++;
					System.out.println("sinA na koniec "+sinA);
		}
		
	}
	public int distanceToPixels(double distance) {
		return ((int) (Math.round(distance * Math.pow(10.0, 3))));
	}
}
