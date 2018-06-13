// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FileLoadLensSystemJMenuItem extends JMenuItem {

	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	
	private MainFrame referenceMainFrame;
	private LensListPanel referenceLensListPanel;
	
	private JFileChooser fileChooser;
	private Path path;
	private FileReader fileReader;
	private BufferedReader bufferedReader;
	
	private String[] arrayOfWords;
	
	public FileLoadLensSystemJMenuItem(MainFrame mainFrame, LensListPanel listPanel) {
		setText(labels.getString("loadSystemFromFileItem"));
		
		referenceMainFrame = mainFrame;
		referenceLensListPanel = listPanel;
		
		fileChooser = new JFileChooser();
		
		addActionListener(loadSystemListener);
	}
	
	private void loadSystemFromFile() {
		try {
			fileReader = new FileReader(path.toFile());
			bufferedReader = new BufferedReader(fileReader);
			
			String line;
			int lensNumber;
			int initialLensNumber = OpticalSystem.lensList.size();
			
			try {
				// NUMBER OD LENS
				line = bufferedReader.readLine();
				arrayOfWords = line.split("\\s+");
				lensNumber = Integer.parseInt(arrayOfWords[arrayOfWords.length-1]);
				
				// REFRACTIVE INDEX SURROUNDINGS
				line = bufferedReader.readLine();
				arrayOfWords = line.split("\\s+");
				OpticalSystem.setRefractiveIndexSurroundings(Double.parseDouble(arrayOfWords[arrayOfWords.length-1]));
				
				// LEGEND
				bufferedReader.readLine();
				
				// LENSES
				for(int ii=0; ii<lensNumber; ii++) {
					line = bufferedReader.readLine();
					arrayOfWords = line.split(",\\s+");
					if(!arrayOfWords[arrayOfWords.length-1].equals("none")) {
						Lens lens = new Lens(Double.parseDouble(arrayOfWords[1]), Double.parseDouble(arrayOfWords[2]), Double.parseDouble(arrayOfWords[3]), Double.parseDouble(arrayOfWords[4]), arrayOfWords[0], Double.parseDouble(arrayOfWords[5]));
						referenceLensListPanel.addLensButton(lens);
					} else if(arrayOfWords[arrayOfWords.length-1].equals("none")) {
						Lens lens = new Lens(Double.parseDouble(arrayOfWords[1]), Double.parseDouble(arrayOfWords[2]), Double.parseDouble(arrayOfWords[3]), Double.parseDouble(arrayOfWords[4]), arrayOfWords[0]);
						referenceLensListPanel.addLensButton(lens);
					}
				}
				
				for(int ii=0; ii<(initialLensNumber); ii++) {
					referenceLensListPanel.removeLensButton(0);
				}
				
				JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileSuccessDialogMessage"), messages.getString("loadSystemFromFileSuccessDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
			} catch(Exception e) {
				JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileFormatErrorDialogMessage"), messages.getString("loadSystemFromFileErrorDialogTitle"), JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
			
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileIOErrorDialogMessage"), messages.getString("loadSystemFromFileErrorDialogTitle"), JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
			System.exit(1);
		}
	}
	
	
	ActionListener loadSystemListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileWarningDialogMessage"), messages.getString("loadSystemFromFileWarningDialogTitle"), JOptionPane.WARNING_MESSAGE);
			
			if(fileChooser.showDialog(referenceMainFrame, "Choose file") == JFileChooser.APPROVE_OPTION) {
				path = Paths.get(fileChooser.getSelectedFile().toURI());			
				loadSystemFromFile();
			}
		}
	};


}
