// KACPER AMBROZIAK

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class DatabaseLoadLensSystemJMenuItem extends JMenuItem {
	
	private ResourceBundle messages = ResourceBundle.getBundle("bundles.Messages", MainFrame.locale);
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.MenuBarLabels", MainFrame.locale);
	
	private MainFrame referenceMainFrame;
	private LensListPanel referenceLensListPanel;
	private LensDrawingPanel referenceLensDrawingPanel;
	
	JFileChooser chooser;
	
	public DatabaseLoadLensSystemJMenuItem(MainFrame mainFrame, LensListPanel listPanel, LensDrawingPanel drawingPanel) {
		setText(labels.getString("loadSystemFromDatabaseItem"));
		
		referenceMainFrame = mainFrame;
		referenceLensListPanel = listPanel;
		referenceLensDrawingPanel = drawingPanel;
		
		addActionListener(loadSystemListener);
	}
	
	private void loadSystemFromDB() throws SQLException {
		Connection connection = null;
		
		try {
			// WPISYWANIE NAZWY BAZY
		//	String dataBaseName = JOptionPane.showInputDialog(this, "Base name:");
			// WYBIERANIE BAZY JFILECHOOSER

		//	chooser.showOpenDialog(null);
			File file = chooser.getSelectedFile();
			String[] chosenDatabase = file.getName().split(".mv");
			//////////////////////////////////////////////////////////////////////////////////
		
			connection = DriverManager.getConnection(	"jdbc:h2:./data/"+chosenDatabase[0], "user", "");
			
			Statement statement = connection.createStatement();
			
			statement.execute("SELECT * FROM lenses");
	
			ResultSet rs = statement.getResultSet();	
			ResultSetMetaData md  = rs.getMetaData();
			
			int initialLensNumber = OpticalSystem.lensList.size();
			
			while (rs.next()) {
				
				String name = rs.getString(2);
				double refInd = Double.parseDouble(String.valueOf(rs.getBigDecimal(3)/*.setScale(3)*/));//rs.getDouble(3);
				double thick = Double.parseDouble(String.valueOf(rs.getBigDecimal(4)));
				double front = Double.parseDouble(String.valueOf(rs.getBigDecimal(5)));
				double back = Double.parseDouble(String.valueOf(rs.getBigDecimal(6)));
				if(rs.getObject(md.getColumnCount()).equals("none")) {
					Lens lens = new Lens(refInd,thick,front,back,name);
					referenceLensListPanel.addLensButton(lens);
				}
				else {
					Lens lens = new Lens(refInd,thick,front,back,name,Double.parseDouble(rs.getString(7)));
					referenceLensListPanel.addLensButton(lens);
				}
			}

			for(int ii=0; ii<(initialLensNumber); ii++) {
				referenceLensListPanel.removeLensButton(0);
			}
			JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileSuccessDialogMessage"), messages.getString("loadSystemFromFileSuccessDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
			
		} finally {
			if (connection!= null){
				connection.close();
			}
		} 
	}


	ActionListener loadSystemListener = new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileWarningDialogMessage"), messages.getString("loadSystemFromFileWarningDialogTitle"), JOptionPane.WARNING_MESSAGE);
				
				chooser = new JFileChooser();
				if(chooser.showDialog(referenceMainFrame, "Choose file") == JFileChooser.APPROVE_OPTION) {
					loadSystemFromDB();
					referenceMainFrame.repaint();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(referenceMainFrame, messages.getString("loadSystemFromFileFormatErrorDialogMessage"), messages.getString("loadSystemFromFileErrorDialogTitle"), JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
			}
		}
	};
}

