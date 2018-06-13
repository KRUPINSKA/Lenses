// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LSPositionFrame extends JFrame {
	
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	
	private Lens referenceLens;
	private LensDrawingPanel referenceLensDrawingPanel;
	private LensListPanel referenceLensListPanel;
	
	private JPanel panel;
	private JLabel label;
	private JTextField textField;
	private JButton button;
	private JPanel panelTextField, panelButton;
	
	public LSPositionFrame(Lens lens, LensDrawingPanel drawingPanel, LensListPanel listPanel, JComponent locationComponent) {
		referenceLens = lens;
		referenceLensDrawingPanel = drawingPanel;
		referenceLensListPanel = listPanel;
		
		setSize(240,140);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(locationComponent);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		panel.setBackground(new Color(210,210,210));
		add(panel);
		
		panelTextField = new JPanel();
		panelTextField.setOpaque(false);
		label = new JLabel(labels.getString("positionFrameLabel"));
		panelTextField.add(label);
		if(referenceLens.isPositionSet()) textField = new JTextField("" + referenceLens.getDoubleFrontVertexPosition(), 6);
		else textField = new JTextField("", 6);
		panelTextField.add(textField);
		
		panelButton = new JPanel();
		panelButton.setOpaque(false);
		button = new JButton(labels.getString("positionFrameButton"));
		button.addActionListener(setPositionListener);
		panelButton.add(button);

		panel.add(panelTextField);
		panel.add(panelButton);
	}
	
	ActionListener setPositionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				double textFieldValue = Double.parseDouble(textField.getText());
				checkPositionAvailability(textFieldValue);
				
				referenceLens.setFrontVertexPosition(textFieldValue,referenceLens);
				referenceLensListPanel.buttonList.get(referenceLens.getLensIndex()).setText(referenceLens.getLensName() + "  ON");
				referenceLensDrawingPanel.repaint();
				
				
				
				dispose();
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(LSPositionFrame.this, /*"Failed to set a new position. Potential causes:\n   - Provided value might have wrong format\n   - Decimal separator might be >> , << instead of >> . <<"*/messages.getString("wrongInputPositionDialogMessage"), /*"Wrong input"*/messages.getString("wrongInputDialogTitle"), JOptionPane.WARNING_MESSAGE);
				//exception.printStackTrace();
			}
		}
	};
	
	private void checkPositionAvailability(double val){
		if(referenceLens.isPositionSet()) {
			referenceLens.setPositionState(false);
			referenceLensListPanel.buttonList.get(referenceLens.getLensIndex()).setText(referenceLens.getLensName() + "  OFF");
			LensSystem.resetSystem();
			
			referenceLensDrawingPanel.repaint();
		}
		
		for(int i=0;i<LensSystem.systemList.size();i++) {
			if( ((val + referenceLens.getLensThickness()) <= LensSystem.systemList.get(i).getDoubleFrontVertexPosition())
					|| (val >= LensSystem.systemList.get(i).getDoubleBackVertexPosition()) ) {
				// wszystko ok
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
}
