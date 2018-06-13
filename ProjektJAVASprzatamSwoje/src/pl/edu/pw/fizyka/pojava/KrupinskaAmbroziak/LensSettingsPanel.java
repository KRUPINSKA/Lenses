// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class LensSettingsPanel extends JPanel {
	
	protected LSPanelRefractiveIndex panelRefractiveIndex;
	protected LSPanelLensThickness panelLensThickness;
	protected LSPanelFrontSurfaceRadius panelFrontSurfaceRadius;
	protected LSPanelBackSurfaceRadius panelBackSurfaceRadius;
	protected LSPanelApplyChanges panelApplyChanges;
		
	// TEN FRAGMENT KODU DOPASOWUJE POLE TEKSTOWE BEZ " m" 
	private JLabel labelUnit = new JLabel(" m");
    private Font font = labelUnit.getFont(); 
    private FontMetrics metrics = new FontMetrics(font) {};  
    private Rectangle2D bounds = metrics.getStringBounds(labelUnit.getText(), null);  
    private int labelUnitWidth = (int) bounds.getWidth(); 
	

	public LensSettingsPanel(Lens lens, LensResultParametersPanel panel, LensListPanel listPanel, LensDrawingPanel drawingPanel) {
		
		// PANELE
		panelRefractiveIndex = new LSPanelRefractiveIndex(lens, labelUnitWidth);
		panelLensThickness = new LSPanelLensThickness(lens, labelUnit);
		panelFrontSurfaceRadius = new LSPanelFrontSurfaceRadius(lens, labelUnit);
		panelBackSurfaceRadius = new LSPanelBackSurfaceRadius(lens, labelUnit);
		panelApplyChanges = new LSPanelApplyChanges(lens, this, panel, listPanel, drawingPanel);
		
		
		// PANEL GLOWNY		
		setBackground(Color.lightGray);
		setLayout(new GridLayout(5,1));
			
				
		add(panelRefractiveIndex);
		add(panelLensThickness);
		add(panelFrontSurfaceRadius);
		add(panelBackSurfaceRadius);
		add(panelApplyChanges);	
						
	}
}
