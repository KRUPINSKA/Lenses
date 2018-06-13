// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LSPanelFrontSurfaceRadius extends JPanel implements TextFieldValue {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);

	private JLabel labelFrontSurfaceRadius = new JLabel(labels.getString("frontRadius"));
	private JTextField textFieldFrontSurfaceRadius = new JTextField("", 4);

	
	public LSPanelFrontSurfaceRadius(Lens lens, JLabel labelUnit) {

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldFrontSurfaceRadius.setText(String.valueOf(lens.getFrontRefractiveSurfaceRadius()));
		textFieldFrontSurfaceRadius.setToolTipText("Type - if the surface that meets the light is concave");
		
		add(labelFrontSurfaceRadius);
		add(textFieldFrontSurfaceRadius);
		add(new JLabel(labelUnit.getText()));
	}
	
	public LSPanelFrontSurfaceRadius(JLabel labelUnit) {

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldFrontSurfaceRadius.setText("");
		textFieldFrontSurfaceRadius.setToolTipText("Type - if the surface that meets the light is concave");

		
		add(labelFrontSurfaceRadius);
		add(textFieldFrontSurfaceRadius);
		add(new JLabel(labelUnit.getText()));
	}
	
	
	@Override
	public void setTextFieldDoubleValue(double value) {
		textFieldFrontSurfaceRadius.setText(String.valueOf(value));
	}
	@Override
	public double getTextFieldDoubleValue() {
		return Double.parseDouble(textFieldFrontSurfaceRadius.getText());
	}
	@Override
	public void setTextFieldStringValue(String value) {
		textFieldFrontSurfaceRadius.setText(value);
	}
	@Override
	public String getTextFieldStringValue() {
		return textFieldFrontSurfaceRadius.getText();
	}
	
}

