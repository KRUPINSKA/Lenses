// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class LensCreationPanel extends JPanel {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensCreationPanelLabels", MainFrame.locale);
	
	private JPanel headPanel;
	private JPanel parametersPanel;
	private JPanel buttonPanel;
	
	private JLabel headLabel;
	
	protected LSPanelRefractiveIndex panelRefractiveIndex;
	protected LSPanelLensThickness panelLensThickness;
	protected LSPanelFrontSurfaceRadius panelFrontSurfaceRadius;
	protected LSPanelBackSurfaceRadius panelBackSurfaceRadius;
	protected LSPanelConstructLens panelConstructLens;
	
	protected LensListPanel referenceLensListPanel;
	
	// DOSTOSOWUJE POLOZENIE POLA TEKSTOWEGO BEZ JEDNOSTKI " m"
	private JLabel labelUnit = new JLabel(" m");
    private Font font = labelUnit.getFont(); 
    private FontMetrics metrics = new FontMetrics(font) {};  
    private Rectangle2D bounds = metrics.getStringBounds(labelUnit.getText(), null);  
    private int labelUnitWidth = (int) bounds.getWidth(); 
	

	public LensCreationPanel(LensListPanel lensListPanel) {
		referenceLensListPanel = lensListPanel;
		
		headPanel = new JPanel();
		parametersPanel = new JPanel();
		buttonPanel = new JPanel();
		
		headLabel = new JLabel(labels.getString("head"));
		headPanel.add(headLabel);
		headLabel.setForeground(Color.darkGray);
		
		// PANELE
		panelRefractiveIndex = new LSPanelRefractiveIndex(labelUnitWidth);
		panelLensThickness = new LSPanelLensThickness(labelUnit);
		panelFrontSurfaceRadius = new LSPanelFrontSurfaceRadius(labelUnit);
		panelBackSurfaceRadius = new LSPanelBackSurfaceRadius(labelUnit);
		panelConstructLens = new LSPanelConstructLens(this, referenceLensListPanel);
		
		headPanel.setOpaque(false);
		parametersPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		setBackground(new Color(210,210,210));
		
		//panelRefractiveIndex.setOpaque(false);
		//panelLensThickness.setOpaque(false);
		//panelFrontSurfaceRadius.setOpaque(false);
		//panelBackSurfaceRadius.setOpaque(false);
		//parametersPanel.setBackground(new Color(230,210,230));
		
		setLayout(new BorderLayout());
		parametersPanel.setLayout(new GridLayout(4,1));
		
		Border panelBorder = BorderFactory.createLineBorder(Color.darkGray, 1);
		headPanel.setBorder(panelBorder);
		parametersPanel.setBorder(new TitledBorder(panelBorder,labels.getString("borderTitle")));
		buttonPanel.setBorder(panelBorder);
		
		parametersPanel.add(panelRefractiveIndex);
		parametersPanel.add(panelLensThickness);
		parametersPanel.add(panelFrontSurfaceRadius);
		parametersPanel.add(panelBackSurfaceRadius);
		buttonPanel.add(panelConstructLens);
			
		add(headPanel, BorderLayout.PAGE_START);
		add(parametersPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.PAGE_END);
	}
}
