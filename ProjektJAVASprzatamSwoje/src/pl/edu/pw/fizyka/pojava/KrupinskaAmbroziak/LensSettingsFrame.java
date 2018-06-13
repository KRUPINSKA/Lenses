// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LensSettingsFrame extends JFrame {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.LensSettingsLabels", MainFrame.locale);
	
	private JPanel rearPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
	
	private LensSettingsPanel lensSettingsPanel;
	private LensResultParametersPanel lensResultParametersPanel;
	private LensListPanel lensListPanel;
	private LensDrawingPanel lensDrawingPanel;
	
	
	public LensSettingsFrame(Lens lens, LensDrawingPanel drawingPanel, LensListPanel listPanel) {
		lensDrawingPanel = drawingPanel;
		lensListPanel = listPanel;
		
		setSize(720, 260);
		setMinimumSize(new Dimension(720,260));
		//setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(labels.getString("lensSettingsFrameTitle") + lens.getLensName());
		
		lensResultParametersPanel = new LensResultParametersPanel(lens);
		lensSettingsPanel = new LensSettingsPanel(lens, lensResultParametersPanel, listPanel ,drawingPanel);	
		setJMenuBar(new LSMenuBar(this, lens, lensListPanel, lensDrawingPanel, lensSettingsPanel));
		
		rearPanel.add(lensSettingsPanel);
		rearPanel.add(lensResultParametersPanel);
		rearPanel.setBackground(new Color(210,210,210));
		
		add(rearPanel);

		setLocationRelativeTo(lensListPanel);
	}
	
}
