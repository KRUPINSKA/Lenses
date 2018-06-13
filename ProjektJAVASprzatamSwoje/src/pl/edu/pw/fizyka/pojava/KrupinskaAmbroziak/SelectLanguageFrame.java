// napisalem, ale w koncu nie uzywane

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.GridLayout;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SelectLanguageFrame extends JFrame {

	private static Locale locale;
	
	private JPanel panel;
	private JButton selectButton;
	
	private JLabel message;
	private JRadioButton enButton, plButton;
	private ButtonGroup buttonGroup = new ButtonGroup();
	
	public SelectLanguageFrame() {
		setSize(140,260);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		
		message = new JLabel("Prosze wybrac jezyk");
		add(message);
		panel = new JPanel();
		add(panel);
		
		
		setVisible(true);
	}
	
	public static Locale funkcja() {
		SelectLanguageFrame languageFrame = new SelectLanguageFrame();
		return locale;
	}
}
