package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main extends JFrame{
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		    	 MainFrame frame = new MainFrame();
		    	 frame.setVisible(true);
		      }
		    });
	}

}
