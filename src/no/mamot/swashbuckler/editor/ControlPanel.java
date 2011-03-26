package no.mamot.swashbuckler.editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ControlPanel extends javax.swing.JFrame {
	private JMenuBar jMenuBar1;
	private JMenu jMenu3;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenu jMenu2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private ControlPanelActionListener controlPanelActionListener;
	private JMenuItem jMenuItem4;

	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public ControlPanel() {
		super();
		controlPanelActionListener = new ControlPanelActionListener();		
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("File");
					{
						jMenuItem1 = new JMenuItem();
						jMenu1.add(jMenuItem1);
						jMenuItem1.setText("Save");
						jMenuItem1.addActionListener(controlPanelActionListener);
					}
					{
						jMenuItem2 = new JMenuItem();
						jMenu1.add(jMenuItem2);
						jMenuItem2.setText("Level properties");
						jMenuItem2.addActionListener(controlPanelActionListener);
					}
					{
						jMenuItem3 = new JMenuItem();
						jMenu1.add(jMenuItem3);
						jMenuItem3.setText("Exit");
						jMenuItem3.addActionListener(controlPanelActionListener);
					}
				}
				{
					jMenu2 = new JMenu();
					jMenuBar1.add(jMenu2);
					jMenu2.setText("Edit");
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Tools");
					{
						jMenuItem4 = new JMenuItem();
						jMenu3.add(jMenuItem4);
						jMenuItem4.setText("Draw Polgyon");
					}
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private class ControlPanelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			if (event.getSource().equals(jMenuItem1)){ //Save
				System.out.println("Save");				
				String path = System.getProperty("user.dir");
				path += "/data/";
				JFileChooser fc = new JFileChooser(path);
				int returnVal = fc.showSaveDialog(jMenuItem1.getParent());
				
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile(); // might just be a string??
					System.out.println("Selected file " + file);		
					
				}
				else {
					System.out.println("Canceled");
				}
				
				
				
				
			}
			else if (event.getSource().equals(jMenuItem2)){ //Level properties
				System.out.println("Level properties");
			}
			else if (event.getSource().equals(jMenuItem3)){ // Exit
				System.out.println("Exit");
			}
			
			
			
		}
			
	 }
	
}



