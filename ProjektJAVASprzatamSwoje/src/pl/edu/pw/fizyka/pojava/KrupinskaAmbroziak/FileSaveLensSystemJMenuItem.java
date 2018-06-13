// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FileSaveLensSystemJMenuItem extends JMenuItem {

	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	
	private MainFrame referenceMainFrame;

	private JFileChooser fileChooser;
	private Path path;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	
	public FileSaveLensSystemJMenuItem(MainFrame mainFrame){
		setText(labels.getString("saveSystemToFileItem"));
		
		referenceMainFrame = mainFrame;
		
		fileChooser = new JFileChooser();
		
		addActionListener(saveSystemListener);
	}
	
	
	private void saveSystemToFile() {
		try {
			fileWriter = new FileWriter(path.toFile());
			bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write("Number of lens: " + OpticalSystem.lensList.size());
			bufferedWriter.newLine();
			bufferedWriter.write("Refractive index of surroundings: " + OpticalSystem.getRefractiveIndexSurroundings());
			bufferedWriter.newLine();
			bufferedWriter.write("Lens name,\tRefractiveIndex,\tThickness [m],\tFront surface radius [m],\tBack surface radius [m],\t Position on axis [m]");
			bufferedWriter.newLine();
			for(int ii=0; ii<OpticalSystem.lensList.size(); ii++) {
				bufferedWriter.write(OpticalSystem.lensList.get(ii).getLensName() + ",\t\t" +
						OpticalSystem.lensList.get(ii).getLensRefractiveIndex() + ",\t\t" +
						OpticalSystem.lensList.get(ii).getLensThickness() + ",\t\t" +
						OpticalSystem.lensList.get(ii).getFrontRefractiveSurfaceRadius() + ",\t\t" +
						OpticalSystem.lensList.get(ii).getBackRefractiveSurfaceRadius());
				if(OpticalSystem.lensList.get(ii).isPositionSet()) bufferedWriter.write(",\t\t" + OpticalSystem.lensList.get(ii).getDoubleFrontVertexPosition());
				else bufferedWriter.write(",\t\t" + "none");
				bufferedWriter.newLine();
			}
			
			JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("saveSystemToFileSuccessDialogMessage"), messages.getString("saveSystemToFileSuccessDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(referenceMainFrame,messages.getString("saveSystemToFileIOErrorDialogMessage"), messages.getString("saveSystemToFileErrorDialogTitle"), JOptionPane.ERROR_MESSAGE);
			System.exit(1);
			//e.printStackTrace();
		}
	}
	
	ActionListener saveSystemListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(fileChooser.showDialog(referenceMainFrame, "Choose file") == JFileChooser.APPROVE_OPTION) {
				path = Paths.get(fileChooser.getSelectedFile().toURI());
				saveSystemToFile();
			}
		}
	};

}
