// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class LSMenuBar extends JMenuBar {
	
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	
	private LensSettingsFrame lensSettingsFrame;
	private Lens referenceLens;
	private LensListPanel referenceLensListPanel;
	private LensDrawingPanel referenceLensDrawingPanel;
	private LSPositionFrame positionFrame;
	private LensSettingsPanel referenceLensSettingsPanel;
	
	private JMenu lensMenu;
	private JMenu positionMenu;
	private JMenu helpMenu;
	
	private JMenuItem saveLensItem;
	private JMenuItem loadLensItem;
	private JMenuItem helpItem;
	private JMenuItem removeLensItem;
	private JMenuItem setPositionItem;
	private JMenuItem removeFromAxisItem;
	
	
	public LSMenuBar(LensSettingsFrame frame, Lens lens, LensListPanel listPanel, LensDrawingPanel drawingPanel, LensSettingsPanel settingsPanel) {
		
		lensSettingsFrame = frame;
		referenceLens = lens;
		referenceLensListPanel = listPanel;
		referenceLensDrawingPanel = drawingPanel;
		referenceLensSettingsPanel = settingsPanel;
		
		lensMenu = new JMenu(labels.getString("lensMenu"));
		positionMenu = new JMenu(labels.getString("positionMenu"));
		helpMenu = new JMenu(labels.getString("helpMenu"));
		
		saveLensItem = new JMenuItem(labels.getString("saveLensItem"));
		loadLensItem = new JMenuItem(labels.getString("loadLensItem"));
		helpItem = new JMenuItem(labels.getString("helpItem"));
		removeLensItem = new JMenuItem(labels.getString("removeLensItem"));
		setPositionItem = new JMenuItem(labels.getString("setPositionItem"));
		removeFromAxisItem = new JMenuItem(labels.getString("removeFromAxisItem"));
		
		//items add Action Listeners
		lensMenu.add(saveLensItem);
		lensMenu.addSeparator();
		lensMenu.add(loadLensItem);
		lensMenu.addSeparator();
		lensMenu.add(removeLensItem);
		
		positionMenu.add(setPositionItem);
		positionMenu.addSeparator();
		positionMenu.add(removeFromAxisItem);
		
		helpMenu.add(helpItem);
		
		helpItem.addActionListener(helpItemListener);
		removeLensItem.addActionListener(removeLensItemListener);
		setPositionItem.addActionListener(setPositionItemListener);
		removeFromAxisItem.addActionListener(removeFromAxisItemListener);
		saveLensItem.addActionListener(new LensToFileWriter(referenceLens));
		loadLensItem.addActionListener(new LensLoadFromFile(referenceLens, referenceLensSettingsPanel));
		
		
		add(lensMenu);
		add(positionMenu);
		add(helpMenu);
	}
	
	ActionListener helpItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(lensSettingsFrame, messages.getString("lsMenuBarHelpDialogMessage"), messages.getString("lsMenuBarHelpDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
		}
	};
	ActionListener removeLensItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			lensSettingsFrame.dispose();
			referenceLensListPanel.removeLensButton(referenceLens.getLensIndex());
		}
	};
	ActionListener setPositionItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			positionFrame = new LSPositionFrame(referenceLens, referenceLensDrawingPanel, referenceLensListPanel, referenceLensSettingsPanel);
			positionFrame.setVisible(true);
		}
	};
	ActionListener removeFromAxisItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			referenceLens.setPositionState(false);
			referenceLensListPanel.buttonList.get(referenceLens.getLensIndex()).setText(referenceLens.getLensName() + "  OFF");
			LensSystem.resetSystem();
			//referenceLensDrawingPanel.addNewElement();
			referenceLensDrawingPanel.repaint();

		}
	};
	
}
