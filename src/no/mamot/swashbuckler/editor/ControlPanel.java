package no.mamot.swashbuckler.editor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;


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
public class ControlPanel extends javax.swing.JFrame implements Runnable {
	
	private LevelEditor levelEditor;
	
	private JMenuBar jMenuBar1;
	private JMenu jMenu3;
	private JMenuItem jMenuItem2;
	private JMenuItem jMenuItem3;
	private JMenu jMenu2;
	private JMenuItem jMenuItem1;
	private JMenu jMenu1;
	private ControlPanelActionListener controlPanelActionListener;
	private ButtonGroup buttonGroup1;
	private JRadioButtonMenuItem jRadioButtonMenuItem1;
	private JRadioButtonMenuItem jRadioButtonMenuItem2;
	private JRadioButtonMenuItem jRadioButtonMenuItem3;
	private JRadioButtonMenuItem jRadioButtonMenuItem4;
	private JRadioButtonMenuItem jRadioButtonMenuItem5;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	
	public ControlPanel(LevelEditor levelEditor) {
		super();
		this.levelEditor = levelEditor;
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
						jRadioButtonMenuItem1 = new JRadioButtonMenuItem();
						jMenu3.add(jRadioButtonMenuItem1);
						jRadioButtonMenuItem1.setText("Draw Polygon");
						getButtonGroup1().add(jRadioButtonMenuItem1);
						jRadioButtonMenuItem1.addActionListener(controlPanelActionListener);
					}
					{
						jRadioButtonMenuItem2 = new JRadioButtonMenuItem();
						jMenu3.add(jRadioButtonMenuItem2);
						jRadioButtonMenuItem2.setText("Draw Particle");
						getButtonGroup1().add(jRadioButtonMenuItem2);
						jRadioButtonMenuItem2.addActionListener(controlPanelActionListener);
						
					}
					{
						jRadioButtonMenuItem3 = new JRadioButtonMenuItem();
						jMenu3.add(jRadioButtonMenuItem3);
						jRadioButtonMenuItem3.setText("Draw Robot");
						getButtonGroup1().add(jRadioButtonMenuItem3);
						jRadioButtonMenuItem3.addActionListener(controlPanelActionListener);
						
					}
					{
						jRadioButtonMenuItem4 = new JRadioButtonMenuItem();
						jMenu3.add(jRadioButtonMenuItem4);
						jRadioButtonMenuItem4.setText("Draw Swashbuckler");
						getButtonGroup1().add(jRadioButtonMenuItem4);
						jRadioButtonMenuItem4.addActionListener(controlPanelActionListener);
						
					}
					{
						jRadioButtonMenuItem5 = new JRadioButtonMenuItem();
						jMenu3.add(jRadioButtonMenuItem5);
						jRadioButtonMenuItem5.setText("Draw Tourmaline");
						getButtonGroup1().add(jRadioButtonMenuItem5);
						jRadioButtonMenuItem5.addActionListener(controlPanelActionListener);
						
					}
				}
			}
			pack();
			setSize(200, 800);
			BoxLayout thisLayout = new BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS);
			getContentPane().setLayout(thisLayout);
			setAlwaysOnTop(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	private class ControlPanelActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource().equals(jMenuItem1)){ //Save
				System.out.println("Save");				
				String path = System.getProperty("user.dir");
				path += "data/levels/";
				JFileChooser fc = new JFileChooser(path);
				fc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return "Level XML files (*.level.xml])";
					}
					
					@Override
					public boolean accept(File f) {
						return f.getName().endsWith(".level.xml");
					}
				});
				int returnVal = fc.showSaveDialog(jMenuItem1.getParent());
				
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile(); // might just be a string??
					System.out.println("Selected file " + file);
					String[] name = file.getName().split("[.]");
					levelEditor.save(name[0]);
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
			else if (event.getSource().equals(jRadioButtonMenuItem1)){ // Toggle Draw Polygon
				System.out.println("Draw polygon");
				if (jRadioButtonMenuItem1.isSelected()){
					levelEditor.setState(DrawEnum.DRAW_POLYGON);
				}
			}
			else if (event.getSource().equals(jRadioButtonMenuItem2)){ // Toggle Draw Particle
				System.out.println("Draw particle");
				if (jRadioButtonMenuItem2.isSelected()){
					levelEditor.setState(DrawEnum.DRAW_PARTICLE);					
				}
			}
			else if (event.getSource().equals(jRadioButtonMenuItem3)){ // Toggle Draw Robot
				System.out.println("Draw robot");
				if (jRadioButtonMenuItem3.isSelected()){
					levelEditor.setState(DrawEnum.DRAW_ROBOT);					
				}
			}
			else if (event.getSource().equals(jRadioButtonMenuItem4)){ // Toggle Draw Swashbuckler
				System.out.println("Draw Swashbuckler");
				if (jRadioButtonMenuItem4.isSelected()){
					levelEditor.setState(DrawEnum.DRAW_SWASHBUCKLER);					
				}
			}
			else if (event.getSource().equals(jRadioButtonMenuItem5)){ // Toggle Draw Tourmaline
				System.out.println("Draw Tourmaline");
				if (jRadioButtonMenuItem5.isSelected()){
					levelEditor.setState(DrawEnum.DRAW_TOURMALINE);					
				}
			}
			
			
		}
			
	 }



	@Override
	public void run() {
		// TODO Auto-generated method stub
		ControlPanel inst = new ControlPanel(levelEditor);
		inst.setLocationRelativeTo(null);
		inst.setVisible(true);
		
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}

}



