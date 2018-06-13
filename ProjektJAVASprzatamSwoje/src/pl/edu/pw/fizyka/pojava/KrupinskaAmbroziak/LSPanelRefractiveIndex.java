// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LSPanelRefractiveIndex extends JPanel implements TextFieldValue {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
	
	private JLabel labelRefractiveIndex = new JLabel(labels.getString("refractiveIndex"));
	private JLabel emptySpace = new JLabel();
	private JTextField textFieldRefractiveIndex = new JTextField("", 4);

	
	public LSPanelRefractiveIndex(Lens lens, int labelUnitWidth) {

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldRefractiveIndex.setText(String.valueOf(lens.getLensRefractiveIndex()));
		
		emptySpace.setBorder(new EmptyBorder(0,labelUnitWidth,0,0));
		
		add(labelRefractiveIndex);
		add(textFieldRefractiveIndex);
		add(emptySpace);
	}
	
	public LSPanelRefractiveIndex(int labelUnitWidth){

		setBackground(new Color(230,210,230));
		setLayout(flowLayout);
		
		textFieldRefractiveIndex.setText("");
		
		emptySpace.setBorder(new EmptyBorder(0,labelUnitWidth,0,0));
		
		add(labelRefractiveIndex);
		add(textFieldRefractiveIndex);
		add(emptySpace);
	}
	
	
	@Override
	public void setTextFieldDoubleValue(double value) {
		textFieldRefractiveIndex.setText(String.valueOf(value));
	}
	@Override
	public double getTextFieldDoubleValue() {
		return Double.parseDouble(textFieldRefractiveIndex.getText());
	}
	@Override
	public void setTextFieldStringValue(String value) {
		textFieldRefractiveIndex.setText(value);
	}
	@Override
	public String getTextFieldStringValue() {
		return textFieldRefractiveIndex.getText();
	}
}

