//Natalia Krupińska

package pl.edu.pw.fizyka.pojava.KrupinskaAmbroziak;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class RayPanel extends JPanel {
	
	private JCheckBox ray;
	private JCheckBox principalPlanes;
	private JCheckBox lensPrincipalPlanes;
	
	private ResourceBundle labels = ResourceBundle.getBundle("bundles.RayPanelLabels", MainFrame.locale);
	
	public RayPanel(LensDrawingPanel lensDrawingPanel){
		
		setBackground(new Color(210,210,210));
		
		ray = new JCheckBox(labels.getString("ray"));
		principalPlanes = new JCheckBox(labels.getString("system"));
		lensPrincipalPlanes = new JCheckBox(labels.getString("lens"));
		
		ray.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		        
		        	lensDrawingPanel.selected =true;        	
		        	lensDrawingPanel.repaint();
		        } else {//checkbox has been deselected
	        		lensDrawingPanel.selected = false;
		        	lensDrawingPanel.repaint();

		        };
		    }
		});
		
		principalPlanes.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		        
		        	lensDrawingPanel.selected2 =true;        	
		        	lensDrawingPanel.repaint();
		        } else {//checkbox has been deselected
	        		lensDrawingPanel.selected2 = false;
		        	lensDrawingPanel.repaint();

		        };
		    }
		});
		
		lensPrincipalPlanes.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
		        
		        	lensDrawingPanel.selected3 =true;        	
		        	lensDrawingPanel.repaint();
		        } else {//checkbox has been deselected
	        		lensDrawingPanel.selected3 = false;
		        	lensDrawingPanel.repaint();

		        };
		    }
		});
		
		add(ray);
		add(principalPlanes);
		add(lensPrincipalPlanes);
	}

	

}
