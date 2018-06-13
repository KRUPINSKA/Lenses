package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private ResourceBundle frameTitle = ResourceBundle.getBundle("bundles.MiscLabels",Locale.getDefault());
	private ResourceBundle selectLanguageDialog = ResourceBundle.getBundle("bundles.SelectLanguage",Locale.getDefault());
	public static Locale locale;
	
	private MainPanel mainPanel;

	public MainFrame() {
		setSize(1400,700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		/////////////////////////////////// PODEJSCIE 1
		/*
		String loc = JOptionPane.showInputDialog(selectLanguageDialog.getString("dialogMessage"), Locale.getDefault().getLanguage());
		
		if(loc.equals("en")) {
			locale = Locale.ROOT;
		} else if(loc.equals("pl")) {
			locale = new Locale("pl", "PL");
		} else {
			locale = Locale.getDefault();
						JOptionPane.showMessageDialog(null, selectLanguageDialog.getString("languageNotChosenMessage"));
		}*/
		///////////////////////////////////
		
		/////////////////////////////////// PODEJSCIE 2
		Object[] possibleLanguages = {"English", "Polski"};
		String loc = String.valueOf(JOptionPane.showInputDialog(this,selectLanguageDialog.getString("dialogMessage"),selectLanguageDialog.getString("dialogTitle"),JOptionPane.PLAIN_MESSAGE,null,possibleLanguages,"English"));
		if(loc.equals("English")) {
			locale = Locale.ROOT;
		} else if(loc.equals("Polski")) {
			locale = new Locale("pl", "PL");
		} else {
			locale = Locale.getDefault();
			JOptionPane.showMessageDialog(null, selectLanguageDialog.getString("languageNotChosenMessage"), selectLanguageDialog.getString("dialogTitle"), JOptionPane.WARNING_MESSAGE);
		}
		///////////////////////////////////
		
		/////////////////////////////////// PODEJSCIE 3
/*		Object[] possibleLanguages = {"English", "Polski"};
		String loc = String.valueOf(JOptionPane.showInputDialog(this,"SelectLanguage","Language",JOptionPane.PLAIN_MESSAGE,null,possibleLanguages,"English"));
		if(loc.equals("English")) {
			locale = Locale.ROOT;
		} else if(loc.equals("Polski")) {
			locale = new Locale("pl", "PL");
		} else {
			locale = Locale.getDefault();
			JOptionPane.showMessageDialog(null, selectLanguageDialog.getString("languageNotChosenMessage"), selectLanguageDialog.getString("dialogTitle"), JOptionPane.WARNING_MESSAGE);
		}
*/		///////////////////////////////////
		
		frameTitle = ResourceBundle.getBundle("bundles.MiscLabels",locale);
		setTitle(frameTitle.getString("mainFrameTitle"));
		
		mainPanel = new MainPanel();
		setJMenuBar(new MainMenuBar(this, mainPanel.lensDrawingPanel, mainPanel.lensListPanel));

		add(mainPanel);
	
	}

	
}
