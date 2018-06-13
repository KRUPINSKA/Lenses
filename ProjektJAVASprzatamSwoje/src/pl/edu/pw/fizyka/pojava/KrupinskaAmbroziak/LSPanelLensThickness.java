// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LSPanelLensThickness extends JPanel implements TextFieldValue {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);

	private JLabel labelLensThickness = new JLabel(labels.getString("thickness"));
	private JTextField textFieldLensThickness = new JTextField("", 4);


	public LSPanelLensThickness(Lens lens, JLabel labelUnit) {

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldLensThickness.setText(String.valueOf(lens.getLensThickness()));
		
		add(labelLensThickness);
		add(textFieldLensThickness);
		add(new JLabel(labelUnit.getText()));
	
	}
	
	public LSPanelLensThickness(JLabel labelUnit) {

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldLensThickness.setText("");
		
		add(labelLensThickness);
		add(textFieldLensThickness);
		add(new JLabel(labelUnit.getText()));
	
	}
	
	
	@Override
	public void setTextFieldDoubleValue(double value) {
		textFieldLensThickness.setText(String.valueOf(value));
	}
	@Override
	public double getTextFieldDoubleValue() {
		return Double.parseDouble(textFieldLensThickness.getText());
	}
	@Override
	public void setTextFieldStringValue(String value) {
		textFieldLensThickness.setText(value);
	}

	@Override
	public String getTextFieldStringValue() {
		return textFieldLensThickness.getText();
	}
}
