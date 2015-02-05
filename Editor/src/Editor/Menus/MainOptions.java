package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import Editor.Frame;
import Editor.MainPanel;
import Editor.Workboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;

public class MainOptions extends JFrame implements ActionListener, WindowListener{

	private JPanel contentPane;
	private JComboBox bBgColor;
	private JComboBox dBgColor;
	private JComboBox dFont;
	private JComboBox dTxtColor;
	private JTextField dDuration;
	private JCheckBox bold;
	private JCheckBox italic;
	

	private JButton confirm;


	private JButton cancel;
	private JTextField dTxtSize;
	private JTextField xValue;
	private JTextField yValue;
	private JTextField dTxtSizeC;
	private JTextField dTxtSizeT;
	private JTextField alertCycle;
	private JTextField alertSpeed;
	private JComboBox alertColor;
	
	public MainOptions() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("Options");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 720);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		addWindowListener(this);
		
		JLabel lblDisplayOptions = new JLabel("Display Options");
		lblDisplayOptions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDisplayOptions.setBounds(10, 144, 111, 23);
		contentPane.add(lblDisplayOptions);
		
		JLabel lblBoardOptions = new JLabel("Board Options");
		lblBoardOptions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBoardOptions.setBounds(10, 11, 111, 23);
		contentPane.add(lblBoardOptions);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 37, 294, 96);
		contentPane.add(panel);
		panel.setLayout(null);
		
		bBgColor = new JComboBox();
		bBgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		bBgColor.setBounds(152, 47, 132, 25);
		panel.add(bBgColor);
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 49, 122, 20);
		panel.add(lblBackgroundColor);
		
		xValue = new JTextField();
		xValue.setBounds(127, 11, 66, 25);
		panel.add(xValue);
		xValue.setColumns(10);
		
		yValue = new JTextField();
		yValue.setColumns(10);
		yValue.setBounds(218, 11, 66, 25);
		panel.add(yValue);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(202, 16, 25, 14);
		panel.add(lblX);
		
		JLabel lblDimension = new JLabel("Dimension");
		lblDimension.setBounds(10, 11, 122, 20);
		panel.add(lblDimension);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(116, 658, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(215, 658, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 169, 294, 290);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		dBgColor = new JComboBox();
		dBgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		dBgColor.setBounds(152, 11, 132, 25);
		panel_1.add(dBgColor);
		
		dFont = new JComboBox();
		dFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		dFont.setBounds(152, 47, 132, 25);
		panel_1.add(dFont);
		
		dTxtColor = new JComboBox();
		dTxtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		dTxtColor.setBounds(152, 191, 132, 25);
		panel_1.add(dTxtColor);
		
		dDuration = new JTextField();
		dDuration.setBounds(189, 227, 95, 25);
		panel_1.add(dDuration);
		dDuration.setColumns(10);
		
		JLabel lblBackgroundColor_1 = new JLabel("Background Color");
		lblBackgroundColor_1.setBounds(10, 13, 110, 20);
		panel_1.add(lblBackgroundColor_1);
		
		JLabel lblFont = new JLabel("Display Font");
		lblFont.setBounds(10, 49, 110, 20);
		panel_1.add(lblFont);
		
		JLabel lblDisplayTextSize = new JLabel("Display Text Size (Button)");
		lblDisplayTextSize.setBounds(10, 85, 145, 20);
		panel_1.add(lblDisplayTextSize);
		
		JLabel lblDisplayTextColor = new JLabel("Display Text Color");
		lblDisplayTextColor.setBounds(10, 194, 110, 19);
		panel_1.add(lblDisplayTextColor);
		
		JLabel lblDisplayDurationms = new JLabel("Display Duration (Seconds)");
		lblDisplayDurationms.setBounds(10, 230, 169, 19);
		panel_1.add(lblDisplayDurationms);
		
		bold = new JCheckBox("Bold");
		bold.setEnabled(true);
		bold.setBackground(new Color(0, 0, 0, 0));
		bold.setBounds(147, 257, 54, 23);
		panel_1.add(bold);
		
		dTxtSize = new JTextField();
		dTxtSize.setColumns(10);
		dTxtSize.setBounds(189, 83, 95, 25);
		panel_1.add(dTxtSize);
		
		JLabel lblDisplayTextSize_1 = new JLabel("Display Text Size (Clock)");
		lblDisplayTextSize_1.setBounds(10, 121, 145, 20);
		panel_1.add(lblDisplayTextSize_1);
		
		JLabel lblDisplayTextSize_2 = new JLabel("Display Text Size (TextBox)");
		lblDisplayTextSize_2.setBounds(10, 157, 145, 20);
		panel_1.add(lblDisplayTextSize_2);
		
		dTxtSizeC = new JTextField();
		dTxtSizeC.setColumns(10);
		dTxtSizeC.setBounds(189, 119, 95, 25);
		panel_1.add(dTxtSizeC);
		
		dTxtSizeT = new JTextField();
		dTxtSizeT.setColumns(10);
		dTxtSizeT.setBounds(189, 155, 95, 25);
		panel_1.add(dTxtSizeT);
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 257, 54, 23);
		panel_1.add(italic);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(10, 496, 294, 134);
		contentPane.add(panel_2);
		
		JLabel label = new JLabel("Alert Color");
		label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setBounds(10, 13, 103, 22);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Alert Cycle (Times)");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(10, 49, 115, 22);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Alert Speed (Seconds)");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(10, 85, 132, 22);
		panel_2.add(label_2);
		
		alertColor = new JComboBox();
		alertColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		alertColor.setBounds(152, 13, 132, 25);
		panel_2.add(alertColor);
		
		alertCycle = new JTextField();
		alertCycle.setColumns(10);
		alertCycle.setBounds(152, 49, 132, 25);
		panel_2.add(alertCycle);
		
		alertSpeed = new JTextField();
		alertSpeed.setColumns(10);
		alertSpeed.setBounds(152, 85, 132, 25);
		panel_2.add(alertSpeed);
		
		JLabel lblAutomaticAlertOptions = new JLabel("Automatic Alert Options");
		lblAutomaticAlertOptions.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAutomaticAlertOptions.setBounds(10, 470, 228, 23);
		contentPane.add(lblAutomaticAlertOptions);
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
		
	}
	
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
			x = Integer.parseInt(xValue.getText());
			y = Integer.parseInt(yValue.getText());
			
			if(x < 200) x = 200;
			if(y < 200) y = 200;

			MainPanel.board.setPreferredSize(new Dimension(x, y));
			
			Workboard.clearGrid();
			Workboard.createGrid(x, y);
			
			if(x > Frame.getScreenWidth()-15){
				x = Frame.mainPanel.getWidth()-20;
			}
			if(y > Frame.getScreenHeight()-75){
				y = Frame.mainPanel.getHeight()-75;
			}
			
			Frame.scroller.setBounds(5, 60, x+15, y+15);
			Frame.getContainer().revalidate();
			Frame.getContainer().repaint();
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
}
