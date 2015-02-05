package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Editor.Workboard;
import Editor.Components.Alert;

public class AlertMenu extends JFrame implements ActionListener, WindowFocusListener{

	private JPanel contentPane;
	private JTextField label;
	private JComboBox bgColor;
	private JComboBox txtColor;
	private JComboBox<String> txtSize;
	private JComboBox txtFont;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox boldOption;
	private JCheckBox italic;
	Alert alert;
	private JTextField alertCycle;
	private JTextField alertSpeed;
	private JComboBox alertColor;
	private boolean confirmed = false;
	
	public AlertMenu() {
		setBackground(SystemColor.menu);
		setTitle("Alert Edit");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 458);
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(111, 396, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(210, 396, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 294, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel ButtonName = new JLabel("Label");
		ButtonName.setBounds(10, 6, 44, 22);
		panel.add(ButtonName);
		ButtonName.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		label = new JTextField();
		label.setBounds(152, 6, 132, 25);
		panel.add(label);
		label.setColumns(10);
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 46, 109, 14);
		panel.add(lblBackgroundColor);
		lblBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextFont = new JLabel("Text Font");
		lblTextFont.setBounds(10, 82, 92, 14);
		panel.add(lblTextFont);
		lblTextFont.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 118, 92, 14);
		panel.add(lblTextColor);
		lblTextColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextSize = new JLabel("Text Size");
		lblTextSize.setBounds(10, 154, 92, 14);
		panel.add(lblTextSize);
		lblTextSize.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		bgColor = new JComboBox();
		bgColor.setBounds(152, 42, 132, 25);
		panel.add(bgColor);
		bgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtFont = new JComboBox();
		txtFont.setBounds(152, 78, 132, 25);
		panel.add(txtFont);
		txtFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		
		txtColor = new JComboBox();
		txtColor.setBounds(152, 114, 132, 25);
		panel.add(txtColor);
		txtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtSize = new JComboBox<String>();
		txtSize.setEditable(true);
		txtSize.setBounds(152, 150, 132, 25);
		panel.add(txtSize);
		txtSize.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "8", "12", "15", "20", "24", "30", "36", "48", "56", "72"}));
		
		boldOption = new JCheckBox("Bold");
		boldOption.setBounds(147, 180, 54, 23);
		panel.add(boldOption);
		boldOption.setEnabled(true);
		boldOption.setBackground(new Color(0,0,0,0));
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 180, 54, 23);
		panel.add(italic);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBounds(10, 232, 294, 134);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAlertColor = new JLabel("Alert Color");
		lblAlertColor.setBounds(10, 13, 103, 22);
		lblAlertColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		panel_1.add(lblAlertColor);
		
		JLabel lblAlertCycle = new JLabel("Alert Cycle (Times)");
		lblAlertCycle.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAlertCycle.setBounds(10, 49, 115, 22);
		panel_1.add(lblAlertCycle);
		
		JLabel lblAlertSpeed = new JLabel("Alert Speed (Seconds)");
		lblAlertSpeed.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAlertSpeed.setBounds(10, 85, 132, 22);
		panel_1.add(lblAlertSpeed);
		
		alertColor = new JComboBox();
		alertColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		alertColor.setBounds(152, 13, 132, 25);
		panel_1.add(alertColor);
		
		alertCycle = new JTextField();
		alertCycle.setColumns(10);
		alertCycle.setBounds(152, 49, 132, 25);
		panel_1.add(alertCycle);
		
		alertSpeed = new JTextField();
		alertSpeed.setColumns(10);
		alertSpeed.setBounds(152, 85, 132, 25);
		panel_1.add(alertSpeed);
	}
	
	public void getComponent(Alert alert){
		this.alert = alert;
		label.setText(alert.getText());	
		
		bgColor.setSelectedIndex(Menu.determineBgColorIndex2(alert.getBackground()));
		txtFont.setSelectedIndex(Menu.determineFtIndex(alert.getFont()));
		txtColor.setSelectedIndex(Menu.determineFgColorIndex(alert.getForeground()));
		txtSize.setSelectedItem(alert.getFont().getSize());
		boldOption.setSelected(Menu.determineBold(alert.getFont()));
		italic.setSelected(Menu.determineItalic(alert.getFont()));
		
		alertColor.setSelectedIndex(Menu.determineBgColorIndex2(alert.getAlertColor()));
		alertCycle.setText(alert.getAlertTimes());
		alertSpeed.setText(alert.getAlertSpeed());		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			confirmed = true;
			alert.setText(label.getText());
			Workboard.wrapText(alert);
			setBgColor();
			setTextColor();
			setFont();
			setAlertColor();
			setAlertCycle();
			setAlertSpeed();
			
			this.setVisible(false);
			this.dispose();
		}
		else{
			this.setVisible(false);
			this.dispose();
		}
	}
	
	private void setBgColor(){
		alert.setBackground(Menu.determineColor(this.bgColor.getSelectedIndex()));
	}
	
	
	private void setTextColor(){
		alert.setForeground(Menu.determineColor(this.txtColor.getSelectedIndex()));
	}
	
	private void setFont(){
		Object a = txtSize.getSelectedItem();
		if(a instanceof Integer){
			alert.setFont(Menu.determineFont((int)a,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
		}	
		else{
			try{
				int b = Integer.parseInt((String)a);
				alert.setFont(Menu.determineFont(b,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Size must only include numbers!");
			}
		}
	}
	
	private void setAlertColor(){
		alert.setAlertColor(Menu.determineColor(this.alertColor.getSelectedIndex()));
	}
	
	private void setAlertCycle(){
		try{
			int test = Integer.parseInt(alertCycle.getText());
			alert.setAlertTimes(alertCycle.getText());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Cycle must only include numbers!");
			alert.setAlertTimes("5");
		}
	}
	
	private void setAlertSpeed(){
		try{
			float test = Float.parseFloat(alertSpeed.getText());
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
			String string = df.format(test);
			alertSpeed.setText(string);
			alert.setAlertSpeed(string);
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Speed must only include numbers!");
			alert.setAlertSpeed("0.50");
		}
	}


	@Override
	public void windowGainedFocus(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent arg0) {
		if(!confirmed){
			this.setVisible(false);
			this.dispose();
		}
	}	
}
