// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;

public class LensListPanel extends JPanel {

	private LensDrawingPanel referenceLensDrawingPanel;
	
	public static ArrayList<JButton> buttonList = new ArrayList<JButton>();
	
	public LensListPanel(LensDrawingPanel drawingPanel) {
		referenceLensDrawingPanel = drawingPanel;
		
		setBackground(new Color(210,210,210));
	}
	
	public class LensButtonListener implements ActionListener {
		private Lens newLens;
		public LensButtonListener(Lens lens){
			newLens = lens;
		}
		public void actionPerformed(ActionEvent e) {
			LensSettingsFrame setFrame = new LensSettingsFrame(newLens, referenceLensDrawingPanel, LensListPanel.this);
			setFrame.setVisible(true);
		}
	}
	
	
	public void addLensButton(Lens lens) {
		int lensIndex = lens.getLensIndex();		
		if(!lens.isPositionSet()) {
			buttonList.add(new JButton(lens.getLensName() + "  OFF"));
		}else if(lens.isPositionSet()) {
			buttonList.add(new JButton(lens.getLensName() + "  ON"));
		}
		buttonList.get(lensIndex).addActionListener(new LensButtonListener(lens));
		resetButtons();
	}
	public void removeLensButton(int lensIndex) {
		buttonList.get(lensIndex).setVisible(false);
		buttonList.remove(lensIndex);
		OpticalSystem.removeLens(lensIndex);
	}
	public void resetButtons() {
		removeAll();
		if(!OpticalSystem.lensList.isEmpty()){
			for(int ii=0; ii<OpticalSystem.lensList.size(); ii++) {
				add(buttonList.get(ii));
			}
		}
		revalidate();
		repaint();
	}

}
