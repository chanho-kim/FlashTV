package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import Editor.Workboard;
import Editor.Components.Button;

import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class ButtonMenu extends JFrame implements ActionListener, WindowFocusListener, FocusListener{

	private JPanel contentPane;
	private JTextField label;
	private JTextField displayTxt;
	private JComboBox bgColor;
	private JComboBox txtColor;
	private JComboBox txtSize;
	private JComboBox txtFont;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox boldOption;
	Button button;
	private JCheckBox italic;
	private boolean confirmed = false;
	
	public ButtonMenu() {
		setBackground(SystemColor.menu);
		setTitle("Button Edit");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setBounds(100, 100, 320, 351);
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(116, 291, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(215, 291, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 294, 250);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel ButtonName = new JLabel("Label");
		ButtonName.setBounds(10, 6, 44, 22);
		
		panel.add(ButtonName);
		ButtonName.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		label = new JTextField();
		label.setBounds(152, 6, 132, 25);
		label.addFocusListener(this);
		panel.add(label);
		label.setColumns(10);
		
		displayTxt = new JTextField();
		displayTxt.setBounds(152, 42, 132, 25);
		panel.add(displayTxt);
		displayTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Display Text");
		lblNewLabel_1.setBounds(10, 42, 109, 23);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 83, 109, 14);
		panel.add(lblBackgroundColor);
		lblBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextFont = new JLabel("Text Font");
		lblTextFont.setBounds(10, 118, 92, 14);
		panel.add(lblTextFont);
		lblTextFont.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 154, 92, 14);
		panel.add(lblTextColor);
		lblTextColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextSize = new JLabel("Text Size");
		lblTextSize.setBounds(10, 190, 92, 14);
		panel.add(lblTextSize);
		lblTextSize.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		bgColor = new JComboBox();
		bgColor.setBounds(152, 78, 132, 25);
		panel.add(bgColor);
		bgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtFont = new JComboBox();
		txtFont.setBounds(152, 114, 132, 25);
		panel.add(txtFont);
		txtFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		
		txtColor = new JComboBox();
		txtColor.setBounds(152, 150, 132, 25);
		panel.add(txtColor);
		txtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtSize = new JComboBox();
		txtSize.setEditable(true);
		txtSize.setBounds(152, 186, 132, 25);
		panel.add(txtSize);
		txtSize.setModel(new DefaultComboBoxModel(new String[] {"5", "8", "12", "15", "20", "24", "30", "36", "48", "56", "72"}));
		
		boldOption = new JCheckBox("Bold");
		boldOption.setBounds(147, 216, 54, 23);
		panel.add(boldOption);
		boldOption.setEnabled(true);
		boldOption.setBackground(new Color(0,0,0,0));
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 216, 54, 23);
		panel.add(italic);
	}
	
	public void getComponent(Button button){
		this.button = button;
		String str = button.getText();
		str = str.replace("<br>", " ");
		str = str.replace("<html>", "");
		str = str.replace("</html>", "");
		str = str.replace("<center>", "");
		label.setText(str);
		displayTxt.setText(button.getDisplayTxt());		
		
		bgColor.setSelectedIndex(Menu.determineBgColorIndex2(button.getBackground()));
		txtFont.setSelectedIndex(Menu.determineFtIndex(button.getFont()));
		txtColor.setSelectedIndex(Menu.determineFgColorIndex(button.getForeground()));
		txtSize.setSelectedItem(button.getFont().getSize());
		boldOption.setSelected(Menu.determineBold(button.getFont()));
		italic.setSelected(Menu.determineItalic(button.getFont()));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			confirmed = true;
			button.setText(label.getText());
			Workboard.wrapText(button);
			button.changeDisplayTxt(displayTxt.getText());
			setBgColor();
			setTextColor();
			setFont();
			this.setVisible(false);
			this.dispose();
		}
		else{
			this.setVisible(false);
			this.dispose();
		}
	}
	
	private void setBgColor(){
		button.setBackground(Menu.determineColor(this.bgColor.getSelectedIndex()));
	}
	
	
	private void setTextColor(){
		button.setForeground(Menu.determineColor(this.txtColor.getSelectedIndex()));
	}
	
	private void setFont(){
		Object a = txtSize.getSelectedItem();
		if(a instanceof Integer){
			button.setFont(Menu.determineFont((int)a,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
		}	
		else{
			try{
				int b = Integer.parseInt((String)a);
				button.setFont(Menu.determineFont(b,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Size must only include numbers!");
			}
		}
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		if(!confirmed){
			this.setVisible(false);
			this.dispose();
		}	
	}

	@Override
	public void focusGained(FocusEvent arg0) {	
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		displayTxt.setText(label.getText());
	}
}
