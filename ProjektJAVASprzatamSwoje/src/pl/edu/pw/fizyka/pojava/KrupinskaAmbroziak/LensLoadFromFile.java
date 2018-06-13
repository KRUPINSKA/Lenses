//Natalia Krupiñska
package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class LensLoadFromFile implements ActionListener {
	
private Lens referenceLens;
private LensSettingsPanel referenceLensSettingsPanel;
	
	public LensLoadFromFile(Lens lens, LensSettingsPanel settingsPanel){
	referenceLens = lens;
	referenceLensSettingsPanel = settingsPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.showDialog(null, "Wybierz");
		chooser.getSelectedFile();
		
		FileReader fr = null;
		
		//		OTWIERANIE PLIKU:
		try {
			fr = new FileReader(chooser.getSelectedFile());
		} catch (FileNotFoundException e) {
			System.out.println("BLAD PRZY OTWIERANIU PLIKU!");
			System.exit(1);
		}
		BufferedReader bfr = new BufferedReader(fr);
		String linia = "";
		double [] dane = new double[4];
		int i = 0;
        // ODCZYT KOLEJNYCH LINII Z PLIKU:
		try {
			while((linia = bfr.readLine()) != null){
			      dane[i]=(Double.parseDouble(linia));
			      i++;
			}
			  
		referenceLens.setLensParameters(dane[0], dane[1], dane[2], dane[3]);
		
		referenceLensSettingsPanel.panelRefractiveIndex.setTextFieldDoubleValue(referenceLens.getLensRefractiveIndex());
		referenceLensSettingsPanel.panelLensThickness.setTextFieldDoubleValue(referenceLens.getLensThickness());
		referenceLensSettingsPanel.panelFrontSurfaceRadius.setTextFieldDoubleValue(referenceLens.getFrontRefractiveSurfaceRadius());
		referenceLensSettingsPanel.panelBackSurfaceRadius.setTextFieldDoubleValue(referenceLens.getBackRefractiveSurfaceRadius());
		
		
		//System.out.println(referenceLens.getBackRefractiveSurfaceRadius());
		 }
		
		 catch (IOException e) {
			System.out.println("BLAD ODCZYTU Z PLIKU!");
			System.exit(2);
		}
		
		// ZAMYKANIE PLIKU
		try {
			fr.close();
		} catch (IOException e) {
			System.out.println("BLAD PRZY ZAMYKANIU PLIKU!");
			System.exit(3);
			}
	}

}
