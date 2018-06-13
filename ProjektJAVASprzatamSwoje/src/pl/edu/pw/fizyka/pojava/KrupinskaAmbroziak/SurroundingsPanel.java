// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SurroundingsPanel extends JPanel {
	
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.SurroundingsPanelLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	
	private JTextField refractiveIndexSurroundingsTextField;
	private JLabel refractiveIndexSurroundingsLabel;
	
	private JButton changeIndexButton;
	
	SurroundingsPanel(){
		setBackground(new Color(210,210,210));
		
		refractiveIndexSurroundingsTextField = new JTextField("" + OpticalSystem.getRefractiveIndexSurroundings(), 4);
		refractiveIndexSurroundingsLabel = new JLabel(labels.getString("label"));
	
		changeIndexButton = new JButton(labels.getString("button"));
		changeIndexButton.addActionListener(refIndexListener);
		
		add(refractiveIndexSurroundingsLabel);
		add(refractiveIndexSurroundingsTextField);
		add(changeIndexButton);
	}
	
	ActionListener refIndexListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				OpticalSystem.setRefractiveIndexSurroundings(Double.parseDouble(refractiveIndexSurroundingsTextField.getText()));
			
				for(int ii=0; ii<OpticalSystem.lensList.size(); ii++) {
					OpticalSystem.lensList.get(ii).setLensParameters(OpticalSystem.lensList.get(ii).getLensRefractiveIndex(), OpticalSystem.lensList.get(ii).getLensThickness(), OpticalSystem.lensList.get(ii).getFrontRefractiveSurfaceRadius(), OpticalSystem.lensList.get(ii).getBackRefractiveSurfaceRadius());
				}
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(SurroundingsPanel.this, messages.getString("wrongInputSurroundingsDialogMessage"), messages.getString("wrongInputDialogTitle"), JOptionPane.WARNING_MESSAGE);
				//e.printStackTrace();
			}
		}
	};
}
