// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LSPanelApplyChanges extends JPanel {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	
	private JPanel panelLensName = new JPanel();//new FlowLayout(FlowLayout.CENTER,0,10));
	private JPanel panelApplyButton = new JPanel();

	private JButton buttonApply = new JButton(labels.getString("applyButton"));
	private JButton buttonCancel = new JButton(labels.getString("cancelButton"));
	
	private JLabel labelLensName;
	
	private Lens referenceLens;
	private LensSettingsPanel referenceLensSettingsPanel;
	private LensResultParametersPanel referenceLensResultParametersPanel;
	private double refractiveIndex, thickness, frontRadius, backRadius;
	
	private LensListPanel referenceLensListPanel;
	private LensDrawingPanel referenceLensDrawingPanel;
	
	ActionListener applyChangesListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		//	referenceLens.setLensParameters(referenceLensSettingsPanel.panelRefractiveIndex.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelLensThickness.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelFrontSurfaceRadius.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelBackSurfaceRadius.getTextFieldDoubleValue());
			try {
				if (Double.parseDouble(referenceLensSettingsPanel.panelLensThickness.getTextFieldStringValue()) <= 0 ||
						Double.parseDouble(referenceLensSettingsPanel.panelFrontSurfaceRadius.getTextFieldStringValue()) == 0 ||
						Double.parseDouble(referenceLensSettingsPanel.panelBackSurfaceRadius.getTextFieldStringValue()) == 0 ) throw new IllegalArgumentException();
				
				referenceLens.setLensParameters(Double.parseDouble(referenceLensSettingsPanel.panelRefractiveIndex.getTextFieldStringValue()),
						Double.parseDouble(referenceLensSettingsPanel.panelLensThickness.getTextFieldStringValue()),
						Double.parseDouble(referenceLensSettingsPanel.panelFrontSurfaceRadius.getTextFieldStringValue()),
						Double.parseDouble(referenceLensSettingsPanel.panelBackSurfaceRadius.getTextFieldStringValue()));
			
				referenceLensResultParametersPanel.resetLensResultParameters(referenceLens);
				
				referenceLens.setPositionState(false);
				referenceLensListPanel.buttonList.get(referenceLens.getLensIndex()).setText(referenceLens.getLensName() + "  OFF");
				LensSystem.resetSystem();
				referenceLensDrawingPanel.repaint();
				
				getParameters();
			} catch (IllegalArgumentException exception) {
				JOptionPane.showMessageDialog(referenceLensSettingsPanel, messages.getString("wrongInputModifyLensDialogMessage"), messages.getString("wrongInputDialogTitle"), JOptionPane.WARNING_MESSAGE);
				//exception.printStackTrace();
			}				
			//	referenceLensResultParametersPanel.resetLensResultParameters(referenceLens);
			//	getParameters();
		}
	};
	ActionListener cancelChangesListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			referenceLensSettingsPanel.panelRefractiveIndex.setTextFieldDoubleValue(refractiveIndex);
			referenceLensSettingsPanel.panelLensThickness.setTextFieldDoubleValue(thickness);
			referenceLensSettingsPanel.panelFrontSurfaceRadius.setTextFieldDoubleValue(frontRadius);
			referenceLensSettingsPanel.panelBackSurfaceRadius.setTextFieldDoubleValue(backRadius);
			
			//TO TEZ POTRZEBNE ... TRZEBA JAKOS WARUNKI USTALIC, DO JAKICH WARTOSCI MA COFAC ****  JEST DOBRZE????? :D
			referenceLens.setLensParameters(referenceLensSettingsPanel.panelRefractiveIndex.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelLensThickness.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelFrontSurfaceRadius.getTextFieldDoubleValue(), referenceLensSettingsPanel.panelBackSurfaceRadius.getTextFieldDoubleValue());
			referenceLensResultParametersPanel.resetLensResultParameters(referenceLens);
		}		
	};
	
	
	public LSPanelApplyChanges(Lens lens, LensSettingsPanel panelSettings, LensResultParametersPanel panelResults, LensListPanel listPanel, LensDrawingPanel drawingPanel) {
		referenceLens = lens;
		referenceLensSettingsPanel = panelSettings;
		referenceLensResultParametersPanel = panelResults;
		
		referenceLensListPanel = listPanel;
		referenceLensDrawingPanel = drawingPanel;
		
		getParameters();
		
		setLayout(new GridLayout(1,2));
		
		labelLensName = new JLabel(lens.getLensName());
		labelLensName.setForeground(Color.white);
		labelLensName.setFont(labelLensName.getFont().deriveFont(14.0f));
		
		buttonApply.addActionListener(applyChangesListener);
		buttonCancel.addActionListener(cancelChangesListener);
		
		panelLensName.setBackground(Color.gray);
		panelLensName.add(labelLensName);
		panelApplyButton.setBackground(Color.gray);
		panelApplyButton.add(buttonApply);
		panelApplyButton.add(buttonCancel);
		
		add(panelLensName);
		add(panelApplyButton);
	}
	
	private void getParameters() {
		refractiveIndex = referenceLensSettingsPanel.panelRefractiveIndex.getTextFieldDoubleValue();
		thickness = referenceLensSettingsPanel.panelLensThickness.getTextFieldDoubleValue();
		frontRadius = referenceLensSettingsPanel.panelFrontSurfaceRadius.getTextFieldDoubleValue();
		backRadius = referenceLensSettingsPanel.panelBackSurfaceRadius.getTextFieldDoubleValue();
	}
}
