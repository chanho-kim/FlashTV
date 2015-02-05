package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Editor.Frame;
import Editor.MainPanel;
import Editor.Workboard;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;

import javax.swing.border.LineBorder;

public class ProgramOption extends JFrame implements ActionListener, WindowListener{

	private JPanel contentPane;
	private JTextField dTxtSize;
	private JTextField dTxtSizeC;
	private JTextField dTxtSizeT;
	private JTextField dDuration;
	private JTextField xValue;
	private JTextField yValue;
	private JTextField alertCycle;
	private JTextField alertSpeed;
	private JComboBox bBgColor;
	private JComboBox alertColor;
	private JCheckBox italic;
	private JCheckBox bold;
	private JComboBox dTxtColor;
	private JComboBox dFont;
	private JComboBox dBgColor;
	private JCheckBox screenChoice;
	private JCheckBox autoAlert;
	private JButton confirm;
	private JButton cancel;

	/**
	 * Create the frame.
	 */
	public ProgramOption() {
		setTitle("Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 333, 440);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.desktop);
		tabbedPane.setBounds(10, 11, 294, 338);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Board", null, panel, null);
		panel.setLayout(null);
		
		screenChoice = new JCheckBox("Ask for the operating screen");
		screenChoice.setBackground(SystemColor.inactiveCaption);
		screenChoice.setBounds(107, 280, 177, 23);
		panel.add(screenChoice);
		
		JLabel label = new JLabel("Dimension");
		label.setBounds(10, 11, 122, 20);
		panel.add(label);
		
		xValue = new JTextField();
		xValue.setColumns(10);
		xValue.setBounds(127, 11, 66, 25);
		panel.add(xValue);
		
		yValue = new JTextField();
		yValue.setColumns(10);
		yValue.setBounds(218, 11, 66, 25);
		panel.add(yValue);
		
		bBgColor = new JComboBox();
		bBgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		bBgColor.setBounds(152, 47, 132, 25);
		panel.add(bBgColor);
		
		JLabel label_1 = new JLabel("X");
		label_1.setBounds(202, 16, 25, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Background Color");
		label_2.setBounds(10, 49, 122, 20);
		panel.add(label_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Display", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("Background Color");
		label_3.setBounds(10, 13, 110, 20);
		panel_1.add(label_3);
		
		dBgColor = new JComboBox();
		dBgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		dBgColor.setBounds(152, 11, 132, 25);
		panel_1.add(dBgColor);
		
		dFont = new JComboBox();
		dFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		dFont.setBounds(152, 47, 132, 25);
		panel_1.add(dFont);
		
		JLabel label_4 = new JLabel("Display Font");
		label_4.setBounds(10, 49, 110, 20);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("Display Text Size (Button)");
		label_5.setBounds(10, 85, 145, 20);
		panel_1.add(label_5);
		
		dTxtSize = new JTextField();
		dTxtSize.setColumns(10);
		dTxtSize.setBounds(189, 83, 95, 25);
		panel_1.add(dTxtSize);
		
		dTxtSizeC = new JTextField();
		dTxtSizeC.setColumns(10);
		dTxtSizeC.setBounds(189, 119, 95, 25);
		panel_1.add(dTxtSizeC);
		
		JLabel label_6 = new JLabel("Display Text Size (Clock)");
		label_6.setBounds(10, 121, 145, 20);
		panel_1.add(label_6);
		
		JLabel label_7 = new JLabel("Display Text Size (TextBox)");
		label_7.setBounds(10, 157, 145, 20);
		panel_1.add(label_7);
		
		dTxtSizeT = new JTextField();
		dTxtSizeT.setColumns(10);
		dTxtSizeT.setBounds(189, 155, 95, 25);
		panel_1.add(dTxtSizeT);
		
		dTxtColor = new JComboBox();
		dTxtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		dTxtColor.setBounds(152, 191, 132, 25);
		panel_1.add(dTxtColor);
		
		JLabel label_8 = new JLabel("Display Text Color");
		label_8.setBounds(10, 194, 110, 19);
		panel_1.add(label_8);
		
		JLabel label_9 = new JLabel("Display Duration (Seconds)");
		label_9.setBounds(10, 230, 169, 19);
		panel_1.add(label_9);
		
		dDuration = new JTextField();
		dDuration.setColumns(10);
		dDuration.setBounds(189, 227, 95, 25);
		panel_1.add(dDuration);
		
		bold = new JCheckBox("Bold");
		bold.setEnabled(true);
		bold.setBackground(new Color(0, 0, 0, 0));
		bold.setBounds(174, 259, 54, 23);
		panel_1.add(bold);
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(230, 259, 54, 23);
		panel_1.add(italic);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.inactiveCaption);
		tabbedPane.addTab("Automatic Alert", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_10 = new JLabel("Alert Color");
		label_10.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_10.setBounds(10, 11, 103, 22);
		panel_2.add(label_10);
		
		alertColor = new JComboBox();
		alertColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		alertColor.setBounds(152, 11, 132, 25);
		panel_2.add(alertColor);
		
		alertCycle = new JTextField();
		alertCycle.setColumns(10);
		alertCycle.setBounds(152, 47, 132, 25);
		panel_2.add(alertCycle);
		
		JLabel label_11 = new JLabel("Alert Cycle (Times)");
		label_11.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_11.setBounds(10, 47, 115, 22);
		panel_2.add(label_11);
		
		JLabel label_12 = new JLabel("Alert Speed (Seconds)");
		label_12.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_12.setBounds(10, 83, 132, 22);
		panel_2.add(label_12);
		
		alertSpeed = new JTextField();
		alertSpeed.setColumns(10);
		alertSpeed.setBounds(152, 83, 132, 25);
		panel_2.add(alertSpeed);
		
		autoAlert = new JCheckBox("Enable automatic alert");
		autoAlert.setBackground(SystemColor.inactiveCaption);
		autoAlert.setBounds(137, 280, 147, 23);
		panel_2.add(autoAlert);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(116, 378, 89, 23);
		contentPane.add(confirm);
		confirm.addActionListener(this);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(215, 378, 89, 23);
		contentPane.add(cancel);
		cancel.addActionListener(this);
	}
	
	
	public void getComponent(){
		xValue.setText(Integer.toString(MainPanel.board.getWidth()));
		yValue.setText(Integer.toString(MainPanel.board.getHeight()));
		bBgColor.setSelectedIndex(Menu.determineBgColorIndex2(MainPanel.board.getBackground()));
		dBgColor.setSelectedIndex(Menu.determineBgColorIndex2(MainPanel.displayBg));
		dFont.setSelectedIndex(Menu.determineFtIndex(MainPanel.displayFt));
		dTxtSize.setText(MainPanel.displayTxtSize);
		dTxtSizeC.setText(MainPanel.displayTxtSizeC);
		dTxtSizeT.setText(MainPanel.displayTxtSizeT);
		dTxtColor.setSelectedIndex(Menu.determineFgColorIndex(MainPanel.displayTxtColor));
		dDuration.setText(MainPanel.displayDuration);
		if(MainPanel.bold) bold.setSelected(true);
		else bold.setSelected(false);
		if(MainPanel.italic) italic.setSelected(true);
		else italic.setSelected(false);
		
		alertColor.setSelectedIndex(Menu.determineBgColorIndex2(MainPanel.autoAlertColor));
		alertCycle.setText(MainPanel.autoAlertTimes);
		alertSpeed.setText(MainPanel.autoAlertSpeed);
		
		if(MainPanel.autoAlert) autoAlert.setSelected(true);
		else autoAlert.setSelected(false);
		
		if(MainPanel.screenChoice) screenChoice.setSelected(true);
		else screenChoice.setSelected(false);		
	}


	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		MainPanel.mainOptionsOn = false;		
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			setXY();
			setbBgColor();
			setdBgColor();
			setFont();
			setTextColor();
			setDuration();
			setAlertColor();
			setAlertCycle();
			setAlertSpeed();
			
			if(autoAlert.isSelected()) MainPanel.autoAlert = true;
			else MainPanel.autoAlert= false;
			if(screenChoice.isSelected()) MainPanel.screenChoice = true;
			else MainPanel.screenChoice = false;
			
			MainPanel.mainOptionsOn = false;
			this.setVisible(false);
			this.dispose();
		}
		else{
			MainPanel.mainOptionsOn = false;
			this.setVisible(false);
			this.dispose();
		}	
		
	}
	
	private void setXY(){
		int x = 0;
		int y = 0;
		try{
			int height = MainPanel.board.getHeight(); 
			int width = MainPanel.board.getWidth();
			
			x = Integer.parseInt(xValue.getText());
			y = Integer.parseInt(yValue.getText());
			
			if(x < 200) x = 200;
			if(y < 200) y = 200;
			
			
			if(x < width || y < height){
				Rectangle a = new Rectangle(0,0,0,0);
				Rectangle b = new Rectangle(0,0,0,0);				
				if(x < width) a = new Rectangle(x+5,60,width - x,height);
				
				if(y < height) b = new Rectangle(5,60+y,width,height - y);
				Component[] components = MainPanel.board.getComponents();
				for(int i=0; i < components.length; i+=1){
					int x2 = components[i].getX();
					int y2 = components[i].getY();
					if(a.contains(components[i].getX()+5,components[i].getY()+60)){
						x2 = x - components[i].getWidth() + 2;
					}
					if(b.contains(components[i].getX()+5, components[i].getY()+60)){
						y2 = y - components[i].getHeight() + 2;
					}
					components[i].setBounds(x2, y2, components[i].getWidth(), components[i].getHeight());
				}				
			}
			
			MainPanel.board.setPreferredSize(new Dimension(x, y));
			
			Workboard.clearGrid();
			Workboard.createGrid(x, y);
			
			if(x > Frame.frame.getWidth()-20){
				x = Frame.frame.getWidth()-20;
			}
			
			if(y > Frame.frame.getHeight()-75){
				y = Frame.frame.getHeight()-75;
			}
			
			x = x+15;
			y = y+15;
			
			Frame.scroller.setBounds(5,60,x,y);
			
			JScrollBar vertBar = Frame.scroller.getVerticalScrollBar();
			JScrollBar horiBar = Frame.scroller.getHorizontalScrollBar();
			
			vertBar.setValue(vertBar.getMinimum());
			horiBar.setValue(horiBar.getMinimum());
			
//			if(x > Frame.getScreenWidth()-20){
//				x = Frame.mainPanel.getWidth()-20;
//			}
//			if(y > Frame.getScreenHeight()-75){
//				y = Frame.mainPanel.getHeight()-75;
//			}
//			
//			Frame.scroller.setBounds(5, 60, x+15, y+15);
			
			//this works for now..
			Frame.frame.setSize(Frame.frame.getWidth(), Frame.frame.getHeight() + 1); 
			Frame.frame.setSize(Frame.frame.getWidth(), Frame.frame.getHeight() - 1); 
			
//			Frame.frame.validate();
//			Frame.frame.repaint();

			
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Dimension values must only include numbers!");
		}	
	}
	
	private void setbBgColor() {
		MainPanel.board.setBackground(Menu.determineColor(bBgColor.getSelectedIndex()));
	}
	
	private void setdBgColor() {
		MainPanel.displayBg = Menu.determineColor(dBgColor.getSelectedIndex());
	}
	
	private void setFont(){
		try{
			int test = Integer.parseInt(dTxtSize.getText());
			MainPanel.displayFt = Menu.determineFont(0, dFont.getSelectedIndex(), bold.isSelected(), italic.isSelected());
			MainPanel.displayTxtSize = dTxtSize.getText();
			
			test = Integer.parseInt(dTxtSizeC.getText());
			MainPanel.displayTxtSizeC = dTxtSizeC.getText();
			
			test = Integer.parseInt(dTxtSizeT.getText());
			MainPanel.displayTxtSizeT = dTxtSizeT.getText();			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Size must only include numbers!");
		}
	}
	
	private void setTextColor(){
		MainPanel.displayTxtColor = Menu.determineColor(dTxtColor.getSelectedIndex());
	}
	
	private void setDuration(){
		try{
			float test = Float.parseFloat(dDuration.getText());
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			String string = df.format(test);
			dDuration.setText(string);			
			MainPanel.displayDuration = dDuration.getText();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Duration must only include numbers!");
		}
	}
	
	private void setAlertColor(){
		MainPanel.autoAlertColor = Menu.determineColor(this.alertColor.getSelectedIndex());
	}
	
	private void setAlertCycle(){
		try{
			int test = Integer.parseInt(alertCycle.getText());
			MainPanel.autoAlertTimes = alertCycle.getText();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Cycle must only include numbers!");
			MainPanel.autoAlertTimes = "5";
		}
	}
	
	private void setAlertSpeed(){
		try{
			float test = Float.parseFloat(alertSpeed.getText());
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			String string = df.format(test);
			alertSpeed.setText(string);
			MainPanel.autoAlertSpeed = string;
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Speed must only include numbers!");
			MainPanel.autoAlertSpeed = "0.50";
		}
	}
	
}
