package Editor.Menus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Editor.Workboard;
import Editor.Components.TextBox;

public class TextBoxMenu extends JFrame implements ActionListener, WindowFocusListener{

	private JPanel contentPane;
	private JComboBox bgColor;
	private JComboBox txtColor;
	private JComboBox txtSize;
	private JComboBox txtFont;
	private JButton confirm;
	private JButton cancel;
	private JCheckBox boldOption;
	TextBox txtBox;
	private JCheckBox italic;
	private boolean confirmed = false;
	
	public TextBoxMenu() {
		setBackground(SystemColor.menu);
		setTitle("TextBox Edit");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 278);
		setLocationRelativeTo(null);
		addWindowFocusListener(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		confirm = new JButton("Confirm");
		confirm.setBounds(112, 216, 89, 23);
		confirm.addActionListener(this);
		contentPane.add(confirm);
		
		cancel = new JButton("Cancel");
		cancel.setBounds(210, 216, 89, 23);
		cancel.addActionListener(this);
		contentPane.add(cancel);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(10, 11, 294, 175);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBackgroundColor = new JLabel("Background Color");
		lblBackgroundColor.setBounds(10, 10, 109, 14);
		panel.add(lblBackgroundColor);
		lblBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextFont = new JLabel("Text Font");
		lblTextFont.setBounds(10, 46, 92, 14);
		panel.add(lblTextFont);
		lblTextFont.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		JLabel lblTextColor = new JLabel("Text Color");
		lblTextColor.setBounds(10, 82, 92, 14);
		panel.add(lblTextColor);
		lblTextColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel lblTextSize = new JLabel("Text Size");
		lblTextSize.setBounds(10, 118, 92, 14);
		panel.add(lblTextSize);
		lblTextSize.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		bgColor = new JComboBox();
		bgColor.setBounds(152, 6, 132, 25);
		panel.add(bgColor);
		bgColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtFont = new JComboBox();
		txtFont.setBounds(152, 42, 132, 25);
		panel.add(txtFont);
		txtFont.setModel(new DefaultComboBoxModel(new String[] {"Times New Roman", "Arial", "Verdana", "Courier New", "Comic Sans MS", "Impact", "Lucida Console"}));
		
		txtColor = new JComboBox();
		txtColor.setBounds(152, 78, 132, 25);
		panel.add(txtColor);
		txtColor.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "Blue", "Green", "Red", "Yellow", "Pink", "Orange", "Magenta", "Gray"}));
		
		txtSize = new JComboBox();
		txtSize.setEditable(true);
		txtSize.setBounds(152, 114, 132, 25);
		panel.add(txtSize);
		txtSize.setModel(new DefaultComboBoxModel(new String[] {"5", "8", "12", "15", "20", "24", "30", "36", "48", "56", "72"}));
		
		boldOption = new JCheckBox("Bold");
		boldOption.setBounds(147, 144, 54, 23);
		panel.add(boldOption);
		boldOption.setEnabled(true);
		boldOption.setBackground(new Color(0,0,0,0));
		
		italic = new JCheckBox("Italic");
		italic.setEnabled(true);
		italic.setBackground(new Color(0, 0, 0, 0));
		italic.setBounds(200, 144, 54, 23);
		panel.add(italic);
	}
	
	public void getComponent(TextBox txtBox){
		this.txtBox = txtBox;
		
		bgColor.setSelectedIndex(Menu.determineBgColorIndex2(txtBox.getBackground()));
		txtFont.setSelectedIndex(Menu.determineFtIndex(txtBox.getFont()));
		txtColor.setSelectedIndex(Menu.determineFgColorIndex(txtBox.getForeground()));
		txtSize.setSelectedItem(txtBox.getFont().getSize());
		boldOption.setSelected(Menu.determineBold(txtBox.getFont()));
		italic.setSelected(Menu.determineItalic(txtBox.getFont()));
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirm)){
			confirmed = true;
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
		txtBox.setBackground(Menu.determineColor(this.bgColor.getSelectedIndex()));
	}
	
	private void setTextColor(){
		txtBox.setForeground(Menu.determineColor(this.txtColor.getSelectedIndex()));
	}
	
	private void setFont(){
		Object a = txtSize.getSelectedItem();
		if(a instanceof Integer){
			txtBox.setFont(Menu.determineFont((int)a,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
		}	
		else{
			try{
				int b = Integer.parseInt((String)a);
				txtBox.setFont(Menu.determineFont(b,this.txtFont.getSelectedIndex(), this.boldOption.isSelected(),this.italic.isSelected()));
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

}
