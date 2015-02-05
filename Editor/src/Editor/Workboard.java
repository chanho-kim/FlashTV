package Editor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

import Editor.Components.Alert;
import Editor.Components.Button;
import Editor.Components.Clock;
import Editor.Components.Label;
import Editor.Components.TextBox;
import Editor.Menus.AlertMenu;
import Editor.Menus.ButtonMenu;
import Editor.Menus.ClockMenu;
import Editor.Menus.GroupMenu;
import Editor.Menus.LabelMenu;
import Editor.Menus.Menu;
import Editor.Menus.TextBoxMenu;

public class Workboard extends JPanel implements MouseListener, MouseMotionListener{
	
	private static ArrayList<Integer> gridx = new ArrayList<>();
	private static ArrayList<Integer> gridy = new ArrayList<>();
	
	private boolean resize = false;
	private boolean drag = false;
	private Point dragStart;
	private JLabel dragImg;
	private Rectangle dragArea;
	private static ArrayList<JComponent> dragSelection = new ArrayList<>();
	private static ArrayList<Border> dragSelectionBorder = new ArrayList<>();
	public static boolean dragSelected = false;
	
	private static Border selectedBorder = BorderFactory.createDashedBorder(Color.BLUE,1,5,1,false);
	public static Border defaultBorder = BorderFactory.createLineBorder(Color.BLACK,3);
	private Font defaultFont = new Font("Times New Roman",Font.PLAIN,12);
	
	private Point prevPoint;
	
	public static Component selected = null;
	public static JComponent rightSelected = null;
	
	private static ButtonMenu buttonMenu;
	private static LabelMenu labelMenu;
	private static ClockMenu clockMenu;
	private static AlertMenu alertMenu;
	private static TextBoxMenu txtBoxMenu;
	private static GroupMenu grpMenu;
	
	//private static EditMenu editMenu;

	public static Border savedBorder;
	
	public static ImageIcon cornerImage = new ImageIcon();
	
	

	//Initialization
	Workboard(int width, int height){
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		this.setPreferredSize(new Dimension(width-20,height-100));
		createGrid(width,height);
		dragArea = new Rectangle();
		dragImg = new JLabel();
		dragImg.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		addMouseListener(this);
		addMouseMotionListener(this);	
				
		InputMap inputMap;
	//	inputMap = this.getInputMap(JPanel.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0,true), "delete");
		this.getActionMap().put("delete", new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("delete!");
				delete2();
			}			
		});
		
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_DOWN_MASK,true), "copy");
		this.getActionMap().put("copy", new AbstractAction(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("copy!");
				copy2();
			}			
		});
		
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		try {
			image = ImageIO.read(ResourceLoader.load("corner.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cornerImage = new ImageIcon(image);
		
	}
	
	public void newBoard(){
		clear();
		
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		//this.setBounds(5, 80, this.getWidth(), this.getHeight());
		
		MainPanel.savefile = "";
		MainPanel.displayBg = Color.BLACK;
		MainPanel.displayFt = new Font("Times New Roman",Font.BOLD,20);
		MainPanel.displayTxtSize = "300";
		MainPanel.displayTxtSizeC = "300";
		MainPanel.displayTxtSizeT = "200";
		MainPanel.displayTxtColor = Color.WHITE;
		MainPanel.displayDuration = "5.00";
		MainPanel.bold = true;
		MainPanel.italic = false;
		MainPanel.editMode = true;
		MainPanel.autoAlertColor = Color.RED;
		MainPanel.autoAlertSpeed = "0.50";
		MainPanel.autoAlertTimes = "5";		
	}
	
	public void save(String file){
		PrintWriter out;
		
		
		try{
			File myFile = new File(file);
			myFile.delete();
			File myNewFile = new File(file);
			myNewFile.createNewFile();
			out = new PrintWriter(new FileWriter(file,true));
			
			//save the size of this board
			out.println(expandNumber(this.getWidth())+" "+expandNumber(this.getHeight()));
			out.println(Menu.determineBgColorIndex2(this.getBackground()));
			out.println(Menu.determineBgColorIndex2(MainPanel.displayBg));
			out.println(Menu.determineFtIndex(MainPanel.displayFt));
			out.println(MainPanel.displayTxtSize);
			out.println(MainPanel.displayTxtSizeC);
			out.println(MainPanel.displayTxtSizeT);
			out.println(Menu.determineBgColorIndex2(MainPanel.displayTxtColor));
			out.println(MainPanel.displayDuration);
			out.println(MainPanel.bold);
			out.println(MainPanel.italic);
			out.println(Menu.determineBgColorIndex2(MainPanel.autoAlertColor));
			out.println(MainPanel.autoAlertSpeed);
			out.println(MainPanel.autoAlertTimes);
			
			
			Component[] components = this.getComponents();
			for(int i=0; i<components.length; i+=1){
				if(components[i] instanceof Button){
					Button button = (Button) components[i];
					Font font = button.getFont();
					out.println("0");
					out.print(button.getX()+" "+button.getY()+" "+button.getWidth()+" "+button.getHeight()+" ");
					out.print(Menu.determineBgColorIndex2(button.getBackground())+" ");
					out.print(Menu.determineFgColorIndex(button.getForeground())+" ");
					out.print(Menu.determineFtIndex(font) + " ");
					out.print(font.getSize() + " ");
					if(Menu.determineBold(font)) out.print("1" + " ");
					else out.print("0" + " ");
					if(Menu.determineItalic(font)) out.println("1");
					else out.println("0");
					out.println(button.getText());
					out.println(button.getDisplayTxt());
					out.println();
				}
				else if(components[i] instanceof Label){
					Label label = (Label) components[i];
					Font font = label.getFont();
					out.println("1");
					out.print(label.getX()+" "+label.getY()+" "+label.getWidth()+" "+label.getHeight()+" ");
					out.print(Menu.determineBgColorIndex2(label.getBackground())+" ");
					out.print(Menu.determineFgColorIndex(label.getForeground())+" ");
					out.print(Menu.determineFtIndex(font) + " ");
					out.print(font.getSize() + " ");
					if(Menu.determineBold(font)) out.print("1" + " ");
					else out.print("0" + " ");
					if(Menu.determineItalic(font)) out.println("1");
					else out.println("0");
					out.println(label.getText());
					out.println();
				}
				else if(components[i] instanceof Clock){
					Clock clock = (Clock) components[i];
					Font font = clock.getFont();
					out.println("2");
					out.print(clock.getX()+" "+clock.getY()+" "+clock.getWidth()+" "+clock.getHeight()+" ");
					out.print(Menu.determineBgColorIndex2(clock.getBackground())+" ");
					out.print(Menu.determineFgColorIndex(clock.getForeground())+" ");
					out.print(Menu.determineFtIndex(font) + " ");
					out.print(font.getSize() + " ");
					if(Menu.determineBold(font)) out.print("1" + " ");
					else out.print("0" + " ");
					if(Menu.determineItalic(font)) out.println("1");
					else out.println("0");
					out.println(clock.getText());
					out.println();
				}
				else if(components[i] instanceof TextBox){
					TextBox txtbox = (TextBox) components[i];
					Font font = txtbox.getFont();
					out.println("3");
					out.print(txtbox.getX()+" "+txtbox.getY()+" "+txtbox.getWidth()+" "+txtbox.getHeight()+" ");
					out.print(Menu.determineBgColorIndex2(txtbox.getBackground())+" ");
					out.print(Menu.determineFgColorIndex(txtbox.getForeground())+" ");
					out.print(Menu.determineFtIndex(font) + " ");
					out.print(font.getSize() + " ");
					if(Menu.determineBold(font)) out.print("1" + " ");
					else out.print("0" + " ");
					if(Menu.determineItalic(font)) out.println("1");
					else out.println("0");
					out.println();
				}
				else if(components[i] instanceof Alert){
					Alert alert = (Alert) components[i];
					Font font = alert.getFont();
					out.println("4");
					out.print(alert.getX()+" "+alert.getY()+" "+alert.getWidth()+" "+alert.getHeight()+" ");
					out.print(Menu.determineBgColorIndex2(alert.getBackground())+" ");
					out.print(Menu.determineFgColorIndex(alert.getForeground())+" ");
					out.print(Menu.determineFtIndex(font) + " ");
					out.print(font.getSize() + " ");
					if(Menu.determineBold(font)) out.print("1" + " ");
					else out.print("0" + " ");
					if(Menu.determineItalic(font)) out.print("1" + " ");
					else out.print("0" + " ");
					out.println(Menu.determineBgColorIndex2(alert.getAlertColor()));
					out.println(alert.getText());
					out.println(alert.getAlertSpeed());
					out.println(alert.getAlertTimes());
					out.println();					
				}
			}			
			out.close();			
		} catch(IOException e){
		}		
	}
	
	public void load(String file){
		String text;
		
		clear();
		try{
			int bold;
			int italic;
			
			BufferedReader in = new BufferedReader(new FileReader(file));
			text = in.readLine();
			int screenWidth = Integer.parseInt(text.substring(0, 4));
			int screenHeight = Integer.parseInt(text.substring(5));
			this.setPreferredSize(new Dimension(screenWidth, screenHeight));
			
			Workboard.clearGrid();
			Workboard.createGrid(screenWidth, screenHeight);
			
			if(screenWidth > Frame.getScreenWidth()-15){
				screenWidth = Frame.mainPanel.getWidth()-20;
			}
			if(screenHeight > Frame.getScreenHeight()-75){
				screenHeight = Frame.mainPanel.getHeight()-75;
			}
			Frame.scroller.setBounds(5, 60, screenWidth+15, screenHeight+15);			
			
			this.setBackground(Menu.determineColor(Integer.parseInt(in.readLine())));
			MainPanel.displayBg = Menu.determineColor(Integer.parseInt(in.readLine()));
			int ftIndex = Integer.parseInt(in.readLine());
			MainPanel.displayTxtSize = in.readLine();
			MainPanel.displayTxtSizeC = in.readLine();
			MainPanel.displayTxtSizeT = in.readLine();
			MainPanel.displayTxtColor = Menu.determineColor(Integer.parseInt(in.readLine()));
			MainPanel.displayDuration = in.readLine();
			if(in.readLine().equals(true)) bold = 1;
			else bold = 0;
			if(in.readLine().equals(true)) italic = 1;
			else italic = 0;
			MainPanel.displayFt = Menu.determineFont2(ftIndex, 0, bold, italic);
			
			MainPanel.autoAlertColor = Menu.determineColor(Integer.parseInt(in.readLine()));
			MainPanel.autoAlertSpeed = in.readLine();
			MainPanel.autoAlertTimes = in.readLine();
			
			while((text=in.readLine())!=null){
				int object = Integer.parseInt(text);
				if(object == 0){
					text = in.readLine();
					String[] properties = text.split("\\s+");
					text = in.readLine();
					Button button = new Button(text);
					text = in.readLine();
					button.changeDisplayTxt(text);
					button.setBounds(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
					button.setBackground(Menu.determineColor(Integer.parseInt(properties[4])));
					button.setForeground(Menu.determineColor(Integer.parseInt(properties[5])));
					button.setFont(Menu.determineFont2(Integer.parseInt(properties[6]), Integer.parseInt(properties[7]), Integer.parseInt(properties[8]), Integer.parseInt(properties[9])));
					button.addMouseListener(this);
					button.addMouseMotionListener(this);
					button.setBorder(defaultBorder);
					this.add(button);
					text = in.readLine();
				}
				else if(object == 1){
					text = in.readLine();
					String[] properties = text.split("\\s+");
					text = in.readLine();
					Label label = new Label(text);
					label.setBounds(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
					label.setBackground(Menu.determineColor(Integer.parseInt(properties[4])));
					label.setForeground(Menu.determineColor(Integer.parseInt(properties[5])));
					label.setFont(Menu.determineFont2(Integer.parseInt(properties[6]), Integer.parseInt(properties[7]), Integer.parseInt(properties[8]), Integer.parseInt(properties[9])));
					label.addMouseListener(this);
					label.addMouseMotionListener(this);
					label.setOpaque(true);
					label.setHorizontalAlignment(SwingConstants.CENTER);
					label.setVerticalAlignment(SwingConstants.CENTER);
					label.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
					this.add(label);
					text = in.readLine();
				}
				else if(object == 2){
					text = in.readLine();
					String[] properties = text.split("\\s+");
					text = in.readLine();
					Clock clock = new Clock();
					clock.setText(text);
					clock.setBounds(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
					clock.setBackground(Menu.determineColor(Integer.parseInt(properties[4])));
					clock.setForeground(Menu.determineColor(Integer.parseInt(properties[5])));
					clock.setFont(Menu.determineFont2(Integer.parseInt(properties[6]), Integer.parseInt(properties[7]), Integer.parseInt(properties[8]), Integer.parseInt(properties[9])));
					clock.addMouseListener(this);
					clock.addMouseMotionListener(this);
					clock.setBorder(defaultBorder);
					this.add(clock);
					text = in.readLine();					
				}
				else if(object == 3){
					text = in.readLine();
					String[] properties = text.split("\\s+");
					TextBox txtbox = new TextBox();
					txtbox.setBounds(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
					txtbox.setBackground(Menu.determineColor(Integer.parseInt(properties[4])));
					txtbox.setForeground(Menu.determineColor(Integer.parseInt(properties[5])));
					txtbox.setFont(Menu.determineFont2(Integer.parseInt(properties[6]), Integer.parseInt(properties[7]), Integer.parseInt(properties[8]), Integer.parseInt(properties[9])));
					txtbox.addMouseListener(this);
					txtbox.addMouseMotionListener(this);
					txtbox.setBorder(defaultBorder);
					txtbox.setEditable(false);
					this.add(txtbox);
					text = in.readLine();
				}
				else if(object == 4){
					text = in.readLine();
					String[] properties = text.split("\\s+");
					text = in.readLine();
					Alert alert = new Alert(text);
					text = in.readLine();
					alert.setAlertSpeed(text);
					text = in.readLine();
					alert.setAlertTimes(text);
					alert.setBounds(Integer.parseInt(properties[0]), Integer.parseInt(properties[1]), Integer.parseInt(properties[2]), Integer.parseInt(properties[3]));
					alert.setBackground(Menu.determineColor(Integer.parseInt(properties[4])));
					alert.setForeground(Menu.determineColor(Integer.parseInt(properties[5])));
					alert.setFont(Menu.determineFont2(Integer.parseInt(properties[6]), Integer.parseInt(properties[7]), Integer.parseInt(properties[8]), Integer.parseInt(properties[9])));
					alert.addMouseListener(this);
					alert.addMouseMotionListener(this);
					alert.setBorder(defaultBorder);
					alert.setAlertColor(Menu.determineColor(Integer.parseInt(properties[10])));
					this.add(alert);
					text = in.readLine();
				}
				
			}
			in.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(Frame.frame, "<html>An error occurred while loading the file!<br>The file does not exist / is corrupted!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		Frame.getContainer().revalidate();
		Frame.getContainer().repaint();
	}

	private String expandNumber(int in){
		String string = String.valueOf(in);
		if(in < 1000) string = "0" + string;
		if(in < 100) string = "0" + string;
		return string;
	}
	
	
	public void insertNewButton(){
		Boolean empty = false;
		
		cancelSingleSelection();	
		cancelSelection();
		
		//Button newButton = new Button("Button");
		Button newButton = new Button("Button");
		Component comp;
		Point p = new Point(95,35);
		while(!empty){
			if(!((comp = this.getComponentAt(p)) instanceof Workboard)){
				p.x += 10;
				p.y += 10;
			}
			else empty = true;
		}
		//newButton.setOpaque(true);
		newButton.setBounds((int)p.getX()-95, (int)p.getY()-35, 100, 40);
		//newButton.createCorner();		
		//newButton.setText("<html><div style=\"text-align:center\";>Hello<br>World");
		
		newButton.setFont(defaultFont);
		newButton.addMouseListener(this);
		newButton.addMouseMotionListener(this);
		newButton.setBackground(Color.WHITE); //set default colors here
		newButton.setForeground(Color.BLACK);  //
		newButton.setBorder(selectedBorder);
		this.add(newButton);
		this.setComponentZOrder(newButton, 0);
		selected = newButton;
		savedBorder = defaultBorder;
		//newButton.setBorder(selectedBorder);		
		this.repaint();
	}
	
	public void insertNewLabel(){
		Boolean empty = false;
		
		cancelSingleSelection();	
		cancelSelection();
		
		Label newLabel = new Label("Label");
		
		Component comp;
		Point p = new Point(75,25);
		while(!empty){
			if(!((comp = this.getComponentAt(p)) instanceof Workboard)){
				p.x += 10;
				p.y += 10;
			}
			else empty = true;
		}
		
		newLabel.setFont(defaultFont);
		newLabel.setBounds((int)p.getX()-75, (int)p.getY()-25, 80, 30);
		newLabel.setOpaque(true);
		newLabel.setForeground(Color.BLACK);
		newLabel.setBackground(Color.WHITE);
		newLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newLabel.setVerticalAlignment(SwingConstants.CENTER);
		newLabel.addMouseListener(this);
		newLabel.addMouseMotionListener(this);
		this.add(newLabel);
		this.setComponentZOrder(newLabel, 0);
		selected = newLabel;
	//  UIManager.getBorder("Label.border");
		savedBorder = BorderFactory.createEmptyBorder(1,1,1,1);
		newLabel.setBorder(selectedBorder);	
		this.repaint();		
	}
	
	public void insertNewClock(){
		cancelSingleSelection();	
		cancelSelection();

		Clock clock = new Clock();
		clock.setBounds(0, 0, 140, 50);
		clock.setForeground(Color.BLACK);
		clock.setBackground(Color.WHITE);
		clock.setFont(new Font("Times New Roman",Font.BOLD,20));
		clock.addMouseListener(this);
		clock.addMouseMotionListener(this);
		this.add(clock);
		this.setComponentZOrder(clock, 0);
		selected = clock;
		savedBorder = defaultBorder;
		clock.setBorder(selectedBorder);	
		this.repaint();
	}
	
	public void insertNewTextBox(){
		cancelSingleSelection();	
		cancelSelection();

		TextBox textbox = new TextBox();
		textbox.setBounds(0, 0, 150, 50);
		textbox.setEditable(false);
		textbox.setBackground(Color.WHITE);
		textbox.setForeground(Color.BLACK);
		
		textbox.setFont(defaultFont);
		textbox.addMouseListener(this);
		textbox.addMouseMotionListener(this);
		
		
		this.add(textbox);
		
		this.setComponentZOrder(textbox, 0);
		selected = textbox;
		savedBorder = defaultBorder;
		textbox.setBorder(selectedBorder);
		this.repaint();
	}
	
	public void insertNewAlert(){
		cancelSingleSelection();	
		cancelSelection();
		
		Alert alert = new Alert();
		alert.setBounds(0, 0, 140, 50);
		alert.setForeground(Color.BLACK);
		alert.setBackground(Color.RED);
		//alert.setBackground(new Color(170,1,20));
		alert.setFont(new Font("Times New Roman",Font.BOLD,20));
		alert.addMouseListener(this);
		alert.addMouseMotionListener(this);
		this.add(alert);
		this.setComponentZOrder(alert, 0);
		selected = alert;
		savedBorder = defaultBorder;
		alert.setBorder(selectedBorder);	
		this.repaint();
	}
	
	public static void openEditMenu(){
		if(!dragSelected){
			if(rightSelected instanceof Button){
				buttonMenu = new ButtonMenu();
				buttonMenu.getComponent((Button) rightSelected);
				buttonMenu.setVisible(true);
			}
			else if(rightSelected instanceof Label){
				labelMenu = new LabelMenu();
				labelMenu.getComponent((Label) rightSelected);
				labelMenu.setVisible(true);
			}
			else if(rightSelected instanceof TextBox){
				txtBoxMenu = new TextBoxMenu();
				txtBoxMenu.getComponent((TextBox) rightSelected);
				txtBoxMenu.setVisible(true);
			}
			else if(rightSelected instanceof Clock){
				clockMenu = new ClockMenu();
				clockMenu.getComponent((Clock) rightSelected);
				clockMenu.setVisible(true);				
			}
			else if(rightSelected instanceof Alert){
				alertMenu = new AlertMenu();
				alertMenu.getComponent((Alert) rightSelected);
				alertMenu.setVisible(true);				
			}
		}
		//
			//menu for group will be put here
		else{
			grpMenu = new GroupMenu();
			grpMenu.getComponent(dragSelection); 
			grpMenu.setVisible(true);			
		}
	}
	
	public static void copy(){
		if(!dragSelected){
			JComponent temp = Menu.copy(rightSelected);
			temp.setBounds(rightSelected.getX()+15, rightSelected.getY()+15, rightSelected.getWidth(), rightSelected.getHeight());
			temp.addMouseListener(MainPanel.board);
			temp.addMouseMotionListener(MainPanel.board);
			
			if(temp instanceof JLabel){
				temp.setOpaque(true);
			}
			
			wrapText(temp);
			MainPanel.board.add(temp);
			MainPanel.board.setComponentZOrder(temp, 0);
			temp.requestFocus();
			MainPanel.board.repaint();
		}
	}
	
	public static void copy2(){
		if(!dragSelected){
			((JComponent) selected).setBorder(savedBorder);
			JComponent temp = Menu.copy((JComponent) selected);
			temp.setBounds(selected.getX()+15, selected.getY()+15, selected.getWidth(), selected.getHeight());
			temp.addMouseListener(MainPanel.board);
			temp.addMouseMotionListener(MainPanel.board);
			
			if(temp instanceof JLabel){
				temp.setOpaque(true);
			}
			
			wrapText(temp);
			
			savedBorder = temp.getBorder();
			temp.setBorder(selectedBorder);
			selected = temp;
			
			MainPanel.board.add(temp);
			MainPanel.board.setComponentZOrder(temp, 0);
			temp.requestFocus();
			MainPanel.board.repaint();
			
			
		}		
	}

	public static void delete(){
		if(!dragSelected){		
			Container temp;
			temp = rightSelected.getParent();
			temp.remove(rightSelected);
			temp.repaint();
		}
		else{
			Container temp;
			temp = rightSelected.getParent();
			Iterator<JComponent> iterator = dragSelection.iterator();
			while(iterator.hasNext()){
				Component comp = iterator.next();
				temp.remove(comp);				
			}
			dragSelection.removeAll(dragSelection);
			dragSelectionBorder.removeAll(dragSelectionBorder);
			temp.repaint();
			dragSelected = false;
		}
	}
	
	public static void delete2(){
		if(!dragSelected){
			Container temp = selected.getParent();
			temp.remove(selected);
			temp.repaint();
		}
		else{
			Container temp = MainPanel.board;
			Iterator<JComponent> iterator = dragSelection.iterator();
			while(iterator.hasNext()){
				Component comp = iterator.next();
				temp.remove(comp);				
			}
			dragSelection.removeAll(dragSelection);
			dragSelectionBorder.removeAll(dragSelectionBorder);
			temp.repaint();
			dragSelected = false;
		}
	}
	
	
	public void mouseClicked(MouseEvent e) {		
		//This handles the right click which displays the pop-up menu
		if(SwingUtilities.isRightMouseButton(e)){
			
			
			rightSelected = (JComponent) e.getSource();
			
			//cancelSingleSelection();	
			//cancelSelection();
			
			if(dragSelection.indexOf(e.getSource()) != -1){
				//this is for group selection.. leave it alone for now
				System.out.println("YES!");
				if(e.getSource() instanceof Button){
					Button button = (Button) e.getSource();
					Point p = button.getMousePosition();
					button.menu.removeCopy();
					button.menu.show(button,(int)p.getX(),(int)p.getY());
					button.menu.insertCopy();
				}
				else if(e.getSource() instanceof Label){
					Label label = (Label) e.getSource();
					Point p = label.getMousePosition();
					label.menu.removeCopy();
					label.menu.show(label, (int)p.getX(), (int)p.getY());	
					label.menu.insertCopy();
				}
				else if(e.getSource() instanceof TextBox){
					TextBox txtBox = (TextBox) e.getSource();
					Point p = txtBox.getMousePosition();
					txtBox.menu.removeCopy();
					txtBox.menu.show(txtBox, (int)p.getX(), (int)p.getY());	
					txtBox.menu.insertCopy();
				}
				else if(e.getSource() instanceof Clock){
					Clock clock = (Clock) e.getSource();
					Point p = clock.getMousePosition();
					clock.menu.removeCopy();
					clock.menu.show(clock, (int)p.getX(), (int)p.getY());
					clock.menu.insertCopy();
				}
				else if(e.getSource() instanceof Alert){
					Alert alert = (Alert) e.getSource();
					Point p = alert.getMousePosition();
					alert.menu.removeCopy();
					alert.menu.show(alert, (int)p.getX(), (int)p.getY());
					alert.menu.insertCopy();
				}
			}
			else if(e.getSource() instanceof Button){
				cancelSingleSelection();	
				cancelSelection();
				Button button = (Button) e.getSource();
				savedBorder = button.getBorder();
				Point p = button.getMousePosition();
				button.menu.show(button, (int)p.getX(), (int)p.getY());
			}
			else if(e.getSource() instanceof Label){
				cancelSingleSelection();	
				cancelSelection();
				Label label = (Label) e.getSource();
				savedBorder = label.getBorder();
				Point p = label.getMousePosition();
				label.menu.show(label, (int)p.getX(), (int)p.getY());				
			}
			else if(e.getSource() instanceof TextBox){
				cancelSingleSelection();	
				cancelSelection();
				TextBox txtBox = (TextBox) e.getSource();
				savedBorder = txtBox.getBorder();
				Point p = txtBox.getMousePosition();
				txtBox.menu.show(txtBox, (int)p.getX(), (int)p.getY());		
			}
			else if(e.getSource() instanceof Clock){
				cancelSingleSelection();	
				cancelSelection();
				Clock clock = (Clock) e.getSource();
				savedBorder = clock.getBorder();
				Point p = clock.getMousePosition();
				clock.menu.show(clock, (int)p.getX(), (int)p.getY());
			}
			else if(e.getSource() instanceof Alert){
				cancelSingleSelection();	
				cancelSelection();
				Alert alert = (Alert) e.getSource();
				savedBorder = alert.getBorder();
				Point p = alert.getMousePosition();
				alert.menu.show(alert, (int)p.getX(), (int)p.getY());
			}
		}
		
		if(SwingUtilities.isLeftMouseButton(e) && e.getClickCount() > 1){
			rightSelected = (JComponent) e.getSource();
			
			if(rightSelected instanceof Button){
				buttonMenu = new ButtonMenu();
				buttonMenu.getComponent((Button) rightSelected);
				buttonMenu.setVisible(true);
			}
			
			else if(rightSelected instanceof Label){
				labelMenu = new LabelMenu();
				labelMenu.getComponent((Label) rightSelected);
				labelMenu.setVisible(true);
			}
			
			else if(rightSelected instanceof TextBox){
				txtBoxMenu = new TextBoxMenu();
				txtBoxMenu.getComponent((TextBox) rightSelected);
				txtBoxMenu.setVisible(true);
			}
			
			else if(rightSelected instanceof Clock){
				clockMenu = new ClockMenu();
				clockMenu.getComponent((Clock) rightSelected);
				clockMenu.setVisible(true);
			}
			
			else if(rightSelected instanceof Alert){
				alertMenu = new AlertMenu();
				alertMenu.getComponent((Alert) rightSelected);
				alertMenu.setVisible(true);
			}
		}
		
	}


	public void mouseEntered(MouseEvent arg0) {
	}


	public void mouseExited(MouseEvent arg0) {
	}


	public void mousePressed(MouseEvent e) {
		this.add(dragImg);
		if(SwingUtilities.isLeftMouseButton(e)){
			Point p = this.getMousePosition();
			System.out.println(p.getX() + " " + p.getY());
			
			cancelSingleSelection();
			
			//drag selecting has been done, but the object clicked isn't one of the selections
			if(dragSelected && dragSelection.indexOf(e.getSource()) == -1){
				cancelSelection();
			}
			
			//mouse is pressed in the background
			if(e.getSource() instanceof Workboard){
				dragStart = new Point(p);
				drag = true;
			}
			
			//a button is pressed & selected
			else if(e.getSource() instanceof Button || e.getSource() instanceof Clock || e.getSource() instanceof Alert){
				JButton button = (JButton) e.getSource();
				this.setComponentZOrder(button, 0);
				savedBorder = button.getBorder();
				button.setBorder(selectedBorder);
				selected = button;
			
				//did we click a corner?
				if(determineCorner(p,button)){
					resize = true;
				}
			}
	
			//a label is pressed & selected
			else if(e.getSource() instanceof Label){
				Label label = (Label) e.getSource();
				this.setComponentZOrder(label, 0);
				savedBorder = label.getBorder();
				label.setBorder(selectedBorder);
				selected = label;
				
				//did we click a corner?
				if(determineCorner(p,label)){
					resize = true;
				}
			}
			
			else if(e.getSource() instanceof TextBox){
				TextBox txtbox = (TextBox) e.getSource();
				this.setComponentZOrder(txtbox, 0);
				savedBorder = txtbox.getBorder();
				txtbox.setBorder(selectedBorder);
				selected = txtbox;
				
				//did we click a corner?
				if(determineCorner(p,txtbox)){
					resize = true;
				}
			}
			
			prevPoint = determinePosition((int)p.getX(),(int)p.getY());
		}
	}


	public void mouseReleased(MouseEvent arg0) {
		//see if we were dragging 
		if(drag){
			for(int i=0; i<this.getComponentCount(); i+=1){
				Component comp = this.getComponent(i);
				int x = comp.getX();
				int y = comp.getY();
				Point p1 = new Point(x,y);
				Point p2 = new Point(x+comp.getWidth(),y);
				Point p3 = new Point(x,y+comp.getHeight());
				Point p4 = new Point(x+comp.getWidth(),y+comp.getHeight());
				
				if(dragArea.contains(p1) && dragArea.contains(p2) && dragArea.contains(p3) && dragArea.contains(p4) && !comp.equals(dragImg)){
					dragSelection.add((JComponent) comp);
					if(comp instanceof Button || comp instanceof Clock || comp instanceof Alert){
						dragSelectionBorder.add(((JButton) comp).getBorder());
						((JButton) comp).setBorder(selectedBorder);
					}
					else if(comp instanceof Label){
						dragSelectionBorder.add(((Label) comp).getBorder());
						((Label) comp).setBorder(selectedBorder);
					}
					else if(comp instanceof TextBox){
						dragSelectionBorder.add(((TextBox) comp).getBorder());
						((TextBox) comp).setBorder(selectedBorder);
					}					
				}
			}
			if(!dragSelection.isEmpty()){
				dragSelected = true;
			}
			/*else{
				dragSelection.removeAll(dragSelection);
				dragSelectionBorder.removeAll(dragSelectionBorder);
				dragSelected = false;
			}*/
		}
				
		resize = false;
		drag = false;
		dragStart = null;
		dragImg.setVisible(false);
		dragArea.setBounds(0, 0, 0, 0);
		remove(dragImg);
	}

	public void mouseDragged(MouseEvent e) {
		if(SwingUtilities.isLeftMouseButton(e) && !SwingUtilities.isRightMouseButton(e)){
		
		Point p = this.getMousePosition();
		
		//if mouse is out of the panel, return
		if(p == null) return;		
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		if((e.getSource() instanceof Button || e.getSource() instanceof Clock || e.getSource() instanceof Alert) && !dragSelected){
			if(!resize){
				JButton button = (JButton) e.getSource();
				
				//Aligning the mouse pointer with center coordinate of the button
				int width = button.getWidth();
				int height = button.getHeight();
				x = x - width/2;
				y = y - height/2;
				//if x or y coordinate is negative, set it equal to 0
				if(x<0) x=0;
				if(y<0) y=0;
				
				p = determinePosition(x,y);
				button.setBounds((int)p.getX(), (int)p.getY(), width, height);
				//this.repaint();		
			}
			else{
				int xp = selected.getX();
				int yp = selected.getY();
				Point q = determinePosition(x,y);
				int width = (int)q.getX() - xp;
				int height = (int)q.getY() - yp;
				
				if(width <= 10 || height <= 10) return;
				selected.setBounds(xp, yp, width, height);
				wrapText((JComponent) selected);
			}
		}
		
		else if(e.getSource() instanceof Label && !dragSelected){
			if(!resize){
				Label label = (Label) e.getSource();
				
				int width = label.getWidth();
				int height = label.getHeight();
				x = x - width/2;
				y = y - height/2;
				
				if(x<0) x=0;
				if(y<0) y=0;
				
				p = determinePosition(x,y);
				label.setBounds((int)p.getX(), (int)p.getY(), width, height);
				//this.repaint();
			}
			else{
				int xp = selected.getX();
				int yp = selected.getY();
				Point q = determinePosition(x,y);
				int width = (int)q.getX() - xp;
				int height = (int)q.getY() - yp;
				
				if(width <= 10 || height <= 10) return;
				selected.setBounds(xp, yp, width, height);
				wrapText((JComponent) selected);
			}	
		}
		
		else if(e.getSource() instanceof TextBox && !dragSelected){
			if(!resize){
				TextBox txtbox = (TextBox) e.getSource();
				
				int width = txtbox.getWidth();
				int height = txtbox.getHeight();
				x = x - width/2;
				y = y - height/2;
				
				if(x<0) x=0;
				if(y<0) y=0;
				
				p = determinePosition(x,y);
				txtbox.setBounds((int)p.getX(), (int)p.getY(), width, height);
				//this.repaint();
			}
			else{
				int xp = selected.getX();
				int yp = selected.getY();
				Point q = determinePosition(x,y);
				int width = (int)q.getX() - xp;
				int height = (int)q.getY() - yp;
				
				if(width <= 10 || height <= 10) return;
				selected.setBounds(xp, yp, width, height);
				//this.repaint();
			}	
		}
		
		else if(dragSelection.indexOf(e.getSource()) != -1){
			Point point = determinePosition(x,y);
			int xchange = (int) (prevPoint.getX() - point.getX());
			int ychange = (int) (prevPoint.getY() - point.getY());
			
			Iterator<JComponent> iterator = dragSelection.iterator();
			while(iterator.hasNext()){
				Component comp = iterator.next();
				comp.setBounds(comp.getX() - xchange, comp.getY() - ychange, comp.getWidth(), comp.getHeight());
			}
			prevPoint = point;
		}		
		else if(drag){
			determineBoundary(dragStart, p);
			dragImg.setVisible(true);
			this.setComponentZOrder(dragImg, 0);
			//this.repaint();	
		}
		}
	}

	public void mouseMoved(MouseEvent arg0) {
	}
	
	//find the nearest grid point
	private Point determinePosition(int x, int y){
		Point p = new Point();
		int index = x/10;
		
		if(index == gridx.size()-1){
			p.x = gridx.get(index);
		}
		
		else if(-(gridx.get(index)-x) > (gridx.get(index+1)-x)){
			p.x = gridx.get(index+1);		
		}
		else{
			p.x = gridx.get(index);
		}
		
		index = y/10;
		
		if(index == gridy.size()-1){
			p.y = gridy.get(index);
		}
		else if(-(gridy.get(index)-y) > (gridy.get(index+1)-y)){
			p.y = gridy.get(index+1);			
		}
		else{
			p.y = gridy.get(index);
		}	
		
		return p;
	}
	
	//did we click the corner?
	private boolean determineCorner(Point p, Component comp){
		Rectangle corner = new Rectangle(comp.getX()+comp.getWidth()-10,comp.getY()+comp.getHeight()-10,10,10);
		if(corner.contains(p)){
			System.out.println("corner!");
			return true;
		}
		else return false;
	}
	
	//a is the starting point, b is the ending point
	private void determineBoundary(Point a, Point b){
		int x1 = (int) a.getX();
		int y1 = (int) a.getY();
		int x2 = (int) b.getX();
		int y2 = (int) b.getY();
		
		if(x2 >= x1 && y2 >= y1){
			dragImg.setBounds(x1, y1, x2-x1, y2-y1);
			dragArea.setBounds(x1, y1, x2-x1, y2-y1);
		}
		
		else if(x2 <= x1 && y2 >= y1){
			dragImg.setBounds(x2, y1, x1-x2, y2-y1);
			dragArea.setBounds(x2, y1, x1-x2, y2-y1);
		}
		
		else if(x2 >= x1 && y2 <= y1){
			dragImg.setBounds(x1, y2, x2-x1, y1-y2);
			dragArea.setBounds(x1, y2, x2-x1, y1-y2);
		}
		
		else if(x2 <= x1 && y2 <= y1){
			dragImg.setBounds(x2, y2, x1-x2, y1-y2);
			dragArea.setBounds(x2, y2, x1-x2, y1-y2);
		}
	}
	
	public void cancelSelection(){
		if(dragSelected){
			Iterator<JComponent> iterator = dragSelection.iterator();
			Iterator<Border> iterator2 = dragSelectionBorder.iterator();
			while(iterator.hasNext()){
				Component comp = iterator.next();
				Border border = iterator2.next();
				if(comp instanceof Button || comp instanceof Clock || comp instanceof Alert){
					((JButton) comp).setBorder(border);
				}
				else if(comp instanceof Label){
					((Label) comp).setBorder(border);
				}
				else if(comp instanceof TextBox){
					((TextBox) comp).setBorder(border);
				}
			}
			dragSelection.removeAll(dragSelection);
			dragSelectionBorder.removeAll(dragSelectionBorder);
			dragSelected = false;
		}
	}
	
	public void cancelSingleSelection(){
		if(selected != null){
			if(selected instanceof Button || selected instanceof Clock || selected instanceof Alert){
				((JButton) selected).setBorder(savedBorder);
				selected = null;
				savedBorder = null;
			}
			if(selected instanceof Label){
				((Label) selected).setBorder(savedBorder);
				selected = null;
				savedBorder = null;
			}
			if(selected instanceof TextBox){
				((TextBox) selected).setBorder(savedBorder);
				selected = null;
				savedBorder = null;
			}
		}
	}
	
	private void clear(){
		cancelSingleSelection();
		cancelSelection();
		this.removeAll();
		this.repaint();		
	}
	
	public static int measureStringLength(JComponent c, String str){
		int length = 0;
		FontMetrics metrics = c.getFontMetrics(c.getFont());
		if(str == null){
			if(c instanceof Button || c instanceof Clock || c instanceof Alert){
				JButton button = (JButton) c;
				length = metrics.stringWidth(button.getText());
			}
			else if(c instanceof Label || c instanceof JLabel){
				JLabel label = (JLabel) c;
				length = metrics.stringWidth(label.getText());
			}
		}
		else{
			if(c instanceof Button || c instanceof Clock || c instanceof Alert){
				JButton button = (JButton) c;
				length = metrics.stringWidth(str);
			}
			else if(c instanceof Label || c instanceof JLabel){
				JLabel label = (JLabel) c;
				length = metrics.stringWidth(str);
			}
		}		
		return length;
	}

	public static void wrapText(JComponent c){
		JButton button = new JButton();
		JLabel label = new JLabel();
		String endResult = "";
		String temp = "";
		String string = "";
	
		int length;
		int limit = c.getWidth();
		if(c instanceof Button || c instanceof Clock || c instanceof Alert){
			button = (JButton) c;
			string = button.getText().replace("<br>", " ");			
		}
		else if(c instanceof Label || c instanceof JLabel){
			label = (JLabel) c;
			string = label.getText().replace("<br>", " ");		
		}
		else return;
		
		string = string.replace("<html>", "");
		string = string.replace("</html>", "");
		string = string.replace("<center>", "");
		
		length = measureStringLength(c,string);
		if(length <= limit-5){
			if(c instanceof Button || c instanceof Clock || c instanceof Alert) button.setText(string);
			else if(c instanceof Label || c instanceof JLabel) label.setText(string);
			return;
		}
		String[] str = string.split("\\s+");
		for(int i=0; i<str.length; i+=1){
			endResult = endResult + str[i];
			temp = temp + str[i];
			if(i != str.length-1){
				String temp2 = temp + " " + str[i+1];
				if(measureStringLength(c,temp2) > limit){
					endResult = endResult + "<br>";
					temp = "";
				}
				else{
					endResult = endResult + " ";
					temp = temp + " ";
				}
			}
		}
		if(c instanceof Button || c instanceof Clock || c instanceof Alert) button.setText("<html><center>" + endResult + "</html>");
		else if(c instanceof Label || c instanceof JLabel) label.setText("<html><center>" + endResult + "</html>");
		return;
	}
	
	public static void createGrid(int width, int height){
		for(int i=0; i < width; i+=10){
			gridx.add(i);
		}
		
		for(int i=0; i < height; i+=10){
			gridy.add(i);
		}
	}
	
	public static void clearGrid(){
		gridx.clear();
		gridy.clear();
	}
}
