//Natalia Krupiñska

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LensToFileWriter implements ActionListener {
	
	private Lens referenceLens;
	
	public LensToFileWriter(Lens lens){
	referenceLens = lens;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		File file = new File(referenceLens.getLensName()+".txt");
		
		String content = Double.toString(referenceLens.getLensRefractiveIndex())+"\n"+ Double.toString(referenceLens.getLensThickness())+"\n"+ Double.toString(referenceLens.getFrontRefractiveSurfaceRadius())+"\n"+ Double.toString(referenceLens.getBackRefractiveSurfaceRadius());
		try (FileOutputStream fop = new FileOutputStream(file)) {

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			/*String content cannot be directly written into
			   * a file. It needs to be converted into bytes
			   */
			byte[] contentInBytes = content.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
