// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DatabaseSaveLensSystemJMenuItem extends JMenuItem {
	
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	
	private MainFrame referenceMainFrame;

	public DatabaseSaveLensSystemJMenuItem(MainFrame mainFrame) {
		//setText(labels.getString("saveSystemItem"));
		setText(labels.getString("saveSystemToDatabaseItem"));
		
		
		referenceMainFrame = mainFrame;

		addActionListener(saveSystemListener);
	}
	
	private void saveSystemToDB() throws SQLException {
		Connection connection = null;
		try {
			// WPISYWANIE NAZWY BAZY
			String databaseName = JOptionPane.showInputDialog(this, "Base name:");
			// WYBIERANIE BAZY JFILECHOOSER
		//	JFileChooser chooser = new JFileChooser();
		//	chooser.showOpenDialog(null);
		//	File file = chooser.getSelectedFile();
		//	String chosenDataBase = file.getName();
			//////////////////////////////////////////////////////////////////////////////////
			
			connection = DriverManager.getConnection(	"jdbc:h2:./data/"+databaseName, "user", "");
		
			Statement statement = connection.createStatement();
		    statement.executeUpdate("DROP TABLE IF EXISTS `lenses`;");
				

			statement.executeUpdate("CREATE TABLE `lenses` ("+
						  "`Id` int(3) unsigned NOT NULL auto_increment,"+
						  "`Name` varchar default NULL,"+
						  "`RefractiveIndex` float(3) default NULL,"+
						  "`Thickness` float(3) default NULL,"+
						  "`FrontRadius` float(3) default NULL,"+
						  "`BackRadius` float(3) default NULL,"+
						  "`Position` varchar default NULL,"+
						  "PRIMARY KEY  (`Id`)"+") ;");
			for(int i=0;i<OpticalSystem.lensList.size();i++) {
				String position;
				String name = OpticalSystem.lensList.get(i).getLensName();
				double refInd = OpticalSystem.lensList.get(i).getLensRefractiveIndex();
				double thick = OpticalSystem.lensList.get(i).getLensThickness();
				double front = OpticalSystem.lensList.get(i).getFrontRefractiveSurfaceRadius();
				double back = OpticalSystem.lensList.get(i).getBackRefractiveSurfaceRadius();
				
				if(OpticalSystem.lensList.get(i).isPositionSet()) {
					position = String.valueOf(OpticalSystem.lensList.get(i).getDoubleFrontVertexPosition());
				} else {
					position = "none";
				}
					
				statement.executeUpdate("INSERT INTO `lenses` "
						+ "(`Id`,`Name`,`RefractiveIndex`,`Thickness`,`FrontRadius`,`BackRadius`,`Position`)"
						+ " VALUES ("+i+",'"+name+"','"+refInd+"','"+thick+"','"+front+"','"+back+"','"+position+"');");
			}
			
			JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("saveSystemToFileSuccessDialogMessage"), messages.getString("saveSystemToFileSuccessDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (connection!= null){
				connection.close();
			}
		}
		
		System.out.println("Utworzono tabele danych" );
	}
	
	ActionListener saveSystemListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				saveSystemToDB();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileFormatErrorDialogMessage"), messages.getString("loadSystemFromFileErrorDialogTitle"), JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
		}
	};
}
