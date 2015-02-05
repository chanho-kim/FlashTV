package Editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import Editor.Components.Button;
import Editor.Components.Clock;
import Editor.Components.Label;
import Editor.Menus.MainOptions;
import Editor.Menus.ProgramOption;

public class MainPanel extends JPanel implements WindowListener, ActionListener, ComponentListener{
	
	private static BoardPanel bPanel;
	public static Workboard board;
	private static Playboard playboard;
	private static ProgramOption options;
	
	private static JScrollPane scroller;
	
	public static boolean editMode;
	public static boolean mainOptionsOn;
	
	
	public static Color displayBg;
	public static Font displayFt;
	public static String displayTxtSize;
	public static String displayTxtSizeC;
	public static String displayTxtSizeT;
	public static Color displayTxtColor;
	public static String displayDuration;
	public static boolean bold;
	public static boolean italic;
	public static Color autoAlertColor;
	public static String autoAlertTimes;
	public static String autoAlertSpeed;
	public static boolean autoAlert;
	public static boolean screenChoice;
	
	
	public static String savefile = null;
	
	JPanel bottom;
	
	
	public static String name = "Chris Vu";
	public static String sc = "0123456789";
	
	MainPanel(int x, int y, int width, int height){
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		
		bPanel = new BoardPanel(this.getWidth());
		board = new Workboard(width,height);
		bottom = new JPanel();
		bottom.setBounds(0, height-30, width, 30);
		bottom.setBackground(SystemColor.inactiveCaption);
		add(bottom);
		
		
		bPanel.newBoard.addActionListener(this);
		bPanel.save.addActionListener(this);
		bPanel.saveAs.addActionListener(this);
		bPanel.open.addActionListener(this);
		bPanel.globalOption.addActionListener(this);
		bPanel.play.addActionListener(this);
		
		
		bPanel.newButton.addActionListener(this);
		bPanel.newLabel.addActionListener(this);
		bPanel.newTextBox.addActionListener(this);
		bPanel.newClock.addActionListener(this);
		bPanel.newAlert.addActionListener(this);
		
		mainOptionsOn = false;
		
		this.add(bPanel);
		this.add(board);
		
		displayBg = Color.BLACK;
		displayFt = new Font("Times New Roman",Font.BOLD,20);
		displayTxtSize = "300";
		displayTxtSizeC = "300";
		displayTxtSizeT = "200";
		displayTxtColor = Color.WHITE;
		displayDuration = "5.00";
		bold = true;
		italic = false;
		editMode = true;
		
		autoAlertColor = Color.RED;
		autoAlertTimes = "5";
		autoAlertSpeed = "0.50";
		
		autoAlert = true;
		screenChoice = false;
	}

	private void openOptions() {
		options = new ProgramOption();
		options.getComponent();
		options.setVisible(true);		
	}
	
	private void play(){
		int boundX = 0;
		int boundY = 0;
		
		editMode = false;
		int answer = JOptionPane.CANCEL_OPTION;
		Component[] components = board.getComponents();
		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		 GraphicsDevice[] gs = ge.getScreenDevices();
		 
		 if(gs.length != 1){
			GraphicsDevice gd = gs[0];
			JFrame frame = new JFrame(gd.getDefaultConfiguration());
			if(MainPanel.screenChoice){
				answer = JOptionPane.showConfirmDialog(frame, "Will you be operating on this screen?", "Detected Multiple Displays!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
			}
			else{
				answer = JOptionPane.YES_OPTION;
			}
			frame.dispose();
		 }
		 
		 //they do not have multiple display
		 if(answer == JOptionPane.CANCEL_OPTION){
			 GraphicsConfiguration gc1 = gs[0].getDefaultConfiguration();
			 playboard = new Playboard(board.getWidth(), board.getHeight(),components, gc1, null);
			 boundX = (int) gc1.getBounds().getWidth();
			 boundY = (int) gc1.getBounds().getHeight();
		 }
		 
		 else if(answer == JOptionPane.YES_OPTION){
			 GraphicsConfiguration gc1 = gs[0].getDefaultConfiguration();
			 GraphicsConfiguration gc2 = gs[1].getDefaultConfiguration();
			 
			 playboard = new Playboard(board.getWidth(), board.getHeight(),components, gc1, gc2);
			 
			 boundX = (int) gc1.getBounds().getWidth();
			 boundY = (int) gc1.getBounds().getHeight();
		 }
		 else if(answer == JOptionPane.NO_OPTION){
			 GraphicsConfiguration gc1 = gs[0].getDefaultConfiguration();
			 GraphicsConfiguration gc2 = gs[1].getDefaultConfiguration();		 
			 playboard = new Playboard(board.getWidth(), board.getHeight(),components, gc2, gc1); 
			 
			 boundX = (int) gc2.getBounds().getWidth();
			 boundY = (int) gc2.getBounds().getHeight();
		 }
		 else{
			 return;			 
		 }
		 playboard.addWindowListener(this);
		 Playboard.display.addWindowListener(this);
		 
		 
//		display = new Display();
//		display.addWindowListener(this);
//		
		 scroller = new JScrollPane(Playboard.playPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		 scroller.setBounds(0,0,boundX-15,boundY-35);
		 //scroller.setUI(Frame.originalUI);
		 //scroller.setViewportBorder(BorderFactory.createLineBorder(SystemColor.activeCaption, 3));
		 playboard.getContentPane().add(scroller);
		 playboard.addComponentListener(this);
		 
		 playboard.repaint();
		 playboard.setVisible(true);
		 Frame.frame.setVisible(false);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		playboard.setVisible(false);
		Playboard.display.setVisible(false);
		Playboard.display.dispose();
		playboard.dispose();
		Frame.frame.setVisible(true);
		editMode = true;
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bPanel.newBoard)){
			System.out.println("new board");
			int approval = JOptionPane.showConfirmDialog(this, "<html>Do you want to clear the board?<br>All your unsaved progress will be lost.", "Warning!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(approval == JOptionPane.YES_OPTION){
				board.newBoard();
			}
		}
		
		else if(e.getSource().equals(bPanel.save)){
			if(savefile != null){
				board.save(savefile);
			}
			else{
				final JFileChooser fc = new JFileChooser();
				
				fc.setSelectedFile(new File("*.sav"));				
				FileFilter filter = new FileNameExtensionFilter("*.sav","sav");
				fc.setFileFilter(filter);
			
				int response = fc.showSaveDialog(this);
				if(response == JFileChooser.APPROVE_OPTION){
					String name = fc.getSelectedFile().toString();
									
					int index = name.length() - 4;
					if(index > -1){
						String substring = name.substring(index);
						if(substring.equals(".sav")){
							savefile = name;
						}
						else{
							savefile = name + ".sav";
						}
					}
					else{
						savefile = name + ".sav";
					}
					board.save(savefile);
				}
				else{
				}				
			}
		}
		
		else if(e.getSource().equals(bPanel.saveAs)){
			final JFileChooser fc = new JFileChooser();
			fc.setSelectedFile(new File("*.sav"));	
			FileFilter filter = new FileNameExtensionFilter("*.sav","sav");
			fc.setFileFilter(filter);
			int response = fc.showSaveDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				String name = fc.getSelectedFile().toString();
									
				int index = name.length() - 4;
				if(index > -1){
					String substring = name.substring(index);
					if(substring.equals(".sav")){
						savefile = name;
					}
					else{
						savefile = name + ".sav";
					}
				}
				else{
					savefile = name + ".sav";
				}
				board.save(savefile);
			}
			else{
			}
			
		}
		
		else if(e.getSource().equals(bPanel.open)){
//			String load = JOptionPane.showInputDialog(this, "<html>Which file would you like to load? <br>(Enter the name without the '.dat' at the end)", "Load", JOptionPane.QUESTION_MESSAGE);
//			board.load(load);
//			savefile = load;
			
			final JFileChooser fc = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("*.sav","sav");
			fc.setFileFilter(filter);
			int response = fc.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				String name = fc.getSelectedFile().toString();
				board.load(name);
				savefile = name;
			}
			else{
			}
		}

		else if(e.getSource().equals(bPanel.globalOption)){
			if(!mainOptionsOn){
				System.out.println("Options");
				openOptions();
				mainOptionsOn = true;
			}
			else{
				options.requestFocus();
			}
		}
		
		else if(e.getSource().equals(bPanel.play)){
			System.out.println("play");
			board.cancelSelection();
			board.cancelSingleSelection();
			play();	
		}
		
		else if(e.getSource().equals(bPanel.newButton)){
			board.insertNewButton();
		}
		
		else if(e.getSource().equals(bPanel.newLabel)){
			board.insertNewLabel();
		}
		
		else if(e.getSource().equals(bPanel.newTextBox)){
			board.insertNewTextBox();
		}
		
		else if(e.getSource().equals(bPanel.newClock)){
			board.insertNewClock();
		}
		
		else if(e.getSource().equals(bPanel.newAlert)){
			board.insertNewAlert();
		}
		
	}

	public static int convertDisplayDuration(){
		float b = Float.parseFloat(displayDuration);
		b = b * 1000;
		b = Math.round(b);
		int a = (int)b;
		return a;	
	}
	
	public static int convertAutoAlertSpeed(){
		float b = Float.parseFloat(autoAlertSpeed);
		b = b * 1000;
		b = Math.round(b);
		int a = (int)b;
		return a;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		int width = playboard.getWidth();
		int height = playboard.getHeight();
		Dimension dimension = playboard.getContentPane().getPreferredSize();
		int x = (int)dimension.getWidth();
		int y = (int)dimension.getHeight();
		if(x < width){
			x = width - 15;
		}		
		else if(x > width-15){
			x = width - 15;
		}
		if(y < height){
			y = height - 35;
		}
		else if(y > height-35){
			y = height - 35;
		}
		
		scroller.setBounds(0, 0, x, y);
		playboard.getContentPane().revalidate();
		playboard.getContentPane().repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	 
}
