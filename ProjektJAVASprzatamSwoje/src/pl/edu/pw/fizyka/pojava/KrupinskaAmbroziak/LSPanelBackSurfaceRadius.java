// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LSPanelBackSurfaceRadius extends JPanel implements TextFieldValue {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);

	private JLabel labelBackSurfaceRadius = new JLabel(labels.getString("backRadius"));
	private JTextField textFieldBackSurfaceRadius = new JTextField("", 4);

	
	public LSPanelBackSurfaceRadius(Lens lens, JLabel labelUnit){

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		
		textFieldBackSurfaceRadius.setText(String.valueOf(lens.getBackRefractiveSurfaceRadius()));
		
		add(labelBackSurfaceRadius);
		add(textFieldBackSurfaceRadius);
		add(new JLabel(labelUnit.getText()));
	}
	
	public LSPanelBackSurfaceRadius(JLabel labelUnit){

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldBackSurfaceRadius.setText("");
		
		add(labelBackSurfaceRadius);
		add(textFieldBackSurfaceRadius);
		add(new JLabel(labelUnit.getText()));
	}
	
	
	@Override
	public void setTextFieldDoubleValue(double value) {
		textFieldBackSurfaceRadius.setText(String.valueOf(value));
	}
	@Override
	public double getTextFieldDoubleValue() {
		return Double.parseDouble(textFieldBackSurfaceRadius.getText());
	}
	@Override
	public void setTextFieldStringValue(String value) {
		textFieldBackSurfaceRadius.setText(value);		
	}
	@Override
	public String getTextFieldStringValue() {
		return textFieldBackSurfaceRadius.getText();
	}
	
}
