// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LensResultParametersPanel extends JPanel {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensResultParametersLabels", MainFrame.locale);
	
	private JPanel panelDescription = new JPanel();
	private JPanel panelResults = new JPanel();	
	
	private JLabel labelOpticalPower = new JLabel();
	private JLabel labelFrontOpticalPower = new JLabel();
	private JLabel labelBackOpticalPower = new JLabel();
	private JLabel labelFocalLength = new JLabel();
	private JLabel labelFrontFocalDistance = new JLabel();
	private JLabel labelBackFocalDistance = new JLabel();
	
	private JLabel labelDescriptionOpticalPower = new JLabel(labels.getString("opticalPower"));
	private JLabel labelDescriptionFrontOpticalPower = new JLabel(labels.getString("frontOpticalPower"));
	private JLabel labelDescriptionBackOpticalPower = new JLabel(labels.getString("backOpticalPower"));
	private JLabel labelDescriptionFocalLength = new JLabel(labels.getString("focalLength"));
	private JLabel labelDescriptionFrontFocalDistance = new JLabel(labels.getString("frontFocalDistance"));
	private JLabel labelDescriptionBackFocalDistance = new JLabel(labels.getString("backFocalDistance"));
	
	
	// SOCZEWKA
	public LensResultParametersPanel(Lens lens) {
		setBackground(Color.white);
		setLayout(new GridLayout(1,2));
		
		panelDescription.setLayout(new GridLayout(6,1));
//		panelDescription.setOpaque(false);
		panelResults.setLayout(new GridLayout(6,1));
		panelResults.setOpaque(false);
		
		
		panelDescription.add(labelDescriptionOpticalPower);
		panelDescription.add(labelDescriptionFrontOpticalPower);
		panelDescription.add(labelDescriptionBackOpticalPower);
		panelDescription.add(labelDescriptionFocalLength);
		panelDescription.add(labelDescriptionFrontFocalDistance);
		panelDescription.add(labelDescriptionBackFocalDistance);
		
	
		resetLensResultParameters(lens);
		
		panelResults.add(labelOpticalPower);
		panelResults.add(labelFrontOpticalPower);
		panelResults.add(labelBackOpticalPower);
		panelResults.add(labelFocalLength);
		panelResults.add(labelFrontFocalDistance);
		panelResults.add(labelBackFocalDistance);
		
		add(panelDescription);
		add(panelResults);
	}
	
	public void resetLensResultParameters(Lens lens) {
		labelOpticalPower.setText(" = " + roundLensResultParameter(lens.getOpticalPower(), 3.0) + " D");
		labelFrontOpticalPower.setText(" = " + roundLensResultParameter(lens.getFrontOpticalPower(), 3.0) + " D");
		labelBackOpticalPower.setText(" = " + roundLensResultParameter(lens.getBackOpticalPower(), 3.0) + " D");
		labelFocalLength.setText(" = " + roundLensResultParameter(lens.getFocalLength(), 3.0) + " m");
		labelFrontFocalDistance.setText(" = " + roundLensResultParameter(lens.getFrontFocalDistance(), 3.0) + " m");
		labelBackFocalDistance.setText(" = " + roundLensResultParameter(lens.getBackFocalDistance(), 3.0) + " m");
	}
	
	
	// UKŁAD
	public LensResultParametersPanel() {
		setBackground(new Color(210,210,210));
		setLayout(new GridLayout(1,2));
		
		panelDescription.setLayout(new GridLayout(6,1));
		panelDescription.setBackground(new Color(230,210,230));
		
		panelResults.setLayout(new GridLayout(6,1));
		panelResults.setBackground(Color.white);
		
		
		panelDescription.add(labelDescriptionOpticalPower);
		panelDescription.add(labelDescriptionFrontOpticalPower);
		panelDescription.add(labelDescriptionBackOpticalPower);
		panelDescription.add(labelDescriptionFocalLength);
		panelDescription.add(labelDescriptionFrontFocalDistance);
		panelDescription.add(labelDescriptionBackFocalDistance);
		
	
		clearSystemResultParameters();
		
		panelResults.add(labelOpticalPower);
		panelResults.add(labelFrontOpticalPower);
		panelResults.add(labelBackOpticalPower);
		panelResults.add(labelFocalLength);
		panelResults.add(labelFrontFocalDistance);
		panelResults.add(labelBackFocalDistance);
		
		add(panelDescription);
		add(panelResults);
	}
	
	public void resetSystemResultParameters() {
		labelOpticalPower.setText(" = " + roundLensResultParameter(LensSystem.getOpticalPower(), 3.0) + " D");
		labelFrontOpticalPower.setText(" = " + roundLensResultParameter(LensSystem.getFrontOpticalPower(), 3.0) + " D");
		labelBackOpticalPower.setText(" = " + roundLensResultParameter(LensSystem.getBackOpticalPower(), 3.0) + " D");
		labelFocalLength.setText(" = " + roundLensResultParameter(LensSystem.getFocalLength(), 3.0) + " m");
		labelFrontFocalDistance.setText(" = " + roundLensResultParameter(LensSystem.getFrontFocalDistance(), 3.0) + " m");
		labelBackFocalDistance.setText(" = " + roundLensResultParameter(LensSystem.getBackFocalDistance(), 3.0) + " m");
	}
	public void clearSystemResultParameters() {
		labelOpticalPower.setText(" =");
		labelFrontOpticalPower.setText(" =");
		labelBackOpticalPower.setText(" =");
		labelFocalLength.setText(" =");
		labelFrontFocalDistance.setText(" =");
		labelBackFocalDistance.setText(" =");
	}
	
	// ZAOKRĄGLANIE
	private double roundLensResultParameter(double lensResultParameter, double decimalPrecision) {
		return (Math.round(lensResultParameter * Math.pow(10.0, decimalPrecision)) / Math.pow(10.0, decimalPrecision));
	}
}
