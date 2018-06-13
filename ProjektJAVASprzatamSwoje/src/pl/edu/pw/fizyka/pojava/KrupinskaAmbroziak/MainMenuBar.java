package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainMenuBar extends JMenuBar {
	
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	
	private MainFrame mainFrame;
	private LensDrawingPanel lensDrawingPanel;
	private LensListPanel referenceLensListPanel;
	
	private FileSaveLensSystemJMenuItem fileSaveLensSystemJMenuItem;
	private FileLoadLensSystemJMenuItem fileLoadLensSystemJMenuItem;
	
	private DatabaseSaveLensSystemJMenuItem dbSaveLensSystemJMenuItem;
	private DatabaseLoadLensSystemJMenuItem dbLoadLensSystemJMenuItem;
	
	private JMenu saveSystemMenu;
	private JMenu loadSystemMenu;
	
	private JMenuItem helpMe;
	private JMenuItem resetDrawingPanel;
	private JMenuItem rayPosition;
	
	private JMenu menu;
	private JMenu system;
	private JMenu help;

	Lens newlens;
	double refractiveIndex, thickness, frontRadius, backRadius;
	static double ray=0;
	
	public MainMenuBar(MainFrame frame, LensDrawingPanel drawingPanel, LensListPanel listPanel){
		
		mainFrame = frame;
		lensDrawingPanel = drawingPanel;
		referenceLensListPanel = listPanel;
		
		menu = new JMenu(labels.getString("menuMenu"));
		system = new JMenu(labels.getString("systemMenu"));
		help = new JMenu(labels.getString("helpMenu"));

		fileSaveLensSystemJMenuItem = new FileSaveLensSystemJMenuItem(mainFrame);
		fileLoadLensSystemJMenuItem = new FileLoadLensSystemJMenuItem(mainFrame, referenceLensListPanel);
		helpMe = new JMenuItem(labels.getString("instructionItem"));
		resetDrawingPanel = new JMenuItem(labels.getString("resetDrawingPanelItem"));
		rayPosition = new JMenuItem(labels.getString("ray"));
		
		dbSaveLensSystemJMenuItem = new DatabaseSaveLensSystemJMenuItem(mainFrame);
		dbLoadLensSystemJMenuItem = new DatabaseLoadLensSystemJMenuItem(mainFrame, referenceLensListPanel, drawingPanel);
		
		saveSystemMenu = new JMenu(labels.getString("saveSystemMenu"));
		loadSystemMenu = new JMenu(labels.getString("loadSystemMenu"));
		
		system.add(saveSystemMenu);
		system.addSeparator();
		system.add(loadSystemMenu);
		
		
		saveSystemMenu.add(fileSaveLensSystemJMenuItem);
		saveSystemMenu.addSeparator();
		saveSystemMenu.add(dbSaveLensSystemJMenuItem);
		
		loadSystemMenu.add(fileLoadLensSystemJMenuItem);
		loadSystemMenu.addSeparator();
		loadSystemMenu.add(dbLoadLensSystemJMenuItem);
		
		
		menu.add(system);
		
		menu.addSeparator();
		
		resetDrawingPanel.addActionListener(resetDrawingLensesPanelListener);
		menu.add(resetDrawingPanel);
		
		menu.addSeparator();
		
		rayPosition.addActionListener(rayListener);

		menu.add(rayPosition);

		
		helpMe.addActionListener(helpItemListener);
		help.add(helpMe);
		
		
		add(menu);
		add(help);
	}

	ActionListener helpItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(mainFrame, messages.getString("mainMenuBarHelpDialogMessage"), messages.getString("mainMenuBarHelpDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
		}
	};
	
	ActionListener rayListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String rayPosition =JOptionPane.showInputDialog(mainFrame, messages.getString("mainMenuBarRayMessage"),messages.getString("mainMenuBarRayTitle"),JOptionPane.QUESTION_MESSAGE);
			
			
				try {
					ray = Double.parseDouble(rayPosition);
					lensDrawingPanel.repaint();
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(mainFrame, messages.getString("wrongInputRayMessage"), messages.getString("wrongInputDialogTitle"), JOptionPane.WARNING_MESSAGE);
					//e.printStackTrace();
				}
		}
	};

	
	ActionListener resetDrawingLensesPanelListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(int i=0;i<OpticalSystem.lensList.size();i++) {
				if(OpticalSystem.lensList.get(i).isPositionSet()) {
					OpticalSystem.lensList.get(i).setPositionState(false);
					referenceLensListPanel.buttonList.get(i).setText(OpticalSystem.lensList.get(i).getLensName() + "  OFF");
					LensSystem.resetSystem();
				}
			}
			lensDrawingPanel.repaint();
		}
	};
	
	public static double getRayPosition(){
		return ray;
	}
	
}