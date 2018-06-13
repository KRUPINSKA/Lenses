package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class MainPanel extends JPanel {

	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MiscLabels", MainFrame.locale);
	
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel centerPanel;
	
	protected LensCreationPanel lensCreationPanel;
	protected SurroundingsPanel surroundingsPanel;
	protected LensListPanel lensListPanel;
	protected LensDrawingPanel lensDrawingPanel;
	protected RayPanel rayPanel;
	
	private LensSystem lensSystem;
	protected LensResultParametersPanel systemResultPanel;
	
	
	private Border standardBorder = BorderFactory.createLineBorder(Color.darkGray);
			
	MainPanel() {
		setLayout(new BorderLayout());
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBorder(standardBorder);
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setBorder(standardBorder);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBorder(standardBorder);
		
		lensDrawingPanel = new LensDrawingPanel();
		lensDrawingPanel.setBorder(standardBorder);
		centerPanel.add(lensDrawingPanel, BorderLayout.CENTER);
		
		lensListPanel = new LensListPanel(lensDrawingPanel);
		lensListPanel.setBorder(new TitledBorder(standardBorder, labels.getString("lensListPanelBorderTitle")));
		topPanel.add(lensListPanel, BorderLayout.CENTER);
		
		lensCreationPanel = new LensCreationPanel(lensListPanel);
		lensCreationPanel.setBorder(standardBorder);
		rightPanel.add(lensCreationPanel, BorderLayout.PAGE_END);
		
		surroundingsPanel = new SurroundingsPanel();
		surroundingsPanel.setBorder(standardBorder);
		
		rayPanel = new RayPanel(lensDrawingPanel);
		rayPanel.setBorder(standardBorder);
		
		lensSystem = new LensSystem(this);
		systemResultPanel = new LensResultParametersPanel();
		systemResultPanel.setBorder(new TitledBorder(standardBorder, labels.getString("systemResultPanelBorderTitle")));
		rightPanel.add(systemResultPanel, BorderLayout.CENTER);
		
		///
		
		JPanel rightTopPanel = new JPanel();
		rightTopPanel.setLayout(new BorderLayout());
		rightPanel.add(rightTopPanel, BorderLayout.PAGE_START);
		rightTopPanel.add(surroundingsPanel, BorderLayout.PAGE_START);
		rightTopPanel.add(rayPanel, BorderLayout.CENTER);	
		///
		
		add(topPanel, BorderLayout.PAGE_START);
		add(rightPanel, BorderLayout.LINE_END);
		add(centerPanel, BorderLayout.CENTER);

		
	}
}
