package Editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel{

	
	public JButton newBoard;
	public JButton open;
	public JButton save;
	public JButton saveAs;
	public JButton play;
	public JButton globalOption;
	
	public JButton newButton;
	public JButton newLabel;
	public JButton newClock;
	public JButton newTextBox;
	public JButton newAlert;
	
	private Font defaultFont = new Font("Times New Roman",6,Font.PLAIN);
	
	BoardPanel(int width){
		this.setLayout(null);
		this.setBounds(0, 0, width, 60);
		//this.setBackground(Color.blue);
		this.setPreferredSize(new Dimension(width,40));
		this.setVisible(true);
		this.setBackground(SystemColor.activeCaption);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, SystemColor.inactiveCaption));
		
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
//		try {
//			image = ImageIO.read(ResourceLoader.load("new board.png"));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		newBoard = new JButton();
		newBoard.setIcon(new ImageIcon(image));
		newBoard.setBounds(0, 0, 40, 40);
		newBoard.setToolTipText("Clears the board");
		add(newBoard);
		
		try {
			image = ImageIO.read(ResourceLoader.load("load.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		open = new JButton();
		open.setIcon(new ImageIcon(image));
		open.setBounds(120, 0, 40, 40);
		open.setToolTipText("Opens a saved board");
		add(open);
		
		try {
			image = ImageIO.read(ResourceLoader.load("save.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		save = new JButton();
		save.setIcon(new ImageIcon(image));
		//save.setPressedIcon(pressedIcon);
		save.setBounds(40, 0, 40, 40);
		save.setToolTipText("Saves the current board");
		add(save);
		
		try {
			image = ImageIO.read(ResourceLoader.load("save as.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		saveAs = new JButton();
		saveAs.setIcon(new ImageIcon(image));
		saveAs.setFont(defaultFont);
		saveAs.setBounds(80, 0, 40, 40);
		saveAs.setToolTipText("Renames and saves the current board");
		add(saveAs);
		
		try {
			image = ImageIO.read(ResourceLoader.load("button-test.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newButton = new JButton();
		newButton.setIcon(new ImageIcon(image));
		newButton.setBounds(200, 0, 40, 40);
		newButton.setToolTipText("Inserts a new button");
		add(newButton);
		
		try {
			image = ImageIO.read(ResourceLoader.load("label-test.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newLabel = new JButton();
		newLabel.setIcon(new ImageIcon(image));
		newLabel.setBounds(240, 0, 40, 40);
		newLabel.setToolTipText("Inserts a new label");
		add(newLabel);
		
		newTextBox = new JButton();
		newTextBox.setIcon(new ImageIcon(""));
		newTextBox.setBounds(280, 0, 40, 40);
		newTextBox.setToolTipText("Inserts a new textbox");
		add(newTextBox);
		
		try {
			image = ImageIO.read(ResourceLoader.load("clock.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newClock = new JButton();
		newClock.setIcon(new ImageIcon(image));
		newClock.setBounds(320, 0, 40, 40);
		newClock.setToolTipText("Inserts a clock button");
		add(newClock);
		
		try {
			image = ImageIO.read(ResourceLoader.load("alert.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newAlert = new JButton();
		newAlert.setIcon(new ImageIcon(image));
		newAlert.setBounds(360, 0, 40, 40);
		newAlert.setToolTipText("Inserts a manual alert button");
		add(newAlert);
		
		try {
			image = ImageIO.read(ResourceLoader.load("alert2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		newAlert.setRolloverIcon(new ImageIcon(image));
		
		
		try {
			image = ImageIO.read(ResourceLoader.load("options.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		globalOption = new JButton();
		globalOption.setIcon(new ImageIcon(image));
		globalOption.setBounds(440, 0, 40, 40);
		globalOption.setToolTipText("Opens the Program Options menu");
		add(globalOption);
		
		try {
			image = ImageIO.read(ResourceLoader.load("play.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		play = new JButton();
		play.setIcon(new ImageIcon(image));
		play.setBounds(480, 0, 40, 40);
		play.setToolTipText("Play");
		add(play);
		
		
	}
}
