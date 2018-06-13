// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.corba.se.impl.io.TypeMismatchException;

public class LSPanelConstructLens extends JPanel {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensCreationPanelLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);

	private JPanel panelLensName = new JPanel();//new FlowLayout(FlowLayout.CENTER,0,10));
	private JPanel panelConstructButton = new JPanel();

	private JButton buttonCreate = new JButton(labels.getString("createButton"));
	private JButton buttonClear = new JButton(labels.getString("clearButton"));
	
	private JTextField textFieldLensName = new JTextField(/*("Lens " + (OpticalSystem.lensList.size()+1))*/"", 8);
	
	private LensCreationPanel referenceLensCreationPanel;
	private LensListPanel referenceLensListPanel;
	
	double refractiveIndex, thickness, frontRadius, backRadius;
	
	ActionListener buttonCreateListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (Double.parseDouble(referenceLensCreationPanel.panelLensThickness.getTextFieldStringValue()) <= 0 ||
						Double.parseDouble(referenceLensCreationPanel.panelFrontSurfaceRadius.getTextFieldStringValue()) == 0 ||
						Double.parseDouble(referenceLensCreationPanel.panelBackSurfaceRadius.getTextFieldStringValue()) == 0 ) throw new IllegalArgumentException();
						
				Lens lens = new Lens(Double.parseDouble(referenceLensCreationPanel.panelRefractiveIndex.getTextFieldStringValue()),
						Double.parseDouble(referenceLensCreationPanel.panelLensThickness.getTextFieldStringValue()),
						Double.parseDouble(referenceLensCreationPanel.panelFrontSurfaceRadius.getTextFieldStringValue()),
						Double.parseDouble(referenceLensCreationPanel.panelBackSurfaceRadius.getTextFieldStringValue()),
						textFieldLensName.getText());
				referenceLensListPanel.addLensButton(lens);
				resetTextFields();
			} catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(referenceLensCreationPanel, messages.getString("wrongInputCreateLensDialogMessage"), messages.getString("wrongInputDialogTitle"), JOptionPane.WARNING_MESSAGE);
				//exception.printStackTrace();
			}
		}
	};
	ActionListener buttonClearListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			resetTextFields();
		}		
	};
	
	
	public LSPanelConstructLens(LensCreationPanel panelCreation, LensListPanel panelList) {
		referenceLensCreationPanel = panelCreation;
		referenceLensListPanel = panelList;
		
		setLayout(new GridLayout(2,1));
		
		resetTextFields();
		
		buttonCreate.addActionListener(buttonCreateListener);
		buttonClear.addActionListener(buttonClearListener);
		
		panelLensName.setBackground(new Color(170,170,170));
		panelLensName.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		panelLensName.add(textFieldLensName);
		panelConstructButton.setBackground(new Color(170,170,170));
		panelConstructButton.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		panelConstructButton.add(buttonCreate);
		panelConstructButton.add(buttonClear);
		
		add(panelLensName);
		add(panelConstructButton);
	}

	
	private void resetTextFields() {
		referenceLensCreationPanel.panelRefractiveIndex.setTextFieldStringValue("");
		referenceLensCreationPanel.panelLensThickness.setTextFieldStringValue("");
		referenceLensCreationPanel.panelFrontSurfaceRadius.setTextFieldStringValue("");
		referenceLensCreationPanel.panelBackSurfaceRadius.setTextFieldStringValue("");
		textFieldLensName.setText(labels.getString("lensNameField") + " " + (OpticalSystem.lensList.size()+1));
	}
}
